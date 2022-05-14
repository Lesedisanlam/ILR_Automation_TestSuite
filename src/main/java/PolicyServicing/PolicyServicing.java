package src.main.java.PolicyServicing;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.SkipException;
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

import static java.lang.System.in;

public class PolicyServicing extends Base {

    @BeforeClass
    public WebDriver login() throws InterruptedException {
        siteConnection();
        getExcelMethods();
        createTesResultFile();
        return _driver;

    }


    @Test
    public void addBeneficiary() throws InterruptedException {
        if(!excelMethods.contains("addBeneficiary")){
            throw new SkipException("Skipping this exception");
        }
        System.out.println("Executed Successfully");
        String PolicyNo = getPolicyNoFromExcel("Policy-Servicing", "addBeneficiary");
        Dictionary testData = getDataFromSheet("AddBeneficiary");
        clickOnMainMenu();
        Delay(2);
        try {
            policySearch(PolicyNo);
            String results = "Failed";
            String comments = "";

            Delay(2);

            String commDate = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[6]/td[2]")).getText();
            _driver.findElement(By.name("btnAddRolePlayer")).click();
            Delay(3);
            //Select role
            Select oSelect = new Select(_driver.findElement(By.name("frmRoleObj")));

            oSelect.selectByValue("41667.19");
            Delay(2);
            //Effective date
            _driver.findElement(By.name("frmEffectiveFromDate")).clear();
            Delay(2);
            _driver.findElement(By.name("frmEffectiveFromDate")).sendKeys(commDate);

            _driver.findElement(By.name("btncbmre7")).click();
            Delay(3);

            _driver.findElement(By.name("frmIDNumber")).sendKeys(testData.get("ID_NO").toString());
            //8604225772087
            Delay(3);
            _driver.findElement(By.name("btncbmre13")).click();
            Delay(10);

            Select oSelect1 = new Select(_driver.findElement(By.name("frmRelationshipCodeObj")));

            oSelect1.selectByValue("905324144.488");
            Delay(3);
            _driver.findElement(By.name("btncbmre16")).click();
            Delay(3);
            _driver.findElement(By.id("t0_749")).click();
            Delay(3);
            clickOnMainMenu();
            Delay(3);

            _driver.findElement(By.name("2000175333.8")).click();
            Delay(3);
            int row = 2;
            int maxRows = 23;
            for (int i = row; i < maxRows; i++) {

                String xpath = "//*[@id='CntContentsDiv11']/table/tbody/tr[" + i + "]/td[1]";
                try {
                    String role = _driver.findElement(By.xpath(xpath)).getText();
                    if (role.equals("Beneficiary")) {
                        results = "Passed";
                        break;
                    }
                    else {
                        comments = "Beneficiary was not added";
                    }
                } catch (RuntimeException e) {
                    break;
                }


            }


            Delay(3);
            writeResults("Policy-Servicing", "addBeneficiary", results,"Method Failed");

        }catch (Exception ex)
        {
            writeResults("Policy-Servicing","addBeneficiary","Failed",ex.toString());
        }
    }

    @Test
    public void ReInstate() throws InterruptedException {
        if(!excelMethods.contains("ReInstate"))
            throw new SkipException("Skipping reinstate");
        String results;
        try {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing", "ReInstate");
            ;
            clickOnMainMenu();
            Delay(2);
            policySearch(PolicyNo);
            results = "";

            Delay(4);

            Delay(2);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();

            //Contract Status validation
            Delay(2);
            String Cancelled = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[2]/td[2]/u/font")).getText();

            WebElement policyOptionElement3 = _driver.findElement(By.xpath("//*[@id='m0i0o1']"));

            //Creating object of an Actions class
            Actions action2 = new Actions(_driver);


            //Performing the mouse hover action on the target element.
            action2.moveToElement(policyOptionElement3).perform();
            Delay(2);


            //Click on Reinstate
            _driver.findElement(By.xpath("//*[@id='m0t0']/tbody/tr[10]/td/div/div[3]/a/img")).click();
            Delay(2);


            Select selecCom = new Select(_driver.findElement(By.name("frmReason")));
            selecCom.selectByValue("ReinstateReason2");
            Delay(2);


            //Click submit
            _driver.findElement(By.name("btnctcrereinstatecsu5")).click();
            Delay(4);

            //Click submit
            _driver.findElement(By.name("btnctcrereinstatecsu2")).click();
            Delay(5);


            //Contract Status validation

            String StatusInForce = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[2]/td[2]/u/font")).getText();


            assert (StatusInForce.equals("In-Force"));

            Delay(3);

            if (StatusInForce.equals("In-Force")) {
                results = "Passed";
            } else {
                results = "Failed";
            }

            writeResults("Policy-Servicing", "ReInstate", results, "");
        } catch (Exception e) {

            results = "Failed";
            writeResults("Policy-Servicing", "ReInstate", results, e.toString());
        }
    }
    @Test
    public void CancelPolicy() throws InterruptedException {
        if(!excelMethods.contains("CancelPolicy"))
            throw new SkipException("Skipping reinstate");
        String results;
        try {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing", "CancelPolicy");
            clickOnMainMenu();
            Delay(2);
            policySearch(PolicyNo);
            results = "";
            Delay(2);
            String commDate = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[6]/td[2]")).getText();
            Delay(3);
            //Hover on policy options
            WebElement policyOptionElement = _driver.findElement(By.xpath("//*[@id='m0i0o1']"));
            //Creating object of an Actions class
            Actions action = new Actions(_driver);
            //Performing the mouse hover action on the target element.
            action.moveToElement(policyOptionElement).perform();
            Delay(5);
            //Click on Cancel
            _driver.findElement(By.xpath("//table[@id='m0t0']/tbody/tr/td/div/div[3]/a/img")).click();
            Delay(5);
            //Set Cancellation data
            _driver.findElement(By.name("frmTerminationDate")).clear();
            Delay(1);
            _driver.findElement(By.name("frmTerminationDate")).sendKeys(commDate);
            Select selecCom = new Select(_driver.findElement(By.name("frmCancelReason")));
            selecCom.selectByValue("Cancelled by external service");
            Delay(2);
            //cancel
            _driver.findElement(By.name("btnSubmit")).click();
            Delay(2);
            // Switching to Alert
            Alert alert = _driver.switchTo().alert();
            // '.Accept()' is used to accept the alert '(click on the Ok button)'
            alert.accept();
            Delay(7);
            String newStatus = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[2]/td[2]/u/font")).getText();
            if (newStatus.equals("Cancelled") || newStatus.equals("Not Taken Up")) {
                results = "Passed";
            } else {
                results = "Failed";
            }
            writeResults("Policy-Servicing", "CancelPolicy", results, "");
        } catch (Exception e) {

            results = "Failed";
            writeResults("Policy-Servicing", "CancelPolicy", results, e.toString());
        }
    }
    @Test
    public void ChangeCollectionMethod() throws InterruptedException {
        if (excelMethods.contains("ChangeCollectionMethod")){
            throw new SkipException("Skipping reinstate");
        }
        String results="";
        try {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","ChangeCollectionMethod");
            Dictionary testData = getDataFromSheet("CollectionM");
            clickOnMainMenu();
            Delay(2);
            policySearch(PolicyNo);
            String employee_number1 = "";
            String date = String.format("%1$s", LocalDateTime.now());

            Delay(4);



            //click on policy payer
            _driver.findElement(By.name("fcRoleEntityLink3")).click();

            WebElement policyOptionElement = _driver.findElement(By.xpath("//*[@id='m0i0o1']"));
            //Creating object of an Actions class
            Actions action = new Actions(_driver);
            //Performing the mouse hover action on the target element.
            action.moveToElement(policyOptionElement).perform();

            //Click on options
            Delay(1);
            _driver.findElement(By.xpath("//*[@id='m0t0']/tbody/tr[3]/td/div/div[3]/a/img")).click();

            Delay(4);
            Select oSelect7 = new Select(_driver.findElement(By.name("fcCollectionMethod")));

            oSelect7.selectByValue("108978.19");
            Delay(5);


            //Click on EMPLOYEE NUMBER
            _driver.findElement(By.name("fcEmployeeNumber")).click();
            Delay(5);

            //Click on EMPLOYEE NUMBER
            _driver.findElement(By.name("fcEmployeeNumber")).sendKeys(testData.get("employee_number1").toString());
            Delay(5);


            //Search employee
            _driver.findElement(By.name("fcEmployerButton")).click();

            Delay(6);
            String mainwindow = _driver.getWindowHandle();
            Set<String> s2 = _driver.getWindowHandles();
            Iterator<String> i1 = s2.iterator();

            while (i1.hasNext()) {
                String ChildWindow = i1.next();
                if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
                    _driver.switchTo().window(ChildWindow);
                    _driver.findElement(By.xpath("//*[@id='lkpResultsTable']/tbody/tr[5]/td[5]")).click();

                }
            }
            //  Switch back to the main window which is the parent window.
            _driver.switchTo().window(mainwindow);
            Delay(5);


            //Click on submit
            _driver.findElement(By.id("GBLbl-1")).click();
            Delay(5);

            String expectedcollectionM = _driver.findElement(By.xpath("//*[@id='frmCbmre']/tbody/tr[8]/td[4]")).getText();


            Delay(3);

            if (expectedcollectionM.equals("Stop Order")) {
                results = "Passed";
            } else {
                results = "Failed";
            }

            writeResults("Policy-Servicing","ChangeCollectionMethod",results,"");

        } catch (Exception e) {

            results = "Failed";
            writeResults("Policy-Servicing", "ChangeCollectionMethod", results, e.toString());
        }
    }

    @Test
    public void ChangeCollectionNegative() throws InterruptedException {
        if (excelMethods.contains("ChangeCollectionMethodNegative")){
            throw new SkipException("Skipping ChanegeCollectionNegative");
        }
        String results="";
        try {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","ChangeCollectionNegative");
            Dictionary testData = getDataFromSheet("CollectionM");

            clickOnMainMenu();
            Delay(2);
            policySearch(PolicyNo);
            String employee_number2 = "";
            String date = String.format("%1$s", LocalDateTime.now());

            Delay(4);

            //SetproductName("ChangeCollectionNegative");

            Delay(3);



            //click on policy payer
            _driver.findElement(By.name("fcRoleEntityLink3")).click();



            WebElement policyOptionElement = _driver.findElement(By.xpath("//*[@id='m0i0o1']"));
            //Creating object of an Actions class
            Actions action = new Actions(_driver);
            //Performing the mouse hover action on the target element.
            action.moveToElement(policyOptionElement).perform();

            //Click on options
            Delay(1);
            _driver.findElement(By.xpath("//*[@id='m0t0']/tbody/tr[3]/td/div/div[3]/a/img")).click();


            Select oSelect8 = new Select(_driver.findElement(By.name("fcCollectionMethod")));

            oSelect8.selectByValue("108978.19");
            Delay(5);
            //ExcelData

            //Click on EMPLOYEE NUMBER
            _driver.findElement(By.name("fcEmployeeNumber")).click();
            Delay(5);

            //Click on EMPLOYEE NUMBER
            _driver.findElement(By.name("fcEmployeeNumber")).sendKeys(testData.get("employee_number2").toString());
            Delay(5);
            //Search employee
            _driver.findElement(By.name("fcEmployerButton")).click();

            Delay(6);
            String mainwindow = _driver.getWindowHandle();
            Set<String> s2 = _driver.getWindowHandles();
            Iterator<String> i1 = s2.iterator();

            while (i1.hasNext()) {
                String ChildWindow = i1.next();
                if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
                    _driver.switchTo().window(ChildWindow);
                    _driver.findElement(By.xpath("//*[@id='lkpResultsTable']/tbody/tr[19]")).click();

                }
            }
            //  Switch back to the main window which is the parent window.
            _driver.switchTo().window(mainwindow);
            Delay(5);




            //Click on submit
            _driver.findElement(By.id("GBLbl-1")).click();
            Delay(5);

            String expectedcollectionM = _driver.findElement(By.xpath("//*[@id='frmCbmre']/tbody/tr[8]/td[4]")).getText();


            TakeScreenshot(_driver, String.format("%1$s\\NegativeExpected\\"), "collectionMethodStopOrder");

            Delay(3);

            if (expectedcollectionM.equals("Debi-Check"))
            {
                results = "Passed";
            }
            else
            {
                results = "Failed";
            }


            writeResults("Policy-Servicing","ChangeCollectionNegative",results,"");
        } catch (Exception e) {

            results = "Failed";
            writeResults("Policy-Servicing", "ChangeCollectionNegative" ,results, e.toString());
        }
    }

    @Test
    private void PostDatedDowngrade() throws InterruptedException {
        if (excelMethods.contains("PostDatedDowngrade")){
            throw new SkipException("Skipping ChanegeCollectionNegative");
        }
        String results="";
        try {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","PostDatedDowngrade");
            Dictionary testData = getDataFromSheet("");
            Delay(2);
            clickOnMainMenu();
            policySearch(PolicyNo);
            String currentSumAssured = "";
            Delay(2);

            //SetproductName("PostDatedDowngrade")
            clickOnMainMenu();



            Delay(3);

            String contractPrem = _driver.findElement(By.xpath("//*[@id='CntContentsDiv9']/table/tbody/tr[2]/td[2]")).getText();

            //Click on user  contract summary
            _driver.findElement(By.xpath("//*[@id='t0_761']/table/tbody/tr/td[1]/a/img[2]")).click();



            //Click on user  component
            _driver.findElement(By.name("Alf-ICF8_00000214")).click();


            Delay(4);

            //  click on user  component
            _driver.findElement(By.name("fccComponentDescription1")).click();

            Delay(4);
            //Get The current Sum Assured for the life assured
            currentSumAssured = _driver.findElement(By.xpath("//*[@id='frmCbmcc']/tbody/tr[8]/td[4]")).getText();


            Delay(2);
            WebElement policyOptionElement = _driver.findElement(By.xpath("//*[@id='m0i0o1']"));


            //Creating object of an Actions class
            Actions action = new Actions(_driver);

            //Performing the mouse hover action on the target element.
            action.moveToElement(policyOptionElement).perform();

            //Click on options
            _driver.findElement(By.xpath("//*[@id='m0t0']/tbody/tr[1]/td/div/div[3]/a/img")).click();



            Delay(3);
            int year = LocalDateTime.now().getYear();
            String month = (String.valueOf((LocalDateTime.now().getMonthValue())));

            if (month.equals("13"))
            {
                month = "01";

            }
            else if (Integer.parseInt(month) < 10)
            {
                month = "0" + month;
            }


            String day = "01";
            String dt = "" + year + month + day;
            _driver.findElement(By.name("frmCCStartDate")).clear();
            Delay(3);
            _driver.findElement(By.name("frmCCStartDate")).sendKeys(dt);
            String newSumAssured = "";


            if (Integer.parseInt(currentSumAssured) > 10000 || Integer.parseInt(currentSumAssured) == 10000)
            {
                newSumAssured = Integer.toString((Integer.parseInt(currentSumAssured) - 10000));

            }
            else
            {
                newSumAssured = String.valueOf(5000);
            }

            Select oSelect = new Select(_driver.findElement(By.name("frmSPAmount")));

            oSelect.selectByValue(newSumAssured);



            //Click on next
            _driver.findElement(By.name("btncbmcc13")).click();
            Delay(2);


            //Click on next
            _driver.findElement(By.name("btncbmcc17")).click();
            Delay(2);

            Delay(3);



            String expectedPrem = _driver.findElement(By.xpath("//*[@id='CntContentsDiv9']/table/tbody/tr[2]/td[4]")).getText();

            String eventDescription = _driver.findElement(By.xpath("//*[@id='CntContentsDiv9']/table/tbody/tr[2]/td[3]")).getText();


            if (Double.parseDouble(expectedPrem) < Double.parseDouble(contractPrem) && eventDescription.equals("Post Dated Downgrade"))
            {
                results = "Passed";
            }
            else
            {
                results = "Failed";
            }
            writeResults("Policy-Servicing", "PostDatedDowngrade", results, "");

        } catch (Exception e) {

            results = "Failed";
            writeResults("Policy-Servicing", "PostDatedDowngrade", results, e.toString());
        }
    }

    @Test
    private void PostDatedUpgrade() throws InterruptedException {
        if (excelMethods.contains("PostDatedUpgrade")){
            throw new SkipException("Skipping ChanegeCollectionNegative");
        }
        try
        {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","PostDatedUpgrade");;
            clickOnMainMenu();
            Delay(2);
            policySearch(PolicyNo);

            String results = "";

            String date = String.format("%1$s", LocalDateTime.now());

            String currentSumAssured = "";


            Delay(2);

            // SetproductName("PostDatedUpgrade");
            clickOnMainMenu();



            Delay(3);

            String contractPrem = _driver.findElement(By.xpath("//*[@id='CntContentsDiv9']/table/tbody/tr[2]/td[2]")).getText();

            //Click on user  contract summary
            _driver.findElement(By.xpath("//*[@id='t0_761']/table/tbody/tr/td[1]/a/img[2]")).click();



            //Click on user  component
            _driver.findElement(By.name("Alf-ICF8_00000214")).click();


            Delay(4);

            //  click on user  component
            _driver.findElement(By.name("fccComponentDescription1")).click();

            Delay(4);
            //Get The current Sum Assured for the life assured
            currentSumAssured = _driver.findElement(By.xpath("//*[@id='frmCbmcc']/tbody/tr[8]/td[4]")).getText();


            Delay(2);
            WebElement policyOptionElement = _driver.findElement(By.xpath("//*[@id='m0i0o1']"));


            //Creating object of an Actions class
            Actions action = new Actions(_driver);

            //Performing the mouse hover action on the target element.
            action.moveToElement(policyOptionElement).perform();

            //Click on options
            _driver.findElement(By.xpath("//*[@id='m0t0']/tbody/tr[1]/td/div/div[3]/a/img")).click();



            Delay(3);
            String year = String.valueOf(LocalDateTime.now().getYear());
            String month = (String.valueOf((LocalDateTime.now().getMonthValue())));

            if (month.equals("13"))
            {
                month = "01";

            }
            else if (Integer.parseInt(month) < 10)
            {
                month = "0" + month;
            }
            String day = "01";
            String dt = "" + year + month + day;
            _driver.findElement(By.name("frmCCStartDate")).clear();
            Delay(3);
            _driver.findElement(By.name("frmCCStartDate")).sendKeys(dt);
            String newSumAssured = "";
            //Do a  upgrade on current sum assured by 5000
            if (Integer.parseInt(currentSumAssured) > 10000 || Integer.parseInt(currentSumAssured) == 10000)
            {
                newSumAssured =  Integer.toString((Integer.parseInt(currentSumAssured) + 10000));
            }
            else
            {
                newSumAssured = String.valueOf(10000);
            }

            Select oSelect = new Select(_driver.findElement(By.name("frmSPAmount")));

            oSelect.selectByValue(newSumAssured);


            //Click on next
            _driver.findElement(By.name("btncbmcc13")).click();
            Delay(2);


            //Click on next
            _driver.findElement(By.name("btncbmcc17")).click();
            Delay(2);

            // Click on finish
            _driver.findElement(By.name("btncbmcc23")).click();
            Delay(5);


            String expectedPrem = _driver.findElement(By.xpath("//*[@id='CntContentsDiv9']/table/tbody/tr[2]/td[4]")).getText();

            String eventDescription = _driver.findElement(By.xpath("//*[@id='CntContentsDiv9']/table/tbody/tr[2]/td[3]")).getText();



            if (Double.parseDouble(expectedPrem) < Double.parseDouble(contractPrem) && eventDescription.equals("Post Dated Upgrade"))
            {
                results = "Passed";
            }
            else
            {
                results = "Failed";
            }
            writeResults("Policy-Servicing", "PostDatedUpgrade", results, "");
        } catch (Exception e)
        {

            String results = "Failed";
            writeResults("Policy-Servicing", "PostDatedUpgrade", results, e.toString());
        }
    }
    @Test
    private void AddRole_NextMonth() throws InterruptedException {
        if (excelMethods.contains("AddRole_NextMonth")){
            throw new SkipException("Skipping ChanegeCollectionNegative");
        }
        try {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing", "AddRole_NextMonth");
            Dictionary testData =  getDataFromSheet("AddRole_NextMonth");
            clickOnMainMenu();
            Delay(2);
            policySearch(PolicyNo);
            String results = "";
            String title = testData.get("Title").toString(),
                    first_name = testData.get("First_Name").toString(),
                    surname = testData.get("Surname").toString(),
                    initials = testData.get("Initials").toString(),
                    dob = testData.get("DOB").toString(),
                    gender = testData.get("Gender").toString(),
                    id_number = testData.get("ID_number").toString(),
                    relationship = testData.get("Relationship").toString(),
                    Comm_date = testData.get("Comm_date").toString();
            Delay(2);

            //  SetproductName("AddRole_NextMonth");
            Delay(3);

            //get commencement date
            String commencementDate = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[6]/td[2]")).getText();
            //click add role player
            _driver.findElement(By.name("btnAddRolePlayer")).click();

            //Select role
            WebElement selectRole = _driver.findElement(By.name("frmRoleObj"));
            Select s = new Select(selectRole);
            s.selectByIndex(4);
            Delay(3);
            //Click calendar

            Delay(1);
            _driver.findElement(By.name("frmEffectiveFromDate")).clear();
            _driver.findElement(By.name("frmEffectiveFromDate")).sendKeys(commencementDate);


            Delay(4);


            _driver.findElement(By.xpath("//*[@id='GBLbl-4']/span/a")).click();


            //click next to enter new role player
            _driver.findElement(By.xpath("//*[@id='GBLbl-5']/span/a")).click();

            //enter initials
            _driver.findElement(By.name("frmPersonInitials")).clear();
            _driver.findElement(By.name("frmPersonInitials")).sendKeys(initials);
            Delay(2);
            //enter name
            _driver.findElement(By.name("frmPersonFirstName")).clear();
            _driver.findElement(By.name("frmPersonFirstName")).sendKeys(first_name);
            Delay(2);
            //enter surname
            _driver.findElement(By.name("frmPersonLastName")).clear();
            _driver.findElement(By.name("frmPersonLastName")).sendKeys(surname);
            Delay(2);
            //enter


            _driver.findElement(By.name("frmPersonIDNumber")).sendKeys(id_number);
            Delay(2);
            //enter
            _driver.findElement(By.name("frmPersonDateOfBirth")).sendKeys(dob);
            Delay(2);
            //marital status
            WebElement marital = _driver.findElement(By.name("frmPersonMaritalStatus"));
            Select iselect = new Select(marital);
            iselect.selectByIndex(1);


            //Select gender
            List<WebElement> rdos = _driver.findElements(By.xpath("//input[@name='frmPersonGender']"));

            for (WebElement radio : rdos) {

                if (radio.getAttribute("value").equals("er_AcPerGenMal")) {

                    radio.click();
                    break;

                } else {

                    radio.findElement(By.xpath("//*[@id='frmSubCbmre']/tbody/tr[1]/td[4]/table/tbody/tr/td[3]/input")).click();

                }

            }

            //Select Relationship
            var V_relationship = "";
            switch (relationship) {

                case "Additional parent":
                    V_relationship = "951372577.488";
                    break;
                case "Spouse":
                    V_relationship = "854651144.248";
                    break;
                case "Additional child":
                    V_relationship = "905324120.488";
                    break;
                case "Child":
                    V_relationship = "905324138.488";
                    break;
                case "Parent":
                    V_relationship = "347901097.188";
                    break;

                case "Brother":
                    V_relationship = "951371842.488";
                    break;

            }
            WebElement relation = _driver.findElement(By.name("frmRelationshipCodeObj"));
            Select oselect = new Select(relation);
            oselect.selectByValue(V_relationship);
            //Title
            var value = "";
            switch (title) {
                case "Mr":
                    value = "er_AcPerTitleMr";
                    break;
                case "Mrs":
                    value = "er_AcPerTitleMrs";
                    break;

                case "Ms":
                    value = "er_AcPerTitleMs";
                    break;

                case "Prf":
                    value = "er_AcPerTitlePrf";
                    break;
                case "Dr":
                    value = "er_AcPerTitleDoc";
                    break;

                case "Adm":
                    value = "er_AcPerTitleADM";
                    break;

                case "Miss":
                    value = "er_AcPerTitleMiss";
                    break;

                default:
                    break;
            }

            Select oSelect = new Select(_driver.findElement(By.name("frmPersonTitle")));
            oSelect.selectByValue(value);


            Delay(2);
            //save
            _driver.findElement(By.xpath(" //*[@id='GBLbl-5']/span/a")).click();
            Delay(2);
            //Roleplayer ID
            var LifeA_ID = _driver.findElement(By.xpath(" //*[@id='frmSubCbmre']/tbody/tr[4]/td[4]")).getText();

            //validation
            if (LifeA_ID.equals(id_number)) {
                results = "Passed";

            } else {
                results = "Fail";

            }
            writeResults("Policy-Servicing","AddRole_NextMonth",results,"");


            clickOnMainMenu();

            Delay(2);
            //click contract summary
            _driver.findElement(By.xpath(" //*[@id='t0_771']/table/tbody/tr/td[3]/a")).click();

            Delay(3);
            // _driver.Navigate().Refresh();
            //click on add componet
            _driver.findElement(By.xpath("   //*[@id='GBLbl-5']/span/a")).click();


            Delay(3);

            _driver.findElement(By.xpath("//*[@id='GBLbl-6']/span/a")).click();


            Delay(3);
            //Click calendar
            _driver.findElement(By.name("frmCCStartDate")).clear();
            _driver.findElement(By.name("frmCCStartDate")).sendKeys(Comm_date);


            Delay(3);
            _driver.findElement(By.xpath("//*[@id='GB-6']")).click();
            Delay(2);

            //Dropdown
            WebElement elem = _driver.findElement(By.name("frmRolePlayers"));
            Select option = new Select(elem);
            option.deselectAll();
            for (int i = 1; i < 30; i++) {
                String roles = _driver.findElement(By.xpath("//*[@id='frmCbmcc']/tbody/tr[6]/td[2]/select/option[{i.ToString()}]")).getText();

                String Ridno1 = roles.split(" ")[roles.split(" ").length - 1].toString();

                String[] arrOfStr = roles.split("", -1);

                String ID1 = Ridno1.substring(1, 13);

                if (ID1 == id_number) {
                    option.selectByVisibleText(roles);
                    break;
                }


            }

            Delay(3);


            //next
            _driver.findElement(By.xpath(" //*[@id='GBLbl-7']/span/a")).click();

            //Validate roleplayer ID number
            Delay(2);

            var RoleINno = _driver.findElement(By.xpath("//*[@id='frmCbmcc']/tbody/tr[9]/td[2]")).getText();

            var Ridno = RoleINno.split(java.util.regex.Pattern.quote(" "), -1)[RoleINno.split(java.util.regex.Pattern.quote(" "), -1).length - 1].toString();
            var ID = Ridno.substring(1, 14);

            assert id_number == ID;

            Delay(2);

            _driver.findElement(By.xpath("//*[@id='GBLbl-7']/span/a")).click();

            Delay(2);
        }catch (Exception e){
            writeResults("Policy-Servicing","AddRole_NextMonth","Failed",e.toString());
        }
    }
    @Test
    private void TerminateRoleNext_month() throws InterruptedException {
        if (excelMethods.contains("TerminateRoleNext_month")){
            throw new SkipException("Skipping ChanegeCollectionNegative");
        }
        String PolicyNo = getPolicyNoFromExcel("Policy-Servicing", "TerminateRoleNext_month");
        clickOnMainMenu();
        Delay(2);
        try {


            policySearch(PolicyNo);
            //  SetproductName("TerminateRoleNext_month");
            String results = "";
            Delay(2);

            for (int i = 2; i < 23; i++) {
                String txt = _driver.findElement(By.xpath(String.format("//*[@id='CntContentsDiv11']/table/tbody/tr[%1$s]/td[1]/span", String.valueOf(i)))).getText();
                String relationship = _driver.findElement(By.xpath(String.format("//*[@id='CntContentsDiv11']/table/tbody/tr[%1$s]/td[3]", String.valueOf(i)))).getText();

                if (txt.equals("Life Assured") && !relationship.equals("Self")) {
                    _driver.findElement(By.xpath(String.format("//*[@id='CntContentsDiv11']/table/tbody/tr[%1$s]/td[1]/span", String.valueOf(i)))).click();
                    break;
                }

            }
            Delay(3);
            String expected = "Are you sure you want to terminate this role";
            _driver.findElement(By.name("btnTerminate")).click();
            String alerttext = _driver.switchTo().alert().getText();
            _driver.switchTo().alert().accept();
            // assert expected == alerttext;

            //change effective to date
            Delay(2);
            WebElement effectivefrom = _driver.findElement(By.name("frmEffectiveFromDate"));
            String effectvalue = effectivefrom.getAttribute("value");

            Delay(1);
            _driver.findElement(By.name("frmEffectiveToDate")).clear();
            _driver.findElement(By.name("frmEffectiveToDate")).sendKeys(effectvalue);

            Delay(2);
            //click terminate

            _driver.findElement(By.name("btnTerminate")).click();

            //validation
            if (_driver.getCurrentUrl().equals("http://ilr-tst.safrican.co.za/web/wspd_cgi.sh/WService=wsb_ilrtst/run.w?")) {
                results = "Passed";

            } else {
                results = "Fail";


            }

            writeResults("Policy-Servicing", "TerminateRoleNext_month", results, "");
        }catch (Exception e) {
            writeResults("Policy-Servicing", "TerminateRoleNext_month", "Failed", e.toString());
        }
    }
    @Test
    private void TerminateRolePlayer() throws InterruptedException {
        if (excelMethods.contains("TerminateRoleNext_month")){
            throw new SkipException("Skipping ChanegeCollectionNegative");
        }
        try {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing", "TerminateRolePlayer");
            clickOnMainMenu();
            Delay(2);
            policySearch(PolicyNo);
            SetproductName("TerminateRolePlayer");
            String results = "";
            Delay(2);

            for (int i = 2; i < 23; i++) {
                String txt = _driver.findElement(By.xpath(String.format("//*[@id='CntContentsDiv11']/table/tbody/tr[%1$s]/td[1]/span", String.valueOf(i)))).getText();
                String relationship = _driver.findElement(By.xpath(String.format("//*[@id='CntContentsDiv11']/table/tbody/tr[%1$s]/td[3]", String.valueOf(i)))).getText();

                if (txt.equals("Life Assured") && !relationship.equals("Self")) {
                    _driver.findElement(By.xpath(String.format("//*[@id='CntContentsDiv11']/table/tbody/tr[%1$s]/td[1]/span", String.valueOf(i)))).click();
                    break;
                }

            }
            Delay(3);
            String expected = "Are you sure you want to terminate this role";
            _driver.findElement(By.name("btnTerminate")).click();
            String alerttext = _driver.switchTo().alert().getText();
            _driver.switchTo().alert().accept();
            // assert expected == alerttext;

            //change effective to date
            Delay(2);
            WebElement effectivefrom = _driver.findElement(By.name("frmEffectiveFromDate"));
            String effectvalue = effectivefrom.getAttribute("value");

            Delay(1);
            _driver.findElement(By.name("frmEffectiveToDate")).clear();
            _driver.findElement(By.name("frmEffectiveToDate")).sendKeys(effectvalue);

            Delay(2);
            //click terminate

            _driver.findElement(By.name("btnTerminate")).click();

            //validation
            if (_driver.getCurrentUrl().equals("http://ilr-tst.safrican.co.za/web/wspd_cgi.sh/WService=wsb_ilrtst/run.w?")) {
                results = "Passed";

            } else {
                results = "Fail";


            }

            writeResults("Policy-Servicing", "TerminateRolePlayer", results, "");
        }catch (Exception e) {
            writeResults("Policy-Servicing", "TerminateRolePlayer", "Failed", e.toString());
        }
    }
    @Test
    private void ChangeLifeAssured() throws InterruptedException {
        if (excelMethods.contains("ChangeLifeAssured")){
            throw new SkipException("Skipping ChanegeCollectionNegative");
        }
        String results;
        try {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing", "ChangeLifeAssured");
            Dictionary testData = getDataFromSheet("ChangeLifeData");
            clickOnMainMenu();
            Delay(2);
            policySearch(PolicyNo);
            results = "";
            SetproductName("ChangeLifeAssured");
            String title = testData.get("Title").toString(),
                    surname = testData.get("Surname").toString(),
                    MaritalStatus = testData.get("MaritalStatus").toString(),
                    EducationLevel = testData.get("EducationLevel").toString(),
                    Department = testData.get("Department").toString(),
                    Profession = testData.get("Profession").toString();

            Delay(2);
            //click on life Assured
            _driver.findElement(By.name("fcOwningEntityLink1")).click();

            Delay(2);
            String oldMartialStatus = _driver.findElement(By.xpath("//*[@id='CntContentsDiv4']/table/tbody/tr[2]/td[4]")).getText();

            //click oN Change
            _driver.findElement(By.name("btnChangePerson")).click();


            String value = "";
            switch (title) {
                case "Mr":
                    value = "er_AcPerTitleMr";
                    break;
                case "Ms":
                    value = "er_AcPerTitleMs";
                    break;
                case "Mrs":
                    value = "er_AcPerTitleMrs";
                    break;
                case "Prf":
                    value = "er_AcPerTitlePrf";
                    break;
                case "Dr":
                    value = "er_AcPerTitleDr";
                    break;
                case "Adm":
                    value = "er_AcPerTitleADM";
                    break;
                case "Adv":
                    value = "er_AcPerTitleADV";
                    break;
                case "Capt":
                    value = "er_AcPerTitleCAP";
                    break;
                case "Col":
                    value = "er_AcPerTitleCOL";
                    break;
                case "Exec":
                    value = "er_AcPerTitleEXE";
                    break;
                case "Gen":
                    value = "er_AcPerTitleGEN";
                    break;
                case "Hon":
                    value = "er_AcPerTitleHON";
                    break;
                case "Lt":
                    value = "er_AcPerTitleLUI";
                    break;
                case "Maj":
                    value = "er_AcPerTitleMAJ";
                    break;
                case "Messr":
                    value = "er_AcPerTitleMES";
                    break;
                case "Pstr":
                    value = "er_AcPerTitlePST";
                    break;
                case "Rev":
                    value = "er_AcPerTitleREV";
                    break;
                case "Sir":
                    value = "er_AcPerTitleSIR";
                    break;
                case "Brig":
                    value = "er_AcPerTitleBRG";
                    break;
                case "Miss":
                    value = "er_AcPerTitleMiss";
                    break;

                default:
                    break;
            }
            Select oSelect = new Select(_driver.findElement(By.name("fcTitle")));
            //Select title
            oSelect.selectByValue(value);
            Delay(2);

            //Insert Surname
            _driver.findElement(By.name("fcLastName")).clear();
            _driver.findElement(By.name("fcLastName")).sendKeys(surname);

            Delay(2);

            //Insert MaritalStatus

            switch (MaritalStatus) {


                case "Single":
                    value = "er_AcStaMarSin";
                    break;
                case "Married":
                    value = "er_AcStaMarMar";
                    break;
                case "Divorced":
                    value = "er_AcStaMarDiv";
                    break;
                case "Widowed":
                    value = "er_AcStaMarWid";
                    break;
                case "Separated":
                    value = "er_AcStaMarSep";
                    break;
                case "Unknown":
                    value = "er_AcStaMarUnk";
                    break;
                case "Married (in Community of Property)":
                    value = "er_AcStaMarMac";
                    break;
                case "Married (not in Community of Property)":
                    value = "er_AcStaMarMnc";
                    break;
                case "Domestic Partnership/Co-habiting":
                    value = "er_AcStaMarDpp";
                    break;

                default:
                    break;

            }
            Select oSelect2 = new Select(_driver.findElement(By.name("fcMaritalStatus")));
            oSelect2.selectByValue(value);
            Delay(2);


            //Insert EducationLevel
            switch (EducationLevel) {
                case "No Matric":
                    value = "NMT";
                    break;
                case "Matric":
                    value = "MTR";
                    break;
                case "Diploma (less than 3 years)":
                    value = "DPL";
                    break;
                case "Diploma (3 years or more)":
                    value = "DPM";
                    break;
                case "University/Undergraduate Degree":
                    value = "UND";
                    break;
                case "Postgraduate Study":
                    value = "PGD";
                    break;
                default:
                    break;
            }

            Select oSelect1 = new Select(_driver.findElement(By.name("fcEducationLevel")));

            //Select title
            oSelect1.selectByValue(value);
            Delay(2);

            //Department
            //Insert Surname
            _driver.findElement(By.name("fcDepartment")).clear();
            _driver.findElement(By.name("fcDepartment")).sendKeys(Department);
            Delay(2);


            switch (Profession) {
                case "Accountant":
                    value = "ACU";
                    break;
                case "Actor/Actress":
                    value = "ACT";
                    break;
                case "Actuary":
                    value = "ATY";
                    break;
                case "Adverting":
                    value = "ADS";
                    break;
                case "Agriculture":
                    value = "AGC";
                    break;
                case "Architect":
                    value = "ARC";
                    break;
                case "Auditor":
                    value = "AUD";
                    break;
                case "Banker":
                    value = "BKR";
                    break;
                case "book keeper":
                    value = "";
                    break;
                case "Broker":
                    value = "BRK";
                    break;
                case "Doctor":
                    value = "DTR";
                    break;
                case ("Engineer"):
                    value = "EGR";
                    break;
                case ("Human Resources"):
                    value = "HRC";
                    break;
                case ("Import/Export"):
                    value = "IEX";
                    break;
                case ("Information Technology"):
                    value = "ITC";
                    break;
                case ("Insurance"):
                    value = "ISE";
                    break;
                case ("Lawyer"):
                    value = "LAW";
                    break;
                case ("Military"):
                    value = "MLY";
                    break;
                case ("Pilot"):
                    value = "PLT";
                    break;

                default:
                    break;
            }

            Select oSelect3 = new Select(_driver.findElement(By.name("fcProfession")));
            //Select title
            oSelect3.selectByValue(value);
            Delay(2);

            //Click on the submit btn
            _driver.findElement(By.name("btnSubmit")).click();


            Delay(2);
            String newMartialStatus = _driver.findElement(By.xpath("//*[@id='CntContentsDiv4']/table/tbody/tr[2]/td[4]")).getText();


            //Vaidation based Martial stat

            if ((oldMartialStatus) != (newMartialStatus)) {
                //Details sucessfully changed);
                results = "Passed";
            } else {
                results = "Failed";
            }
            writeResults("Policy-Servicing", "ChangeLifeAssured", results, "");
        } catch (Exception e) {

            e.printStackTrace();
            results = "Failed";
            writeResults("Policy-Servicing", "ChangeLifeAssured", results, e.toString());
        }
    }

    @Test
    private void RemovalOfNonCompulsoryLife() throws InterruptedException
    {
        if (excelMethods.contains("RemovalOfNonCompulsoryLife")){
            throw new SkipException("Skipping ChanegeCollectionNegative");
        }
        String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","RemovalOfNonCompulsoryLife");
        clickOnMainMenu();
        Delay(2);
        try {
            policySearch(PolicyNo);
            String results = "";
            String currentPremium = "";
            String newPremium = "";


            Delay(4);
            SetproductName("RemovalOfNonCompulsoryLife");
            Delay(2);
            //Get commencement data
            String commDate = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[6]/td[2]")).getText();
            Delay(2);
            //Get Current premium
            currentPremium = _driver.findElement(By.xpath("//*[@id='CntContentsDiv9']/table/tbody/tr[2]/td[2]")).getText();
            Delay(4);
            //Click on the component we want to terminate
            _driver.findElement(By.name("fccComponentDescription2")).click();
            Delay(3);
            //Click on the terminate btn
            _driver.findElement(By.name("btncbmcc29")).click();
            Delay(3);

            String[] commSplit = commDate.split("/");
            commDate = "";
            for (String i : commSplit) {
                commDate += i;
            }
            Delay(3);

            _driver.findElement(By.name("frmCCEndDate")).clear();
            Delay(3);
            _driver.findElement(By.name("frmCCEndDate")).sendKeys(commDate);
            Delay(3);
            //Click on submit
            _driver.findElement(By.name("btncbmcc31")).click();
            Delay(3);
            newPremium = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr/td[2]")).getText();
            Delay(3);
            results = Double.valueOf(newPremium) < Double.valueOf(currentPremium) ? "Passed" : "Failed";
            Delay(4);
            writeResults("Policy-Servicing", "RemovalOfNonCompulsoryLife", "Failed", "");
        }catch (Exception e){
            writeResults("Policy-Servicing", "RemovalOfNonCompulsoryLife", "Failed", e.toString());
        }

    }

    @Test
    private void AddaLife() throws InterruptedException {
        if (excelMethods.contains("AddaLife")){
            throw new SkipException("Skipping ChanegeCollectionNegative");
        }
        try
        {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","AddaLife");
            Dictionary testData =  getDataFromSheet("AddaLife");
            clickOnMainMenu();
            Delay(2);
            policySearch(PolicyNo);
            Delay(2);
            String results = "";

            String title = testData.get("Title").toString(),
                    first_name = testData.get("First_Name").toString(),
                    surname = testData.get("Surname").toString(),
                    initials = testData.get("Initials").toString(),
                    dob = testData.get("dob").toString(),
                    gender = testData.get("Gender").toString(),
                    id_number = testData.get("ID_number").toString(),
                    relationship = testData.get("Relationship").toString();

            Delay(2);


            String oldPrem = _driver.findElement(By.xpath("//*[@id='CntContentsDiv9']/table/tbody/tr[2]/td[2]")).getText();
            //Get the Commencement date from contract summary screen
            String commDate = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[6]/td[2]")).getText();

            //click add policy

            _driver.findElement(By.xpath("//*[@id='GBLbl-1']/span/a")).click();

            //Select role
            WebElement selectRole = _driver.findElement(By.name("frmRoleObj"));
            Select s = new Select(selectRole);
            s.selectByIndex(4);

            Delay(2);
            _driver.findElement(By.name("frmEffectiveFromDate")).clear();
            Delay(2);
            _driver.findElement(By.name("frmEffectiveFromDate")).sendKeys(commDate);
            Delay(4);


            _driver.findElement(By.xpath("//*[@id='GBLbl-4']/span/a")).click();

            //click next to enter new role player
            _driver.findElement(By.xpath("//*[@id='GBLbl-5']/span/a")).click();


            //enter initials
            _driver.findElement(By.name("frmPersonInitials")).clear();
            _driver.findElement(By.name("frmPersonInitials")).sendKeys(initials);
            Delay(2);
            //enter name
            _driver.findElement(By.name("frmPersonFirstName")).clear();
            _driver.findElement(By.name("frmPersonFirstName")).sendKeys(first_name);
            Delay(2);
            //enter surname
            _driver.findElement(By.name("frmPersonLastName")).clear();
            _driver.findElement(By.name("frmPersonLastName")).sendKeys(surname);
            Delay(2);
            //enter


            _driver.findElement(By.name("frmPersonIDNumber")).sendKeys(id_number);
            Delay(2);



            //enter
            _driver.findElement(By.name("frmPersonDateOfBirth")).clear();
            _driver.findElement(By.name("frmPersonDateOfBirth")).sendKeys(dob);
            Delay(2);


            //marital status
            WebElement marital = _driver.findElement(By.name("frmPersonMaritalStatus"));
            Select select = new Select(marital);
            select.selectByIndex(1);


            //Select gender
            List<WebElement> rdos = _driver.findElements(By.xpath("//input[@name='frmPersonGender']"));

            for  (WebElement radio : rdos)
            {

                if (radio.getAttribute("value").equals("er_AcPerGenMal"))
                {

                    radio.click();
                    break;

                }
                else
                {

                    radio.findElement(By.xpath("//*[@id='frmSubCbmre']/tbody/tr[1]/td[4]/table/tbody/tr/td[3]/input")).click();

                }


            }

            //Select Relationship
            String V_relationship = "";
            switch (relationship)
            {

                case "Additional parent":
                    V_relationship = "951372577.488";
                    break;
                case "Spouse":
                    V_relationship = "854651144.248";
                    break;
                case "Additional child":
                    V_relationship = "905324120.488";
                    break;
                case "Child":
                    V_relationship = "905324138.488";
                    break;
                case "Parent":
                    V_relationship = "347901097.188";
                    break;

                case "Brother":
                    V_relationship = "951371842.488";
                    break;

            }

            WebElement relation = _driver.findElement(By.name("frmRelationshipCodeObj"));
            Select oselect = new Select(relation);
            oselect.selectByValue(V_relationship);
            //Title
            String value = "";
            switch (title)
            {
                case "Mr":
                    value = "er_AcPerTitleMr";
                    break;
                case "Mrs":
                    value = "er_AcPerTitleMrs";
                    break;

                case "Ms":
                    value = "er_AcPerTitleMs";
                    break;

                case "Prf":
                    value = "er_AcPerTitlePrf";
                    break;
                case "Dr":
                    value = "er_AcPerTitleDoc";
                    break;

                case "Adm":
                    value = "er_AcPerTitleADM";
                    break;

                case "Miss":
                    value = "er_AcPerTitleMiss";
                    break;

                default:
                    break;
            }

            Select oSelect = new Select(_driver.findElement(By.name("frmPersonTitle")));
            oSelect.selectByValue(value);

            Delay(2);
            //save
            _driver.findElement(By.xpath(" //*[@id='GBLbl-5']/span/a")).click();
            Delay(2);



            clickOnMainMenu();

            //click contract summary
            _driver.findElement(By.name("2000175333.8")).click();



            Delay(2);
            //click on component
            _driver.findElement(By.xpath("//*[@id='GBLbl-5']/span/a")).click();


            Delay(2);

            WebElement parentcomponent = _driver.findElement(By.name("frmParentComponentObj"));
            Select selecCom = new Select(parentcomponent);
            selecCom.selectByIndex(1);
            Delay(2);

            _driver.findElement(By.xpath("//*[@id='GBLbl-6']/span/a")).click();

            Delay(2);
            _driver.findElement(By.name("frmCCStartDate")).clear();
            Delay(2);
            _driver.findElement(By.name("frmCCStartDate")).sendKeys(commDate);

            Select oSelect4 = new Select(_driver.findElement(By.name("frmSPAmount")));
            oSelect4.selectByValue("20000");
            Delay(2);

            //Click next
            _driver.findElement(By.name("btncbmcc2")).click();
            Delay(3);


            //Click on next
            _driver.findElement(By.name("btncbmcc5")).click();
            Delay(2);

            //Click on finish
            _driver.findElement(By.name("btncbmcc11")).click();
            Delay(2);

            String newPrem = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr/td[2]")).getText();



            if (Double.parseDouble(newPrem) > Double.parseDouble(oldPrem))
            {
                results = "Passed";
            }
            else
            {
                results = "Failed";

            }
            writeResults("Policy-Servicing","AddaLife",results,"");
        }
        catch (Exception e)
        {

            String results = "Failed";
            writeResults("Policy-Servicing", "AddaLife", "Failed", e.toString());

        }

    }
    @Test
    private void IncreaseSumAssured() throws InterruptedException {
        if (excelMethods.contains("IncreaseSumAssured")){
            throw new SkipException("Skipping ChanegeCollectionNegative");
        }
        try
        {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","IncreaseSumAssured");;
            clickOnMainMenu();
            Delay(2);
            policySearch(PolicyNo);
            String results = "";
            String date = LocalDateTime.now().toString();
            String currentSumAssured = "";
            String commDate = "";
            Delay(2);

            SetproductName("IncreaseSumAssured");

            Delay(3);
            //Get the Commencement date from contract summary screen
            commDate = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[6]/td[2]")).getText();

            //Scroll Down
            Delay(2);

            String contractPrem = _driver.findElement(By.xpath("//*[@id='CntContentsDiv9']/table/tbody/tr[2]/td[2]")).getText();

            clickOnMainMenu();
            //Click on user  contract summary
            _driver.findElement(By.xpath("//*[@id='t0_771']/table/tbody/tr/td[1]/a/img[2]")).click();



            //Click on user  component
            _driver.findElement(By.xpath("//*[@id='t0_774']/a")).click();


            Delay(4);

            //  click on user  component
            _driver.findElement(By.name("fccComponentDescription1")).click();

            //Get The current Sum Assured for the life assured
            currentSumAssured = _driver.findElement(By.xpath("//*[@id='frmCbmcc']/tbody/tr[8]/td[4]")).getText();


            Delay(2);

            WebElement policyOptionElement = _driver.findElement(By.xpath("//*[@id='m0i0o1']"));
            //Creating object of an Actions class
            Actions action = new Actions(_driver);
            //Performing the mouse hover action on the target element.
            action.moveToElement(policyOptionElement).perform();
            //Click on options
            Delay(1);
            _driver.findElement(By.xpath("//*[@id='m0t0']/tbody/tr[1]/td/div/div[3]/a/img")).click();

            //Date selection
            Delay(4);
            _driver.findElement(By.name("frmCCStartDate")).clear();
            Delay(2);
            _driver.findElement(By.name("frmCCStartDate")).sendKeys(commDate);

            Delay(5);

            String newSumAssured = "";
            //Do a  upgrade on current sum assured by 5000
            if (Integer.parseInt(currentSumAssured) > 10000 || Integer.parseInt(currentSumAssured) == 10000)
            {
                newSumAssured =  Integer.toString((Integer.parseInt(currentSumAssured) + 10000));
            }
            else
            {

                newSumAssured = String.valueOf(60000);
            }

            Select oSelect = new Select(_driver.findElement(By.name("frmSPAmount")));

            oSelect.selectByValue(newSumAssured);


            //Click on next
            _driver.findElement(By.name("btncbmcc13")).click();
            Delay(2);


            //Click on next
            _driver.findElement(By.name("btncbmcc17")).click();
            Delay(2);

            // Click on finish
            _driver.findElement(By.name("btncbmcc23")).click();
            Delay(5);
            var newPrem = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr/td[2]")).getText();

            if (Double.parseDouble(newPrem) > Double.parseDouble(contractPrem))
            {
                results = "Passed";
            }
            else
            {
                results = "Failed";
            }

            writeResults("Policy-Servicing","IncreaseSumAssured",results,"");
        } catch (Exception e)
        {

            e.printStackTrace();
            String results = "Failed";
            writeResults("Policy-Servicing", "IncreaseSumAssured", results, e.toString());
        }
    }

    @Test
    private void AddRolePlayer() throws InterruptedException {
        if (excelMethods.contains("AddRolePlayer")){
            throw new SkipException("Skipping ChanegeCollectionNegative");
        }
        Dictionary testData = getDataFromSheet("AddRolePlayer");
        String title = testData.get("Title").toString(),
                first_name = testData.get("First_Name").toString(),
                surname = testData.get("Surname").toString(),
                initials = testData.get("Initials").toString(),
                dob = testData.get("DOB").toString(),
                gender = testData.get("Gender").toString(),
                id_number = testData.get("ID_number").toString(),
                relationship = testData.get("Relationship").toString(),
                Comm_date = testData.get("Comm_date").toString();
        try
        {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","AddRolePlayer");;
            clickOnMainMenu();
            Delay(2);
            policySearch(PolicyNo);

            //    JavaScriptExecutor js2 = (JavaScriptExecutor)_driver;

            String results = "";

            Delay(2);

            // SetproductName("AddRolePlayer");

            // Delay(5);



            //get commencement date
            String commencementDate = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[6]/td[2]")).getText();
            Delay(3);
            //click add role player
            _driver.findElement(By.name("btnAddRolePlayer")).click();

            //Select role
            WebElement selectRole = _driver.findElement(By.name("frmRoleObj"));
            Select s = new Select(selectRole);
            s.selectByIndex(4);
            Delay(3);
            //Click calendar

            Delay(1);
            _driver.findElement(By.name("frmEffectiveFromDate")).clear();
            _driver.findElement(By.name("frmEffectiveFromDate")).sendKeys(commencementDate);


            Delay(4);


            _driver.findElement(By.xpath("//*[@id='GBLbl-4']/span/a")).click();
            //assert

            //click next to enter new role player
            _driver.findElement(By.xpath("//*[@id='GBLbl-5']/span/a")).click();
            //extract excell data



            //enter initials
            _driver.findElement(By.name("frmPersonInitials")).clear();
            _driver.findElement(By.name("frmPersonInitials")).sendKeys(initials);
            Delay(2);
            //enter name
            _driver.findElement(By.name("frmPersonFirstName")).clear();
            _driver.findElement(By.name("frmPersonFirstName")).sendKeys(first_name);
            Delay(2);
            //enter surname
            _driver.findElement(By.name("frmPersonLastName")).clear();
            _driver.findElement(By.name("frmPersonLastName")).sendKeys(surname);
            Delay(2);
            //enter


            _driver.findElement(By.name("frmPersonIDNumber")).sendKeys(id_number);
            Delay(2);
            //enter
            _driver.findElement(By.name("frmPersonDateOfBirth")).sendKeys(dob);
            Delay(2);
            //marital status
            WebElement merital = _driver.findElement(By.name("frmPersonMaritalStatus"));
            Select iselect = new Select(merital);
            iselect.selectByIndex(1);



            //Select gender
            List<WebElement> rdos = _driver.findElements(By.xpath("//input[@name='frmPersonGender']")); for (WebElement radio : rdos)
        { if (radio.getAttribute("value").equals("er_AcPerGenMal"))
        { radio.click();
            break; }
        else
        { radio.findElement(By.xpath("//*[@id='frmSubCbmre']/tbody/tr[1]/td[4]/table/tbody/tr/td[3]/input")).click(); }




        }

            //Select Relationship
            String V_relationship = "";
            switch (relationship)
            {

                case "Additional parent":
                    V_relationship = "951372577.488";
                    break;
                case "Spouse":
                    V_relationship = "854651144.248";
                    break;
                case "Additional child":
                    V_relationship = "905324120.488";
                    break;
                case "Child":
                    V_relationship = "905324138.488";
                    break;
                case "Parent":
                    V_relationship = "347901097.188";
                    break;

                case "Brother":
                    V_relationship = "951371842.488";
                    break;

            }

            WebElement relation = _driver.findElement(By.name("frmRelationshipCodeObj"));
            Select oselect = new Select(relation);
            oselect.selectByValue(V_relationship);
            //Title
            String value = "";
            switch (title)
            {
                case "Mr":
                    value = "er_AcPerTitleMr";
                    break;
                case "Mrs":
                    value = "er_AcPerTitleMrs";
                    break;

                case "Ms":
                    value = "er_AcPerTitleMs";
                    break;

                case "Prf":
                    value = "er_AcPerTitlePrf";
                    break;
                case "Dr":
                    value = "er_AcPerTitleDoc";
                    break;

                case "Adm":
                    value = "er_AcPerTitleADM";
                    break;

                case "Miss":
                    value = "er_AcPerTitleMiss";
                    break;

                default:
                    break;
            }

            Select oSelect = new Select(_driver.findElement(By.name("frmPersonTitle")));
            oSelect.selectByValue(value);




            String url2 = _driver.getCurrentUrl();
            Assert.assertEquals(url2, "http://ilr-tst.safrican.co.za/web/wspd_cgi.sh/WService=wsb_ilrtst/run.w?");

            Delay(2);
            //save
            _driver.findElement(By.xpath(" //*[@id='GBLbl-5']/span/a")).click();
            Delay(2);
            //Roleplayer ID
            String LifeA_ID = _driver.findElement(By.xpath(" //*[@id='frmSubCbmre']/tbody/tr[4]/td[4]")).getText();

            //validation
            if (id_number.contains(LifeA_ID))
            {
                results = "Passed";

            }
            else

            {
                results = "Fail";

            }

            writeResults("Policy-Servicing","AddRolePlayer",results,"");

        } catch (Exception e)
        {


            String results = "Failed";
            writeResults("Policy-Servicing", "AddRolePlayer", results, e.toString());
        }

        clickOnMainMenu();

        Delay(2);
        //click contract summary
        _driver.findElement(By.xpath(" //*[@id='t0_771']/table/tbody/tr/td[3]/a")).click();

        Delay(3);
        //  _driver.Navigate().Refresh();

        //click on add componet
        _driver.findElement(By.xpath("   //*[@id='GBLbl-5']/span/a")).click();


        Delay(3);
        _driver.findElement(By.xpath("//*[@id='GBLbl-6']/span/a")).click();

        //get commencement date
        String commencementDate = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[6]/td[2]")).getText();


        Delay(3);
        //Click calendar
        _driver.findElement(By.name("frmCCStartDate")).clear();
        _driver.findElement(By.name("frmCCStartDate")).sendKeys(commencementDate);




        Delay(3);
        _driver.findElement(By.xpath("//*[@id='GB-6']")).click();
        Delay(2);

        //Dropdown

        // var roles = _driver.FindElement(By.XPath($"//*[@id='frmCbmcc']/tbody/tr[6]/td[2]/select/option[1]")).Text;
        WebElement elem = _driver.findElement(By.name("frmRolePlayers"));
        Select option = new Select(elem);
        option.deselectAll();



        for (int i = 1; i < 30; i++)
        {
            //   var txt = _driver.FindElement(By.XPath($"//*[@id='CntContentsDiv11']/table/tbody/tr[{i.ToString()}]/td[1]/span")).Text;

            var roles = _driver.findElement(By.xpath("//*[@id='frmCbmcc']/tbody/tr[6]/td[2]/select/option[{i.ToString()}]")).getText();

            var Ridno1 = roles.split(" ")[roles.split(" ").length - 1].toString();
            var ID1 = Ridno1.substring(1, 13);

            if (ID1 ==id_number )
            {


                option.selectByVisibleText(roles);
                break;
            }


        }

        Delay(3);


        //next
        _driver.findElement(By.xpath(" //*[@id='GBLbl-7']/span/a"));

        //Validate roleplayer ID number
        Delay(2);

        var RoleINno = _driver.findElement(By.xpath("//*[@id='frmCbmcc']/tbody/tr[9]/td[2]")).getText();

        var Ridno = RoleINno.split(" ")[RoleINno.split(" ").length - 1].toString();
        var ID = Ridno.substring(1, 13);

        Assert.assertEquals(id_number, ID);

        Delay(2);

        _driver.findElement(By.xpath("//*[@id='GBLbl-7']/span/a")).click();

        Delay(2);




    }

    private void SetproductName(String methodname) {
    }

    private void clickOnMainMenu()
    {
        try
        {
            //find the contract search option
            WebElement search = _driver.findElement(By.name("alf-ICF8_00000222"));
        }
        catch(Exception e)
        {
            //clickOnMainMenu
            _driver.findElement(By.name("CBWeb")).click();
        }
    }
    private void policySearch(String PolicyNo) throws InterruptedException {

        // String PolicyNo = "";
        //Click on contract search
        Delay(2);

        _driver.findElement(By.name("alf-ICF8_00000222")).click();
        Delay(2);

        //Type in contract ref
        _driver.findElement(By.name("frmContractReference")).sendKeys(PolicyNo);

        Delay(4);

        //Click on Search Icon
        _driver.findElement(By.name("btncbcts0")).click();
        Delay(2);
        _driver.findElement(By.xpath("//*[@id='AppArea']/table[2]/tbody/tr[2]/td[1]/a")).click();

    }


    public final void Delay(int delaySeconds) throws InterruptedException {

        Thread.sleep(delaySeconds * 1000);

    }

}


