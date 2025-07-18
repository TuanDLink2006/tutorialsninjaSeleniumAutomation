package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SiteMapPage {

    WebDriver driver;
    public SiteMapPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[normalize-space()='Site Map']")
    WebElement tagSiteMap;

    @FindBy(linkText="Search")
    WebElement searchLink;

    public boolean displayTagSiteMap(){
        return tagSiteMap.isDisplayed();
    }

    public SearchPage clickOnSearchLink(){
        searchLink.click();
        return new SearchPage(driver);
    }
}
