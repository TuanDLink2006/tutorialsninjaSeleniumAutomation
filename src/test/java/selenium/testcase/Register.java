package selenium.testcase;

import listeners.MyListeners;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import org.testng.asserts.SoftAssert;

import selenium.base.Base;
import utils.CommonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Properties;

@Listeners(MyListeners.class)
public class Register extends Base {

    public WebDriver driver;
    Properties prop;
    HeaderOptions headerOptions;
    RegisterPage registerPage;
    AccountSuccessPage accountSuccessPage;
    MyAccountPage myAccountPage;
    NewsletterPage newsletterPage;
    LoginPage loginPage;
    RightColumnOptions rightColumnOptions;
    EditAccountPage editAccountPage;
    ContactUsPage contactUs;
    ShoppingCartPage shoppingCartPage;
    SearchPage searchPage;
    HomePage homePage;
    ForgottenPassword forgottenPassword;
    FooterOption footerOption;
    AboutUsPage aboutUsPage;
    DeliveryInformationPage deliveryInformationPage;
    PrivatePolicyPage privatePolicyPage;
    TermsPage termsPage;
    ProductReturnPage productReturnsPage;
    SiteMapPage siteMapPage;
    BrandPage brandPage;
    GiftCertificatePage giftCertificatePage;
    SpecialOffersPage specialOffersPage;
    AffiliateLoginPage affiliateLoginPage;
    int currentIndex;

    @BeforeMethod
    public void setup(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        headerOptions = new HeaderOptions(driver);
        headerOptions.clickOnMyAccount();
        registerPage = new RegisterPage(driver);
        registerPage = headerOptions.clickOnRegister();
        accountSuccessPage = new AccountSuccessPage(registerPage.getDriver());
        myAccountPage = new MyAccountPage(registerPage.getDriver());
        newsletterPage = new NewsletterPage(registerPage.getDriver());
        loginPage = new LoginPage(registerPage.getDriver());
        rightColumnOptions = new RightColumnOptions(registerPage.getDriver());
        contactUs = new ContactUsPage(driver);
        searchPage = new SearchPage(driver);
        homePage = new HomePage(driver);
        footerOption = new FooterOption(driver);
        forgottenPassword = new ForgottenPassword(driver);
        deliveryInformationPage = new DeliveryInformationPage(driver);
        privatePolicyPage = new PrivatePolicyPage(driver);
        termsPage = new TermsPage(driver);
        productReturnsPage = new ProductReturnPage(driver);
        siteMapPage = new SiteMapPage(driver);
        brandPage = new BrandPage(driver);
        giftCertificatePage = new GiftCertificatePage(driver);
        specialOffersPage = new SpecialOffersPage(driver);
        affiliateLoginPage = new AffiliateLoginPage(driver);

    }

    @AfterMethod
    public void tearDown(){
        closeBrowser(driver);
    }

    @Test(priority = 1)
    public void verifyRegisterWithMandatoryField(){

        String expectedHeading = "Your Account Has Been Created!";
        String actualProperDetailOne = "Congratulations! Your new account has been successfully created!";
        String actualProperDetailTwo = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String actualProperDetailThree = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String actualProperDetailFour = "contact us";

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateEmail());
        registerPage.enterPhone(prop.getProperty("telephone"));
        registerPage.enterPassword(prop.getProperty("password"));
        registerPage.enterConfirmPass(prop.getProperty("password"));
        registerPage.clickAgree();
        accountSuccessPage = registerPage.clickContinue();

        Assert.assertTrue(accountSuccessPage.displayTagRegisterSuccess());
        Assert.assertEquals(accountSuccessPage.tagHeader(),expectedHeading);
        Assert.assertTrue((accountSuccessPage.expectedProperDetail()).contains(actualProperDetailOne));
        Assert.assertTrue((accountSuccessPage.expectedProperDetail()).contains(actualProperDetailTwo));
        Assert.assertTrue((accountSuccessPage.expectedProperDetail()).contains(actualProperDetailThree));
        Assert.assertTrue((accountSuccessPage.expectedProperDetail()).contains(actualProperDetailFour));

        myAccountPage =  accountSuccessPage.clickContinueMyAccount();
        Assert.assertTrue(myAccountPage.displayEditYourAccount());
    }

    @Test(priority = 2)
    public void testValidateField(){

        registerPage.clickContinue();

        String stringFirstName = "First Name must be between 1 and 32 characters!";
        String stringLastName = "Last Name must be between 1 and 32 characters!";
        String stringEmail = "E-Mail Address does not appear to be valid!";
        String stringPhone = "Telephone must be between 3 and 32 characters!";
        String stringPassword = "Password must be between 4 and 20 characters!";
        String stringWarringDismiss = "Warning: You must agree to the Privacy Policy!";

        Assert.assertEquals(registerPage.errorFirstName(), stringFirstName);
        Assert.assertEquals(registerPage.errorLastName(), stringLastName);
        Assert.assertEquals(registerPage.errorEmail(), stringEmail);
        Assert.assertEquals(registerPage.errorPhone(), stringPhone);
        Assert.assertEquals(registerPage.errorPassword(), stringPassword);
        Assert.assertEquals(registerPage.errorDismiss(), stringWarringDismiss);
    }

    @Test(priority = 3)
    public void verifyRegisterAccountWithAllFields() {

        String expectedProperDetailsOne = "Your Account Has Been Created!";
        String expectedProperDetailsTwo = "Congratulations! Your new account has been successfully created!";
        String expectedProperDetailsThree = "You can now take advantage of member privileges to enhance your online shopping experience with us.";
        String expectedProperDetailsFour = "If you have ANY questions about the operation of this online shop, please e-mail the store owner.";
        String expectedProperDetailsFive = "A confirmation has been sent to the provided e-mail address. If you have not received it within the hour, please ";
        String expectedProperDetailsSix = "contact us";

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateEmail());
        registerPage.enterPhone(prop.getProperty("telephone"));
        registerPage.enterPassword(prop.getProperty("password"));
        registerPage.enterConfirmPass(prop.getProperty("password"));
        registerPage.clickYesSubscribe();
        registerPage.clickAgree();
        accountSuccessPage = registerPage.clickContinue();
        Assert.assertTrue(accountSuccessPage.displayTagRegisterSuccess());
        Assert.assertTrue(accountSuccessPage.displayLogoutOption());

        String actualProperDetails = accountSuccessPage.expectedProperDetail();
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsOne));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsTwo));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsThree));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFour));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsFive));
        Assert.assertTrue(actualProperDetails.contains(expectedProperDetailsSix));
        myAccountPage = accountSuccessPage.clickContinueMyAccount();
        Assert.assertTrue(myAccountPage.displayEditYourAccount());

    }

    @Test(priority = 4)
    public void verifyRegisteringAccountBySubscribingToNewsletter() {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateEmail());
        registerPage.enterPhone(prop.getProperty("telephone"));
        registerPage.enterPassword(prop.getProperty("password"));
        registerPage.enterConfirmPass(prop.getProperty("password"));
        registerPage.clickYesSubscribe();
        registerPage.clickAgree();
        accountSuccessPage = registerPage.clickContinue();
        myAccountPage = accountSuccessPage.clickContinueMyAccount();
        newsletterPage = myAccountPage.clickNewsletter();
        Assert.assertTrue(newsletterPage.displayTagNewsletter());
        Assert.assertTrue(newsletterPage.displayTitleHeader());
    }

    @Test(priority = 5)
    public void verifyRegisteringAccountBySayingNoToNewsletter() {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateEmail());
        registerPage.enterPhone(prop.getProperty("telephone"));
        registerPage.enterPassword(prop.getProperty("password"));
        registerPage.enterConfirmPass(prop.getProperty("password"));
        registerPage.clickNoSubscribe();
        registerPage.clickAgree();
        accountSuccessPage = registerPage.clickContinue();
        myAccountPage = accountSuccessPage.clickContinueMyAccount();
        newsletterPage = myAccountPage.clickNewsletter();
        Assert.assertTrue(newsletterPage.displayTagNewsletter());
        Assert.assertTrue(newsletterPage.displayTitleHeader());

    }

    @Test(priority = 6)
    public void verifyNavigatingToRegisterAccountUsingMultipleWay() {

        Assert.assertTrue(registerPage.displayTagRegister());
        headerOptions.clickOnMyAccount();
        loginPage = headerOptions.clickOnLogin();
        registerPage = loginPage.clickBtnContinue();
        Assert.assertTrue(registerPage.displayTagRegister());
        headerOptions.clickOnMyAccount();
        loginPage = headerOptions.clickOnLogin();
        registerPage = rightColumnOptions.clickRegisterGroup();
        Assert.assertTrue(registerPage.displayTagRegister());

    }

    @Test(priority = 7)
    public void verifyRegisteringAccountByProvidingMismatchingPasswords() {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateEmail());
        registerPage.enterPhone(prop.getProperty("telephone"));
        registerPage.enterPassword(prop.getProperty("password"));
        registerPage.enterConfirmPass(prop.getProperty("invalidPass"));
        registerPage.clickYesSubscribe();
        registerPage.clickAgree();
        registerPage.clickContinue();
        String expectedWarningMessage = "Password confirmation does not match password!";
        Assert.assertEquals(registerPage.errorConfirmPassword(), expectedWarningMessage);
    }

    @Test(priority = 8)
    public void verifyRegisteringAccountUsingExistingEmail() {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(prop.getProperty("emailExist"));
        registerPage.enterPhone(prop.getProperty("telephone"));
        registerPage.enterPassword(prop.getProperty("password"));
        registerPage.enterConfirmPass(prop.getProperty("password"));
        registerPage.clickYesSubscribe();
        registerPage.clickAgree();
        registerPage.clickContinue();
        String expectedWarningMessage = "Warning: E-Mail Address is already registered!";
        Assert.assertEquals(registerPage.errorExistingEmail(), expectedWarningMessage);
    }

    @Test(priority = 9)
    public void verifyRegisteringAccountWithInvalidEmails() throws InterruptedException, IOException {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(prop.getProperty("invalidEmailOne"));
        registerPage.enterPhone(prop.getProperty("telephone"));
        registerPage.enterPassword(prop.getProperty("password"));
        registerPage.enterConfirmPass(prop.getProperty("password"));
        registerPage.clickYesSubscribe();
        registerPage.clickAgree();
        registerPage.clickContinue();

        Thread.sleep(3000);
        Assert.assertEquals(registerPage.getValidateEmail(),
                "Please include an '@' in the email address. 'tranmanhtuan20062k' is missing an '@'.");

        registerPage.clearEmail();
        registerPage.enterEmail(prop.getProperty("invalidEmailTwo"));
        registerPage.clickContinue();

        Thread.sleep(2000);
        Assert.assertEquals(registerPage.getValidateEmail(),
                "Please enter a part following '@'. 'tranmanhtuan20062k@' is incomplete.");

        registerPage.clearEmail();
        registerPage.enterEmail(prop.getProperty("invalidEmailThree"));
        registerPage.clickContinue();

        Thread.sleep(2000);

        String expectedWarningMessage = "E-Mail Address does not appear to be valid!";
        Thread.sleep(2000);
        Assert.assertEquals(registerPage.errorEmail(), expectedWarningMessage);

        registerPage.clearEmail();
        registerPage.enterEmail(prop.getProperty("invalidEmailFour"));
        registerPage.clickContinue();

        Thread.sleep(3000);

        Assert.assertEquals(registerPage.getValidateEmail(), "'.' is used at a wrong position in 'gmail.'.");
    }

    @Test(priority = 10)
    public void verifyRegisterAccountByProvidingInvalidTelephoneNumber() {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateEmail());
        registerPage.enterPhone(prop.getProperty("telephone"));
        registerPage.enterPassword(prop.getProperty("password"));
        registerPage.enterConfirmPass(prop.getProperty("password"));
        registerPage.clickYesSubscribe();
        registerPage.clickAgree();
        registerPage.clickContinue();

        String expectedWarningMessage = "Telephone number does not appear to be valid";

        boolean state = false;

        try {
            String actualWarningMessage = registerPage.getMessageWarningPhone();
            if (actualWarningMessage.equals(expectedWarningMessage)) {
                state = true;
            }
        } catch (NoSuchElementException e) {
            state = false;
        }

        Assert.assertTrue(state);
    }

    @Test(priority = 11)
    public void verifyRegisteringAccountUsingKeyboardKeys() {

        driver = pressKeyMultipleTimes(driver, Keys.TAB, 23);
        driver = enterDetailInterRegisterAccountPageFields(driver);

        Assert.assertTrue(accountSuccessPage.displayTagRegisterSuccess());
        Assert.assertTrue(accountSuccessPage.displayLogoutOption());
    }

    @Test(priority = 12)
    public void verifyPlaceHoldersOfTextFieldsInRegisterAccountPage() {

        String expectedFirstNamePlaceHolderText = "First Name";
        Assert.assertEquals(registerPage.getAttributePlaceholderFirstName(), expectedFirstNamePlaceHolderText);

        String expectedLastNamePlaceHolderText = "Last Name";
        Assert.assertEquals(registerPage.getAttributePlaceholderLastName(), expectedLastNamePlaceHolderText);

        String expectedEmailPlaceHolderText = "E-Mail";
        Assert.assertEquals(registerPage.getAttributePlaceholderEmail(), expectedEmailPlaceHolderText);

        String expectedTelephonePlaceHolderText = "Telephone";
        Assert.assertEquals(registerPage.getAttributePlaceholderPhone(), expectedTelephonePlaceHolderText);

        String expectedPasswordPlaceHolderText = "Password";
        Assert.assertEquals(registerPage.getAttributePlaceholderPassword(), expectedPasswordPlaceHolderText);

        String expectedPasswordConfirmPlaceHolderText = "Password Confirm";
        Assert.assertEquals(registerPage.getAttributePlaceholderConfirmPass(), expectedPasswordConfirmPlaceHolderText);

    }

    @Test(priority = 13)
    public void verifyMandatoryFieldsSymbolAndColorInRegisterAccountPage() {

        String expectedContent = "\"* \"";
        String expectedColor = "rgb(255, 0, 0)";

        Assert.assertEquals(registerPage.getFirstNameLabelContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getFirstNameLabelColor(driver), expectedColor);
        Assert.assertEquals(registerPage.getLastNameContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getLastNameColor(driver), expectedColor);
        Assert.assertEquals(registerPage.getEmailContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getEmailColor(driver), expectedColor);
        Assert.assertEquals(registerPage.getPhoneContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getPhoneColor(driver), expectedColor);
        Assert.assertEquals(registerPage.getPasswordContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getPasswordColor(driver), expectedColor);
        Assert.assertEquals(registerPage.getConfirmPasswordContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getConfirmPasswordColor(driver), expectedColor);
        Assert.assertEquals(registerPage.getAgreeContent(driver), expectedContent);
        Assert.assertEquals(registerPage.getAgreeColor(driver), expectedColor);
    }

    @Test(priority = 14)
    public void verifyRegisteringAccountWithOnlySpaces() throws InterruptedException {

        registerPage.enterFirstName(" ");
        registerPage.enterLastName(" ");
        registerPage.enterEmail(" ");
        registerPage.enterPhone(" ");
        registerPage.enterPassword(" ");
        registerPage.enterConfirmPass(" ");
        registerPage.clickYesSubscribe();
        registerPage.clickAgree();
        registerPage.clickContinue();

        String firstNameWarning = "First Name must be between 1 and 32 characters!";
        String lastNameWarning = "Last Name must be between 1 and 32 characters!";
        String emailWarning = "E-Mail Address does not appear to be valid!";
        String telephoneWarning = "Telephone must be between 3 and 32 characters!";
        String passwordWarning = "Password must be between 4 and 20 characters!";

        Assert.assertEquals(registerPage.errorFirstName(), firstNameWarning);
        Assert.assertEquals(registerPage.errorLastName(), lastNameWarning);
        Assert.assertEquals(registerPage.errorEmail(), emailWarning);
        Assert.assertEquals(registerPage.errorPhone(), telephoneWarning);
        Assert.assertEquals(registerPage.errorPassword(), passwordWarning);

    }

    @Test(priority = 15, dataProvider = "passwordSupplier")
    public void verifyRegisteringAccountAndCheckingPasswordComplexityStandards(HashMap<String, String> hMap) {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateEmail());
        registerPage.enterPhone(prop.getProperty("telephone"));
        registerPage.clickYesSubscribe();
        registerPage.clickAgree();
        registerPage.enterPassword(hMap.get("password"));
        registerPage.enterConfirmPass(hMap.get("password"));
        registerPage.clickContinue();

        Assert.assertTrue(accountSuccessPage.displayTagRegisterSuccess());

        if (currentIndex < supplyPasswords().length - 1 ){
            headerOptions.clickOnMyAccount();
            headerOptions.clickOnLogout();
        }
        currentIndex ++;
    }

    @DataProvider(name = "passwordSupplier")
    private Object[][] supplyPasswords() {
        return new Object[][] {
                { new HashMap<String, String>() {{ put("password", "12345"); }} },
                { new HashMap<String, String>() {{ put("password", "adjfhfh"); }} },
                { new HashMap<String, String>() {{ put("password", "ahdhd1234"); }} },
                { new HashMap<String, String>() {{ put("password", "afdgdg123$"); }} },
                { new HashMap<String, String>() {{ put("password", "GAAGAG12#$"); }} }
        };
    }


    @Test(priority = 16)
    public void verifyRegisteringAccountFieldsHeightWidthAlignment() throws IOException {

        String browserName = prop.getProperty("browserName");

        String expectedHeight = "34px";
        String expectedWidth = "701.25px";

        String actualFirstNameFieldHeight = registerPage.getHeightFirstName();
        String expectedFirstNameFieldWidth = registerPage.getWidthFirstName();
        Assert.assertEquals(actualFirstNameFieldHeight, expectedHeight);
        Assert.assertEquals(expectedFirstNameFieldWidth, expectedWidth);

        registerPage.enterFirstName(" ");
        registerPage.clickContinue();
        String expectedWarning = "First Name must be between 1 and 32 characters!";
        Assert.assertEquals(registerPage.errorFirstName(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearFirstName();
        registerPage.enterFirstName("a");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorFirstName());

        registerPage = new RegisterPage(driver);
        registerPage.clearFirstName();
        registerPage.enterFirstName("ab");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorFirstName());

        registerPage = new RegisterPage(driver);
        registerPage.clearFirstName();
        registerPage.enterFirstName("abcdefghijklmnopq");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorFirstName());

        registerPage = new RegisterPage(driver);
        registerPage.clearFirstName();
        registerPage.enterFirstName("abcdefghijklmnopabcdefghijklmnop");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorFirstName());

        registerPage = new RegisterPage(driver);
        registerPage.clearFirstName();
        registerPage.enterFirstName("abcdefghijklmnopabcdefghijklmnopq");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorFirstName());

        // ---------------------

        registerPage = new RegisterPage(driver);
        String actualLastNameFieldHeight = registerPage.getHeightLastName();
        String actualLastNameFieldWidth = registerPage.getWidthLastName();
        Assert.assertEquals(actualLastNameFieldHeight, expectedHeight);
        Assert.assertEquals(actualLastNameFieldWidth, expectedWidth);
        expectedWarning = "Last Name must be between 1 and 32 characters!";

        registerPage.clearLastName();
        registerPage.enterLastName("");
        registerPage.clickContinue();
        Assert.assertEquals(registerPage.errorLastName(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearLastName();
        registerPage.enterLastName("a");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorLastName());

        registerPage = new RegisterPage(driver);
        registerPage.clearLastName();
        registerPage.enterLastName("ab");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorLastName());

        registerPage = new RegisterPage(driver);
        registerPage.clearLastName();
        registerPage.enterLastName("abcdefghijklmnopq");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorLastName());

        registerPage = new RegisterPage(driver);
        registerPage.clearLastName();
        registerPage.enterLastName("abcdefghijklmnopabcdefghijklmnop");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorLastName());

        registerPage = new RegisterPage(driver);
        registerPage.clearLastName();
        registerPage.enterLastName("abcdefghijklmnopabcdefghijklmnopq");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorLastName());

        registerPage = new RegisterPage(driver);
        String actualEmailFieldHeight = registerPage.getHeightEmail();
        String actualEmailFieldWidth = registerPage.getWidthEmail();
        Assert.assertEquals(actualEmailFieldHeight, expectedHeight);
        Assert.assertEquals(actualEmailFieldWidth, expectedWidth);

        registerPage = new RegisterPage(driver);
        registerPage.clearEmail();
        registerPage.enterEmail("abcdefghijklmnopabcdefghijklmnopqabcdefghijklmnopabcdefghijklmno@gmail.com");
        registerPage.clickContinue();

        Assert.assertFalse(registerPage.displayErrorEmail());

        // ----------------------------------------

        registerPage = new RegisterPage(driver);
        String actualTelephoneFieldHeight = registerPage.getHeightPhone();
        String actualTelephoneFieldWidth = registerPage.getWidthPhone();
        Assert.assertEquals(actualTelephoneFieldHeight, expectedHeight);
        Assert.assertEquals(actualTelephoneFieldWidth, expectedWidth);
        expectedWarning = "Telephone must be between 3 and 32 characters!";

        registerPage.clearPhone();
        registerPage.enterPhone("");
        registerPage.clickContinue();
        Assert.assertEquals(registerPage.errorPhone(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearPhone();
        registerPage.enterPhone("a");
        registerPage.clickContinue();
        Assert.assertEquals(registerPage.errorPhone(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearPhone();
        registerPage.enterPhone("ab");
        registerPage.clickContinue();
        Assert.assertEquals(registerPage.errorPhone(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearPhone();
        registerPage.enterPhone("abc");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorPhone());

        registerPage = new RegisterPage(driver);
        registerPage.clearPhone();
        registerPage.enterPhone("abcd");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorPhone());

        registerPage = new RegisterPage(driver);
        registerPage.clearPhone();
        registerPage.enterPhone("abcdefghijklmnop");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorPhone());

        registerPage = new RegisterPage(driver);
        registerPage.clearPhone();
        registerPage.enterPhone("abcdefghijklmnopabcdefghijklmnop");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorPhone());

        registerPage = new RegisterPage(driver);
        registerPage.clearPhone();
        registerPage.enterPhone("abcdefghijklmnopabcdefghijklmnopq");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorPhone());

        // -----------------------

        registerPage = new RegisterPage(driver);
        String actualPasswordFieldHeight = registerPage.getHeightPass();
        String actualPasswordFieldWidth = registerPage.getWidthPass();
        Assert.assertEquals(actualPasswordFieldHeight, expectedHeight);
        Assert.assertEquals(actualPasswordFieldWidth, expectedWidth);
        expectedWarning = "Password must be between 4 and 20 characters!";
        registerPage.clearPassword();
        registerPage.enterPassword("");
        registerPage.clickContinue();
        Assert.assertEquals(registerPage.errorPassword(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearPassword();
        registerPage.enterPassword("a");
        registerPage.clickContinue();
        Assert.assertEquals(registerPage.errorPassword(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearPassword();
        registerPage.enterPassword("ab");
        registerPage.clickContinue();
        Assert.assertEquals(registerPage.errorPassword(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearPassword();
        registerPage.enterPassword("abc");
        registerPage.clickContinue();
        Assert.assertEquals(registerPage.errorPassword(), expectedWarning);

        registerPage = new RegisterPage(driver);
        registerPage.clearPassword();
        registerPage.enterPassword("abcd");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorPassword());

        registerPage = new RegisterPage(driver);
        registerPage.clearPassword();
        registerPage.enterPassword("abcde");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorPassword());

        registerPage = new RegisterPage(driver);
        registerPage.clearPassword();
        registerPage.enterPassword("abcdefghij");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorPassword());

        registerPage = new RegisterPage(driver);
        registerPage.clearPassword();
        registerPage.enterPassword("abcdefghijabcdefghi");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorPassword());

        registerPage = new RegisterPage(driver);
        registerPage.clearPassword();
        registerPage.enterPassword("abcdefghijabcdefghij");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorPassword());

        registerPage = new RegisterPage(driver);
        registerPage.clearPassword();
        registerPage.enterPassword("abcdefghijabcdefghijk");
        registerPage.clickContinue();
        Assert.assertFalse(registerPage.displayErrorPassword());

        registerPage = new RegisterPage(driver);
        String actualConfirmPasswordFieldHeight = registerPage.getHeightConfirmPassword();
        String actualConfirmPasswordFieldWidth = registerPage.getWidthConfirmPassword();
        Assert.assertEquals(actualConfirmPasswordFieldHeight, expectedHeight);
        Assert.assertEquals(actualConfirmPasswordFieldWidth, expectedWidth);
        driver = negativeURL(driver, prop.getProperty("registerPageURL"));
        driver = CommonUtils.takeScreenshot(driver, "\\screenshots\\registerPageActualAligment.png");
        Assert.assertFalse(CommonUtils.compareToScreenshot("\\Screenshots\\registerPageActualAligment.png",
                    "\\screenshots\\registerPageChromeExpectedAligment.png"));
    }

    @Test(priority = 17)
    public void verifyLeadingAndTrailingSpacesWhileRegisteringAccount() {

        SoftAssert softAssert = new SoftAssert();

        String enteredFirstName = "        " + prop.getProperty("firstName") + "        ";
        registerPage.enterFirstName(enteredFirstName);
        String enteredLastName = "       " + prop.getProperty("lastName") + "       ";
        registerPage.enterLastName(enteredLastName);
        String enteredEmail = "       " + CommonUtils.generateEmail() + "       ";
        registerPage.enterEmail(enteredEmail);
        String enteredTelephone = "       " + prop.getProperty("telephone") + "       ";
        registerPage.enterPhone(enteredTelephone);
        registerPage.enterPassword(prop.getProperty("password"));
        registerPage.enterConfirmPass(prop.getProperty("password"));
        registerPage.clickAgree();
        accountSuccessPage = registerPage.clickContinue();

        myAccountPage = accountSuccessPage.clickContinueMyAccount();
        editAccountPage = myAccountPage.clickEditYourAccount();
        softAssert.assertEquals(editAccountPage.getAttributeFirstNameValue(), enteredFirstName.trim());
        softAssert.assertEquals(editAccountPage.getAttributeLastNameValue(), enteredLastName.trim());
        softAssert.assertEquals(editAccountPage.getAttributeEmailValue(), enteredEmail.trim());
        softAssert.assertEquals(editAccountPage.getAttributePhoneValue(), enteredTelephone.trim());
        softAssert.assertAll();
    }

    @Test(priority = 18)
    public void verifyPrivacyPolicyFieldOnRegisterAccountPage() {
        Assert.assertTrue(registerPage.displayTagRegister());

    }

    @Test(priority = 19)
    public void verifyRegisteringAccountWithoutPrivacyPolicySelection() {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateEmail());
        registerPage.enterPhone(prop.getProperty("telephone"));
        registerPage.enterPassword(prop.getProperty("password"));
        registerPage.enterConfirmPass(prop.getProperty("password"));
        registerPage.clickContinue();

        String expectedWarningMessage = "Warning: You must agree to the Privacy Policy!";
        Assert.assertEquals(registerPage.errorDismiss(), expectedWarningMessage);

    }

    @Test(priority = 20)
    public void  verifyVisibilityToggledOfPasswordsFieldsOnRegisterAccount() {

        Assert.assertEquals(registerPage.getAttributePlaceholderPassword(), "Password");
        Assert.assertEquals(registerPage.getAttributePlaceholderConfirmPass(), "Password Confirm");

    }

    @Test(priority = 21)
    public void verifyWorkingOfEveryLinkOnRegisterAccountPage() {

        driver = registerPage.getDriver();
        headerOptions = new HeaderOptions(driver);
        contactUs = headerOptions.clickContactPhone();
        Assert.assertTrue(contactUs.displayTagContactUs());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        headerOptions = new HeaderOptions(registerPage.getDriver());
        loginPage = headerOptions.clickWishlist();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        headerOptions = new HeaderOptions(registerPage.getDriver());
        shoppingCartPage = headerOptions.clickShoppingCart();
        Assert.assertTrue(shoppingCartPage.displayTagShoppingCart());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        headerOptions = new HeaderOptions(registerPage.getDriver());
        shoppingCartPage = headerOptions.clickCheckout();
        Assert.assertTrue(shoppingCartPage.displayTagShoppingCart());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        headerOptions = new HeaderOptions(registerPage.getDriver());
        headerOptions.clickTitle();
        Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("homePageURL"));
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        headerOptions = new HeaderOptions(registerPage.getDriver());
        searchPage = headerOptions.clickBtnSearch();
        Assert.assertTrue(searchPage.displayTagSearch());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        registerPage = registerPage.clickOnRegisterBreadcrumb();
        Assert.assertTrue(registerPage.displayTagRegister());

        registerPage = new RegisterPage(driver);
        headerOptions = new HeaderOptions(registerPage.getDriver());
        loginPage = headerOptions.clickAccountBreadcrumb();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        headerOptions = new HeaderOptions(registerPage.getDriver());
        homePage = headerOptions.clickHomeBreadcrumb();
        Assert.assertEquals(getPageURL(driver), prop.getProperty("homePageURL"));
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = registerPage.clickLinkLoginPage();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        registerPage.clickPrivatePolicy();
        registerPage.closePrivacyPolicyDialog();

        registerPage = new RegisterPage(driver);
        registerPage.clickContinue();
        Assert.assertTrue(registerPage.displayTagRegister());

        registerPage = new RegisterPage(driver);
        loginPage = rightColumnOptions.clickLoginGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        registerPage = rightColumnOptions.clickRegisterGroup();
        Assert.assertTrue(registerPage.displayTagRegister());

        registerPage = new RegisterPage(driver);
        forgottenPassword = rightColumnOptions.clickForgotPassGroup();
        Assert.assertTrue(forgottenPassword.displayForgottenPassword());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = rightColumnOptions.clickMyAccountGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = rightColumnOptions.clickAddressBookGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = rightColumnOptions.clickWishlistGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = rightColumnOptions.clickOrderHistoryGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = rightColumnOptions.clickDownloadGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = rightColumnOptions.clickPaymentsGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = rightColumnOptions.clickRewardGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = rightColumnOptions.clickReturnGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = rightColumnOptions.clickTransactionGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = rightColumnOptions.clickNewsletterGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        aboutUsPage = footerOption.clickAboutUsInformation();
        Assert.assertTrue(aboutUsPage.displayTagAboutUs());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        deliveryInformationPage = footerOption.clickDeliveryInformation();
        Assert.assertTrue(deliveryInformationPage.displayTagDelivery());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        privatePolicyPage = footerOption.clickPrivatePolicy();
        Assert.assertTrue(privatePolicyPage.displayTagPrivatePolicy());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        termsPage = footerOption.clickTerms();
        Assert.assertTrue(termsPage.displayTerms());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        contactUs = footerOption.clickContactUs();
        Assert.assertTrue(contactUs.displayTagContactUs());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        productReturnsPage = footerOption.clickReturns();
        Assert.assertTrue(productReturnsPage.displayTagProductReturn());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        siteMapPage = footerOption.clickSiteMap();
        Assert.assertTrue(siteMapPage.displayTagSiteMap());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        brandPage = footerOption.clickBrands();
        Assert.assertTrue(brandPage.displayTagBrand());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        giftCertificatePage = footerOption.clickGiftCertificate();
        Assert.assertTrue(giftCertificatePage.displayTagGiftCertificate());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        affiliateLoginPage = footerOption.clickAffiliate();
        Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("affiliateLoginPageURL"));
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        specialOffersPage = footerOption.clickSpecialOffers();
        Assert.assertTrue(specialOffersPage.displayTagSpecialOffers());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = footerOption.clickMyAccountFooter();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = footerOption.clickOrderHistory();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = footerOption.clickWishList();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        registerPage = new RegisterPage(driver);
        loginPage = footerOption.clickNewsletter();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

    }

    @Test(priority = 22)
    public void verifyRegisteringAccountWithoutEnteringPasswordIntoPasswordConfirmField() {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateEmail());
        registerPage.enterPhone(prop.getProperty("telephone"));
        registerPage.enterPassword(prop.getProperty("password"));
        registerPage.clickYesSubscribe();
        registerPage.clickAgree();
        registerPage.clickContinue();

        String expectedWarning = "Password confirmation does not match password!";
        Assert.assertEquals(registerPage.errorConfirmPassword(), expectedWarning);

    }

    @Test(priority = 23)
    public void verifyBreadcrumbURLHeadingTitleOfRegisterAccountPage() {

        Assert.assertTrue(registerPage.displayTagRegister());
        Assert.assertEquals(registerPage.getTextHeaderRegister(), prop.getProperty("registerPageTitle"));
        Assert.assertEquals(getPageURL(driver), prop.getProperty("registerPageURL"));
        Assert.assertEquals(getPageTitle(driver), prop.getProperty("registerPageTitle"));

    }

    @Test(priority = 24)
    public void verifyUIORegisterPage() throws IOException {
        CommonUtils.takeScreenshot(driver, "\\screenshot\\actualRegisterPageUI.png");
        Assert.assertFalse(CommonUtils.compareToScreenshot(
                "\\screenshot\\actualRegisterPageUI.png",
                "\\screenshot\\expectedRegisterPageUI.png"));
    }

    @Test(priority = 25)
    public void verifyRegisteringAccountInDifferentTestEnvironments() {

        registerPage.enterFirstName(prop.getProperty("firstName"));
        registerPage.enterLastName(prop.getProperty("lastName"));
        registerPage.enterEmail(CommonUtils.generateEmail());
        registerPage.enterPhone(prop.getProperty("telephone"));
        registerPage.enterPassword(prop.getProperty("password"));
        registerPage.enterConfirmPass(prop.getProperty("password"));
        registerPage.clickYesSubscribe();
        registerPage.clickAgree();
        accountSuccessPage = registerPage.clickContinue();

        Assert.assertTrue(accountSuccessPage.displayLogoutOption());
        Assert.assertTrue(accountSuccessPage.displayTagRegisterSuccess());
        accountSuccessPage.clickContinueMyAccount();
        Assert.assertEquals(driver.getTitle(), prop.getProperty("accountPageTitle"));
    }
}
