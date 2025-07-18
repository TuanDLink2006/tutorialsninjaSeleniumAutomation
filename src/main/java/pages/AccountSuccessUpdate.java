package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessUpdate {

    WebDriver driver;
    public AccountSuccessUpdate(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement updateSuccess;

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[normalize-space()='Account']")
    WebElement tagAccount;

    public boolean displayUpdateSuccess(){
        return updateSuccess.isDisplayed();
    }

    public boolean displayTagAccount(){
        return tagAccount.isDisplayed();
    }

}
