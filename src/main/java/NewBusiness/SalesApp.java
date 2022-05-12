package src.main.java.NewBusiness;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import src.main.java.PolicyServicing.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeSuite;
import java.util.Random;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.Set;
import java.util.ArrayList;
import org.openqa.selenium.Keys;
import java.time.LocalDateTime;


import java.time.format.DateTimeFormatter;


public class SalesApp extends TestBase {

    @BeforeClass
    public WebDriver Login() throws InterruptedException {
        salesAppSiteConnection();
        createTesResultFile();
        Delay(22);
        PositiveTestProcess();


        return _driver;


    }

    public final void RunTest() throws InterruptedException {

        Delay(4);
        //Click on Menu
        _driver.findElement(By.xpath("/html/body/div[1]/div[1]/nav/button")).click();
        Delay(4);
        //Click on Dashbaord
        _driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/section[1]/a[1]")).click();

    }

    @Test
    public final void PositiveTestProcess() throws InterruptedException {

        var upload_file = "C:\\Users\\G992107\\Documents\\GitHub\\ILR_Automation_TestSuite\\upload";
        Delay(10);
        String results = "", comment = "";

        //get policy holder data
        //String policyHolderData = getPolicyHolderDetails(scenario_ID);
        _driver.switchTo().activeElement();
        _driver.findElement(By.xpath("//*[@id='___gatsby']"));
        Delay(20);
        WebElement new_client = _driver.findElement(By.className("new-app-button"));
        new_client.click();
        //  Actions action = new Actions(_driver);
        // action.MoveToElement(new_client).Perform()
        Delay(2);
        WebElement town = _driver.findElement(By.name("town"));
        town.sendKeys("Johannesburg");
        Delay(1);
        town.sendKeys(Keys.ARROW_DOWN);
        Delay(1);
        town.sendKeys(Keys.ENTER);
        Delay(4);
        WebElement worksite = _driver.findElement(By.name("worksite"));
        worksite.sendKeys("Nike");
        Delay(1);
        worksite.sendKeys(Keys.ARROW_DOWN);
        Delay(1);
        worksite.sendKeys(Keys.ENTER);
        Delay(2);
        WebElement employer = _driver.findElement(By.name("employer-name"));
        employer.sendKeys("Nike");
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
        _driver.findElement(By.xpath("//*[@id='/name']")).sendKeys("Firstname");
        Delay(2);
        //maiden name
        _driver.findElement(By.xpath("//*[@id='/maiden-surname']")).sendKeys("MaidenSurname");
        Delay(2);
        //Id
        Delay(3);
        _driver.findElement(By.xpath("//*[@id='/id-number']")).sendKeys("7102016422088");
        Delay(2);
        _driver.findElement(By.xpath("//*[@id='/surname']")).sendKeys("Surname");
        Delay(2);
        //Select ethicity
        WebElement select = _driver.findElement(By.xpath(" //*[@id='/ethnicity']"));
        Select oselect = new Select(select);
        oselect.selectByValue("BLACKAFRICAN");
        Delay(2);
        //Select Maratiel
        WebElement selectstatus = _driver.findElement(By.xpath("//*[@id='/marital-status']"));
        Select cselect = new Select(selectstatus);
        cselect.selectByValue("Married");
        Delay(2);
        //Enter contact number
        _driver.findElement(By.xpath("//*[@id='/contact-number']")).sendKeys("0679774589");
        Delay(2);
        //Enter email
        _driver.findElement(By.xpath("//*[@id='/email']")).sendKeys("Mosa@gmail.com");
        Delay(2);
        //Enter gross monthly
        _driver.findElement(By.xpath("//*[@id='/gross-monthly-income']")).sendKeys("50000");
        Delay(2);
        //Select employent type
        if ("Permanent".equals("Yes")) {
            _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[16]/div/label[1]")).click();
        } else {
            _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[16]/div/label[2]")).click();
        }
        Delay(2);
        //Salary frequency
        switch ("Salary_frequency") {
            case "Weekly":
                _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[17]/div/label[1]")).click();
                break;
            case "Monthly":
                _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[17]/div/label[2]")).click();
                break;
            case "Other":
                _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[17]/div/label[3]")).click();
                break;
            default:
                _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[17]/div/label[2]")).click();
                break;
        }


        //click next
        Delay(2);
        try {
            _driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[1]/a")).click();

        } catch (java.lang.Exception e) {
            //Age validation
            _driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[1]/button")).click();

            String ValidationMsg = _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/section/form/div/div[8]/span/span")).getText();
            comment = "Main Life Assured ";
            comment += ValidationMsg;
            if (ValidationMsg.equals("Must be at lenast 18 years old.") || ValidationMsg.contains("Must not be older than 74 years of age.")) {
                results = "Failed";
                // (_driver,String.format("%1$s\\Validations\\", _screenShotFolder), "MainLife_Age");

                // return (results, comment);


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
        _driver.findElement(By.name("/total-salary-after-deductions")).sendKeys("50000");
        //Additional income
        Delay(1);
        _driver.findElement(By.name("/additional-income")).sendKeys("10000");
        //Existing Financial Cover
        Delay(1);
        _driver.findElement(By.name("/existing-financial-cover")).sendKeys("0");
        //School Fees
        Delay(1);
        _driver.findElement(By.name("/school-fees")).sendKeys("3000");
        //Food
        Delay(1);
        _driver.findElement(By.name("/food")).sendKeys("2900");
        //Retail accounts
        Delay(1);
        _driver.findElement(By.name("/retail-accounts")).sendKeys("3000");
        //Cellphone
        Delay(1);
        _driver.findElement(By.name("/cellphone")).sendKeys("1500");
        //Debt
        Delay(1);
        _driver.findElement(By.name("/debt")).sendKeys("2000");
        // Mortgage / Rent
        Delay(1);
        _driver.findElement(By.name("/mortage-rent")).sendKeys("12000");
        //Transport
        Delay(1);
        _driver.findElement(By.name("/transport")).sendKeys("2000");
        //Entertainment / Other
        Delay(1);
        _driver.findElement(By.name("/entertainment-other")).sendKeys("3000");
        //click next
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[1]/a[2]")).click();
        //click tickbox for agreement
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/form/section/div[2]/input[1]")).click();
        //click next
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[1]/a[2]")).click();

        String policyplayers = ("");
        ArrayList<String> keys = new ArrayList<String>();
        keys.add("PolicyHolder_Details");
        keys.add("spouse");
        keys.add("Children");
        keys.add("Parents");
        keys.add("Extended");

        var beneficiaries = policyplayers["Beneficiaries"];

        //click tickbox product
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/section/div[1]/div[3]/button/span")).Click();

        //click tickbox product
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/form/div/div[2]/label/div")).Click();


        //click next
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[1]/a")).Click();
        //click on No
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/form/section[1]/div/div[2]/div/div/label[2]")).Click();

        //click on 5%
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/form/section[2]/div/div[2]/div/div/label[1]")).Click();
        //Add Provided LAs
        var lifeAsuredCounter = 0;
        var label = 1;
        var section = 3;
        WebElement DOB;
        String date_of_birth = "", frontEndPrem = "", frontEndMin = "", frontEndMax = "";
        Tuple<String, String> validation;


        for (var key : keys) {

            for (var item : policyplayers[key]) {

                if (item.Count > 0) {
                    if (key.equals("PolicyHolder_Details")) {
                        if (item["Covered"].equals("Yes")) {

                            //add main life
                            Delay(1);
                            _driver.findElement(By.xpath(String.format("//*[@id='gatsby-focus-wrapper']/article/form/section[%1$s]/div[2]/div[1]/div/label[%2$s]", section, label))).Click();
                            //Cover Amount
                            DOB = _driver.findElement(By.xpath(String.format("/html/body/div[1]/div[1]/article/form/section[%1$s]/div[3]/div[5]/input", section)));
                            date_of_birth = DOB.GetAttribute("value");
                            SlideBar(item["Cover_Amount"], lifeAsuredCounter, "Myself");
                            Delay(2);
                            frontEndPrem = (_driver.findElement(By.xpath(String.format("/html/body/div[1]/div[1]/article/form/section[%1$s]/div[4]/div[1]/label/h2/strong[2]", section))).Text).Remove(0, 1).strip();
                            frontEndMin = (_driver.findElement(By.xpath(String.format("/html/body/div[1]/div[1]/article/form/section[%1$s]/div[4]/div[1]/div[2]/span[1]", section))).Text).Remove(0, 1).replace(" ", "");
                            frontEndMax = (_driver.findElement(By.xpath(String.format("/html/body/div[1]/div[1]/article/form/section[%1$s]/div[4]/div[1]/div[2]/span[2]", section))).Text).Remove(0, 1).replace(" ", "");
                            validation = RolePlayerValidation(_driver, item["Cover_Amount"], "ML", date_of_birth, frontEndPrem, frontEndMin, frontEndMax);
                            if (validation.Item1.equals("Failed")) {
                                return Tuple.Create("Failed", validation.Item2);
                            }
                            //ID ,cover
                            section++;
                            lifeAsuredCounter++;
                            break;
                        }
                    }
                    //click Add
                    Delay(2);
                    _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/form/button")).Click();

                    //select relationship
                    Delay(2);
                    _driver.findElement(By.xpath(String.format("//*[@id='gatsby-focus-wrapper']/article/form/section[%1$s]/div[2]/div[1]/div/label[%2$s]", section, label))).Click();
                    if (key.equals("Extended")) {
                        //Extended Relationship Type

                        WebElement RelationshipType = _driver.FindElement(By.Name(String.format("/cover-details[%1$s].relationship-extended-type", lifeAsuredCounter)));
                        RelationshipType.sendKeys(item["Extended_RelationshipType"]);
                        RelationshipType.sendKeys(Keys.ArrowDown);
                        RelationshipType.sendKeys(Keys.Enter);
                    }
                    //FirstName
                    Delay(1);
                    _driver.findElement(By.Name(String.format("/cover-details[%1$s].name", lifeAsuredCounter))).SendKeys(item["First_name"]);
                    //Surname
                    Delay(2);
                    _driver.findElement(By.Name(String.format("/cover-details[%1$s].surname", lifeAsuredCounter))).SendKeys(item["Surname"]);
                    //ID Number
                    Delay(1);
                    _driver.findElement(By.Name(String.format("/cover-details[%1$s].id-number", lifeAsuredCounter))).SendKeys(item["ID_number"]);
                    //MaxMin Age validation
                    Tuple<String, String> ageValidationResults = MaxMinAgeValidation(section);
                    if (!ageValidationResults.Item1.equals("") && !ageValidationResults.Item2.equals("")) {
                        return Tuple.Create(ageValidationResults.Item1, ageValidationResults.Item2);
                    }


                    //Cellphone
                    Delay(2);
                    _driver.findElement(By.Name(String.format("/cover-details[%1$s].contact-number", lifeAsuredCounter))).SendKeys(item["Cellphone"]);
                    DOB = _driver.findElement(By.xpath(String.format("/html/body/div[1]/div[1]/article/form/section[%1$s]/div[3]/div[5]/input", section)));
                    date_of_birth = DOB.GetAttribute("value");
                    SlideBar(item["Cover_Amount"], lifeAsuredCounter, key);
                    //
                    Delay(2);
                    frontEndPrem = (_driver.findElement(By.xpath(String.format("/html/body/div[1]/div[1]/article/form/section[%1$s]/div[4]/div[1]/label/h2/strong[2]", section))).Text).Remove(0, 1).strip();
                    frontEndMin = (_driver.findElement(By.xpath(String.format("/html/body/div[1]/div[1]/article/form/section[%1$s]/div[4]/div[1]/div[2]/span[1]", section))).Text).Remove(0, 1).replace(" ", "");
                    frontEndMax = (_driver.findElement(By.xpath(String.format("/html/body/div[1]/div[1]/article/form/section[%1$s]/div[4]/div[1]/div[2]/span[2]", section))).Text).Remove(0, 1).replace(" ", "");
                    validation = RolePlayerValidation(_driver, item["Cover_Amount"], key, date_of_birth, frontEndPrem, frontEndMin, frontEndMax);
                    if (validation.Item1.equals("Failed")) {
                        return Tuple.Create("Failed", validation.Item2);
                    }


                    section++;
                    lifeAsuredCounter++;
                } else {
                    break;
                }
            }

            label++;

        }


        Delay(1);

        //Click next
        Delay(3);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[1]/a[2]")).Click();


        var beneCounter = 0;
        //payment reciever(Beneficiary)
        for (var item : beneficiaries) {
            //click relationship
            Delay(1);
            _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/form/div/section/div[3]/div/div[1]/div/label[1]")).Click();

            //FirstName
            Delay(1);
            _driver.findElement(By.name(String.format("/funeral-beneficiaries[%1$s].name", beneCounter))).SendKeys(item["First_name"]);
            //Surname
            Delay(1);
            _driver.findElement(By.name(String.format("/funeral-beneficiaries[%1$s].surname", beneCounter))).SendKeys(item["Surname"]);

            //ID Number
            Delay(1);
            _driver.findElement(By.name(String.format("/funeral-beneficiaries[%1$s].id-number", beneCounter))).sendKeys(item["ID_number"]);

            //Cellphone
            Delay(1);
            _driver.findElement(By.name(String.format("/funeral-beneficiaries[%1$s].contact-number", beneCounter))).sendKeys(item["Cellphone"]);

            //Percentage
            WebElement sliderbar5 = _driver.findElement(By.className("slider"));
            int widthslider5 = sliderbar5.Size.Width;
            Delay(1);
            WebElement slider5 = _driver.findElement(By.className("slider"));
            Actions slideraction5 = new Actions(_driver);
            slideraction5.clickAndHold(slider5);
            slideraction5.moveByOffset(260, 0).build().perform();
            beneCounter++;

        }


        //Click next
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[1]/a[2]")).Click();


        //Word of advice
        Delay(1);
        _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/form/section/div[2]/div/textarea")).SendKeys("Test");


        //Click next
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[1]/a[2]")).Click();

        //Click No
        Delay(3);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/section/div[2]/form/div[2]/div/label[2]")).Click();

        //go to payment
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div/a[2]")).Click();

        /////////Payment Details
        String bank = policyHolderData["Bank"];

        //policy payer
        Delay(1);
        _driver.findElement(By.name("/same-as-fna")).Click();


        //bank details
        //Bank Selction

        Select oSelect1 = new Select(_driver.findElement(By.name("/bank-select")));
        oSelect1.SelectByValue(bank);

        //Account Number
        Delay(1);
        _driver.findElement(By.name("/account-number")).SendKeys(policyHolderData["Account_Number"]);


        //Account Type
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/form/section[1]/div[2]/div[4]/div/label[2]")).Click();


        /**debit - order - date / debit - order - date
         */
        Select oSelect = new Select(_driver.findElement(By.name("/debit-order-date")));
        oSelect.SelectByValue(policyHolderData["Debit_Order_Day"]);

        //salarypaydate
        Delay(1);
        _driver.findElement(By.xpath("/html/body/div[1]/div[1]/article/form/section[1]/div[2]/div[6]/input")).SendKeys(policyHolderData["Salary_Date"]);

        //click tickbox
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='/arrange-payment-gather-information-disclaimer']")).Click();

        //click yes
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/form/section[2]/section/div[1]/div/div/label[1]")).Click();

        //click yes
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/form/section[2]/section/div[2]/div/div/label[1]")).Click();


        //click next
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div/a")).click();

        //click i uderstand
        Delay(1);
        WebElement iagree = _driver.findElement(By.xpath("/html/body/reach-portal/div/div/div/button"));
        iagree.click();

        //click start
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/section/div[3]/button")).click();

        //debicheck loading delay
        //Impletent implicit wait
        WebDriverWait wait = new WebDriverWait(_driver, TimeSpan.FromSeconds(160));
        try {

            wait.Until(ExpectedConditions.ElementExists(By.XPath("//*[@id='gatsby-focus-wrapper']/div[2]/div/a[2]")));
        } catch
        {
            var tries = 2;
            for (int i = 0; i < tries; i++) {
                WebDriverWait wt = new WebDriverWait(_driver, TimeSpan.FromSeconds(160));
                wt.Until(ExpectedConditions.ElementExists(By.XPath("/html/body/div[1]/div[1]/article/section/div[3]/button")));
                _driver.findElement(By.XPath("/html/body/div[1]/div[1]/article/section/div[3]/button")).Click();
            }


        }


        var Errormessage = _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/section/div[2]/div[2]/div[2]/p")).getText();

        if (Errormessage == "DebiCheck accepted by customer") {


            Delay(1);
            //Click next
            _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div/a[2]")).click();


        } else {

            comment = "Debicheck Failed";
            results = "Failed";

            return Tuple.Create(results, comment);


        }


        //Physical Address

        //Building
        Delay(3);
        _driver.findElement(By.name("/physical-address-building")).sendKeys("Building");
        //Street
        Delay(1);
        _driver.findElement(By.name("/physical-address-street")).sendKeys("Street");

        //Town
        Delay(1);
        _driver.findElement(By.name("/physical-address-town")).sendKeys("City");

        //Suburb
        Delay(1);
        _driver.findElement(By.name("/physical-address-suburb")).sendKeys("Suburb");

        //CodeField
        _driver.findElement(By.name("/physical-address-code")).sendKeys("1876");


        ///click tickbox same-as-physical
        Delay(1);
        _driver.findElement(By.name("/same-as-physical")).click();

        ///click tickbox
        Delay(1);
        _driver.findElement(By.name("/policy-holder-signature-datetime")).click();

        ///click tickbox
        Delay(1);
        _driver.findElement(By.name("/premium-payer-signature-datetime")).click();

        //reference no
        Delay(1);
        _driver.findElement(By.name("/call-reference-number")).sendKeys("0987565786");

        //click next
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[1]/a[2]")).click();


        ///click tickbox
        Delay(1);
        _driver.findElement(By.name("/popia-consent-datetime")).click();


        //click next
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div/a[2]")).click();


        Delay(2);
        //upload1
        _driver.findElement(By.id("/identification")).sendKeys(upload_file);

        //upload2
        Delay(2);
        _driver.findElement(By.id("/q-link")).sendKeys(upload_file);
        //upload3
        Delay(1);
        _driver.findElement(By.id("/proof-of-income")).sendKeys(upload_file);


        //click next
        Delay(8);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div/a[2]")).click();
        var cardNum = "";
        for (int i = 0; i < 8; i++) {
            Random rnd = new Random();
            cardNum = cardNum + rnd.nextInt(10);
        }

        //Card number
        Delay(4);
        _driver.findElement(By.id("/card-number")).sendKeys(cardNum);

        //next
        Delay(2);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div[1]/a[2]")).click();


        //next
        Delay(4);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div[2]/div/a")).click();


        //sync
        Delay(1);
        _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/nav/div/div/button")).click();

        var appStatus = _driver.findElement(By.cssSelector("#gatsby-focus-wrapper > article > div.card.tab-container > div.tab-body > section > section:nth-child(1) > div > div.final-block > span")).Text;


        for (int i = 0; i < 5; i++) {

            _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/nav/div/div/button")).click();
            Delay(10);

            appStatus = _driver.findElement(By.cssSelector("#gatsby-focus-wrapper > article > div.card.tab-container > div.tab-body > section > section:nth-child(1) > div > div.final-block > span")).Text;
            if (appStatus.equals("Uploaded")) {
                break;
            }
        }

        if (appStatus.equals("Uploaded")) {
            results = "Passed";
        } else {
            results = "Failed";
            comment = "Application was not succesfull";
        }


        return Tuple.Create(results, comment);
    }


    private Tuple<String, String> MaxMinAgeValidation(int section) {

        try {
            String results = "";
            String validationMsg = _driver.FindElement(By.XPath(String.format("//*[@id='gatsby-focus-wrapper']/article/form/section[%1$s]/div[4]/div[1]/label", section))).Text;

            switch (validationMsg) {
                case "Cover is only available for parents from 26 to 85 years of age":
                    TakeScreenshot(_driver, String.format("%1$s\\Failed_Scenarios\\", _screenShotFolder), "ParentAgeValidation");
                    results = "Failed";
                    return Tuple.Create(results, validationMsg);

                case "Cover is only available for spouses from 18 to 64 years of age":
                    TakeScreenshot(_driver, String.format("%1$s\\Failed_Scenarios\\", _screenShotFolder), "SpouseAgeValidation");
                    results = "Failed";
                    return Tuple.Create(results, validationMsg);


                case "Cover is only available for persons up to 85 years of age":
                    TakeScreenshot(_driver, String.format("%1$s\\Failed_Scenarios\\", _screenShotFolder), "ExtendedAgeValidation");
                    results = "Failed";
                    return Tuple.Create(results, validationMsg);
                case "Cover is only available for children up to 25 years of age":
                    TakeScreenshot(_driver, String.format("%1$s\\Failed_Scenarios\\", _screenShotFolder), "ChildAgeValidation");
                    results = "Failed";
                    return Tuple.Create(results, validationMsg);
            }
        } catch (java.lang.Exception e) {

            return Tuple.Create("", "");

        }

        return Tuple.Create("", "");

    }


    public void SlideBar(String coverAmount, int counts, String role) {


        if (role == "Myself") {

            var V_Position = "";
            switch (coverAmount) {

                case "5000":
                    V_Position = "-500";
                    break;
                case "7500":
                    V_Position = "-400";
                    break;
                case "10000":
                    V_Position = "-300";
                    break;
                case "15000":
                    V_Position = "-200";
                    break;
                case "20000":
                    V_Position = "50";
                    break;

                case "30000":
                    V_Position = "200";
                    break;
                case "40000":
                    V_Position = "300";
                    break;
                case "50000":
                    V_Position = "400";
                    break;
                case "60000":
                    V_Position = "500";
                    break;

            }
            WebElement sliderbar = _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/form/section[3]/div[4]/div[1]"));

            int widthslider = sliderbar.Size.Width;

            Delay(1);

            WebElement slider = _driver.findElement(By.xpath($"//*[@id='/cover-details[{counts}].cover-amount']"));

            Actions slideraction = new Actions(_driver);
            slideraction.clickAndHold(slider);

            Delay(1);

            //f = Mathf.Round(f * 100.0f) * 0.01f;
            slideraction.moveToElement(Convert.ToInt32(V_Position), 0).Build().Perform();

        }


        if (role == "Children" || role == "spouse") {


            var V1_Position = "";
            switch (coverAmount) {

                case "5000":
                    V1_Position = "-500";
                    break;
                case "7500":
                    V1_Position = "-400";
                    break;
                case "10000":
                    V1_Position = "-300";
                    break;
                case "15000":
                    V1_Position = "-200";
                    break;
                case "20000":
                    V1_Position = "50";
                    break;

                case "30000":
                    V1_Position = "200";
                    break;
                case "40000":
                    V1_Position = "300";
                    break;
                case "50000":
                    V1_Position = "400";
                    break;
                case "60000":
                    V1_Position = "500";
                    break;
            }
            WebElement sliderbar = _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/form/section[3]/div[4]/div[1]"));

            int widthslider = sliderbar.Size.Width;

            Delay(1);
            WebElement slider = _driver.findElement(By.xpath($"//*[@id='/cover-details[{counts}].cover-amount']"));
            Actions slideraction = new Actions(_driver);
            slideraction.clickAndHold(slider);
            Delay(1);
            //f = Mathf.Round(f * 100.0f) * 0.01f;
            slideraction.moveByOffset(Convert.ToInt32(V1_Position), 0).Build().Perform();


        }
        if (role == "Parents") {

            var V2_Position = "";
            switch (coverAmount) {

                case "5000":
                    V2_Position = "-500";
                    break;
                case "7500":
                    V2_Position = "-400";
                    break;
                case "10000":
                    V2_Position = "-300";
                    break;
                case "15000":
                    V2_Position = "-200";
                    break;
                case "20000":
                    V2_Position = "50";
                    break;

            }

            WebElement sliderbar = _driver.findElement(By.className("slider"));
            int widthslider = sliderbar.Size.Width;
            Delay(1);
            WebElement slider = _driver.findElement(By.xpath($"//*[@id='/cover-details[{counts}].cover-amount']"));
            Actions slideraction = new Actions(_driver);
            slideraction.clickAndHold(slider);
            slideraction.moveByOffset(Convert.ToInt32(V2_Position), 0).Build().Perform();


            if (role == "Extended") {

                var V3_Position = "";
                switch (coverAmount) {

                    case "5000":
                        V3_Position = "-500";
                        break;
                    case "7500":
                        V3_Position = "-400";
                        break;
                    case "10000":
                        V3_Position = "-300";
                        break;
                    case "15000":
                        V3_Position = "-200";
                        break;
                    case "20000":
                        V3_Position = "50";
                        break;
                    case "30000":
                        V3_Position = "200";
                        break;
                }
                WebElement sliderbar2 = _driver.findElement(By.className("slider"));
                int widthslider2 = sliderbar.Size.Width;
                Delay(1);
                WebElement slider2 = _driver.findElement(By.xpath($"//*[@id='/cover-details[{counts}].cover-amount']"));
                Actions slideraction2 = new Actions(_driver);
                slideraction.clickAndHold(slider2);
                slideraction.moveByOffset(Convert.ToInt32(V3_Position), 0).Build().Perform();


            }


        }


    }

    public Tuple<string, string> RolePlayerValidation(IWebDriver _driver, string coverAmount, string roleplayer, string dob, string expectedPrem, string frondEndMin, string frondEndMax) {

        //calulate age
        String premValidation = "", coverAmountsValidation = "", comment = "", age;
        var birthYear = dob.Split("-")[0];
        var birthMonth = dob.Split("-")[1];
        var birthDay = dob.Split("-")[2];

        age = (DateTime.Now.Year - Convert.ToInt32(birthYear)).ToString();

        if (Convert.ToInt32(birthMonth) > DateTime.Now.Month || (Convert.ToInt32(birthMonth) == DateTime.Now.Month && Convert.ToInt32(birthDay) > DateTime.Now.Day)) {
            age = (Convert.ToInt32(age) - 1).ToString();
        }


        Delay(2);
        var premiumFromRate = getPremuimFromRateTable(age, "ML", coverAmount, "Serenity_Funeral_Core");


        //Validate Cover Limits

        //Check if age falls between ages from spreadsheet
        if (Convert.ToInt32(age) >= Convert.ToInt32(minAge) && Convert.ToInt32(age) <= Convert.ToInt32(maxAge)) {
            //check the amount is between
            if (minCover == frondEndMin && maxCover == frondEndMax) {
                coverAmountsValidation = "Passed";
            } else {
                coverAmountsValidation = "Failed";
                comment += $
                "Scenario Failed - max and min cover amounts validation erorr for {roleplayer} age {age} ";
            }

        }


        if (premiumFromRate != Convert.ToDecimal(expectedPrem)) {
            premValidation = "Failed";
            comment += $
            "Scenario Failed premium validation for {roleplayer}. Premuim on frontend does not match one in rate table";

        } else {
            premValidation = "Passed";
        }

        if (premValidation == "Passed" && coverAmountsValidation == "Passed") {
            return Tuple.Create("Passed", "");
        } else {

            TakeScreenshot(_driver, $"C:/Users/G992107/Documents/GitHub/ILR_TestSuite/ILR_TestSuite/New Business/{DateTime.Now.Year + DateTime.Now.Month + DateTime.Now.Day}​/validations/", $"{roleplayer}_premiumValidation");
            return Tuple.Create("Failed", comment);


        }
    }

    public final void Delay(int delaySeconds) throws InterruptedException {

        Thread.sleep(delaySeconds * 1000);

    }


     [TearDown]
    public void closeBrowser()
    {
        base.DisconnectBrowser();
    }


}




            [TearDown]
    public void closeBrowser()
    {
        base.DisconnectBrowser();
    }


}
