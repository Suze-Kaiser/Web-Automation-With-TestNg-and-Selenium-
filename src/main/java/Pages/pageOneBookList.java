package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.functionUtilities;
public class pageOneBookList extends functionUtilities{
   // protected  WebDriver driver;

    @FindBy(xpath = "//a[@class='paginate' and contains(text(),'→')]")
    WebElement nextBookListPage;

    /*@FindBy(xpath = "//img[@title='রুকইয়ার-আয়াত-ও-দুআ']")
    WebElement bookNameRuqaiyaByAlMahmud;
     */

//    public pageOneBookList (WebDriver driver) {
//        this.driver=driver;
//        setDriver (driver);
//        PageFactory.initElements (driver,this);
//    }
    public pageOneBookList () {
        PageFactory.initElements (udriver,this);
    }
    public void navigateToNextBookListPage() throws InterruptedException {
        scrollToBottom ();
        impWait (2);
        scrollToElement (nextBookListPage);
        impWait (3);
        jsClick (nextBookListPage);
        impWait (5);
    }
}
