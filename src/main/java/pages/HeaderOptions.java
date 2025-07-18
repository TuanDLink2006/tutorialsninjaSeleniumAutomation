package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HeaderOptions {

    WebDriver driver;

    public HeaderOptions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//i[@class='fa fa-phone']")
    WebElement contactPhone;

    @FindBy(xpath = "//i[@class='fa fa-heart']")
    WebElement wishlist;

    @FindBy(xpath = "//span[normalize-space()='My Account']")
    WebElement myAccount;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
    WebElement login;

    @FindBy(xpath = "//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Logout']")
    WebElement logout;

    @FindBy(xpath = "//a[@title='Shopping Cart']//i[@class='fa fa-shopping-cart']")
    WebElement shoppingCart;

    @FindBy(xpath = "//a[normalize-space()='Register']")
    WebElement register;

    @FindBy(xpath = "//span[normalize-space()='Checkout']")
    WebElement checkout;

    @FindBy(xpath = "//a[normalize-space()='Qafox.com']")
    WebElement title;

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Login']")
    WebElement loginBreadcrumb;

    @FindBy(xpath = "//a[normalize-space()='Account']")
    WebElement accountBreadcrumb;

    @FindBy(xpath = "//ul[@class='breadcrumb']//i[@class='fa fa-home']/parent::*")
    WebElement homeBreadcrumb;

    @FindBy(xpath = "//input[@placeholder='Search']")
    WebElement searchField;

    @FindBy(xpath="//button[@class='btn btn-default btn-lg']")
    WebElement searchBtn;

    public void enterProductNameSearch(String textSearch){
        searchField.sendKeys(textSearch);
    }

    public SearchPage clickBtnSearch(){
        searchBtn.click();
        return new SearchPage(driver);
    }

    public HomePage clickHomeBreadcrumb() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homeBreadcrumb);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", homeBreadcrumb);
        return new HomePage(driver);
    }

    public LoginPage clickAccountBreadcrumb() {
        accountBreadcrumb.click();
        return new LoginPage(driver);
    }

    public LoginPage clickLoginBreadcrumb() {
        loginBreadcrumb.click();
        return new LoginPage(driver);
    }

    public ContactUsPage clickContactPhone() {
        contactPhone.click();
        return new ContactUsPage(driver);
    }

    public LoginPage clickWishlist() {
        wishlist.click();
        return new LoginPage(driver);
    }

    public void clickOnMyAccount() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement myAccountOption = wait.until(ExpectedConditions.elementToBeClickable(myAccount));
        myAccountOption.click();
    }

    public RegisterPage clickOnRegister(){
        register.click();
        return new RegisterPage(driver);
    }

    public LoginPage clickOnLogin() {
        login.click();
        return new LoginPage(driver);
    }

    public LogoutPage clickOnLogout(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(logout)).click();
        return new LogoutPage(driver);
    }

    public ShoppingCartPage clickShoppingCart() {
        shoppingCart.click();
        return new ShoppingCartPage(driver);
    }

    public ShoppingCartPage clickCheckout() {
        checkout.click();
        return new ShoppingCartPage(driver);
    }

    public HomePage clickTitle() {
        title.click();
        return new HomePage(driver);
    }

    public boolean displayLogout() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.visibilityOf(logout));
            return logout.isDisplayed();

        } catch (Exception e) {
            e.getMessage();
            return false;
        }
    }

    public String getPlaceholderAttributeSearch(){
        return searchField.getAttribute("placeholder");
    }

}
