package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RightColumnOptions {
    WebDriver driver;

    public RightColumnOptions(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Login']")
    WebElement loginGroup;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
    WebElement logoutGroup;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Register']")
    WebElement registerGroup;

    @FindBy(xpath = "//a[normalize-space()='Forgotten Password']")
    WebElement forgotPasswordGroup;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='My Account']")
    WebElement myAccountGroup;

    @FindBy(xpath = "//a[normalize-space()='Address Book']")
    WebElement addressBookGroup;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Wish List']")
    WebElement wishListGroup;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Order History']")
    WebElement historyGroup;

    @FindBy(xpath = "//a[normalize-space()='Downloads']")
    WebElement downloadsGroup;

    @FindBy(xpath = "//a[normalize-space()='Recurring payments']")
    WebElement paymentsGroup;

    @FindBy(xpath = "//a[normalize-space()='Reward Points']")
    WebElement rewardPointGroup;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Returns']")
    WebElement returnGroup;

    @FindBy(xpath = "//a[normalize-space()='Transactions']")
    WebElement transactionGroup;

    @FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Newsletter']")
    WebElement newsletterGroup;

    public LoginPage clickLoginGroup(){
        loginGroup.click();
        return new LoginPage(driver);
    }

    public LogoutPage clickLogoutGroup(){
        logoutGroup.click();
        return new LogoutPage(driver);
    }

    public RegisterPage clickRegisterGroup(){
        registerGroup.click();
        return new RegisterPage(driver);
    }

    public ForgottenPassword clickForgotPassGroup(){
        forgotPasswordGroup.click();
        return new ForgottenPassword(driver);
    }

    public LoginPage clickMyAccountGroup(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(myAccountGroup)).click();
        return new LoginPage(driver);
    }

    public LoginPage clickAddressBookGroup(){
        addressBookGroup.click();
        return new LoginPage(driver);
    }

    public LoginPage clickWishlistGroup(){
        wishListGroup.click();
        return new LoginPage(driver);
    }

    public LoginPage clickOrderHistoryGroup(){
        historyGroup.click();
        return new LoginPage(driver);
    }

    public LoginPage clickDownloadGroup(){
        downloadsGroup.click();
        return new LoginPage(driver);
    }

    public LoginPage clickPaymentsGroup(){
        paymentsGroup.click();
        return new LoginPage(driver);
    }

    public LoginPage clickRewardGroup(){
        rewardPointGroup.click();
        return new LoginPage(driver);
    }

    public LoginPage clickReturnGroup(){
        returnGroup.click();
        return new LoginPage(driver);
    }

    public LoginPage clickTransactionGroup(){
        transactionGroup.click();
        return new LoginPage(driver);
    }

    public LoginPage clickNewsletterGroup(){
        newsletterGroup.click();
        return new LoginPage(driver);
    }

    public boolean displayLogout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {

            wait.until(ExpectedConditions.visibilityOf(logoutGroup));
            return logoutGroup.isDisplayed();

        } catch (Exception e) {
            System.out.println("Không thể hiển thị nút Logout: " + e.getMessage());
            return false;
        }
    }

}
