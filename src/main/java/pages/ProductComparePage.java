package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductComparePage {

    WebDriver driver;
    public ProductComparePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Product Comparison']")
    WebElement tagProductCompare;

    public boolean displayTagProductCompare(){
        return tagProductCompare.isDisplayed();
    }

}
