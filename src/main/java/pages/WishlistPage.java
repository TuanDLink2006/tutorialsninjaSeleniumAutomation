package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class WishlistPage {

    WebDriver driver;
    public WishlistPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
