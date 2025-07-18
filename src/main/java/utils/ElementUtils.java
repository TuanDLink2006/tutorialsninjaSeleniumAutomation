package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.NoSuchElementException;

public class ElementUtils {

    WebDriver driver;
    public ElementUtils(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver pressKeyMultipleTimes(WebDriver driver, Keys keyName, int count) {

        Actions actions = new Actions(driver);

        for (int i = 1; i <= count; i++) {
            actions.sendKeys(keyName).perform();
        }

        return driver;

    }

    public WebDriver enterTextIntoFieldUsingKeyboardKeys(WebDriver driver, String text) {

        Actions actions = new Actions(driver);
        actions.sendKeys(text).perform();
        return driver;

    }

    public void pressKeyboardKey(Keys key) {
        Actions actions = new Actions(driver);
        actions.sendKeys(key).perform();
    }

    public String getCSSPropertyOfPuseudoElement(WebElement element, String property) {
        String text = "";
        try {
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            text = (String) jse.executeScript(
                    "return window.getComputedStyle(arguments[0], '::before').getPropertyValue('" + property + "');",
                    element);
        } catch (NoSuchElementException e) {
            text = "";
        } catch (Exception e) {
            text = "";
        }
        return text;
    }

}
