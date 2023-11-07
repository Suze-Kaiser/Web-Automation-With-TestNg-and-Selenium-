package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.functionUtilities;
public class pageTwoBookList extends functionUtilities{

    @FindBy(xpath = "//img[contains(@title,'রুকইয়ার-আয়াত-ও-দুআ')]")
    WebElement RokayaBook;
    public pageTwoBookList(){
        PageFactory.initElements (udriver,this);
    }

    public String clickOnBook_RokayaAyatODoa() throws InterruptedException {
        RokayaBook.click ();

        return getTitle ();
    }

}
