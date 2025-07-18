package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h2[normalize-space()='New Customer']")
    WebElement headingOne;

    @FindBy(xpath = "//h2[normalize-space()='Returning Customer']")
    WebElement headingTwo;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement emailField;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement passwordField;

    @FindBy(xpath = "//input[@value='Login']")
    WebElement login;

    @FindBy(xpath = "//div[contains(@class,'alert-danger')]")
    WebElement warningMessage;

    @FindBy(xpath = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']")
    WebElement forgotPasswordLink;

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[normalize-space()='Login']")
    WebElement loginBreadcrumb;

    public String getTextHeadingPageOne(){
        return headingOne.getText();
    }

    public String getTextHeadingPageTwo(){
        return headingTwo.getText();
    }

    public void enterEmail(String textEmail){
        emailField.sendKeys(textEmail);
    }

    public String getEmailPlaceholder(){
        return emailField.getDomAttribute("placeholder");
    }

    public String getTextCopiedIntoEmailField(){
        return emailField.getDomAttribute("value");
    }

    public void enterPass(String textPass){
        passwordField.sendKeys(textPass);
    }

    public void clearPass(){
        passwordField.clear();
    }

    public String getPasswordPlaceholder(){
        return passwordField.getDomAttribute("placeholder");
    }

    public String getPasswordFieldType(){
        return passwordField.getDomAttribute("type");
    }

    public WebDriver pastedCopiedPasswordTextIntoEmailField(WebDriver driver){
        Actions actions = new Actions(driver);
        actions.click(emailField).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL);
        return driver;
    }

    public WebDriver selectPasswordFieldTextAndCopy(WebDriver driver){
        Actions actions = new Actions(driver);
        actions.doubleClick(passwordField).keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL);
        return driver;
    }

    public WebDriver getDriver(){
        return driver;
    }

    public boolean displayTagLogin(){
        return loginBreadcrumb.isDisplayed();
    }

    public ForgottenPassword clickForgottenPassLink(){
        forgotPasswordLink.click();
        return new ForgottenPassword(driver);
    }

    public MyAccountPage clickLogin(){
        login.click();
        return new MyAccountPage(driver);
    }

    public String getWarningMessage(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(warningMessage));
        return warningMessage.getText();
    }

    public RegisterPage clickBtnContinue(){
        btnContinue.click();
        return new RegisterPage(driver);
    }

    public MyAccountPage loginToApplication(String emailText, String passwordText){
        enterEmail(emailText);
        enterPass(passwordText);
        return clickLogin();
    }

}
