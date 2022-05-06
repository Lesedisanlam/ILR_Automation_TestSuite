package Testbase;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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

public class Base {
    private ChromeOptions _chromeOptions;
    static WebDriver _driver;
    private String username;
    private String password;
    private String _screenShotFolder;
    private String _connStr;
    private String target_url;
    String result_path, gCODE;


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
        System.setProperty("webdriver.chrome.driver", "C:\\Code\\bin\\chromeDriver.exe");
        _driver = new ChromeDriver();
        _driver.get("http://ilr-tst.safrican.co.za/web/wspd_cgi.sh/WService=wsb_ilrtst/run.w");
        username = "SKA008PPE";
        password = "SKA008PPE/c";
        gCODE = "G992107";
        //  _driver.get(target_url);
        _driver.manage().window().maximize();
        Delay(2);
        _driver.findElement(By.name("fcUserCode")).sendKeys(username);
        Delay(5);
        _driver.findElement(By.name("fcPassword")).click();
        _driver.findElement(By.name("fcPassword")).sendKeys(password);
        Delay(3);
        _driver.findElement(By.name("btnLogin")).click();
        Delay(2);

    }

    public Dictionary getDataFromSheet(String ws) {
        Dictionary data = new Hashtable();
        ArrayList<String> colContents = new ArrayList<String>();
        ArrayList<String> headers = new ArrayList<String>();
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


    public double getPremiumFromRateTable(double age, String rolePlayer, String sumAsured, String product) {
        double premium = 0.0;
        String cover = rolePlayer + "_" + sumAsured;
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\" + gCODE + "\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestData.xlsx"));
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
            result_path = "C:\\Users\\" + gCODE + "\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestResults\\TestResults" + now.getHour() + "_" + now.getMinute() + "_" + now.getYear() + "_" + now.getDayOfMonth() + "_" + now.getDayOfMonth() + ".xlsx";

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

    public String getScreenShot(String testCaseName,WebDriver driver) throws IOException {


        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destinationfile = System.getProperty("user.dir")+"\\Reports\\"+testCaseName+".png";

        FileUtils.copyFile(source,new File(destinationfile));
        return destinationfile;


    }
}
