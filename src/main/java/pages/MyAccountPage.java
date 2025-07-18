package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAccountPage {

    WebDriver driver;
    public MyAccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Account']")
    WebElement tagAccount;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement logOutOption;

    @FindBy(xpath = "//a[normalize-space()='Edit your account information']")
    WebElement editYourAccount;

    @FindBy(xpath = "//a[normalize-space()='Change your password']")
    WebElement changeYourPassword;

    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    WebElement messageSuccess;

    @FindBy(xpath = "//a[normalize-space()='Subscribe / unsubscribe to newsletter']")
    WebElement newsletter;

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[normalize-space()='Newsletter']")
    WebElement tagANewsletter;

    @FindBy(xpath = "//input[@value='1']")
    WebElement yesSubscribe;

    @FindBy(xpath = "//input[@value='0']")
    WebElement noSubscribe;

    public boolean displayTagAccount(){
        return tagAccount.isDisplayed();
    }

    public LogoutPage clickLogoutOption(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logOutOption);
        logOutOption.click();
        return new LogoutPage(driver);
    }

    public boolean displayLogoutOption(){
        return logOutOption.isDisplayed();
    }

    public EditAccountPage clickEditYourAccount(){
        editYourAccount.click();
        return new EditAccountPage(driver);
    }

    public boolean displayEditYourAccount(){
        return editYourAccount.isDisplayed();
    }

    public ChangePasswordPage clickChangeYourPassword(){
        changeYourPassword.click();
        return new ChangePasswordPage(driver);
    }

    public String getMessageSuccess(){
        return messageSuccess.getText();
    }

    public NewsletterPage clickNewsletter(){
        newsletter.click();
        return new NewsletterPage(driver);
    }

    public boolean tagNewsletter(){
        return tagANewsletter.isDisplayed();
    }

    public boolean isSelectYesSubscribe(){
        return yesSubscribe.isSelected();
    }

    public boolean isSelectNoSubscribe(){
        return noSubscribe.isSelected();
    }
}
