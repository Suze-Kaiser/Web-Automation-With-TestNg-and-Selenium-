package Pages;

import utilities.functionUtilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends  functionUtilities {
    //protected WebDriver driver;
    @FindBy(xpath = "//a[@href='https://www.wafilife.com/cat/books/']//span[@class='menu-label-level-0'][contains(text(),'বই')]")
    WebElement booksListBtn; //7118
//    public HomePage(WebDriver driver){
//        this.driver = driver;
//        setDriver (driver);
//       PageFactory.initElements (driver,this);
//    }
    public HomePage() {
        PageFactory.initElements (udriver, this);
    }

    @FindBy(xpath ="//a[@href='https://www.wafilife.com/cat/books/']//span[@class='menu-label-level-0'][contains(text(),'বই')]")
    WebElement bookListBtn;
    public String clickOnBookListBtn(){
        bookListBtn.click ();
        return getTitle ();
    }

    public String homePageTitle(){
        return getTitle ();
    }






}
