package utils;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {
    private static ExtentReports extent;
    private static final String REPORT_DIRECTORY = "test-output/reports/";

    public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            createInstance();
        }
        return extent;
    }

    private static void createInstance() {
        // Raporu belirli bir klasörde oluştur
        File reportDir = new File(REPORT_DIRECTORY);
        if (!reportDir.exists()) {
            reportDir.mkdirs(); // Eğer dizin yoksa oluştur
        }

        // Zaman damgası ile rapor ismini oluştur
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = REPORT_DIRECTORY + "extent-report_" + timeStamp + ".html";

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
        htmlReporter.config().setDocumentTitle("Test Automation Report");
        htmlReporter.config().setReportName("Automation Test Report");

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        // Sistem bilgilerini rapora ekleyelim
        extent.setSystemInfo("OS", System.getProperty("os.name"));
        extent.setSystemInfo("Java Version", System.getProperty("java.version"));
    }
}