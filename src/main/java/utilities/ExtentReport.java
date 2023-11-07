package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    public static final ExtentReports extentReport = new ExtentReports ();
    public static final ExtentReports extentReports = new ExtentReports ();

    public synchronized static ExtentReports getInstance () {
        ExtentSparkReporter reporter = new ExtentSparkReporter ("./Resources/Reports/Report.html");
        reporter.config ().setReportName ("Sample Extent Report");
        extentReports.attachReporter (reporter);
        return extentReports;
    }
}


