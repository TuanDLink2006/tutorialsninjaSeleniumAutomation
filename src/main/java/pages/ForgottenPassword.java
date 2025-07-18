package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ForgottenPassword {

    WebDriver driver;

    public ForgottenPassword(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[normalize-space()='Forgotten Password']")
    WebElement tagForgottenPassword;

    public boolean displayForgottenPassword(){
        return tagForgottenPassword.isDisplayed();
    }
}
