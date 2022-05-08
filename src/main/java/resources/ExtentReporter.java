package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.annotations.BeforeTest;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReporter {
   static ExtentReports extent;

    public static ExtentReports getReportObject(){

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());

        String path =	System.getProperty("user.dir")+"\\"+date+".html";
        ExtentSparkReporter report = new ExtentSparkReporter(path);
        report.config().setReportName("ILR Policy servicing Automation Results");
        report.config().setDocumentTitle("Test Results");

        extent = new ExtentReports();
        extent.attachReporter(report);
        extent.setSystemInfo("Tester","Nape Boshielo");

        return extent;
    }





}
