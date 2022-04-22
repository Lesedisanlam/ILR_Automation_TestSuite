package src.main.java.PolicyServicing;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.Alert;
import java.util.Iterator;
import java.util.Set;
import java.util.List;

import java.math.BigDecimal;
import java.time.LocalDateTime;


import java.time.format.DateTimeFormatter;

public class PolicyServicing extends Base {

@BeforeClass
    public WebDriver login() throws InterruptedException {
        super.siteConnection();
        return _driver;

    }

  /*  @Test
    public void addBeneficiary() throws InterruptedException {

        getPolicyNoFromExcel("Policy-Servicing","addBeneficiary");
        clickOnMainMenu();
        Delay(2);
        String PolicyNo = "";
        policySearch(PolicyNo);
        String results = "Failed";

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
        _driver.findElement(By.name("frmIDNumber")).sendKeys("8604225772087");
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
            } catch (RuntimeException e) {
                break;
            }


        }


        Delay(3);
writeResults("Policy-Servicing","PolicyNo","results","");
        //super.writeResultsToExcell(results, sheet, "addBeneficiary");
    }
*/
    @Test
    public void ReInstate() throws InterruptedException {

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

            writeResults("Policy-Servicing", "PolicyNo", "results", "");
        } catch (Exception e) {

            e.printStackTrace();
            results = "Failed";
            writeResults("Policy-Servicing", "PolicyNo", "results", "");
        }
    }
    @Test(dependsOnMethods = {"ReInstate"},alwaysRun = true)
    public void CancelPolicy() throws InterruptedException {

        String results;
        try {
            String PolicyNo = getPolicyNoFromExcel("Policy-Servicing", "CancelPolicy");
            clickOnMainMenu();
            Delay(2);
            policySearch(PolicyNo);
            results = "";
            Delay(2);
            String commDate = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[6]/td[2]")).getText();
            String dt = "commDate";

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
            writeResults("Policy-Servicing", "PolicyNo", "results", "");

        } catch (Exception e) {

            e.printStackTrace();
            results = "Failed";
            writeResults("Policy-Servicing", "PolicyNo", "results", "");
        }
    }
    @Test
    public void ChangeCollectionMethod() throws InterruptedException {
        String results="";
        try {
        String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","ChangeCollectionMethod");
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

        //get employee number fom excel


        //Click on EMPLOYEE NUMBER
        _driver.findElement(By.name("fcEmployeeNumber")).click();
        Delay(5);

        //Click on EMPLOYEE NUMBER
        _driver.findElement(By.name("fcEmployeeNumber")).sendKeys("98765654");
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

        writeResults("Policy-Servicing","PolicyNo","results","");
        //super.writeResultsToExcell(results, sheet, "ChangeCollectionMethod");
        } catch (Exception e) {

            e.printStackTrace();
            results = "Failed";
            writeResults("Policy-Servicing", "PolicyNo", "results", "");
        }
    }


    @Test
    public void ChangeCollectionNegative() throws InterruptedException {

        String results="";
        try {
        String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","ChangeCollectionNegative");;
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
        _driver.findElement(By.name("fcEmployeeNumber")).sendKeys("88977898");
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


        writeResults("Policy-Servicing","PolicyNo","results","");
        } catch (Exception e) {

            e.printStackTrace();
            results = "Failed";
            writeResults("Policy-Servicing", "PolicyNo", "results", "");
        }
    }

    @Test
    private void PostDatedDowngrade() throws InterruptedException {

        String results="";
        try {
        String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","PostDatedDowngrade");;
        clickOnMainMenu();
        Delay(2);
        policySearch(PolicyNo);
        String date = String.format("%1$s", LocalDateTime.now());
        String currentSumAssured = "";

        Delay(2);

        //SetproductName("PostDatedDowngrade");

        Delay(3);

        String contractPrem = _driver.findElement(By.xpath("//*[@id='CntContentsDiv9']/table/tbody/tr[2]/td[2]")).getText();

        //Click on user  component
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

        writeResults("Policy-Servicing","PolicyNo","results","");
        //super.writeResultsToExcell(results, sheet, "PostDatedDowngrade");

        } catch (Exception e) {

            e.printStackTrace();
            results = "Failed";
            writeResults("Policy-Servicing", "PolicyNo", "results", "");
        }
    }

    @Test
    private void PostDatedUpgrade() throws InterruptedException {

        String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","PostDatedUpgrade");;
        clickOnMainMenu();
        Delay(2);
        policySearch(PolicyNo);

        String results = "";

        String date = String.format("%1$s", LocalDateTime.now());

        String currentSumAssured = "";


        Delay(2);

        SetproductName("PostDatedUpgrade");

        Delay(3);

        String contractPrem = _driver.findElement(By.xpath("//*[@id='CntContentsDiv9']/table/tbody/tr[2]/td[2]")).getText();



        //Click on user  component
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
        Delay(4);
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

        writeResults("Policy-Servicing","PolicyNo","results","");

        //super.writeResultsToExcell(results, sheet, "PostDatedUpgrade");

    }
     @Test
    private void AddRole_NextMonth(String contractRef) throws InterruptedException {

         String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","AddRole_NextMonth");;
         clickOnMainMenu();
         Delay(2);
         policySearch(PolicyNo);
         String results = "";
         String title = "", first_name = "", surname = "", initials = "", dob = "", gender = "", id_number = "", relationship = "", Comm_date = "";
         Delay(2);

        SetproductName("AddRole_NextMonth");
        Delay(3);

        //get commencement date
        var commencementDate = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[6]/td[2]")).getText();

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


        ///get excel  values



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

        for (WebElement radio : rdos)
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
        var V_relationship = "";
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
        var value = "";
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
        //Roleplayer ID
        var LifeA_ID = _driver.findElement(By.xpath(" //*[@id='frmSubCbmre']/tbody/tr[4]/td[4]")).getText();

        //validation
        if (LifeA_ID.equals(id_number))
        {
            results = "Passed";

        }
        else

        {
            results = "Fail";

        }

         writeResults("Policy-Servicing","PolicyNo","results","");

        clickOnMainMenu();

        Delay(2);
        //click contract summary
        _driver.findElement(By.xpath(" //*[@id='t0_82']/table/tbody/tr/td[3]/a")).click();

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



         for (int i = 1; i < 30; i++)
         {
             String roles = _driver.findElement(By.xpath("//*[@id='frmCbmcc']/tbody/tr[6]/td[2]/select/option[{i.ToString()}]")).getText();

             String Ridno1 = roles.Split(" ")[roles.Split(" ").Length - 1].ToString();
             String ID1 = Ridno1.Substring(1, 13);

             if (ID1 == id_number)
             {


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


    }




    @Test
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



