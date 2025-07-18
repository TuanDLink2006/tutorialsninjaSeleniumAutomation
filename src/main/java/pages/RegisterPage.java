package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;

public class RegisterPage {

    WebDriver driver;
    ElementUtils elementUtils;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        elementUtils = new ElementUtils(driver);

    }

    @FindBy(xpath = "//input[@id='input-firstname']")
    WebElement firstName;

    @FindBy(css = "label[for='input-firstname']")
    WebElement firstNameLabel;

    @FindBy(xpath = "//input[@id='input-lastname']")
    WebElement lastName;

    @FindBy(css = "label[for='input-lastname']")
    WebElement lastNameLabel;

    @FindBy(xpath = "//input[@id='input-email']")
    WebElement email;

    @FindBy(css = "label[for='input-email']")
    WebElement emailLabel;

    @FindBy(xpath = "//input[@id='input-telephone']")
    WebElement phone;

    @FindBy(css = "label[for='input-telephone']")
    WebElement phoneLabel;

    @FindBy(xpath = "//input[@id='input-password']")
    WebElement password;

    @FindBy(css = "label[for='input-password']")
    WebElement passwordLabel;

    @FindBy(xpath = "//input[@id='input-confirm']")
    WebElement confirmPass;

    @FindBy(css = "label[for='input-confirm']")
    WebElement confirmPassLabel;

    @FindBy(xpath = "//label[normalize-space()='Yes']")
    WebElement yesSubscribe;

    @FindBy(xpath = "//input[@value='0']")
    WebElement noSubscribe;

    @FindBy(xpath = "//input[@name='agree']")
    WebElement agree;

    @FindBy(css = "a[class='agree'] b")
    WebElement agreeLabel;

    @FindBy(xpath = "//input[@value='Continue']")
    WebElement goOn;

    @FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
    WebElement errorFirstName;

    @FindBy(xpath = "//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
    WebElement errorLastName;

    @FindBy(xpath = "//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
    WebElement errorEmail;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    WebElement errorExistingEmail;

    @FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
    WebElement errorPhone;

    @FindBy(xpath = "//input[@id='input-telephone']/following-sibling::div")
    WebElement telephoneWarning;

    @FindBy(xpath = "//div[contains(text(),'Password must be between 4 and 20 characters!')]")
    WebElement errorPassword;

    @FindBy(xpath = "//div[@class='text-danger']")
    WebElement errorConfirmPass;

    @FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
    WebElement errorDismissible;

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[normalize-space()='Register']")
    WebElement tagRegister;

    @FindBy(xpath = "//h1[normalize-space()='Register Account']")
    WebElement headerRegister;

    @FindBy(xpath = "//a[normalize-space()='login page']")
    WebElement linkLoginPage;

    @FindBy(xpath = "//b[normalize-space()='Privacy Policy']")
    WebElement linkPrivatePolicy;

    @FindBy(xpath = "//button[text()='×']")
    WebElement xOption;

    public WebDriver getDriver(){
        return driver;
    }

    public String getTextHeaderRegister(){
        return headerRegister.getText();
    }

    public void enterFirstName(String textFirstName){
        firstName.sendKeys(textFirstName);
    }

    public String getFirstNameLabelContent(WebDriver driver) {
        return elementUtils.getCSSPropertyOfPuseudoElement(firstNameLabel,"content");
    }

    public String getFirstNameLabelColor(WebDriver driver) {
        return elementUtils.getCSSPropertyOfPuseudoElement(firstNameLabel,"color");
    }

    public void clearFirstName(){
        firstName.clear();
    }

    public String getHeightFirstName(){
        return firstName.getCssValue("height");
    }

    public String getWidthFirstName(){
        return firstName.getCssValue("width");
    }

    public boolean displayErrorFirstName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textErrorFirstName = wait.until(ExpectedConditions.visibilityOf(errorFirstName));

        return textErrorFirstName.isDisplayed();
    }

    public String getAttributePlaceholderFirstName(){
        return firstName.getAttribute("placeholder");
    }

    public void enterLastName(String textLastName){
        lastName.sendKeys(textLastName);
    }

    public String getLastNameContent(WebDriver driver){
        return elementUtils.getCSSPropertyOfPuseudoElement(lastNameLabel,"content");
    }

    public String getLastNameColor(WebDriver driver){
        return elementUtils.getCSSPropertyOfPuseudoElement(lastNameLabel,"color");
    }

    public void clearLastName(){
        lastName.clear();
    }

    public boolean displayErrorLastName(){
        return errorLastName.isDisplayed();
    }

    public String getHeightLastName(){
        return lastName.getCssValue("height");
    }

    public String getWidthLastName(){
        return lastName.getCssValue("width");
    }

    public String getAttributePlaceholderLastName(){
        return lastName.getAttribute("placeholder");
    }

    public void enterEmail(String textEmail){
        email.sendKeys(textEmail);
    }

    public String getEmailContent(WebDriver driver){
        return elementUtils.getCSSPropertyOfPuseudoElement(emailLabel,"content");
    }

    public String getEmailColor(WebDriver driver){
        return elementUtils.getCSSPropertyOfPuseudoElement(emailLabel,"color");
    }

    public void clearEmail(){
        email.clear();
    }

    public String getValidateEmail(){
        return utils.ValidateUtils.getValidationMessage(driver, email);
    }

    public boolean displayErrorEmail(){
        return errorEmail.isDisplayed();
    }

    public String getHeightEmail(){
        return email.getCssValue("height");
    }

    public String getWidthEmail(){
        return email.getCssValue("width");
    }

    public String getAttributePlaceholderEmail(){
        return email.getAttribute("placeholder");
    }

    public void enterPhone(String textPhone){
        phone.sendKeys(textPhone);
    }

    public String getPhoneContent(WebDriver driver){
        return elementUtils.getCSSPropertyOfPuseudoElement(phoneLabel,"content");
    }

    public String getPhoneColor(WebDriver driver){
        return elementUtils.getCSSPropertyOfPuseudoElement(phoneLabel,"color");
    }

    public String getMessageWarningPhone(){
        return telephoneWarning.getText();
    }

    public void clearPhone(){
        phone.clear();
    }

    public boolean displayErrorPhone(){
        return errorPhone.isDisplayed();
    }

    public String getHeightPhone(){
        return phone.getCssValue("height");
    }

    public String getWidthPhone(){
        return phone.getCssValue("width");
    }

    public String getAttributePlaceholderPhone(){
        return phone.getAttribute("placeholder");
    }

    public void enterPassword(String textPassword){
        password.sendKeys(textPassword);
    }

    public String getPasswordContent(WebDriver driver){
        return elementUtils.getCSSPropertyOfPuseudoElement(passwordLabel,"content");
    }

    public String getPasswordColor(WebDriver driver){
        return elementUtils.getCSSPropertyOfPuseudoElement(passwordLabel,"color");
    }

    public void clearPassword(){
        password.clear();
    }

    public boolean displayErrorPassword(){
        return errorPassword.isDisplayed();
    }

    public String getHeightPass(){
        return password.getCssValue("height");
    }

    public String getWidthPass(){
        return password.getCssValue("width");
    }

    public String getAttributePlaceholderPassword(){
        return password.getAttribute("placeholder");
    }

    public String getAttributePasswordType(){
        return password.getAttribute("type");
    }

    public void enterConfirmPass(String textPassword){
        confirmPass.sendKeys(textPassword);
    }

    public String getConfirmPasswordContent(WebDriver driver){
        return elementUtils.getCSSPropertyOfPuseudoElement(confirmPassLabel,"content");
    }

    public String getConfirmPasswordColor(WebDriver driver){
        return elementUtils.getCSSPropertyOfPuseudoElement(confirmPassLabel,"color");
    }

    public String getHeightConfirmPassword(){
        return confirmPass.getCssValue("height");
    }

    public String getWidthConfirmPassword(){
        return confirmPass.getCssValue("width");
    }

    public String getAttributePlaceholderConfirmPass(){
        return confirmPass.getAttribute("placeholder");
    }

    public String getAttributeConfirmPasswordType(){
        return confirmPass.getAttribute("type");
    }

    public void clickYesSubscribe(){
        yesSubscribe.click();
    }

    public void clickNoSubscribe(){
        noSubscribe.click();
    }

    public void clickAgree(){
        agree.click();
    }

    public String getAgreeContent(WebDriver driver){
        return elementUtils.getCSSPropertyOfPuseudoElement(agreeLabel,"content");
    }

    public String getAgreeColor(WebDriver driver){
        return elementUtils.getCSSPropertyOfPuseudoElement(agreeLabel,"color");
    }

    public AccountSuccessPage clickContinue(){
        goOn.click();
        return  new AccountSuccessPage(driver);
    }

    public String errorFirstName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement textErrorFirstName = wait.until(ExpectedConditions.visibilityOf(errorFirstName));
        return textErrorFirstName.getText();
    }

    public String errorLastName(){
        return errorLastName.getText();
    }

    public String errorEmail(){
        return errorEmail.getText();
    }

    public String errorExistingEmail(){
        return errorExistingEmail.getText();
    }

    public String errorPhone(){
        return errorPhone.getText();
    }

    public String errorPassword(){
        return errorPassword.getText();
    }

    public String errorConfirmPassword(){
        return errorConfirmPass.getText();
    }

    public String errorDismiss(){
        return errorDismissible.getText();
    }

    public boolean displayTagRegister(){
        return tagRegister.isDisplayed();
    }

    public RegisterPage clickOnRegisterBreadcrumb(){
        tagRegister.click();
        return new RegisterPage(driver);
    }

    public LoginPage clickLinkLoginPage(){
        linkLoginPage.click();
        return new LoginPage(driver);
    }

    public void clickPrivatePolicy(){
        linkPrivatePolicy.click();
    }

    public void closePrivacyPolicyDialog(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement closeBtn = wait.until(ExpectedConditions.elementToBeClickable(xOption));
        closeBtn.click();
    }
}
