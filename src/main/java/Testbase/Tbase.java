package Testbase;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Tbase {
	ExtentReports extent;
@BeforeTest
public void config(){
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
	 public void policy() {

	extent.createTest("ILR Demo");
	        System.setProperty("webdriver.chrome.driver","C:\\Code\\bin\\ChromeDriver.exe");
	       WebDriver _driver = new ChromeDriver();
	       _driver.get("httpxx 2://ilr-int.safricdan.co.za/web/wspd_cgi.sh/WService=wsb_ilrint/run.w?eeeeeee");
	      _driver.manage().window().maximize();

	        _driver.findElement(By.name("fcUserCode")).sendKeys("SKA008weePPE");
	        _driver.findElement(By.name("fcPassword")).sendKeys("Aw1234dd56");

	        _driver.findElement(By.name("btnLogin")).click();
         extent.flush();
	    }





}


