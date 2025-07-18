package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductReturnPage {

    WebDriver driver;
    public ProductReturnPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Product Returns']")
    WebElement tagProductReturn;

    public boolean displayTagProductReturn(){
        return tagProductReturn.isDisplayed();
    }
}
