package TestScripts;

import Pages.pageTwoBookList;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestBookRokayaAyatODoa {
    SoftAssert softAssert ;


    pageTwoBookList bookListPageTwo;

    @Test(priority = 0)
    public void TestBookListTitle(){
        softAssert = new SoftAssert ();
        bookListPageTwo = new pageTwoBookList ();
        softAssert.assertEquals (bookListPageTwo.getTitle (),
                "Bangla & English Islamic, Academic, Non Fiction Books | Wafilife",
                "Not the right PAge!");
        softAssert.assertAll();
    }

    @Test
    public void navigateToNextBookListPage() throws InterruptedException {
        softAssert = new SoftAssert ();
        bookListPageTwo = new pageTwoBookList ();
        String actualMessage = bookListPageTwo.clickOnBook_RokayaAyatODoa ();

        softAssert.assertEquals (actualMessage, "রুকইয়ার আয়াত ও দুআ - আব্দুল্লাহ আল মাহমুদ | Ruqiyar Ayat O Dua | Wafilife",
                "Not in ROkaya Book details page!");
        softAssert.assertAll ();
    }








}
