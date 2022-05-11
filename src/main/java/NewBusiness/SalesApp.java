package src.main.java.NewBusiness;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import src.main.java.PolicyServicing.Base;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.Set;
import java.util.List;
import java.lang.System.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import java.time.format.DateTimeFormatter;


public class SalesApp extends TestBase {

    @BeforeClass
    public WebDriver SetUp() throws InterruptedException {
        salesAppSiteConnection();
        createTesResultFile();
        return _driver;

    }
    @Test
    public final void RunTest() throws InterruptedException {
        Delay(15);
        Delay(4);
        //Click on Menu
        _driver.findElement(By.xpath("/html/body/div[1]/div[1]/nav/button")).click();
        Delay(4);
        //Click on Dashbaord
        //*[@id="gatsby-focus-wrapper"]/div/section[1]/a[1]
        _driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/section[1]/a[1]")).click();

    }
    public final void PositiveTestProcess(String scenario_ID)
    {
        String upload_file = "C:/Users/G992107/Documents/GitHub/ILR_TestSuite/ILR_TestSuite/New Business/upload/download.jpg";
        Delay(10);
        String results = "", comment = "";

        //get policy holder data
        var policyHolderData = getPolicyHolderDetails(scenario_ID);
        _driver.switchTo().activeElement();
        _driver.findElement(By.XPath("//*[@id='___gatsby']"));
        Delay(20);
        IWebElement new_client = _driver.FindElement(By.ClassName("new-app-button"));
        new_client.Click();
        //  Actions action = new Actions(_driver);
        // action.MoveToElement(new_client).Perform()
        Delay(2);
        IWebElement town = _driver.FindElement(By.Name("town"));
        town.SendKeys(policyHolderData["Town"]);
        Delay(1);
        town.SendKeys(Keys.ArrowDown);
        Delay(1);
        town.SendKeys(Keys.Enter);
        Delay(4);
        IWebElement worksite = _driver.FindElement(By.Name("worksite"));
        worksite.SendKeys(policyHolderData["Worksite"]);
        Delay(1);
        worksite.SendKeys(Keys.ArrowDown);
        Delay(1);
        worksite.SendKeys(Keys.Enter);
        Delay(2);
        IWebElement employer = _driver.FindElement(By.Name("employer-name"));
        employer.SendKeys(policyHolderData["Employment"]);
        Delay(1);
        employer.SendKeys(Keys.ArrowDown);
        Delay(1);
        employer.SendKeys(Keys.Enter);
        Delay(2);
        WebElement yes = _driver.FindElement(By.XPath("/html/body/reach-portal/div/div/div/div[4]/div/div[2]/div/label[1]"));
        Delay(1);
        yes.click();
        Delay(2);
        IWebElement cont = _driver.FindElement(By.XPath(" /html/body/reach-portal/div/div/div/div[5]/button[2]"));
        cont.Click();
        Delay(4);
        IWebElement agree = _driver.FindElement(By.XPath(" /html/body/reach-portal/div/div/div/div[2]/button"));
        agree.Click();
        //Personal Details
        Delay(2);
        //firstname
        _driver.FindElement(By.XPath("//*[@id='/name']")).SendKeys(policyHolderData["First_name"]);
        Delay(2);
        //maiden name
        _driver.FindElement(By.XPath("//*[@id='/maiden-surname']")).SendKeys(policyHolderData["Maiden_Surname"]);
        Delay(2);
        //Id
        Delay(3);
        _driver.FindElement(By.XPath("//*[@id='/id-number']")).SendKeys(policyHolderData["ID_number"]);
        Delay(2);
        _driver.FindElement(By.XPath("//*[@id='/surname']")).SendKeys(policyHolderData["Surname"]);
        Delay(2);
        //Select ethicity
        IWebElement select = _driver.FindElement(By.XPath(" //*[@id='/ethnicity']"));
        SelectElement oselect = new SelectElement(select);
        oselect.SelectByValue(policyHolderData["Ethnicity"]);
        Delay(2);
        //Select Maratiel
        WebElement selectstatus = _driver.FindElement(By.XPath("//*[@id='/marital-status']"));
        SelectElement cselect = new SelectElement(selectstatus);
        cselect.SelectByValue(policyHolderData["Marital_Status"]);
        Delay(2);
        //Enter contact number
        _driver.FindElement(By.XPath("//*[@id='/contact-number']")).SendKeys(policyHolderData["CellPhone_number"]);
        Delay(2);
        //Enter email
        _driver.FindElement(By.XPath("//*[@id='/email']")).SendKeys(policyHolderData["Email"]);
        Delay(2);
        //Enter gross monthly
        _driver.FindElement(By.XPath("//*[@id='/gross-monthly-income']")).SendKeys(policyHolderData["Gross"]);
        Delay(2);




        public final void Delay(int delaySeconds) throws InterruptedException {

        Thread.sleep(delaySeconds * 1000);

    }

}
