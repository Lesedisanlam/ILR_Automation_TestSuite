package src.main.java.NewBusiness;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private ChromeOptions _chromeOptions;
    public WebDriver _driver;
    private String _salesAppUserName;

    private String _salesAppPassword;

    private String _salesAppPin;

    public String _screenShotFolder;

    public String _testDataUrl = "C:\\Users\\G992107\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestData\\NewBusiness\\TestData.xlsx";
    public String _gcode;
    String result_path;
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Code\\bin\\chromeDriver.exe");
        _gcode = "G992107";
        _testDataUrl = "C:\\Users\\"+_gcode+"\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestData\\NewBusiness\\TestData.xlsx";
        _screenShotFolder = "C:\\Users\\"+_gcode+"\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestResults\\NewBusiness\\Failed_Scenarios\\" +screenShotDailyFolderName() +"\\";
        File theDir = new File(_screenShotFolder);
        if (!theDir.exists()){
            theDir.mkdirs();
        }
    }

    private String screenShotDailyFolderName()

    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);

    }

    public void takeSnapShot(String file_name) throws Exception{

        //Convert web driver object to TakeScreenshot

        TakesScreenshot scrShot = ((TakesScreenshot)_driver);

        //Call getScreenshotAs method to create image file

        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination

        File DestFile=new File(_screenShotFolder+file_name+".png");

        //Copy file at destination

        FileUtils.copyFile(SrcFile, DestFile);

    }

    public void salesAppSiteConnection()
    {
        System.setProperty("webdriver.chrome.driver", "C:\\Code\\bin\\chromeDriver.exe");
        _chromeOptions = new ChromeOptions();
        _chromeOptions.addArguments("--incognito");
        _chromeOptions.addArguments("--ignore-certificate-errors");
        _driver = new ChromeDriver(_chromeOptions);
        _driver.get("https://uat-fe.safricansalesapp.net/advisor/dashboard/");
        try
        {
            _salesAppUserName = "G992092";//TODO add your user name and password
            _salesAppPassword = "SCHggy024588";
            _salesAppPin = "880808";
            TimeUnit.SECONDS.sleep(2);
            _driver.manage().window().maximize();
            TimeUnit.SECONDS.sleep(2);
            _driver.manage().window().maximize();

            _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/article/div/div[2]/button")).click();
            TimeUnit.SECONDS.sleep(2);

            _driver.switchTo().frame("form-frame");
            TimeUnit.SECONDS.sleep(4);

            WebElement loginTextBox = _driver.findElement(By.name("username"));
            TimeUnit.SECONDS.sleep(4);

            WebElement passwordTextBox = _driver.findElement(By.name("password"));
            TimeUnit.SECONDS.sleep(3);
            WebElement loginBtn = _driver.findElement(By.name("action"));
            TimeUnit.SECONDS.sleep(3);
            loginTextBox.sendKeys(_salesAppUserName);
            TimeUnit.SECONDS.sleep(4);
            passwordTextBox.sendKeys(_salesAppPassword);
            TimeUnit.SECONDS.sleep(3);
            loginBtn.click();
            TimeUnit.SECONDS.sleep(3);
            //_driver.SwitchTo().DefaultContent();

            TimeUnit.SECONDS.sleep(10);

            //create pin
            var pin1 = _driver.findElement(By.name("pin"));
            pin1.sendKeys(_salesAppPin);
            TimeUnit.SECONDS.sleep(3);
            _driver.findElement(By.name("pinConfirm")).sendKeys(_salesAppPin);
            TimeUnit.SECONDS.sleep(4);
            WebElement create = _driver.findElement(By.xpath("//*[@id='gatsby-focus-wrapper']/div/section/form/button"));
            TimeUnit.SECONDS.sleep(2);
            create.click();
            TimeUnit.SECONDS.sleep(4);

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }



    }

    public void testGetPoliciyHolderDta(){
        //getPolicyHolderDetails("1",false);
    }
    @Test
    public void testGetData(){
        Hashtable<String,ArrayList<Dictionary>> dta = getPolicyData("1",false);
        System.out.println((dta.get("PolicyHolder_Details")));
    }
    public Hashtable<String,ArrayList<Dictionary>> getPolicyData(String scenario_id, Boolean phd)
    {
        Hashtable<String,ArrayList<Dictionary>> policyHolderData = new Hashtable<String,ArrayList<Dictionary>>();

        //Sheets in the test data file that we want to access to extract Policy holder data
        ArrayList<String> sheets = new ArrayList<String>();

        if(phd)
        {
            sheets.add("Affordability_Check");
            sheets.add("BankDetails");
            sheets.add("PhysicalAddress");
            sheets.add("PolicyHolder_Details");
        }
        else{

            sheets.add("Extended");
            sheets.add("Parents");
            sheets.add("Children");
            sheets.add("spouse");
            sheets.add("PolicyPayer");
            sheets.add("Beneficiaries");
        }

        try {
                FileInputStream file = new FileInputStream(new File(_testDataUrl));
                //Create Workbook instance holding reference to .xlsx file
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                //Get first/desired sheet from the workbook

                for (String ws:sheets) {
                    XSSFSheet sheet = workbook.getSheet(ws);
                    ArrayList<Dictionary> roles = new ArrayList<Dictionary>();
                    //Iterate through each rows one by one
                    int lastRwNum = sheet.getPhysicalNumberOfRows();
                    ArrayList<String> hearders = new ArrayList<String>();
                    for (int i = 0; i < lastRwNum; i++) {
                        Row rw = sheet.getRow(i);
                        int lastColNum = rw.getPhysicalNumberOfCells();
                        if(i==0){
                            for (int j = 0; j < lastColNum; j++) {
                                hearders.add(rw.getCell(j).getStringCellValue());
                            }
                        }
                        else{
                            //get id from excel
                            boolean checker;
                            if(rw.getCell(0).getCellType().equals(CellType.STRING))
                            {

                                checker = rw.getCell(0).getStringCellValue().trim().equalsIgnoreCase(scenario_id);

                            }else
                            {
                                checker = (int)rw.getCell(0).getNumericCellValue() == Integer.parseInt(scenario_id);
                            }

                            if(checker)
                            {
                                Dictionary rowData = new Hashtable();
                                for (int j = 0; j < lastColNum; j++) {
                                    String key = hearders.get(j);
                                    String val ="";
                                    if(rw.getCell(j).getCellType().equals(CellType.STRING))
                                    {
                                        val = rw.getCell(j).getStringCellValue();

                                    }else
                                    {
                                        val = ""+(int)(rw.getCell(j).getNumericCellValue());
                                    }
                                    rowData.put(key,val);
                                }
                                roles.add(rowData);
                            }
                        }

                    }
                    policyHolderData.put(ws,roles);
                }
                file.close();


            } catch (Exception e) {
                e.printStackTrace();
            }
        return policyHolderData;
    }

    public double getPremiumFromRateTable(double age, String rolePlayer, String sumAsured, String product) {
        double premium = 0.0;
        String cover = rolePlayer + "_" + sumAsured;
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\"+_gcode+"\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestData\\PolicyServicing\\TestData.xlsx"));
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet(product);

            //Iterate through each rows one by one
            Iterator<Row> rowIterator = sheet.iterator();
            int coverColNo = 0;
            int ageRowNo = 0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Iterator<Cell> cellIterator = row.cellIterator();

                String band;
                double exclAge;
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    int colNo = cell.getColumnIndex();
                    int rowNo = cell.getRowIndex();

                    if (rowNo < 1 && colNo > 0) {
                        band = cell.getStringCellValue();
                        if (band.contains(cover)) {
                            coverColNo = cell.getColumnIndex();
                            break;
                        }
                    }
                    if (rowNo > 0 && colNo < 1) {
                        exclAge = cell.getNumericCellValue();
                        if (exclAge == age) {
                            ageRowNo = cell.getRowIndex();
                            break;
                        }
                    }


                }
            }
            premium = sheet.getRow(ageRowNo).getCell(coverColNo).getNumericCellValue();
            file.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return premium;
    }
    public void writeResults(String ws, String function, String results, String commnents) {
        try {
            FileInputStream inputxls = new FileInputStream("C:\\Users\\" + _gcode + "\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestData.xlsx");
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
            FileInputStream myxls = new FileInputStream(result_path);
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
            FileOutputStream output_file = new FileOutputStream(new File(result_path));
            //write changes
            studentsSheet.write(output_file);
            output_file.close();
            System.out.println("has successfully written");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTesResultFile(){
        try
        {
            LocalDateTime now = LocalDateTime.now();
            result_path = "C:\\Users\\" + _gcode + "\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestResults\\TestResults" + now.getHour() + "_" + now.getMinute() + "_" + now.getYear() + "_" + now.getDayOfMonth() + "_" + now.getDayOfMonth() + ".xlsx";

            Workbook workbook = new XSSFWorkbook();

            Sheet sheet = workbook.createSheet("Policy-Servicing");
            Row header = sheet.createRow(0);

            CellStyle headerStyle = workbook.createCellStyle();
            headerStyle.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
            headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            XSSFFont font = ((XSSFWorkbook) workbook).createFont();
            font.setFontName("Arial");


            font.setFontHeightInPoints((short) 16);
            font.setBold(true);
            font.setColor(IndexedColors.WHITE.getIndex());
            headerStyle.setFont(font);

            Cell headerCell = header.createCell(0);
            headerCell.setCellValue("Policy No");
            headerCell.setCellStyle(headerStyle);
            headerCell = header.createCell(1);
            headerCell.setCellValue("Function");
            headerCell.setCellStyle(headerStyle);
            headerCell = header.createCell(2);
            headerCell.setCellValue("Scenario");
            headerCell.setCellStyle(headerStyle);
            headerCell = header.createCell(3);
            headerCell.setCellValue("Scenario Detail");
            headerCell.setCellStyle(headerStyle);
            headerCell = header.createCell(4);
            headerCell.setCellValue("Product");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(5);
            headerCell.setCellValue("Expected Results");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(6);
            headerCell.setCellValue("Actual Results");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(7);
            headerCell.setCellValue("Test Results");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(8);
            headerCell.setCellValue("Comments");
            headerCell.setCellStyle(headerStyle);

            headerCell = header.createCell(9);
            headerCell.setCellValue("Test Date");
            headerCell.setCellStyle(headerStyle);

            FileOutputStream outputStream = new FileOutputStream(result_path);
            workbook.write(outputStream);
            workbook.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
