package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager {

    private static ExtentReports extent;
    private static final Object lock = new Object();
    private ExtentTest test;

    private ExtentReportManager() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Test Report");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    public static ExtentReports getInstance() {
        if (extent == null) {
            synchronized (lock) {
                if (extent == null) {
                    new ExtentReportManager();  // initialize if not already done
                }
            }
        }
        return extent;
    }

    public ExtentTest createTest(String testName) {
        test = getInstance().createTest(testName);
        return test;
    }

    public void flush() {
        getInstance().flush();
    }
    public ExtentTest getTest() {
        return test;
    }


}
