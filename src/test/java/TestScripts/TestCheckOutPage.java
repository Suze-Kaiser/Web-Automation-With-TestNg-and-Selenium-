package TestScripts;

import Pages.checkOutPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import io.qameta.allure.testng.Tags;

import org.testng.annotations.Test;

import java.io.IOException;

public class TestCheckOutPage {
    checkOutPage checkoutPage = new checkOutPage();

    @Test(description = "Filling out billing and delivery information")
    @Severity (SeverityLevel.CRITICAL)
    @Description("Test Dscription: giving information for billing")
    @Tags ({@Tag("smoke"),@Tag("tag Testing")})
    @Owner ("Cameron Winter")
    public void allInputFieldTest() throws InterruptedException, IOException {
        checkoutPage.setNewAccountCreationFlag (true);
        checkoutPage.fillingAllInputField (2);
        checkoutPage.clickOnFinalOrder ();
    }
}
