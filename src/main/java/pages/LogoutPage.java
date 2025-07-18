package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogoutPage {

    WebDriver driver;
    public LogoutPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[normalize-space()='Logout']")
    WebElement tagLogout;

    @FindBy(xpath = "//a[normalize-space()='Continue']")
    WebElement btnContinue;

    @FindBy(xpath = "//h1[normalize-space()='Account Logout']")
    WebElement headingTitle;

    public boolean displayTagLogout(){
        return tagLogout.isDisplayed();
    }

    public HomePage clickBtnContinue(){
        btnContinue.click();
        return new HomePage(driver);
    }

    public String getTextHeadingLogout(){
        return headingTitle.getText();
    }

}
