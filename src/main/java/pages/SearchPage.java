package pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ElementUtils;

import java.time.Duration;
import java.util.List;

public class SearchPage {

    WebDriver driver;
    ElementUtils elementUtils;

    public SearchPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        elementUtils = new ElementUtils(driver);
    }

    @FindBy(xpath = "//ul[@class='breadcrumb']//a[text()='Search']")
    WebElement searchBreadcrumb;

    @FindBy(xpath = "//a[normalize-space()='HP LP3065']")
    WebElement existProduct;

    @FindBy(xpath = "//a[normalize-space()='iMac']")
    WebElement iMacProduct;

    @FindBy(xpath = "//div[@class='product-thumb']")
    List<WebElement> numberOfProducts;

    @FindBy(xpath = "//*[@id=\"content\"]/p[2]")
    WebElement noProduct;

    @FindBy(xpath = "//input[@id='input-search']")
    WebElement searchCriteria;

    @FindBy(xpath = "//input[@id='button-search']")
    WebElement btnSearchCriteria;

    @FindBy(xpath = "//input[@id='description']")
    WebElement searchInProductDescriptionCheckboxBoxField;

    @FindBy(xpath = "//select[@name='category_id']")
    WebElement dropdownCategory;

    @FindBy(xpath = "//input[@name='sub_category']")
    WebElement subCategory;

    @FindBy(xpath = "//a[@id='compare-total']")
    WebElement productCompareLink;

    @FindBy(xpath = "//button[@id='list-view']")
    WebElement listViewOption;

    @FindBy(xpath = "//button[@id='grid-view']")
    WebElement gridViewOption;

    @FindBy(xpath = "//div[@class='alert alert-success alert-dismissible']")
    WebElement successMessage;

    @FindBy(xpath = "//span[text()='Add to Cart']")
    WebElement addToCartOption;

    @FindBy(xpath = "//button[@type='button']//i[@class='fa fa-heart']")
    WebElement addToWishlistOption;

    @FindBy(xpath = "//i[@class='fa fa-exchange']")
    WebElement compareThisProduct;

    @FindBy(xpath = "//div[@class='product-thumb']//img")
    WebElement productImage;

    @FindBy(xpath = "//select[@id='input-sort']")
    WebElement sortByDropdownField;

    @FindBy(xpath = "(//div[@class='product-thumb']//h4//a)[1]")
    WebElement firstProductInSearchResults;

    @FindBy(xpath = "(//div[@class='product-thumb']//h4//a)[2]")
    WebElement secondProductInSearchResults;

    @FindBy(xpath = "(//div[@class='product-thumb']//h4//a)[3]")
    WebElement thirdProductInSearchResults;

    @FindBy(xpath = "(//div[@class='product-thumb']//h4//a)[4]")
    WebElement fourthProductInSearchResults;

    @FindBy(xpath = "//div[@id='content']/h1")
    WebElement pageHeading;

    public String getPageHeading() {
        return pageHeading.getText();
    }

    public String getFirstProductName(){
        return firstProductInSearchResults.getText();
    }

    public String getSecondProductName(){
        return secondProductInSearchResults.getText();
    }

    public String getThirdProductName(){
        return thirdProductInSearchResults.getText();
    }

    public String getFourthProductName(){
        return fourthProductInSearchResults.getText();
    }

    public boolean displayProductSubCategory(){
        return subCategory.isDisplayed();
    }

    public boolean displayCategorySearchResult(){
        return iMacProduct.isDisplayed();
    }

    public boolean displayProductHavingDescription(){
        return iMacProduct.isDisplayed();
    }

    public boolean displayExistProduct(){
        return existProduct.isDisplayed();
    }

    public boolean displayTagSearch(){
        return searchBreadcrumb.isDisplayed();
    }

    public SearchPage clickSearchBreadcrumb(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", searchBreadcrumb);
        searchBreadcrumb.click();
        return new SearchPage(driver);
    }

    public String getMessageNoProduct(){
        return noProduct.getText();
    }

    public int getNumberOfProducts(){
        return numberOfProducts.size();
    }

    public void enterSearchCriteriaField(String productText){
        searchCriteria.sendKeys(productText);
    }

    public String getPlaceholderAttributeSearchKeywords(){
        return searchCriteria.getAttribute("placeholder");
    }

    public void clickBtnSearchCriteria(){
        btnSearchCriteria.click();
    }

    public void selectCheckboxInProductDescription(){
        searchInProductDescriptionCheckboxBoxField.click();
    }

    public void selectCheckboxSearchInSubCategories(){
        subCategory.click();
    }

    public void selectOptionDropdownIndex(int index){
        Select select = new Select(dropdownCategory);
        select.selectByIndex(index);
    }

    public void selectOptionInSortByDropdownField(String sortText){
        Select select = new Select(sortByDropdownField);
        select.selectByVisibleText(sortText);
    }

    public void clickListViewOption(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement listViewButton = wait.until(ExpectedConditions.elementToBeClickable(listViewOption));
        listViewButton.click();
    }

    public void clickGridViewOption(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement gridViewButton = wait.until(ExpectedConditions.elementToBeClickable(gridViewOption));
        gridViewButton.click();
    }

    public void clickAddToCart(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToCartOption);
        addToCartOption.click();
    }

    public String getMessageSuccess(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement successAlert = wait.until(ExpectedConditions.visibilityOf(successMessage));
        return successAlert.getText();
    }

    public void clickToWishList(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addToWishlistOption);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToWishlistOption);
    }

    public void clickToCompareThisProduct(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", compareThisProduct);
        compareThisProduct.click();
    }

    public ProductDisplayPage clickProductImage(){
        productImage.click();
        return new ProductDisplayPage(driver);
    }

    public ProductDisplayPage clickProductName(){
        iMacProduct.click();
        return new ProductDisplayPage(driver);
    }

    public ProductComparePage clickProductCompareLink(){
        productCompareLink.click();
        return new ProductComparePage(driver);
    }

    public SearchPage searchUsingSearchCriteriaFieldInSearchResultsPageUsingKeyboardKeys(String productName){
        elementUtils.pressKeyMultipleTimes(driver, Keys.TAB, 21);
        elementUtils.pressKeyboardKey(Keys.BACK_SPACE);
        elementUtils.enterTextIntoFieldUsingKeyboardKeys(driver, productName);
        elementUtils.pressKeyMultipleTimes(driver, Keys.TAB, 3);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

    public SearchPage verifySearchingByCategoryUsingKeyboardKeys(){
        elementUtils.pressKeyMultipleTimes(driver, Keys.TAB, 22);
        elementUtils.pressKeyboardKey(Keys.ARROW_DOWN);
        elementUtils.pressKeyMultipleTimes(driver, Keys.TAB, 3);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

    public SearchPage verifySearchingInSubcategoriesUsingKeyboardKeys(){
        elementUtils.pressKeyMultipleTimes(driver, Keys.TAB, 23);
        elementUtils.pressKeyboardKey(Keys.SPACE);
        elementUtils.pressKeyMultipleTimes(driver, Keys.TAB, 2);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

    public SearchPage verifySearchingUsingDescriptionUsingKeyboardKeys(String productDescription){
        elementUtils.pressKeyMultipleTimes(driver, Keys.TAB, 21);
        elementUtils.pressKeyboardKey(Keys.BACK_SPACE);
        elementUtils.enterTextIntoFieldUsingKeyboardKeys(driver, productDescription);
        elementUtils.pressKeyMultipleTimes(driver, Keys.TAB, 3);
        elementUtils.pressKeyboardKey(Keys.SPACE);
        elementUtils.pressKeyboardKey(Keys.TAB);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

    public SearchPage verifySearchingInListViewUsingKeyboardKeys(){
        elementUtils.pressKeyMultipleTimes(driver, Keys.TAB, 26);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

    public SearchPage verifySearchingInGridViewUsingKeyboardKeys(){
        elementUtils.pressKeyboardKey(Keys.TAB);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new SearchPage(driver);
    }

    public ProductComparePage verifyNavigatingToProductComparedPageUsingKeyboardKeys(){
        elementUtils.pressKeyboardKey(Keys.TAB);
        elementUtils.pressKeyboardKey(Keys.ENTER);
        return new ProductComparePage(driver);
    }

    public SearchPage verifySortInSearchPageUsingKeyboardKeys(){
        elementUtils.pressKeyMultipleTimes(driver, Keys.TAB, 1);
        elementUtils.pressKeyboardKey(Keys.ARROW_DOWN);
        return new SearchPage(driver);
    }

    public SearchPage verifyProductsCountInSearchPageUsingKeyboardKeys(){
        elementUtils.pressKeyMultipleTimes(driver, Keys.TAB, 30);
        elementUtils.pressKeyboardKey(Keys.ARROW_DOWN);
        return new SearchPage(driver);
    }

}
