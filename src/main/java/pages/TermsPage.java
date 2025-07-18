package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TermsPage {

    WebDriver driver;
    public TermsPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[contains(text(),'Terms & Conditions')]")
    WebElement terms;

    public boolean displayTerms(){
        return terms.isDisplayed();
    }

}
