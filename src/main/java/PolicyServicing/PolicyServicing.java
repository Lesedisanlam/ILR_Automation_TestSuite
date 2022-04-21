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
        //super.writeResultsToExcell(results, sheet, "ReInstate");
    }
    @Test(dependsOnMethods = {"ReInstate"},alwaysRun = true)
    public void CancelPolicy() throws InterruptedException  {


        String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","CancelPolicy");
        clickOnMainMenu();
        Delay(2);
        policySearch(PolicyNo);

        String results = "";
        Delay(2);
        String   commDate = _driver.findElement(By.xpath("//*[@id='CntContentsDiv8']/table/tbody/tr[6]/td[2]")).getText();
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

        if (newStatus.equals("Cancelled") || newStatus.equals("Not Taken Up"))
        {
            results = "Passed";
        }
        else
        {
            results = "Failed";
        }
        //super.writeResultsToExcell(results, sheet, "CancelPolicy");
        writeResults("Policy-Servicing","PolicyNo","results","");

    }
    @Test(dependsOnMethods = {"CancelPolicy"},alwaysRun = true)
    public void ChangeCollectionMethod() throws InterruptedException {


        String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","ChangeCollectionMethod");
        clickOnMainMenu();
        Delay(2);
        policySearch(PolicyNo);

        String test_url_1 = "http://ilr-int.safrican.co.za/web/wspd_cgi.sh/WService=wsb_ilrint/run.w?";
        String test_url_2_title = "MIP - Sanlam ARL - Warpspeed Lookup Window";
        JavaScriptExecutor js = (JavaScriptExecutor) _driver;

        String results = "";

        String date = String.format("%1$s", LocalDateTime.now());

        String employee_number1 = "";

        policySearch(PolicyNo);


        Delay(4);

        SetproductName("ChangeCollectionMethod");

        Delay(3);


        //click on policy payer
        _driver.findElement(By.name("fcRoleEntityLink3")).click();


        WebElement policyOptionElement = _driver.findElement(By.xpath("//*[@id='m0i0o1']"));


        //Creating object of an Actions class
        Actions action = new Actions(_driver);


        //Performing the mouse hover action on the target element.
        action.moveToElement(policyOptionElement).perform();


        //Click on options
        _driver.findElement(By.xpath("//*[@id='m0t0']/tbody/tr[3]/td/div/div[3]/a/img")).click();


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

        String MainWindow=_driver.getWindowHandle();
        // To handle all new opened window.
        Set<String> s1=_driver.getWindowHandles();
        Iterator<String> i1=s1.iterator();
        while(i1.hasNext())
        {
            String ChildWindow  =i1.next();
            if(!MainWindow.equalsIgnoreCase(ChildWindow))
        {

        // Switching to Child window
        _driver.switchTo().window(ChildWindow);
        //Search employee
            _driver.findElement(By.name("fcEmployerButton")).click(); Delay(5);
            // Closing the Child Window.
            _driver.close(); }
        }
        //Switching to Parent window i.e Main Window.
        _driver.switchTo().window(MainWindow);
        Delay(3);

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

    }
    @Test(dependsOnMethods = {"ChangeCollectionMethod"},alwaysRun = true)
    public void ChangeCollectionNegative() throws InterruptedException {

        String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","ChangeCollectionNegative");;
        clickOnMainMenu();

        Delay(2);
        policySearch(PolicyNo);

        String test_url_1 = "http://ilr-int.safrican.co.za/web/wspd_cgi.sh/WService=wsb_ilrint/run.w?";
        String test_url_2_title = "MIP - Sanlam ARL - Warpspeed Lookup Window";
        JavaScriptExecutor js = (JavaScriptExecutor)_driver;

        String results = "";

        String date = String.format("%1$s", LocalDateTime.now());

        String employee_number2 = "";

        policySearch(PolicyNo);
        Delay(3);

        SetproductName("ChangeCollectionNegative");

        Delay(3);



        //click on policy payer
        _driver.findElement(By.name("fcRoleEntityLink3")).click();



        WebElement ele = _driver.findElement(By.xpath("//*[@id='m0i0o1']"));

        //Creating object of an Actions class
        Actions action = new Actions(_driver);

        //Performing the mouse hover action on the target element.
        action.moveToElement(ele).perform();


        //Click on options
        _driver.findElement(By.xpath("//*[@id='m0t0']/tbody/tr[3]/td/div/div[3]/a/img")).click();


        Select oSelect8 = new Select(_driver.findElement(By.name("fcCollectionMethod")));

        oSelect8.selectByValue("108978.19");
        Delay(5);
        //ExcelData

        //Click on EMPLOYEE NUMBER
        _driver.findElement(By.name("fcEmployeeNumber")).click();
        Delay(5);

        //Click on EMPLOYEE NUMBER
        _driver.findElement(By.name("fcEmployeeNumber")).sendKeys("88989898");
        Delay(5);


        //Search employee
        _driver.findElement(By.name("fcEmployerButton")).click();

        //get window handle////////////////////////////////////////////////



        //Search employee
        _driver.findElement(By.xpath("//*[@id='lkpResultsTable']/tbody/tr[17]")).click();
        Delay(5);


        /* Return to the window with handle = 0
        _driver.switchTo().window(_driver.getWindowHandles();
        Delay(5);
        */



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
    }

    @Test(dependsOnMethods = {"ChangeCollectionNegative"},alwaysRun = true)
    private void PostDatedDowngrade() throws InterruptedException {


        String PolicyNo = getPolicyNoFromExcel("Policy-Servicing","PostDatedDowngrade");;

        clickOnMainMenu();
        Delay(2);
        policySearch(PolicyNo);

        String results = "";
        String date = String.format("%1$s", LocalDateTime.now());
        String currentSumAssured = "";

        Delay(2);

        SetproductName("PostDatedDowngrade");

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

    }

    @Test(dependsOnMethods = {"PostDatedDowngrade"},alwaysRun = true)
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



