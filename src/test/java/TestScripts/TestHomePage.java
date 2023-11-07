package TestScripts;

import Pages.HomePage;
import utilities.functionUtilities;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestHomePage extends TestSetup{

    HomePage homePage;
    SoftAssert softAssert;
    functionUtilities utility;


    @Test
    public void checkingTitle(){
        softAssert = new SoftAssert ();
        homePage = new HomePage ();
        String expectedTitle = homePage.homePageTitle ();
        String actualTitle = "Buy Islamic Books - Online Book Shop in Bangladesh | Wafilife";
        softAssert.assertEquals(actualTitle,expectedTitle,"not landed in right page!");
        softAssert.assertAll ();

    }
    @Test
    public void clickOnBookList(){
        softAssert = new SoftAssert ();
        homePage = new HomePage ();


        String actualTitle = homePage.clickOnBookListBtn ();
        try {
            homePage.impWait (2);
        } catch (InterruptedException e) {
            throw new RuntimeException (e);
        }
        String expectedTitle = "Bangla & English Islamic, Academic, Non Fiction Books | Wafilife";
        //if(driver == null) System.out.println ("this is null");
        softAssert.assertEquals (actualTitle,expectedTitle,"Title mismatched!");
        softAssert.assertAll ();

    }

}
