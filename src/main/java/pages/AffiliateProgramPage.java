package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AffiliateProgramPage {

    WebDriver driver;
    public AffiliateProgramPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[normalize-space()='Login']")
    WebElement tagLoginAffiliate;

    public boolean displayTagLoginAffiliate(){
        return tagLoginAffiliate.isDisplayed();
    }

}
