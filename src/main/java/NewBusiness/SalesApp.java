package src.main.java.NewBusiness;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.concurrent.TimeUnit;

public class SalesApp {
    @Test
    public void testRun() throws InterruptedException {
        runTest();
    }
    public void runTest() throws InterruptedException {
        TimeUnit.SECONDS.sleep(15);
        try {
            FileInputStream file = new FileInputStream(new File("C:\\Users\\G992127\\Documents\\GitHub\\ILR_Automation_TestSuite\\TestData\\NewBusiness\\TestData.xlsx"));
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheet("Scenarios");
            ArrayList<Dictionary> roles = new ArrayList<Dictionary>();
            //Iterate through each rows one by one
            int lastRwNum = sheet.getPhysicalNumberOfRows();
            for (int i = 1; i < lastRwNum; i++)
            {
                //get scenario id
                Row rw = sheet.getRow(i);
                String id = rw.getCell(0).getStringCellValue();
                System.out.println(id);
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
