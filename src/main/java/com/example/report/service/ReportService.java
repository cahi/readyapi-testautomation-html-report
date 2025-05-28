package com.example.report.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

@Service
public class ReportService {

    public void generateReport(String xmlPath, String outputPath) throws Exception {
        InputStream is = getClass().getClassLoader().getResourceAsStream(xmlPath);
        if (is == null) {
            throw new IllegalArgumentException("Cannot find " + xmlPath);
        }
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
        doc.getDocumentElement().normalize();

        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(outputPath);
        extent.attachReporter(spark);

        NodeList testcases = doc.getElementsByTagName("testcase");
        for (int i = 0; i < testcases.getLength(); i++) {
            Node tcNode = testcases.item(i);
            if (tcNode.getNodeType() == Node.ELEMENT_NODE) {
                Element tcElement = (Element) tcNode;
                String tcName = tcElement.getAttribute("name");
                ExtentTest test = extent.createTest(tcName);

                NodeList steps = tcElement.getElementsByTagName("step");
                for (int j = 0; j < steps.getLength(); j++) {
                    Element step = (Element) steps.item(j);
                    String stepName = step.getAttribute("name");
                    String status = step.getAttribute("status");
                    if ("PASS".equalsIgnoreCase(status)) {
                        test.pass(stepName);
                    } else if ("FAIL".equalsIgnoreCase(status)) {
                        test.fail(stepName);
                    } else {
                        test.info(stepName + " - " + status);
                    }
                }
            }
        }

        extent.flush();
    }
}
