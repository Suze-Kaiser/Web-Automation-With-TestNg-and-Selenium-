package Pages;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utilities.functionUtilities;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class checkOutPage extends functionUtilities {
    @FindBy(xpath = "//input[@id='createaccount']")
    WebElement createAccountCheckboxElement;
    @FindBy(xpath = "//input[@id='billing_first_name']")
    WebElement inputName;

    @FindBy(xpath = "//input[@id='billing_phone']")
    WebElement inputPhone;
    @FindBy(xpath = "//input[@id='billing_email']")
    WebElement inputEmail;
    @FindBy(xpath = "//select[@id='billing_state']")
    WebElement districtDropOption;
    @FindBy(xpath = "//select[@id='billing_area']")
    WebElement areaDropOption;
    @FindBy(xpath = "//textarea[@id='billing_address_1']")
    WebElement inputAddress;

    @FindBy(xpath = "//button[@id='place_order']")
    WebElement orderPlacementFinalBTn;

    @FindBy(xpath = "//ul[@class='woocommerce-error']")
    WebElement errorMessage;

    @FindBy(xpath = "//input[@id='account_password'])[1]")
    WebElement passField;

    @FindBy(xpath = "//input[@id='payment_method_bkash']")
    WebElement bkashRBtn;
    Properties prop;
    int rowIndexG;

    boolean createAccountFlag;


    public checkOutPage () {
        PageFactory.initElements (udriver, this);
    }


    public List<String> getDataFromExcel (int rowIndex) {
        Workbook workbook;
        Row row;
        rowIndexG = rowIndex;
        prop = loadProperties ();
        List<String> regData = new ArrayList<> ();
        String regExcel = prop.getProperty ("regExcelPath");
        workbook = loadDataFromExcel (regExcel);
        Sheet sheet = workbook.getSheetAt (0);
//        if (headerFlag) {
//            row = sheet.getRow (1);
//        } else {
//            row = sheet.getRow (0);
//        }
        row = sheet.getRow (rowIndex);
        System.out.println ("CellNumber: " + row.getLastCellNum ());
        for (Cell cell : row) {
            if ((cell == null) || (cell.getCellType () == CellType.BLANK)) {
                regData.add ("NullFromExcel");
            }
            regData.add (String.valueOf (cell));

        }
        return regData;
    }

    public void fillingAllInputField (int rowIndex) throws InterruptedException {
        List<String> info = getDataFromExcel (rowIndex);
        System.out.print (info);

        try {
            inputName.sendKeys (info.get (0));
            inputPhone.sendKeys (info.get (1));
            inputEmail.sendKeys (info.get (3));
            inputAddress.sendKeys (info.get (2));
            impWait (3);

        } catch (NullPointerException e) {
            System.out.println ("\nRunning From Catch Block!\n");
            PageFactory.initElements (udriver, this);
            inputName.sendKeys (info.get (0));
            inputPhone.sendKeys (info.get (1));
            inputEmail.sendKeys (info.get (3));
            inputAddress.sendKeys (info.get (2));
            impWait (3);

        }
        if(createAccountFlag && (find(By.xpath ("//input[@id='account_password']")).isDisplayed ())){
            System.out.println ("password firld displyed");
            find(By.xpath ("//input[@id='account_password']")).sendKeys (info.get (4));
        }
        Select disDrop = new Select (districtDropOption);
        disDrop.selectByVisibleText ("Sylhet");
        impWait (2);


        Select areaDrop = new Select (areaDropOption);
        areaDrop.selectByValue ("284");

        impWait (3);
        bkashRBtn.click ();
    }

    public void clickOnFinalOrder() throws IOException {
        orderPlacementFinalBTn.click ();
        if(createAccountFlag) writeDataToLoginExcel ();
    }

    public void setNewAccountCreationFlag (boolean flag) {
        if (!(find (By.xpath ("//input[@id='createaccount']")).isSelected ()) && flag) {
            find (By.xpath ("//input[@id='createaccount']")).click ();
        }
        createAccountFlag =flag;

    }

    public void writeDataToLoginExcel () throws IOException {
        List<String> info = getDataFromExcel (rowIndexG);
        Workbook workbook;
        Sheet sheet;
        Row row;
        prop = loadProperties ();
        String logExcel = prop.getProperty ("loginExcelPath");
        workbook = loadDataFromExcel (logExcel);
        if (workbook.getNumberOfSheets () < 1) {
            sheet = workbook.createSheet ("loginData");
        }
        sheet = workbook.getSheetAt (0);
        if (sheet.getLastRowNum () < 0) row = sheet.createRow (0);
        row = sheet.createRow (sheet.getLastRowNum () + 1);
        for (int i = 0; i< 2; i++) {
            Cell cell = row.createCell (i);
            cell.setCellValue (info.get (i + 3));
        }
        FileOutputStream outputStream  = new FileOutputStream (prop.getProperty ("loginExcelPath"));
        workbook.write (outputStream);
        outputStream.close ();

    }


}


