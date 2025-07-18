package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterLocalhost {

    WebDriver driver;

    public RegisterLocalhost(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement myAccount;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement clickRegister;

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(xpath = "//input[@id='input-newsletter']")
    WebElement newsletter;

    @FindBy(xpath = "//button[normalize-space()='Continue']")
    WebElement goOn;

    public void clickMyAccount(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(myAccount))
                .click();
    }

    public void clickRegister(){
        clickRegister.click();
    }

    public void enterFirstName(String textFirstName){
        firstName.sendKeys(textFirstName);
    }

    public void enterLastName(String textLastName){
        lastName.sendKeys(textLastName);
    }

    public void enterEmail(String textEmail){
        email.sendKeys(textEmail);
    }

    public void enterPassword(String textPassword){
        password.sendKeys(textPassword);
    }

    public void clickNewsletter() {
        WebElement newsletterCheckbox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(newsletter));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", newsletterCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", newsletterCheckbox);
    }

    public void clickAgree() {
        WebElement agreeCheckbox = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='agree']")));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", agreeCheckbox);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", agreeCheckbox);
    }

    public void clickGoOn(){
        WebElement goOnButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(goOn));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", goOnButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", goOnButton);
    }


}
