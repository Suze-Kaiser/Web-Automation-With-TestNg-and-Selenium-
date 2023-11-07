package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.functionUtilities;

public class RuqyaBookDetails extends functionUtilities{
    @FindBy(xpath = "//div[@class='body-wrapper']//button[2]")
    WebElement orderBtn;
    @FindBy(xpath = "//a[@title='checkout']")
    WebElement orderConfirmartionBtn;

    @FindBy(xpath = "//button[@id='place_order']")
    WebElement checkoutOrderBtn;
    public RuqyaBookDetails(){
        PageFactory.initElements (udriver,this);
    }

    public boolean clickOnOrder() throws InterruptedException {
        orderBtn.click ();
        waitUntilElementVisible (orderConfirmartionBtn);
        orderConfirmartionBtn.click ();
        return checkoutOrderBtn.isDisplayed ();
    }
}
