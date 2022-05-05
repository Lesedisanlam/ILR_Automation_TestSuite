package reports;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebElement;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;

public class NBTestbase {
    private ChromeOptions _chromeOptions;
    static WebDriver _driver;
    private String _userName;
    private String _password;

    private String _pin;
    private String _screenShotFolder;
    private String _connStr;
    private String target_url;
    String path, gCODE;


    private String screenShotDailyFolderName() {
        LocalDateTime dateObj = LocalDateTime.now();
        DateTimeFormatter dateFormatObj = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = dateObj.format(dateFormatObj);
        return formattedDate;
    }

    public void TakeScreenshot(WebDriver driver, String filePath, String fileName) {

        //check if dir exists
        Path tempDirectory = null;
        try {
            tempDirectory = Files.createTempDirectory(filePath);
            if (!Files.exists(tempDirectory)) {
                File file = new File(filePath);
                //Creating the directory
                file.mkdir();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        //Move image file to new destination
        fileName = fileName + screenShotTime() + ".png";


        File DestFile = new File(filePath + fileName);

        //Copy file at destination

        try {
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String screenShotTime() {

        int hour = LocalDateTime.now().getHour();
        int min = LocalDateTime.now().getMinute();
        return hour + "_" + min;

    }


    public void siteConnection() throws InterruptedException {
        try
        {

            _userName = "G992127";//TODO add your user name and password

            _password = "P@$$word47";

            _pin = "119547";

            _driver.manage().window().maximize();

            Delay(2);

            Delay(2);
            _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/div/div[2]/button")).click();
            Delay(4);
            _driver.switchTo().frame("form-frame");
            Delay(2);
            WebElement loginTextBox = _driver.findElement(By.name("username"));


            Delay(3);
            WebElement passwordTextBox = _driver.findElement(By.name("password"));
            Delay(3);
            WebElement loginBtn = _driver.findElement(By.name("action"));
            Delay(3);
            loginTextBox.sendKeys(_userName);
            Delay(3);
            passwordTextBox.sendKeys(_password);
            Delay(3);
            loginBtn.click();
            Delay(3);
            _driver.switchTo().defaultContent();

            Delay(15);

            //create pin
            _driver.findElement(By.name("pin")).sendKeys(_pin);
            Delay(3);
            _driver.findElement(By.name("pinConfirm")).sendKeys(_pin);
            Delay(3);
            WebElement create = _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div/section/form/button"));
            create.click();
            Delay(2);



        }
        catch (Exception ex) {
            throw ex;
        }



    }



    public Dictionary getDataFromSheet(String ws) {
        Dictionary data = new Hashtable();
        ArrayList<String> colContents = new ArrayList<String>();
        var sheets = new ArrayList<String>();
        sheets.add("PolicyHolder_Details");
        sheets.add("Extended");
        sheets.add("Parents");
        sheets.add("Children");
        sheets.add("spouse");
        sheets.add("PolicyPayer");
        sheets.add("Beneficiaries");
        ArrayList<String> headers = new ArrayList<String>();

        HashMap < String, ArrayList<HashMap<String,String>>> roleplayersData = new HashMap<String, ArrayList<HashMap<String,String>>>();
        var sheets = new ArrayList<String>();
        sheets.add("PolicyHolder_Details");
        sheets.add("Extended");
        sheets.add("Parents");
        sheets.add("Children");
        sheets.add("spouse");
        sheets.add("PolicyPayer");
        sheets.add("Beneficiaries");

        for (var sheet : sheets)
        {
            ArrayList<HashMap<String, String>> roleplyers = new ArrayList<HashMap<String, String>>();
            ArrayList<String> keys = new ArrayList<String>();
        try {

            FileInputStream inputxls = new FileInputStream("C:\\Users\\" + gCODE + "\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestData.xlsx");
            XSSFWorkbook testDataSheet = new XSSFWorkbook(inputxls);
            XSSFSheet testDataworksheet = testDataSheet.getSheet(ws);


            //looop through the rows
            for (int i = 0; i <= testDataworksheet.getLastRowNum(); i++) {
                Row rw = testDataworksheet.getRow(i);
                if (rw != null && i == 0) {
                    for (Cell cell : rw) {
                        headers.add(cell.getStringCellValue());
                    }
                } else if (rw != null && i == 1) {
                    for (Cell cell : rw) {
                        colContents.add(cell.getStringCellValue());

                    }
                    break;
                } else {
                    break;
                }
            }
            inputxls.close();
            //add data to dictionary
            if (!headers.isEmpty() && !colContents.isEmpty()) {
                for (int i = 0; i < headers.size(); i++) {
                    data.put(headers.get(i), colContents.get(i));
                }
            }
        } catch (Exception ex) {

        }

        return data;
    }

    public String getPolicyNoFromExcel(String ws, String func) {
        String policyNo = "";

        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\" + gCODE + "\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestData.xlsx"));
            String fun = func;
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(ws);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int rw = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();
                if (rw != 0) {
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        int colNo = cell.getColumnIndex();
                        if (colNo == 0) {
                            policyNo = cell.getStringCellValue();
                        }
                        if (colNo == 1) {
                            String exlFunc = cell.getStringCellValue();
                            if (exlFunc.contains(fun)) {
                                return policyNo;
                            }

                        }

                    }

                }
                rw++;
            }
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(policyNo);

        return policyNo;

    }

    public void Delay(int delaySeconds) throws InterruptedException {

        Thread.sleep(delaySeconds * 1000);

    }
    import java.util.*;

    public final HashMap<String, ArrayList<HashMap<String, String>>> getRolePlayers(String id)
    {

        HashMap < String, ArrayList<HashMap<String,String>>> roleplayersData = new HashMap<String, ArrayList<HashMap<String,String>>>();
        var sheets = new ArrayList<String>();
        sheets.add("PolicyHolder_Details");
        sheets.add("Extended");
        sheets.add("Parents");
        sheets.add("Children");
        sheets.add("spouse");
        sheets.add("PolicyPayer");
        sheets.add("Beneficiaries");

        for (var sheet : sheets)
        {
            ArrayList<HashMap<String, String>> roleplyers = new ArrayList<HashMap<String, String>>();
            ArrayList<String> keys = new ArrayList<String>();
            //Loop through every row of that sheet and get data of all the players with the scenario id given
            _test_data_connString = "Provider= Microsoft.ACE.OLEDB.12.0;" + "Data Source=C:/Users/G992127/Documents/GitHub/ILR_TestSuite/ILR_TestSuite/New Business/SalesAppBase/TestData.xlsx" + ";Extended Properties='Excel 8.0;HDR=NO'";
            try (OleDbConnection conn = new OleDbConnection(_test_data_connString))
            {
                try
                {
                    // Open connection
                    conn.Open();
                    String cmdQuery = "SELECT * FROM [" + sheet + "$]";

                    OleDbCommand cmd = new OleDbCommand(cmdQuery, conn);

                    // Create new OleDbDataAdapter
                    OleDbDataAdapter oleda = new OleDbDataAdapter();

                    oleda.SelectCommand = cmd;

                    // Create a DataSet which will hold the data extracted from the worksheet.
                    DataSet ds = new DataSet();

                    // Fill the DataSet from the data extracted from the worksheet.
                    oleda.Fill(ds, "PolicyHolderData");

                    var count = 0;

                    int noColumn = ds.Tables[0].Columns.Count;
                    //Loop throgh every row
                    for (var row : ds.Tables[0].DefaultView)
                    {
                        if (count == 0)
                        {
                            for (int i = 1; i < noColumn; i++)
                            {

                                keys.add(((System.Data.DataRowView)row).Row.ItemArray[i].toString());

                            }

                        }
                        else
                        {
                            var scenarioID = ((System.Data.DataRowView)row).Row.ItemArray[0].toString();


                            if (id.equals(scenarioID))
                            {

                                var column = 1;
                                HashMap<String, String> rowData = new HashMap<String, String>();

                                for (String key : keys)
                                {
                                    rowData.put(key, ((System.Data.DataRowView)row).Row.ItemArray[column].toString());
                                    column++;
                                }
                                roleplyers.add(rowData);

                            }
                        }
                        count++;

                    }
                }
                catch (RuntimeException ex)
                {
                    throw ex;
                }
                finally
                {
                    conn.Close();


                    conn.Dispose();
                }
            }
            roleplayersData.put(sheet, roleplyers);
        }

        return roleplayersData;

    }
    public final HashMap<String, String> getPolicyHolderDetails(String scenario_id)
    {
        _test_data_connString = "Provider= Microsoft.ACE.OLEDB.12.0;" + "Data Source=C:/Users/G992127/Documents/GitHub/ILR_TestSuite/ILR_TestSuite/New Business/SalesAppBase/TestData.xlsx" + ";Extended Properties='Excel 8.0;HDR=NO'";
        //Variables to store policy holder data
        HashMap<String, String> policyHolderData = new HashMap<String, String>();


        //Sheets in the test data file that we want to access to extract Policy holder data
        var sheets = new ArrayList<String>();
        sheets.add("PolicyHolder_Details");
        sheets.add("Affordability_Check");
        sheets.add("BankDetails");
        sheets.add("PhysicalAddress");
        //Extracting data from every sheet

        for (var sheet : sheets)
        {

            try (OleDbConnection conn = new OleDbConnection(_test_data_connString))
            {
                try
                {
                    // Open connection
                    conn.Open();
                    String cmdQuery = "SELECT * FROM [" + sheet + "$]";

                    OleDbCommand cmd = new OleDbCommand(cmdQuery, conn);

                    // Create new OleDbDataAdapter
                    OleDbDataAdapter oleda = new OleDbDataAdapter();

                    oleda.SelectCommand = cmd;

                    // Create a DataSet which will hold the data extracted from the worksheet.
                    DataSet ds = new DataSet();

                    // Fill the DataSet from the data extracted from the worksheet.
                    oleda.Fill(ds, "PolicyHolderData");

                    var count = 0;
                    ArrayList<String> keys = new ArrayList<String>();
                    int noColumn = ds.Tables[0].Columns.Count;
                    for (var row : ds.Tables[0].DefaultView)
                    {

                        if (count == 0)
                        {
                            for (int i = 1; i < noColumn ; i++)
                            {

                                keys.add(((System.Data.DataRowView)row).Row.ItemArray[i].toString());

                            }

                        }
                        else
                        {
                            var scenarioID = ((System.Data.DataRowView)row).Row.ItemArray[0].toString();


                            if (scenario_id.equals(scenarioID))
                            {

                                var column = 1;


                                for (String key : keys)
                                {
                                    policyHolderData.put(key, ((System.Data.DataRowView)row).Row.ItemArray[column].toString());
                                    column++;
                                }

                            }
                        }
                        count++;
                    }
                }
                catch (RuntimeException ex)
                {
                    throw ex;
                }
                finally
                {
                    conn.Close();


                    conn.Dispose();
                }
            }

        }

        return policyHolderData;
    }

    public final void DisconnectBrowser()

    {

        if (_driver != null)
        {

            _driver.Quit();
        }

    }




    public void writeResults(String ws, String function, String results, String commnents) {
        try {
            FileInputStream inputxls = new FileInputStream("C:\\Users\\" + gCODE + "\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestData.xlsx");
            XSSFWorkbook testDataSheet = new XSSFWorkbook(inputxls);
            XSSFSheet testDataworksheet = testDataSheet.getSheet(ws);
            ArrayList<String> colContents = new ArrayList<String>();
            //looop through the rows
            int rwNum = testDataworksheet.getPhysicalNumberOfRows();
            for (int i = 1; i < testDataworksheet.getLastRowNum(); i++) {
                Row rw = testDataworksheet.getRow(i);
                if (rw != null && rw.getCell(1).getStringCellValue().contains(function)) {
                    int colNo = rw.getPhysicalNumberOfCells() - 3;
                    for (int j = 0; j < colNo; j++) {
                        colContents.add(rw.getCell(j).getStringCellValue());
                    }
                    break;
                }
            }
            inputxls.close();
            FileInputStream myxls = new FileInputStream("C:\\Users\\" + gCODE + "\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestResult.xlsx");
            XSSFWorkbook studentsSheet = new XSSFWorkbook(myxls);
            XSSFSheet worksheet = studentsSheet.getSheet(ws);
            //Append the datalist with test results
            colContents.add(results);
            colContents.add(commnents);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            colContents.add(dtf.format(now));
            int lastRow = worksheet.getLastRowNum();
            System.out.println(lastRow);
            Row row = worksheet.createRow(++lastRow);
            if (!colContents.isEmpty()) {
                for (int i = 0; i < colContents.size(); i++) {
                    row.createCell(i).setCellValue(colContents.get(i));
                }
            }
            myxls.close();
            FileOutputStream output_file = new FileOutputStream(new File("C:\\Users\\" + gCODE + "\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestResults\\TestResults" + now.getHour() + "_" + now.getMinute() + "_" + now.getYear() + "_" + now.getDayOfMonth() + "_" + now.getDayOfMonth() + ".xlsx"));
            //write changes
            studentsSheet.write(output_file);
            output_file.close();
            System.out.println("has successfully written");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
