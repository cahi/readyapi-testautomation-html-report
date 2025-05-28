package com.example.report;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.report.service.ReportService;

@SpringBootApplication
public class ReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportApplication.class, args);
    }

    @Bean
    CommandLineRunner generateReport(ReportService reportService) {
        return args -> reportService.generateReport("testResult.xml", "report.html");
    }
}
