package selenium.testcase;

import listeners.MyListeners;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import selenium.base.Base;
import utils.CommonUtils;

import java.io.IOException;
import java.util.Properties;

@Listeners(MyListeners.class)
public class Search extends Base {

    public WebDriver driver;
    Properties prop;
    HomePage homePage;
    SearchPage searchPage;
    LoginPage loginPage;
    MyAccountPage accountPage;
    HeaderOptions headerOptions;
    ProductDisplayPage productDisplayPage;
    ProductComparePage productComparePage;
    SiteMapPage siteMapPage;
    FooterOption footerOption;

    @BeforeMethod
    public void setUp(){
        driver = openBrowserAndApplication();
        prop = CommonUtils.loadProperties();
        homePage = new HomePage(driver);
        searchPage = new SearchPage(driver);
        loginPage = new LoginPage(driver);
        accountPage = new MyAccountPage(driver);
        headerOptions = new HeaderOptions(driver);
        productDisplayPage = new ProductDisplayPage(driver);
        productComparePage = new ProductComparePage(driver);
        siteMapPage = new SiteMapPage(driver);
        footerOption = new FooterOption(driver);
    }

    @AfterMethod
    public void tearDown(){
        closeBrowser(driver);
    }

    @Test(priority = 1)
    public void verifySearchWithValidProduct(){
        headerOptions.enterProductNameSearch(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickBtnSearch();
        Assert.assertTrue(searchPage.displayTagSearch());
        Assert.assertTrue(searchPage.displayExistProduct());
    }

    @Test(priority = 2)
    public void verifySearchWithNonExistingProduct(){
        headerOptions.enterProductNameSearch(prop.getProperty("nonExistingProduct"));
        searchPage = headerOptions.clickBtnSearch();
        Assert.assertTrue(searchPage.displayTagSearch());
        Assert.assertEquals(searchPage.getMessageNoProduct(), "There is no product that matches the search criteria.");
    }

    @Test(priority = 3)
    public void verifySearchWithoutEnteringAnyProduct(){
        searchPage = headerOptions.clickBtnSearch();
        Assert.assertTrue(searchPage.displayTagSearch());
        Assert.assertEquals(searchPage.getMessageNoProduct(), "There is no product that matches the search criteria.");
    }

    @Test(priority = 4)
    public void verifySearchAfterLogin(){
        loginPage = homePage.negativeLoginPage();
        accountPage = loginPage.loginToApplication(prop.getProperty("emailExist"), prop.getProperty("password"));
        headerOptions.enterProductNameSearch(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickBtnSearch();
        Assert.assertTrue(searchPage.displayTagSearch());
    }

    @Test(priority = 5)
    public void verifySearchResultingInMultipleProducts(){
        headerOptions.enterProductNameSearch(prop.getProperty("searchTermResultMultipleProducts"));
        searchPage = headerOptions.clickBtnSearch();
        Assert.assertTrue(searchPage.getNumberOfProducts() > 1);
    }

    @Test(priority = 6)
    public void verifySearchFieldsPlaceholders(){
        String expectedSearchBoxPlaceHolderText = "Search";
        Assert.assertEquals(headerOptions.getPlaceholderAttributeSearch(), expectedSearchBoxPlaceHolderText);
        searchPage = headerOptions.clickBtnSearch();
        String expectedSearchCriteriaFieldPlaceHolderText = "Keywords";
        Assert.assertEquals(searchPage.getPlaceholderAttributeSearchKeywords(), expectedSearchCriteriaFieldPlaceHolderText);
    }

    @Test(priority = 7)
    public void verifySearchingForProductUsingSearchCriteriaField(){
        searchPage = headerOptions.clickBtnSearch();
        searchPage.enterSearchCriteriaField(prop.getProperty("existingProduct"));
        searchPage.clickBtnSearchCriteria();
        Assert.assertTrue(searchPage.displayExistProduct());
    }

    @Test(priority = 8)
    public void verifySearchingForProductUsingSomeTextInProductDescription(){
        searchPage = headerOptions.clickBtnSearch();
        searchPage.enterSearchCriteriaField(prop.getProperty("termInProductDescription"));
        searchPage.selectCheckboxInProductDescription();
        searchPage.clickBtnSearchCriteria();
        Assert.assertTrue(searchPage.displayProductHavingDescription());
    }

    @Test(priority = 9)
    public void verifySearchBySelectingSubCategory(){
        searchPage = headerOptions.clickBtnSearch();
        searchPage.enterSearchCriteriaField(prop.getProperty("exitingProductInSubCategory"));
        searchPage.selectOptionDropdownIndex(3);
        searchPage.clickBtnSearchCriteria();
        Assert.assertTrue(searchPage.displayCategorySearchResult());
    }

    @Test(priority = 10)
    public void verifySearchByUsingParentCategoryAndSearchInSubCategoriesOption(){
        searchPage = headerOptions.clickBtnSearch();
        searchPage.enterSearchCriteriaField(prop.getProperty("exitingProductInSubCategory"));
        searchPage.selectOptionDropdownIndex(1);
        searchPage.clickBtnSearchCriteria();
        String expectedMessage = "There is no product that matches the search criteria.";
        Assert.assertEquals(searchPage.getMessageNoProduct(), expectedMessage);
        searchPage.selectCheckboxSearchInSubCategories();
        searchPage.clickBtnSearchCriteria();
        Assert.assertTrue(searchPage.displayProductSubCategory());
    }

    @Test(priority = 11)
    public void verifyProductInSearchResultsInListView() throws InterruptedException {

        headerOptions.enterProductNameSearch(prop.getProperty("exitingProductInSubCategory"));
        searchPage = headerOptions.clickBtnSearch();

        searchPage.clickListViewOption();
        Assert.assertTrue(searchPage.getNumberOfProducts() == 1);

        Thread.sleep(2000);
        searchPage.clickAddToCart();
        String expectedMessage = "Success: You have added iMac to your shopping cart!";
        System.out.println(searchPage.getMessageSuccess());
        Assert.assertEquals(searchPage.getMessageSuccess().replace("×", "").trim(), expectedMessage);

        Thread.sleep(2000);
        searchPage.clickToWishList();
        expectedMessage = "You must login or create an account to save iMac to your wish list!";
        System.out.println(searchPage.getMessageSuccess());
        Assert.assertEquals(searchPage.getMessageSuccess().replace("×", "").trim(), expectedMessage);

        searchPage.clickToCompareThisProduct();
        expectedMessage = "Success: You have added iMac to your product comparison!";
        System.out.println(searchPage.getMessageSuccess());
        Assert.assertEquals(searchPage.getMessageSuccess().replace("×", "").trim(), expectedMessage);

        productDisplayPage = searchPage.clickProductImage();
        Assert.assertTrue(productDisplayPage.displayDetailProduct());

        driver = negativeBack(driver);
        productDisplayPage = searchPage.clickProductName();
        Assert.assertTrue(productDisplayPage.displayDetailProduct());

        driver = negativeBack(driver);
    }

    @Test(priority = 12)
    public void verifyProductInSearchResultsInGridView() {

        headerOptions.enterProductNameSearch(prop.getProperty("exitingProductInSubCategory"));
        searchPage = headerOptions.clickBtnSearch();
        searchPage.clickGridViewOption();
        Assert.assertTrue(searchPage.getNumberOfProducts() == 1);

        searchPage.clickAddToCart();
        String expectedMessage = "Success: You have added iMac to your shopping cart!";
        Assert.assertEquals(searchPage.getMessageSuccess().replace("×", "").trim(), expectedMessage);

        searchPage.clickToWishList();
        expectedMessage = "You must login or create an account to save iMac to your wish list!";
        Assert.assertEquals(searchPage.getMessageSuccess().replace("×", "").trim(), expectedMessage);

        searchPage.clickToCompareThisProduct();
        expectedMessage = "Success: You have added iMac to your product comparison!";
        Assert.assertEquals(searchPage.getMessageSuccess().replace("×", "").trim(), expectedMessage);

        productDisplayPage = searchPage.clickProductImage();
        Assert.assertTrue(productDisplayPage.displayDetailProduct());
        
        driver = negativeBack(driver);
        productDisplayPage = searchPage.clickProductName();
        Assert.assertTrue(productDisplayPage.displayDetailProduct());
        driver = negativeBack(driver);

    }

   @Test(priority = 13)
    public void verifyMultipleProductsInSearchResultsInListViewAndGridView(){
        headerOptions.enterProductNameSearch(prop.getProperty("existingSampleTermResultingInMultipleProducts"));
        searchPage = headerOptions.clickBtnSearch();
        Assert.assertTrue(searchPage.getNumberOfProducts() > 1);
        searchPage.clickListViewOption();
        Assert.assertTrue(searchPage.getNumberOfProducts() > 1);
        searchPage.clickGridViewOption();
        Assert.assertTrue(searchPage.getNumberOfProducts() > 1);

    }

    @Test(priority = 14)
    public void verifyNavigationToProductComparePage(){

        headerOptions.enterProductNameSearch(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickBtnSearch();
        productComparePage = searchPage.clickProductCompareLink();
        Assert.assertTrue(productComparePage.displayTagProductCompare());
    }

    @Test(priority = 15)
    public void verifySortingProductsInSearchResultsPage(){
        headerOptions.enterProductNameSearch(prop.getProperty("existingSampleTermResultingInMultipleProducts"));
        searchPage = headerOptions.clickBtnSearch();
        Assert.assertTrue(searchPage.getNumberOfProducts() > 1);
        searchPage.selectOptionInSortByDropdownField(prop.getProperty("sortOptionOne"));
        Assert.assertEquals(searchPage.getFirstProductName(), "iMac");
        Assert.assertEquals(searchPage.getSecondProductName(), "MacBook");
        Assert.assertEquals(searchPage.getThirdProductName(), "MacBook Air");
        Assert.assertEquals(searchPage.getFourthProductName(), "MacBook Pro");
        searchPage.selectOptionInSortByDropdownField(prop.getProperty("sortOptionTwo"));
        Assert.assertEquals(searchPage.getFirstProductName(), "MacBook Pro");
        Assert.assertEquals(searchPage.getSecondProductName(), "MacBook Air");
        Assert.assertEquals(searchPage.getThirdProductName(), "MacBook");
        Assert.assertEquals(searchPage.getFourthProductName(), "iMac");
        searchPage.selectOptionInSortByDropdownField(prop.getProperty("sortOptionThree"));
        Assert.assertEquals(searchPage.getFirstProductName(), "iMac");
        Assert.assertEquals(searchPage.getSecondProductName(), "MacBook");
        Assert.assertEquals(searchPage.getThirdProductName(), "MacBook Air");
        Assert.assertEquals(searchPage.getFourthProductName(), "MacBook Pro");
        searchPage.selectOptionInSortByDropdownField(prop.getProperty("sortOptionFour"));
        Assert.assertEquals(searchPage.getFirstProductName(), "MacBook Pro");
        Assert.assertEquals(searchPage.getSecondProductName(), "MacBook Air");
        Assert.assertEquals(searchPage.getThirdProductName(), "MacBook");
        Assert.assertEquals(searchPage.getFourthProductName(), "iMac");
    }

    @Test(priority = 16)
    public void verifyNavigatingToSearchPageFromSiteMapPage(){
        siteMapPage = footerOption.clickSiteMap();
        searchPage = siteMapPage.clickOnSearchLink();
        Assert.assertTrue(searchPage.displayTagSearch());
    }

    @Test(priority = 17)
    public void verifySearchPageBreadcrumb(){
        headerOptions.enterProductNameSearch(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickBtnSearch();
        searchPage = searchPage.clickSearchBreadcrumb();
        Assert.assertTrue(searchPage.displayTagSearch());
        homePage = headerOptions.clickTitle();
        Assert.assertEquals(driver.getCurrentUrl(), prop.getProperty("homePageURL"));

    }

    @Test(priority = 18)
    public void verifySearchFunctionalityUsageByUsingKeyboardKeys(){
        searchPage = homePage.searchForAProductUsingKeyboardKeys(prop.getProperty("exitingProductInSubCategory"));
        Assert.assertTrue(searchPage.displayTagSearch());
        Assert.assertTrue(searchPage.getNumberOfProducts() == 1);

        searchPage = searchPage.searchUsingSearchCriteriaFieldInSearchResultsPageUsingKeyboardKeys(prop.getProperty("existingSampleTermResultingInMultipleProducts"));
        Assert.assertTrue(searchPage.getNumberOfProducts() == 4);

        searchPage = searchPage.verifySearchingByCategoryUsingKeyboardKeys();
        Assert.assertTrue(searchPage.getNumberOfProducts() == 2);

        searchPage = searchPage.verifySearchingInSubcategoriesUsingKeyboardKeys();
        Assert.assertTrue(searchPage.getNumberOfProducts() == 3);

        searchPage = searchPage.verifySearchingUsingDescriptionUsingKeyboardKeys(prop.getProperty("termInProductDescription"));
        Assert.assertTrue(searchPage.getNumberOfProducts() == 1);

        searchPage = searchPage.verifySearchingInListViewUsingKeyboardKeys();
        Assert.assertTrue(searchPage.getNumberOfProducts() == 1);

        searchPage = searchPage.verifySearchingInGridViewUsingKeyboardKeys();
        Assert.assertTrue(searchPage.getNumberOfProducts() == 1);

        productComparePage = searchPage.verifyNavigatingToProductComparedPageUsingKeyboardKeys();
        Assert.assertTrue(productComparePage.displayTagProductCompare());
        driver = negativeBack(driver);

        searchPage = searchPage.verifySortInSearchPageUsingKeyboardKeys();
        Assert.assertTrue(searchPage.getNumberOfProducts() == 1);

        searchPage = searchPage.verifyProductsCountInSearchPageUsingKeyboardKeys();
        Assert.assertTrue(searchPage.getNumberOfProducts() == 1);
    }

    @Test(priority = 19)
    public void verifySearchFunctionalityHeadingURLAndTitle(){
        searchPage = headerOptions.clickBtnSearch();
        Assert.assertEquals(searchPage.getPageHeading(), prop.getProperty("searchPageHeading"));
        Assert.assertEquals(getPageTitle(driver), prop.getProperty("searchPageTitle"));
        Assert.assertEquals(getPageURL(driver), prop.getProperty("searchPageURL"));
    }

    @Test(priority = 20)
    public void verifySearchFunctionalityUI() throws IOException {
        searchPage = headerOptions.clickBtnSearch();
        CommonUtils.takeScreenshot(driver,"//screenshot//actualSearchPageUI.png");
        Assert.assertFalse(CommonUtils.compareToScreenshot("//screenshot//actualSearchPageUI.png","//screenshot//expectedSearchPageUI.png"));
    }

    @Test(priority = 21)
    public void verifySearchInAllEnvironments(){
        headerOptions.enterProductNameSearch(prop.getProperty("existingProduct"));
        searchPage = headerOptions.clickBtnSearch();
        Assert.assertTrue(searchPage.displayTagSearch());
        Assert.assertTrue(searchPage.displayExistProduct());
    }
}
