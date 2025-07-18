package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;


public class HomePage {

    WebDriver driver;
    ElementUtils elementUtils;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        elementUtils = new ElementUtils(driver);
    }

    @FindBy(xpath="//span[text()='My Account']")
    WebElement myAccount;

    @FindBy(linkText="Register")
    WebElement registerOption;

    @FindBy(linkText="Login")
    WebElement loginOption;

    public void clickOnMyAccount(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(myAccount)).click();
    }

    public LoginPage negativeLoginPage(){
        clickOnMyAccount();
        return selectLogin();
    }

    public RegisterPage negativeRegisterPage(){
        clickOnMyAccount();
        return selectRegister();
    }

    public LoginPage selectLogin(){
        loginOption.click();
        return new LoginPage(driver);
    }

    public RegisterPage selectRegister(){
        registerOption.click();
        return new RegisterPage(driver);
    }

    public SearchPage searchForAProductUsingKeyboardKeys(String productName) {
        driver = elementUtils.pressKeyMultipleTimes(driver,Keys.TAB,8);
        elementUtils.enterTextIntoFieldUsingKeyboardKeys(driver,productName);
        elementUtils.pressKeyboardKey(Keys.TAB);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

}
