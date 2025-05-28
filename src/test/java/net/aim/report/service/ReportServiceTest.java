import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import nat.aim.report.service.ReportService;

public class ReportServiceTest {

    @TempDir
    Path tempDir;

    @Test
    void generateReport_createsHtmlFile() throws Exception {
        ReportService service = new ReportService();
        Path output = tempDir.resolve("report.html");
        service.generateReport("testResult.xml", output.toString());
        assertTrue(Files.exists(output), "Report file should exist");
    }
}
