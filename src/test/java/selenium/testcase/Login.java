package selenium.testcase;

import listeners.MyListeners;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import selenium.base.Base;
import utils.CommonUtils;

import java.io.IOException;
import java.util.Properties;

@Listeners(MyListeners.class)
public class Login extends Base{

    public WebDriver driver;
    Properties prop;
    HeaderOptions headerOptions;
    LoginPage loginPage;
    MyAccountPage myAccount;
    ForgottenPassword forgottenPassword;
    LogoutPage logoutPage;
    ChangePasswordPage changePasswordPage;
    RegisterPage registerPage;
    RightColumnOptions rightColumnOptions;
    ContactUsPage contactUs;
    ShoppingCartPage shoppingCart;
    HomePage homePage;
    SearchPage searchPage;
    FooterOption footerOption;
    AboutUsPage aboutUsPage;
    DeliveryInformationPage deliveryPage;
    PrivatePolicyPage privatePolicyPage;
    TermsPage termsPage;
    ProductReturnPage returnPage;
    SiteMapPage siteMapPage;
    BrandPage brandPage;
    GiftCertificatePage giftCertificatePage;
    SpecialOffersPage specialOffersPage;
    AffiliateLoginPage affiliateLoginPage;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        headerOptions = new HeaderOptions(driver);
        headerOptions.clickOnMyAccount();
        loginPage = headerOptions.clickOnLogin();
        myAccount = new MyAccountPage(driver);
        forgottenPassword = new ForgottenPassword(driver);
        registerPage = new RegisterPage(driver);
        rightColumnOptions = new RightColumnOptions(driver);
        contactUs = new ContactUsPage(driver);
        shoppingCart = new ShoppingCartPage(driver);
        homePage = new HomePage(driver);
        footerOption = new FooterOption(driver);
        aboutUsPage = new AboutUsPage(driver);
        deliveryPage = new DeliveryInformationPage(driver);
        privatePolicyPage = new PrivatePolicyPage(driver);
        termsPage = new TermsPage(driver);
        returnPage = new ProductReturnPage(driver);
        siteMapPage = new SiteMapPage(driver);
        brandPage = new BrandPage(driver);
        giftCertificatePage = new GiftCertificatePage(driver);
        specialOffersPage = new SpecialOffersPage(driver);
        affiliateLoginPage = new AffiliateLoginPage(driver);
        headerOptions = new HeaderOptions(driver);

    }

    @Test(priority = 1)
    public void verifyLoginCredentials(){
        loginPage.clickLogin();

        String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
    }

    @Test(priority = 2)
    public void verifyLoginWithInvalidCredentials(){

        loginPage.enterEmail(CommonUtils.generateEmail());
        loginPage.enterPass(prop.getProperty("invalidPass"));
        loginPage.clickLogin();

        String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
    }

    @Test(priority = 3)
    public void verifyLoginWithInvalidPass(){

        loginPage.enterEmail(prop.getProperty("emailExist"));
        loginPage.enterPass(prop.getProperty("invalidPass"));
        loginPage.clickLogin();

        String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
    }

    @Test(priority = 4)
    public void verifyLoginWithInvalidEmailCredentials(){

        loginPage.enterEmail(CommonUtils.generateEmail());
        loginPage.enterPass(prop.getProperty("password"));
        loginPage.clickLogin();

        String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
    }

    @Test(priority = 5)
    public void verifyLoginWithValidCredentials(){

        Assert.assertTrue(loginPage.displayTagLogin());
        loginPage.enterEmail(prop.getProperty("emailExist"));
        loginPage.enterPass(prop.getProperty("password"));
        MyAccountPage accountPage = loginPage.clickLogin();
        Assert.assertTrue(accountPage.displayTagAccount());
        Assert.assertTrue(accountPage.displayLogoutOption());
    }

    @Test(priority = 6)
    public void verifyForgottenPasswordLinkOnLoginPage(){

        Assert.assertTrue(loginPage.displayTagLogin());
        forgottenPassword = rightColumnOptions.clickForgotPassGroup();
        Assert.assertTrue(forgottenPassword.displayForgottenPassword());
    }

    @Test(priority = 7)
    public void verifyLoggingIntoTheApplicationUsingKeyboardKeys(){
        driver = pressKeyMultipleTimes(driver, Keys.TAB, 23);
        driver = enterDetailInterRegisterAccountPageFields(driver);
        myAccount = new MyAccountPage(driver);
        Assert.assertTrue(myAccount.displayTagAccount());
    }

    @Test(priority = 8)
    public void verifyLoginFieldsPlaceholders(){
        String expectedEmailPlaceholder = "E-Mail Address";
        String expectedPasswordPlaceholder = "Password";
        Assert.assertEquals(loginPage.getEmailPlaceholder(), expectedEmailPlaceholder);
        Assert.assertEquals(loginPage.getPasswordPlaceholder(), expectedPasswordPlaceholder);
    }

    @Test(priority = 9)
    public void verifyBrowserBackAfterLogin(){
        loginPage.enterEmail(prop.getProperty("emailExist"));
        loginPage.enterPass(prop.getProperty("password"));
        loginPage.clickLogin();
        driver = negativeBack(driver);
        rightColumnOptions.clickMyAccountGroup();
        Assert.assertTrue(myAccount.displayTagAccount());

    }

    @Test(priority = 10)
    public void verifyBrowserBackAfterLoggingOut(){
        loginPage.enterEmail(prop.getProperty("emailExist"));
        loginPage.enterPass(prop.getProperty("password"));
        myAccount = loginPage.clickLogin();
        logoutPage = myAccount.clickLogoutOption();
        driver = negativeBack(driver);
        myAccount = new MyAccountPage(driver);
        myAccount.clickEditYourAccount();
        Assert.assertTrue(loginPage.displayTagLogin());
    }

    @Test(priority = 11)
    public void verifyLoginWithInactiveCredentials(){
        loginPage.enterEmail(prop.getProperty("inActiveEmail"));
        loginPage.enterPass(prop.getProperty("password"));
        loginPage.clickLogin();
        String expectedWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(loginPage.getWarningMessage(), expectedWarning);
    }

    @Test(priority = 12)
    public void verifyNumberOfUnsuccessfulLoginAttempts() throws InterruptedException {
        loginPage.enterEmail(CommonUtils.generateEmail());
        loginPage.enterPass(prop.getProperty("password"));
        String messageWarning = "Warning: No match for E-Mail Address and/or Password.";
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), messageWarning);
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), messageWarning);
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), messageWarning);
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), messageWarning);
        loginPage.clickLogin();
        Assert.assertEquals(loginPage.getWarningMessage(), messageWarning);
        loginPage.clickLogin();
        messageWarning = "Warning: Your account has exceeded allowed number of login attempts. Please try again in 1 hour.";
        Thread.sleep(2000);
        Assert.assertEquals(loginPage.getWarningMessage(),messageWarning);
        Thread.sleep(5000);
    }

    @Test(priority = 13)
    public void verifyTextEnteredIntoPasswordFieldIsToggledHideVisibility(){

        String passwordType = "password";
        Assert.assertEquals(loginPage.getPasswordFieldType(), passwordType);
    }

    @Test(priority = 14)
    public void verifyCopyingOfTextEnteredIntoPasswordField(){

        String passwordText = prop.getProperty("samplePass");
        loginPage.enterPass(passwordText);
        driver = loginPage.selectPasswordFieldTextAndCopy(driver);
        driver = loginPage.pastedCopiedPasswordTextIntoEmailField(driver);

        Assert.assertNotEquals(loginPage.getTextCopiedIntoEmailField(), passwordText);
    }

    @Test(priority = 15)
    public void verifyPasswordIsStoredInHTMLCodeOfThePage(){
        String passwordText = prop.getProperty("samplePass");
        loginPage.enterPass(passwordText);
        Assert.assertFalse(getHTMLCodeOfThePage().contains(passwordText));
        loginPage.clickLogin();
        Assert.assertTrue(getHTMLCodeOfThePage().contains(passwordText));
    }

    @Test(priority = 16)
    public void verifyLoggingIntoApplicationAfterChainingPassword() throws IOException {
        String oldPassword = null;
        String newPassword = null;
        oldPassword = prop.getProperty("samplePasswordTwo");
        newPassword = prop.getProperty("samplePass");
        loginPage.enterEmail(prop.getProperty("emailExistTwo"));
        loginPage.enterPass(oldPassword);
        myAccount = loginPage.clickLogin();
        changePasswordPage = myAccount.clickChangeYourPassword();
        changePasswordPage.enterPassword(newPassword);
        changePasswordPage.enterConfirmPassword(newPassword);

        String messageSuccess = "Success: Your password has been successfully updated.";
        Assert.assertEquals(myAccount.getMessageSuccess(), messageSuccess);
        logoutPage = myAccount.clickLogoutOption();
        headerOptions.clickOnMyAccount();
        loginPage = headerOptions.clickOnLogin();
        loginPage.enterEmail(prop.getProperty("emailExistTwo"));
        loginPage.enterPass(oldPassword);
        loginPage.clickLogin();
        String messageWarning = "Warning: No match for E-Mail Address and/or Password.";
        Assert.assertEquals(loginPage.getWarningMessage(), messageWarning);
        loginPage.clearPass();
        loginPage.enterPass(newPassword);
        myAccount = loginPage.clickLogin();
        Assert.assertTrue(myAccount.displayTagAccount());
        CommonUtils.setProperties("samplePasswordTwo", newPassword, prop);
        CommonUtils.setProperties("samplePass", oldPassword, prop);
    }

    @Test(priority = 17)
    public void verifyNavigatingToDifferentPagedFromLoginPage(){
        driver = loginPage.getDriver();
        contactUs = headerOptions.clickContactPhone();
        Assert.assertTrue(contactUs.displayTagContactUs());
        driver = negativeBack(driver);

        driver = loginPage.getDriver();
        loginPage = headerOptions.clickWishlist();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        driver = loginPage.getDriver();
        shoppingCart = headerOptions.clickShoppingCart();
        Assert.assertTrue(shoppingCart.displayTagShoppingCart());
        driver = negativeBack(driver);

        driver = loginPage.getDriver();
        shoppingCart = headerOptions.clickCheckout();
        Assert.assertTrue(shoppingCart.displayTagShoppingCart());
        driver = negativeBack(driver);

        driver = loginPage.getDriver();
        homePage = headerOptions.clickTitle();
        Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("homePageURL"));
        driver = negativeBack(driver);

        driver = loginPage.getDriver();
        searchPage = headerOptions.clickBtnSearch();
        Assert.assertTrue(searchPage.displayTagSearch());
        driver = negativeBack(driver);

        loginPage = headerOptions.clickLoginBreadcrumb();
        Assert.assertTrue(loginPage.displayTagLogin());

        driver = loginPage.getDriver();
        loginPage = headerOptions.clickAccountBreadcrumb();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        driver = loginPage.getDriver();
        homePage = headerOptions.clickHomeBreadcrumb();
        Assert.assertEquals(prop.getProperty("homePageURL"),getPageURL(driver));
        driver = negativeBack(driver);

        registerPage = loginPage.clickBtnContinue();
        Assert.assertTrue(registerPage.displayTagRegister());
        driver = negativeBack(driver);

        forgottenPassword = loginPage.clickForgottenPassLink();
        Assert.assertTrue(forgottenPassword.displayForgottenPassword());
        driver = negativeBack(driver);

        loginPage.clickLogin();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = negativeBack(driver);

        driver = loginPage.getDriver();
        loginPage = rightColumnOptions.clickLoginGroup();
        Assert.assertTrue(loginPage.displayTagLogin());

        driver = loginPage.getDriver();
        registerPage = rightColumnOptions.clickRegisterGroup();
        Assert.assertTrue(registerPage.displayTagRegister());

        driver = loginPage.getDriver();
        forgottenPassword = rightColumnOptions.clickForgotPassGroup();
        Assert.assertTrue(forgottenPassword.displayForgottenPassword());

        driver = loginPage.getDriver();
        loginPage = rightColumnOptions.clickMyAccountGroup();
        Assert.assertTrue(myAccount.displayTagAccount());

        driver = loginPage.getDriver();
        loginPage = rightColumnOptions.clickAddressBookGroup();
        Assert.assertTrue(loginPage.displayTagLogin());

        driver = loginPage.getDriver();
        loginPage = rightColumnOptions.clickWishlistGroup();
        Assert.assertTrue(loginPage.displayTagLogin());

        driver = loginPage.getDriver();
        loginPage = rightColumnOptions.clickOrderHistoryGroup();
        Assert.assertTrue(loginPage.displayTagLogin());

        driver = loginPage.getDriver();
        loginPage = rightColumnOptions.clickDownloadGroup();
        Assert.assertTrue(loginPage.displayTagLogin());

        driver = loginPage.getDriver();
        loginPage = rightColumnOptions.clickPaymentsGroup();
        Assert.assertTrue(loginPage.displayTagLogin());

        driver = loginPage.getDriver();
        loginPage = rightColumnOptions.clickRewardGroup();
        Assert.assertTrue(loginPage.displayTagLogin());

        driver = loginPage.getDriver();
        loginPage = rightColumnOptions.clickReturnGroup();
        Assert.assertTrue(loginPage.displayTagLogin());

        driver = loginPage.getDriver();
        loginPage = rightColumnOptions.clickTransactionGroup();
        Assert.assertTrue(loginPage.displayTagLogin());

        driver = loginPage.getDriver();
        loginPage = rightColumnOptions.clickNewsletterGroup();
        Assert.assertTrue(loginPage.displayTagLogin());

        aboutUsPage = footerOption.clickAboutUsInformation();
        Assert.assertTrue(aboutUsPage.displayTagAboutUs());
        driver = negativeBack(driver);

        deliveryPage = footerOption.clickDeliveryInformation();
        Assert.assertTrue(deliveryPage.displayTagDelivery());
        driver = negativeBack(driver);

        privatePolicyPage = footerOption.clickPrivatePolicy();
        Assert.assertTrue(privatePolicyPage.displayTagPrivatePolicy());
        driver = negativeBack(driver);

        termsPage = footerOption.clickTerms();
        Assert.assertTrue(termsPage.displayTerms());
        driver = negativeBack(driver);

        contactUs = footerOption.clickContactUs();
        Assert.assertTrue(contactUs.displayTagContactUs());
        driver = negativeBack(driver);

        returnPage = footerOption.clickReturns();
        Assert.assertTrue(returnPage.displayTagProductReturn());
        driver = negativeBack(driver);

        siteMapPage = footerOption.clickSiteMap();
        Assert.assertTrue(siteMapPage.displayTagSiteMap());
        driver = negativeBack(driver);

        brandPage = footerOption.clickBrands();
        Assert.assertTrue(brandPage.displayTagBrand());
        driver = negativeBack(driver);

        giftCertificatePage = footerOption.clickGiftCertificate();
        Assert.assertTrue(giftCertificatePage.displayTagGiftCertificate());
        driver = negativeBack(driver);

        affiliateLoginPage = footerOption.clickAffiliate();
        Assert.assertTrue(affiliateLoginPage.displayTagLogin());
        driver = negativeBack(driver);

        specialOffersPage = footerOption.clickSpecialOffers();
        Assert.assertTrue(specialOffersPage.displayTagSpecialOffers());
        driver = negativeBack(driver);

        loginPage = footerOption.clickMyAccountFooter();
        Assert.assertTrue(loginPage.displayTagLogin());

        loginPage = footerOption.clickOrderHistory();
        Assert.assertTrue(loginPage.displayTagLogin());

        loginPage = footerOption.clickWishList();
        Assert.assertTrue(loginPage.displayTagLogin());

        loginPage = footerOption.clickNewsletter();
        Assert.assertTrue(loginPage.displayTagLogin());

    }

    @Test(priority = 18)
    public void verifyDifferentWaysOfNavigatingToLoginPage(){
        registerPage = loginPage.clickBtnContinue();
        loginPage = rightColumnOptions.clickLoginGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        loginPage.getDriver();
        loginPage = rightColumnOptions.clickLoginGroup();
        Assert.assertTrue(loginPage.displayTagLogin());
        driver = loginPage.getDriver();
        headerOptions.clickOnMyAccount();
        loginPage = headerOptions.clickOnLogin();
        Assert.assertTrue(loginPage.displayTagLogin());
    }

    @Test(priority = 19)
    public void verifyBreadCrumbPageHeadingTitleAndPageURLOfLoginPage(){
        Assert.assertTrue(loginPage.displayTagLogin());
        Assert.assertEquals(getPageTitle(driver), prop.getProperty("loginPageTitle"));
        Assert.assertEquals(getPageURL(driver),prop.getProperty("loginPageURL"));
        Assert.assertEquals(loginPage.getTextHeadingPageOne(),prop.getProperty("headingLoginOne"));
        Assert.assertEquals(loginPage.getTextHeadingPageTwo(),prop.getProperty("headingLoginTwo"));
    }

    @Test(priority = 20)
    public void verifyUIOLoginPage() throws IOException {
        CommonUtils.takeScreenshot(driver, "\\screenshot\\actualLoginPageUI.png");
        Assert.assertFalse(CommonUtils.compareToScreenshot(
                 "\\screenshot\\actualLoginPageUI.png",
                "\\screenshot\\expectedLoginPageUI.png"));
    }

    @Test(priority = 21)
    public void verifyLoginFunctionalityInAllEnvironments(){
        loginPage.enterEmail(prop.getProperty("emailExist"));
        loginPage.enterPass(prop.getProperty("password"));
        myAccount = loginPage.clickLogin();
        Assert.assertTrue(myAccount.displayLogoutOption());
        Assert.assertTrue(myAccount.displayTagAccount());
    }

    @AfterMethod
    public void tearDown(){
        closeBrowser(driver);
    }
}
