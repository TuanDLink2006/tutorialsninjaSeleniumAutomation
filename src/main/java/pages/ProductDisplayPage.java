package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDisplayPage {

    WebDriver driver;
    public ProductDisplayPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(className="thumbnails")
    WebElement thumbnailsSection;

    public boolean displayDetailProduct(){
        return thumbnailsSection.isDisplayed();
    }
}
