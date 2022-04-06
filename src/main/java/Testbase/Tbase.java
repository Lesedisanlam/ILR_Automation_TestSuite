package Testbase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
public class Tbase {


	@Test
	public static void main(String[] args) {

		System.setProperty("webdriver.chrome.driver","C:\\Code\\bin\\ChromeDriver.exe");
		WebDriver _driver = new ChromeDriver();
		_driver.get("http://ilr-int.safrican.co.za/web/wspd_cgi.sh/WService=wsb_ilrint/run.w?");
		_driver.manage().window().maximize();
		_driver.findElement(By.name("fcUserCode")).sendKeys("ilrAdmin");
		_driver.findElement(By.name("fcPassword")).sendKeys("ilrAdmin01");
		_driver.findElement(By.name("btnLogin")).click();
	}


}

