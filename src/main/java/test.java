import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import utilities.functionUtilities;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;



public class test {
    public static void main(String[] argv) throws IOException {
        XSSFWorkbook workbook;
        XSSFSheet sheet;
        XSSFRow row;
        Properties prop = new Properties ();
        File path  = new File ("src/main/java/properties/config.properties");
        List<String> info = new ArrayList<> ();
        info.add ("t@gmail,com");
        info.add("NewPassword");
        System.out.println ("info size"+info.size ());


        try {
            FileInputStream fileInputStream = new FileInputStream (path);
            prop.load (fileInputStream);
        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println ("Something wrong with 'loadproperties' method in functionUti!");
        }
        String logpath = prop.getProperty ("loginExcelPath");
        String logexpath = "src/main/resources/ExcelFiles/LoginData.xlsx";
        try {
            FileInputStream fileInputStream = new FileInputStream ("src/main/resources/ExcelFiles/LoginData.xlsx");
            workbook = new XSSFWorkbook (fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
        if (workbook.getNumberOfSheets () < 1) {
            sheet = workbook.createSheet ("loginData");
            System.out.println ("Noe sheet in loginexcel");
        }
        sheet = workbook.getSheetAt (0);
        System.out.println ("row number: "+sheet.getLastRowNum ());
        if (sheet.getLastRowNum () < 0) row = sheet.createRow (0);
        row = sheet.createRow (sheet.getLastRowNum () + 1);
        for (int i = 1; i < 3; i++) {
            Cell cell = row.createCell (i-1);
            String value = info.get (i-1);
            cell.setCellValue((String) value);
            System.out.println (cell.getStringCellValue ());
            System.out.println ("Iteration no. "+i+ "-> Cell No.-"+ cell.getRowIndex ()+cell.getColumnIndex ());
        }
        FileOutputStream outputStream = new FileOutputStream (logexpath);
        workbook.write (outputStream);
        outputStream.close ();
    }
}

