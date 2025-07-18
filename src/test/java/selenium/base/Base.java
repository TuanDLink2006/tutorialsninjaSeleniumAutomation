package selenium.base;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.CommonUtils;

import java.time.Duration;
import java.util.Properties;

public class Base {

    WebDriver driver;
    WebDriverWait wait;
    Properties prop;

    public WebDriver openBrowserAndApplication(){

        prop = CommonUtils.loadProperties();
        String browserName = prop.getProperty("browserName");

        if (browserName.equals("chrome")){

            driver = new ChromeDriver();
        } else if (browserName.equals("firefox")){

            driver = new FirefoxDriver();
        } else if(browserName.equals("edge")){

            driver = new EdgeDriver();
        }
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(prop.getProperty("appURL"));
        driver.manage().window().maximize();

        return driver;
    }

    public WebDriver negativeURL(WebDriver driver, String URL){
        driver.navigate().to(URL);
        return driver;
    }

    public WebDriver negativeBack(WebDriver driver){
        driver.navigate().back();
        return driver;
    }


    public WebDriver pressKeyMultipleTimes(WebDriver driver, Keys keysName, int count){

        Actions actions = new Actions(driver);
        for (int i = 0; i < count; i++){
            actions.sendKeys(Keys.TAB).perform();
        }
        return driver;
    }

    public WebDriver enterDetailInterRegisterAccountPageFields(WebDriver driver){

        prop = CommonUtils.loadProperties();
        Actions actions = new Actions(driver);

        actions.sendKeys(prop.getProperty("firstName")).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("lastName"))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(CommonUtils.generateEmail())
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(prop.getProperty("telephone")).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
                .pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("password")).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty("password"))
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.LEFT).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.SPACE).pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB)
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.ENTER).build().perform();

        return driver;
    }

    public WebDriver enterDetailInfoLoginPageFields(WebDriver driver){

        prop = CommonUtils.loadProperties();
        Actions actions = new Actions(driver);

        actions.sendKeys(prop.getProperty(prop.getProperty("emailExist"))).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.TAB).pause(Duration.ofSeconds(1)).sendKeys(prop.getProperty(prop.getProperty("password")))
                .pause(Duration.ofSeconds(1)).sendKeys(Keys.TAB).sendKeys(Keys.TAB).pause(Duration.ofSeconds(1))
                .sendKeys(Keys.ENTER).build().perform();

        return driver;
    }

    public String getHTMLCodeOfThePage(){
        return driver.getPageSource();
    }

    public String getPageURL(WebDriver driver){
        return driver.getCurrentUrl();
    }

    public String getPageTitle(WebDriver driver){
        return driver.getTitle();
    }

    public void closeBrowser(WebDriver driver) {
        if(driver!=null) {
            driver.quit();
        }
    }

    public WebDriver refreshPage(WebDriver driver) {
        driver.navigate().refresh();
        return driver;
    }
}
