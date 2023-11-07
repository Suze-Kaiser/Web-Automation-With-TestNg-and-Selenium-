package TestScripts;

import Pages.pageOneBookList;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestBookListPageOne extends TestSetup{
    //protected WebDriver driver;
    SoftAssert softAssert ;

    pageOneBookList bookListPageOne;

    @Test(priority = 0)
    public void TestBookListTitle(){
        softAssert = new SoftAssert ();
        bookListPageOne = new pageOneBookList ();
        softAssert.assertEquals (bookListPageOne.getTitle (),
                "Bangla & English Islamic, Academic, Non Fiction Books | Wafilife",
                "Not the right PAge!");
        softAssert.assertAll();
    }

    @Test
    public void navigateToNextBookListPage() throws InterruptedException {
        bookListPageOne = new pageOneBookList ();
        bookListPageOne.navigateToNextBookListPage ();

    }
}
