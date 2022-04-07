package Testbase;
import org.apache.commons.io.FileUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Base {
    private ChromeOptions _chromeOptions;
    static WebDriver _driver;
    private String username;
    private String password;
    private String _screenShotFolder;
    private String _connStr;
    private String target_url;

    @Test
    public void startBrowser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Code\\bin\\chromeDriver.exe");
        _driver = new ChromeDriver();
        _screenShotFolder = "C:\\Users\\G992127\\Documents\\GitHub\\ILR_Automation_TestSuite\\src\\test\\java\\";
        _screenShotFolder.concat(screenShotDailyFolderName() + "\\");
        //Creating a File object
        File file = new File(_screenShotFolder);
        //Creating the directory
        file.mkdir();

    }

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

    public WebDriver siteConnection() {
        startBrowser();
        target_url = "http://ilr-tst.safrican.co.za/web/wspd_cgi.sh/WService=wsb_ilrtst/run.w";
        username = "SKA008PPE";//
        password = "Aw123456";
        _driver.get(target_url);

        return _driver;

    }
    @Test
    public static void getPolicyNoFromExcel() throws IOException {
        FileInputStream file = new FileInputStream(new File("C:\\Users\\G992127\\Documents\\GitHub\\ILR_Automation_TestSuite\\src\\test\\java\\TestData.xlsx"));
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        Map<Integer, List<String>> data = new HashMap<>();
        int i = 0;
        for (Row row : sheet) {
            data.put(i, new ArrayList<String>());
            for (Cell cell : row) {
                switch (cell.getCellType()) {

                    default:
                        data.get(new Integer(i)).add(" ");
                }
            }
            i++;
        }
    }


}