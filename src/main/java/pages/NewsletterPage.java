package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewsletterPage {

    WebDriver driver;
    public NewsletterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[normalize-space()='Newsletter']")
    WebElement tagNewsletter;

    @FindBy(xpath = "//h1[normalize-space()='Newsletter Subscription']")
    WebElement titleHeader;

    public boolean displayTagNewsletter(){
        return tagNewsletter.isDisplayed();
    }

    public boolean displayTitleHeader(){
        return titleHeader.isDisplayed();
    }
}
