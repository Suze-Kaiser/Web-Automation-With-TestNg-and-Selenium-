package TestScripts;

import Pages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utilities.functionUtilities;

import java.util.Properties;

public class TestSetup extends functionUtilities {
    public WebDriver driver;
    functionUtilities utility = new functionUtilities ();
    Properties prop;
    protected String url;

    @BeforeTest
    public void initializeBrowser () {
        prop = loadProperties ();//utility.loadProperties ();
        String browserName = prop.getProperty ("browserName");
        if (browserName.equalsIgnoreCase ("chrome")) driver = new ChromeDriver ();
        else driver = new EdgeDriver ();
        driver.manage ().window ().maximize ();
        url = prop.getProperty ("Url");
        utility.setdriver (driver);
        //setUdriver (driver);
        driver.get (url);
    }

    @Test
    public void checkingTitle () {
        SoftAssert softAssert = new SoftAssert ();
        HomePage homePage = new HomePage ();
        String expectedTitle = homePage.homePageTitle ();
        String actualTitle = "Buy Islamic Books - Online Book Shop in Bangladesh | Wafilife";
        softAssert.assertEquals (actualTitle, expectedTitle, "not landed in right page!");
        softAssert.assertAll ();

    }
        @AfterSuite
        public void tearDown(){
            driver.quit ();
        }

}


