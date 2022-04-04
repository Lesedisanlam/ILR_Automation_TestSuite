package Testbase;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Tbase {
	ExtentReports extent;
	WebDriver _driver;
	ChromeOptions chromeOptions;
	@BeforeTest
	public void reportConfig(){

		String pattern = "yyyy-MM-dd_HH_ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());

		String path =	System.getProperty("user.dir")+"\\"+date+".html";
		ExtentSparkReporter report = new ExtentSparkReporter(path);
		report.config().setReportName("ILR Policy servicing Automation Results");
		report.config().setDocumentTitle("Test Results");

		extent = new ExtentReports();
		extent.attachReporter(report);
		extent.setSystemInfo("Tester","Nape Boshielo");

	}
	@Test
	public void Browser() {

		ExtentTest test = extent.createTest("ILR Demo") ;
//checks for the latest version of the specified WebDriver binary
		chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().driverVersion("90.0.4430.72").setup();
		_driver = new ChromeDriver(chromeOptions);

		_driver.get("http://ilr-tst.safrican.co.za/web/wspd_cgi.sh/WService=wsb_ilrtst/run.w");
		_driver.manage().window().maximize();

		_driver.findElement(By.name("fcUserCode")).sendKeys("SKA008PPE");
		_driver.findElement(By.name("fcPassword")).sendKeys("Aw123456");

		_driver.findElement(By.name("btnLogin")).click();
		test.fail("wrong Login Details");




		extent.flush();
	}





}


