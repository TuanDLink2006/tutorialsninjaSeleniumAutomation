package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EditAccountPage {

    WebDriver driver;
    public EditAccountPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[normalize-space()='Edit Information']")
    WebElement tagEditInformation;

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement phone;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement goOn;

    public boolean displayTagEditInformation(){
        return tagEditInformation.isDisplayed();
    }

    public String getAttributeFirstNameValue(){
        return firstName.getAttribute("value");
    }

    public String getAttributeLastNameValue(){
        return lastName.getAttribute("value");
    }

    public String getAttributeEmailValue(){
        return email.getAttribute("value");
    }

    public String getAttributePhoneValue(){
        return phone.getAttribute("value");
    }

    public AccountSuccessUpdate clickContinueUpdate(){
        goOn.click();
        return new AccountSuccessUpdate(driver);
    }
}
