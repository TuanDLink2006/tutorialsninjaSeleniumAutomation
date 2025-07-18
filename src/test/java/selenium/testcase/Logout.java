package selenium.testcase;

import listeners.MyListeners;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.*;
import selenium.base.Base;
import utils.CommonUtils;

import java.io.IOException;
import java.util.Properties;

@Listeners(MyListeners.class)
public class Logout extends Base {

    public WebDriver driver;
    Properties prop;
    HomePage homePage;
    LoginPage loginPage;
    RegisterPage registerPage;
    MyAccountPage myAccountPage;
    HeaderOptions headerOptions;
    LogoutPage logoutPage;
    RightColumnOptions rightColumnOptions;

    @AfterMethod
    public void tearDown(){
        closeBrowser(driver);
    }

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        headerOptions = new HeaderOptions(driver);
        rightColumnOptions = new RightColumnOptions(driver);
    }

    @Test(priority = 1)
    public void verifyLoggingOutUsingMyAccountDropMenu(){
        loginPage = homePage.negativeLoginPage();
        myAccountPage = loginPage.loginToApplication(prop.getProperty("emailExist"), prop.getProperty("password"));
        headerOptions.clickOnMyAccount();
        logoutPage = headerOptions.clickOnLogout();
        Assert.assertTrue(logoutPage.displayTagLogout());
        headerOptions.clickOnMyAccount();
        Assert.assertFalse(headerOptions.displayLogout());
        homePage = logoutPage.clickBtnContinue();
        Assert.assertEquals(getPageURL(driver), prop.getProperty("homePageURL"));
    }

    @Test(priority = 2)
    public void verifyLoginOutUsingLogoutRightColumnOption(){
        loginPage = homePage.negativeLoginPage();
        myAccountPage = loginPage.loginToApplication(prop.getProperty("emailExist"), prop.getProperty("password"));
        logoutPage = rightColumnOptions.clickLogoutGroup();
        Assert.assertTrue(logoutPage.displayTagLogout());
        headerOptions.clickOnMyAccount();
        Assert.assertFalse(headerOptions.displayLogout());
        homePage = logoutPage.clickBtnContinue();
        Assert.assertEquals(getPageURL(driver), prop.getProperty("homePageURL"));
    }

    @Test(priority = 3)
    public void verifyLoggingOutAndBrowningBack(){
        loginPage = homePage.negativeLoginPage();
        myAccountPage = loginPage.loginToApplication(prop.getProperty("emailExist"), prop.getProperty("password"));
        headerOptions.clickOnMyAccount();
        logoutPage = headerOptions.clickOnLogout();
        driver = negativeBack(driver);
        driver = refreshPage(driver);
        Assert.assertTrue(loginPage.displayTagLogin());
    }

    @Test(priority = 4)
    public void verifyThereIsNoLogoutOptionBeforeLogin(){
        headerOptions.clickOnMyAccount();
        Assert.assertFalse(headerOptions.displayLogout());
    }

    @Test(priority = 5)
    public void verifyThereIsNoRightColumnLogoutOptionBeforeLogin(){
        registerPage = homePage.negativeRegisterPage();
        Assert.assertFalse(rightColumnOptions.displayLogout());
    }

    @Test(priority = 6)
    public void verifyLoginAfterLogout(){
        loginPage = homePage.negativeLoginPage();
        myAccountPage = loginPage.loginToApplication(prop.getProperty("emailExist"), prop.getProperty("password"));
        headerOptions.clickOnMyAccount();
        logoutPage = headerOptions.clickOnLogout();
        headerOptions.clickOnMyAccount();
        headerOptions.clickOnLogin();
        myAccountPage = loginPage.loginToApplication(prop.getProperty("emailExist"), prop.getProperty("password"));
    }

    @Test(priority = 7)
    public void verifyBreadcrumbTitleHeadingAndURLOfAccountLogoutPage(){
        loginPage = homePage.negativeLoginPage();
        myAccountPage = loginPage.loginToApplication(prop.getProperty("emailExist"), prop.getProperty("password"));
        headerOptions.clickOnMyAccount();
        logoutPage = headerOptions.clickOnLogout();
        Assert.assertEquals(getPageTitle(driver), prop.getProperty("accountLogoutPageTitle"));
        Assert.assertEquals(getPageURL(driver), prop.getProperty("accountLogoutPageURL"));
        Assert.assertEquals(logoutPage.getTextHeadingLogout(), prop.getProperty("accountLogoutPageHeading"));
        Assert.assertTrue(logoutPage.displayTagLogout());
    }

    @Test(priority = 8)
    public void verifyUIOfLogoutOptionAndAccountLogoutPage() throws IOException {
        loginPage = homePage.negativeLoginPage();
        myAccountPage = loginPage.loginToApplication(prop.getProperty("emailExist"), prop.getProperty("password"));
        headerOptions.clickOnMyAccount();
        logoutPage = headerOptions.clickOnLogout();
        CommonUtils.takeScreenshot(driver,"\\screenshot\\actualAccountLogoutPage.png");
        Assert.assertFalse(CommonUtils.compareToScreenshot("\\screenshot\\actualAccountLogoutPage.png","\\screenshot\\expectedAccountLogoutPage.png"));
    }

    @Test(priority = 9)
    public void verifyAccountLogoutFunctionality(){
        loginPage = homePage.negativeLoginPage();
        myAccountPage = loginPage.loginToApplication(prop.getProperty("emailExist"), prop.getProperty("password"));
        headerOptions.clickOnMyAccount();
        logoutPage = headerOptions.clickOnLogout();
        Assert.assertTrue(logoutPage.displayTagLogout());
        homePage = logoutPage.clickBtnContinue();
        Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("homePageURL"));
        headerOptions.clickOnMyAccount();
        Assert.assertFalse(headerOptions.displayLogout());
    }


}
