package TestScripts;

import org.testng.annotations.Test;
import Pages.RuqyaBookDetails;
import org.testng.asserts.SoftAssert;

public class TestRuqayaBookDetails {
    RuqyaBookDetails bookDetails;
    SoftAssert softAssert;

    @Test
    public void VerifyingclikOnOrder () throws InterruptedException {
        softAssert = new SoftAssert ();
        bookDetails = new RuqyaBookDetails ();
        boolean flag = bookDetails.clickOnOrder ();
        softAssert.assertTrue (flag,"Order confirmation option is not visible");
    }


}
