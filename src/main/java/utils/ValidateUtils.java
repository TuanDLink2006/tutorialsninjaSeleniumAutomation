package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ValidateUtils {

    public static String getValidationMessage(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String message = (String) js.executeScript("return arguments[0].validationMessage;", element);
        return message;
    }
}
