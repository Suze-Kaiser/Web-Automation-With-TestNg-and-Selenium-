package utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class functionUtilities {
    public static WebDriver udriver;
    protected Properties prop;

    public void setdriver (WebDriver driver) {
        this.udriver = driver;
        if (udriver == null) System.out.println ("it is empty!");
    }


    public WebElement find (By locator) {
        WebElement element = null;
        try {
            element = udriver.findElement (locator);
        } catch (NullPointerException e) {
            System.out.println ("Driver is no set.Please use setDriver() method!");
        } catch (NoSuchElementException e) {
            System.out.println ("No such element found!");
        }
        return element;
    }

    public List<WebElement> findAll (By locator) {
        return udriver.findElements (locator);
    }

    public boolean isVisible (By locator) {
        return find (locator).isDisplayed ();
    }

    public String get_Text (By locator) {
        return find (locator).getText ();
    }




    public void clickEl (By locator) {
        find (locator).click ();
    }

    public void jsScriptExecute (String script) {
        JavascriptExecutor js = (JavascriptExecutor) udriver;
        js.executeScript (script);
    }

    public void waitUntilElementVisible (WebElement element) {
        WebDriverWait wait = new WebDriverWait (udriver, Duration.ofSeconds (10));
        wait.until (ExpectedConditions.visibilityOf (element));
    }

    public void impWait (int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep (seconds);
    }

    public boolean verifyLink (String url) {
        try {
            URL link = new URL (url);
            HttpURLConnection urlConnection = (HttpURLConnection) link.openConnection ();
            urlConnection.setConnectTimeout (3000);  // 3 seconds for connection timeout
            urlConnection.connect ();

            if (urlConnection.getResponseCode () == 200) {
                urlConnection.disconnect ();
                return true;
            } else {
                urlConnection.disconnect ();
                System.out.println ("Response Status : "
                        + urlConnection.getResponseCode () + " from " + url + " - " + "is a broken link");
            }
        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println (url + " - " + "Exception caught while verifying this link!");

        }

        return false;

    }

    public List<String> CheckAllLinks () {
        List<String> brokenLinks = new ArrayList<> ();
        List<WebElement> allLinks = findAll (By.tagName ("a"));
        for (WebElement link : allLinks) {
            String checkLink = link.getAttribute ("href");
            if (!verifyLink (checkLink)) {
                brokenLinks.add (checkLink);
            }
        }
        return brokenLinks;
    }

    public List<String> CheckAllImages () {
        List<String> brokenImages = new ArrayList<> ();
        List<WebElement> allImages = findAll (By.tagName ("img"));

        for (WebElement image : allImages) {
            String imgSrc = image.getAttribute ("src");
            if (!verifyLink (imgSrc)) {
                brokenImages.add (imgSrc);
            }
        }

        return brokenImages;
    }

    public void saveScreenShot (String name, TakesScreenshot screenshot) {
        File source = screenshot.getScreenshotAs (OutputType.FILE);
        File destination = new File (
                System.getProperty ("user.dir") +
                        "/src/test/resources/failed_test_screenshots/" + name + ".png");
        try {
            FileHandler.copy (source, destination);
        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    public Properties loadProperties () {
        prop = new Properties ();
        File propPath = new File ("src/main/java/properties/config.properties");
        try {
            FileInputStream fileInputStream = new FileInputStream (propPath);
            prop.load (fileInputStream);
        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println ("Something wrong with 'loadproperties' method in functionUti!");
        }
        return prop;
    }

    public Properties loadProperties (String filePath) {
        prop = new Properties ();
        File propPath = new File (filePath);
        try {
            FileInputStream fileInputStream = new FileInputStream (propPath);
            prop.load (fileInputStream);
        } catch (Exception e) {
            e.printStackTrace ();
            System.out.println ("Something wrong with 'loadpPoperties' method in functionUti!");
        }
        return prop;
    }

    public void scrollToBottom () {
        String script = "window.scrollBy(0,document.body.scrollHeight)";
        jsScriptExecute (script);
    }

    public void scrollToElement (WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) udriver;
        js.executeScript ("arguments[0].scrollIntoView();", element);
        ///"arguments[0].scrollIntoView();",
    }

    public String getTitle () {

        if (udriver == null) {

        }
        return udriver.getTitle ();
    }

    public void jsClick (WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) udriver;
        js.executeScript ("arguments[0].click()", element);
    }

    public Workbook loadDataFromExcel (String path) {
        XSSFWorkbook workbook;
        try {
            FileInputStream fileInputStream = new FileInputStream (path);
            workbook = new XSSFWorkbook (fileInputStream);
        } catch (IOException e) {
            throw new RuntimeException (e);
        }
        return workbook;
    }

    public void pageLoadTimneOut(int seconds)
    {
        udriver.manage ().timeouts ().pageLoadTimeout (Duration.ofSeconds (seconds));
    }

//    public Sheet getSheet (Workbook workbook, int sheetIndex) {
//        return workbook.getSheetAt (sheetIndex);
//    }

//    public Sheet getSheet (Workbook workbook) {
//        return getSheet (workbook, 0);
//    }

    public void jsDropDwonSelect(WebElement element, String value){
        JavascriptExecutor js = (JavascriptExecutor) udriver;
        String scipt="arguments[0].value='"+value+"'";
        js.executeScript (scipt,element);

    }


}
