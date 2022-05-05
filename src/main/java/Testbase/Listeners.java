package Testbase;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.BeforeTest;
import resources.ExtentReporter;

import java.lang.annotation.Annotation;

public class Listeners extends Tbase implements ITestListener{

    ExtentTest test;
 ExtentReports  extent =ExtentReporter.getReportObject();
//threadlock makes execution to be thread safe to avoid overriding when tests run parallel

    ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    //before executing @tests
    public void onTestStart(ITestResult result) {
      //  ExtentTest test = extent.createTest("ILR Demo") ;
        //test will be created for every method
         test = extent.createTest(result.getMethod().getMethodName()) ;
      extentTest.set(test);

    }

    public void onTestSuccess(ITestResult result) {
extentTest.get().log(Status.PASS,"Test Passed");

    }

    public void onTestFailure(ITestResult result) {
       // ITestListener.super.onTestFailure(result);
        extentTest.get().fail(result.getThrowable()); //log

         WebDriver driver = null;
        //take screenshot when a specific test fail.
        String testMethodName = result.getMethod().getMethodName();
        // get access to any field in any class
        try {


            driver =   (WebDriver)result.getTestClass().getRealClass().getDeclaredField("_driver").get(result.getInstance());
        } catch (Exception e) {


        }
       // By signing = By.
        try {
            extentTest.get().addScreenCaptureFromPath( getScreenShot(testMethodName,driver),result.getMethod().getMethodName())
           ;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }


    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }


    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }


    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }


    public void onFinish(ITestContext context) {
        extent.flush();

    }
}
