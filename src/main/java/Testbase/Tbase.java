package Testbase;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import static com.sun.org.apache.xml.internal.security.keys.keyresolver.KeyResolver.iterator;

public class Tbase {

	public WebDriver _driver;
	ChromeOptions chromeOptions;



	@Test
	public void Browser() throws InterruptedException {

		//ExtentTest test = extent.createTest("ILR Demo") ;
//checks for the latest version of the specified WebDriver binary
		chromeOptions = new ChromeOptions();
		WebDriverManager.chromedriver().driverVersion("90.0.4430.72").setup();
		_driver = new ChromeDriver(chromeOptions);

		_driver.get("http://ilr-tst.safrican.co.za/web/wspd_cgi.sh/WService=wsb_ilrtst/run.w?");
		_driver.manage().window().maximize();

		_driver.findElement(By.name("fcUserCode")).sendKeys("e697642");
		Thread.sleep(2000);
		_driver.findElement(By.name("fcPassword")).sendKeys("E697642/ks");
		Thread.sleep(2000);
		_driver.findElement(By.name("btnLogin")).click();
		//test.fail("wrong Login Detail
		Thread.sleep(2000);
Assert.assertEquals("SANLAM RM - Safrican Retail - Safrican Rainbow Life - ARL",_driver.findElement(By.xpath("//*[@id=\"AppTitle\"]/table/tbody/tr/td[3]/center/table/tbody/tr/td")).getText());
//_driver.close();


	}



public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {


	TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationfile = System.getProperty("user.dir")+"\\Reports\\"+testCaseName+".png";

	FileUtils.copyFile(source,new File(destinationfile));
return destinationfile;


}





public void  excel() throws IOException {

		FileInputStream fis = new FileInputStream("");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

	int sheets =workbook.getNumberOfSheets();

		for(int i=0;i<sheets;i++){

		if (workbook.getSheetName(i).equalsIgnoreCase("test")) {
			XSSFSheet sheet = workbook.getSheetAt(i);

		Iterator<Row> rows = sheet.iterator();

		}

		}


}



}


