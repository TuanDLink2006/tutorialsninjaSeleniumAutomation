package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class OrderHistoryPage {

    WebDriver driver;
    public OrderHistoryPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


}
