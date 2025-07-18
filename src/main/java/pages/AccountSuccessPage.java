package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

    WebDriver driver;
    public AccountSuccessPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement logOutOption;

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[normalize-space()='Success']")
    WebElement tagRegisterSuccess;

    @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
    WebElement tagHeader;

    @FindBy(xpath = "//div[@id='content']")
    WebElement content;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    WebElement goOnMyAccount;


    public boolean displayLogoutOption(){
        return logOutOption.isDisplayed();
    }

    public boolean displayTagRegisterSuccess(){
        return tagRegisterSuccess.isDisplayed();
    }

    public String expectedProperDetail() {
        return content.getText();
    }

    public String tagHeader(){
        return tagHeader.getText();
    }

    public MyAccountPage clickContinueMyAccount(){
        goOnMyAccount.click();
        return new MyAccountPage(driver);
    }
}
