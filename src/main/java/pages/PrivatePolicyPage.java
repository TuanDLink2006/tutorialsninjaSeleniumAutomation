package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PrivatePolicyPage {

    WebDriver driver;
    public PrivatePolicyPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[contains(text(),'Privacy Policy')]")
    WebElement tagPrivatePolicy;

    public boolean displayTagPrivatePolicy(){
        return tagPrivatePolicy.isDisplayed();
    }
}
