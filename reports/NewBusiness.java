package reports;
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
import org.openqa.selenium.Keys;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import java.time.format.DateTimeFormatter;

import static java.lang.System.in;

public class NewBusiness extends NBTestbase {

    @BeforeClass
    public WebDriver login() throws InterruptedException {
        super.siteConnection();
        return _driver;
    }

    @Test
    public final Tuple<String, String> PositiveTestProcess(String scenario_ID) throws InterruptedException {
        var upload_file = "C:/Users/G992127/Documents/GitHub/ILR_TestSuite/ILR_TestSuite/New Business/upload/download.jpg";
        Delay(10);
        String results = "", comment = "";

        //get policy holder data
        String policyHolderData = getPolicyHolderDetails(scenario_ID);
        _driver.switchTo().activeElement();
        _driver.findElement(By.xpath("//*[@id='___gatsby']"));
        Delay(20);
        WebElement new_client = _driver.findElement(By.className("new-app-button"));
        new_client.click();
        //  Actions action = new Actions(_driver);
        // action.MoveToElement(new_client).Perform()
        Delay(2);
        WebElement town = _driver.findElement(By.name("town"));
        town.sendKeys(policyHolderData["Town"]);
        Delay(1);
        town.sendKeys(Keys.ARROW_DOWN);
        Delay(1);
        town.sendKeys(Keys.ENTER);
        Delay(4);
        WebElement worksite = _driver.findElement(By.name("worksite"));
        worksite.sendKeys(policyHolderData["Worksite"]);
        Delay(1);
        worksite.sendKeys(Keys.ARROW_DOWN);
        Delay(1);
        worksite.sendKeys(Keys.ENTER);
        Delay(2);
        WebElement employer = _driver.findElement(By.name("employer-name"));
        employer.sendKeys(policyHolderData["Employment"]);
        Delay(1);
        employer.sendKeys(Keys.ARROW_DOWN);
        Delay(1);
        employer.sendKeys(Keys.ENTER);
        Delay(2);
        WebElement yes = _driver.findElement(By.xpath("/html/body/reach-portal/div/div/div/div[4]/div/div[2]/div/label[1]"));
        Delay(1);
        yes.click();
        Delay(2);
        WebElement cont = _driver.findElement(By.xpath(" /html/body/reach-portal/div/div/div/div[5]/button[2]"));
        cont.click();
        Delay(4);
        WebElement agree = _driver.findElement(By.xpath(" /html/body/reach-portal/div/div/div/div[2]/button"));
        agree.click();
        //Personal Details
        Delay(2);
        //firstname
        _driver.findElement(By.xpath("//*[@id='/name']")).sendKeys(policyHolderData["First_name"]);
        Delay(2);
        //maiden name
        _driver.findElement(By.xpath("//*[@id='/maiden-surname']")).sendKeys(policyHolderData["Maiden_Surname"]);
        Delay(2);
        //Id
        Delay(3);
        _driver.findElement(By.xpath("//*[@id='/id-number']")).sendKeys(policyHolderData["ID_number"]);
        Delay(2);
        _driver.findElement(By.xpath("//*[@id='/surname']")).sendKeys(policyHolderData["Surname"]);
        Delay(2);
        //Select ethicity
        WebElement select = _driver.findElement(By.xpath(" //*[@id='/ethnicity']"));
        Select oselect = new Select(select);
        oselect.selectByValue(policyHolderData["Ethnicity"]);
        Delay(2);
        //Select Maratiel
        WebElement selectstatus = _driver.findElement(By.xpath("//*[@id='/marital-status']"));
        Select cselect = new Select(selectstatus);
        cselect.selectByValue(policyHolderData["Marital_Status"]);
        Delay(2);
        //Enter contact number
        _driver.findElement(By.xpath("//*[@id='/contact-number']")).sendKeys(policyHolderData["CellPhone_number"]);
        Delay(2);
        //Enter email
        _driver.findElement(By.xpath("//*[@id='/email']")).sendKeys(policyHolderData["Email"]);
        Delay(2);
        //Enter gross monthly
        _driver.findElement(By.xpath("//*[@id='/gross-monthly-income']")).sendKeys(policyHolderData["Gross"]);
        Delay(2);
        //Select employent type
        if (policyHolderData["Permanent"].equals("Yes")) {
            _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[16]/div/label[1]")).Click();
        } else {
            _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[16]/div/label[2]")).Click();
        }
        Delay(2);
        //Salary frequency
        switch (policyHolderData["Salary_frequency"]) {
            case "Weekly":
                _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[17]/div/label[1]")).Click();
                break;
            case "Monthly":
                _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[17]/div/label[2]")).Click();
                break;
            case "Other":
                _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[17]/div/label[3]")).Click();
                break;
            default:
                _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[17]/div/label[2]")).Click();
                break;
        }


        //click next
        Delay(2);
        try {
            _driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[1]/a")).click();

        } catch (java.lang.Exception e) {
            //Age validarion
            _driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[1]/button")).click();

            String ValidationMsg = _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[8]/span/span")).getText();
            comment = "Main Life Assured ";
            comment += ValidationMsg;
            if (ValidationMsg.contains("Must be at least 18 years old.") || ValidationMsg.contains("Must not be older than 74 years of age.")) {
                results = "Failed";
                TakeScreenshot(_driver, String.format("%1$s\\Validations\\", _screenShotFolder), "MainLife_Age");

                return Tuple.Create(results, comment);

            }


        }

        //occupation
        Delay(3);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/form/section/div/div[1]/label")).click();
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[3]/div[1]/a[2]")).click();
        /**dependants
         */
        Delay(4);
        for (int i = 1; i < 5; i++) {
            _driver.findElement(By.xpath(String.format("//*[@id='gatsby-focus-wrapper']/article/section/form/div[1]/section[%1$s]/label", String.valueOf(i)))).click();
        }
        //click next
        Delay(3);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[1]/a[2]")).click();
        Delay(3);
        for (int i = 1; i < 5; i++) {
            _driver.findElement(By.xpath(String.format("//*[@id='gatsby-focus-wrapper']/article/section/form/div[1]/section[%1$s]/label/section/div[1]", String.valueOf(i)))).click();
        }
        //click next
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[1]/a[2]")).click();
        //sclick on non applicable
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/div[1]/div/button")).click();
        //Net Salary After Deductions
        Delay(1);
        _driver.findElement(By.name("/total-salary-after-deductions")).sendKeys(policyHolderData["Net_Salary"]);
        //Additional income
        Delay(1);
        _driver.findElement(By.name("/additional-income")).sendKeys(policyHolderData["Additional_Income"]);
        //Existing Financial Cover
        Delay(1);
        _driver.findElement(By.name("/existing-financial-cover")).sendKeys(policyHolderData["Existing_FinancialCover"]);
        //School Fees
        Delay(1);
        _driver.findElement(By.name("/school-fees")).sendKeys(policyHolderData["School_Fees"]);
        //Food
        Delay(1);
        _driver.findElement(By.name("/food")).sendKeys(policyHolderData["Food"]);
        //Retail accounts
        Delay(1);
        _driver.findElement(By.name("/retail-accounts")).sendKeys(policyHolderData["Retail_accounts"]);
        //Cellphone
        Delay(1);
        _driver.findElement(By.name("/cellphone")).sendKeys(policyHolderData["Cellphone"]);
        //Debt
        Delay(1);
        _driver.findElement(By.name("/debt")).sendKeys(policyHolderData["Debt"]);
        // Mortgage / Rent
        Delay(1);
        _driver.findElement(By.name("/mortage-rent")).sendKeys(policyHolderData["Mortgage_Rent"]);
        //Transport
        Delay(1);
        _driver.findElement(By.name("/transport")).sendKeys(policyHolderData["Transport"]);
        //Entertainment / Other
        Delay(1);
        _driver.findElement(By.name("/entertainment-other")).sendKeys(policyHolderData["Entertainment_Other"]);
        //click next
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[1]/a[2]")).click();
        //click tickbox for agreement
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/form/section/div[2]/input[1]")).click();
        //click next
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[1]/a[2]")).click();

    }

}

    public final void Delay(int delaySeconds) throws InterruptedException {

        Thread.sleep(delaySeconds * 1000);

    }

}




