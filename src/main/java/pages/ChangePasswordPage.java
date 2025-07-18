package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePasswordPage {

    WebDriver driver;
    public ChangePasswordPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confirmPasswordField;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement btnContinue;

    public void enterPassword(String textPass){
        passwordField.sendKeys(textPass);
    }

    public void enterConfirmPassword(String textPass){
        confirmPasswordField.sendKeys(textPass);
    }

    public MyAccountPage clickBtnContinue(){
        btnContinue.click();
        return new MyAccountPage(driver);
    }
}
