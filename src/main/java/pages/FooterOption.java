package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FooterOption {

    WebDriver driver;
    public FooterOption(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='About Us']")
    WebElement aboutUs;

    @FindBy(xpath = "//a[normalize-space()='Delivery Information']")
    WebElement delivery;

    @FindBy(xpath = "//a[normalize-space()='Privacy Policy']")
    WebElement privatePolicy;

    @FindBy(xpath = "//a[normalize-space()='Terms & Conditions']")
    WebElement terms;

    @FindBy(xpath = "//a[normalize-space()='Contact Us']")
    WebElement contactUs;

    @FindBy(xpath = "//a[@href='https://tutorialsninja.com/demo/index.php?route=account/return/add'][normalize-space()='Returns']")
    WebElement returns;

    @FindBy(xpath = "//a[normalize-space()='Site Map']")
    WebElement siteMap;

    @FindBy(xpath = "//a[normalize-space()='Brands']")
    WebElement brands;

    @FindBy(xpath = "//a[normalize-space()='Gift Certificates']")
    WebElement gift;

    @FindBy(xpath = "//a[normalize-space()='Affiliate']")
    WebElement affiliate;

    @FindBy(xpath = "//a[normalize-space()='Specials']")
    WebElement specials;

    @FindBy(xpath = "//a[contains(text(),'My Account')]")
    WebElement myAccount;

    @FindBy(xpath = "//a[normalize-space()='Order History']")
    WebElement orderHistory;

    @FindBy(xpath = "//a[normalize-space()='Wish List']")
    WebElement wishList;

    @FindBy(xpath = "//a[normalize-space()='Newsletter']")
    WebElement newsletter;

    public AboutUsPage clickAboutUsInformation(){
        aboutUs.click();
        return new AboutUsPage(driver);
    }

    public DeliveryInformationPage clickDeliveryInformation(){
        delivery.click();
        return new DeliveryInformationPage(driver);
    }

    public PrivatePolicyPage clickPrivatePolicy(){
        privatePolicy.click();
        return new PrivatePolicyPage(driver);
    }

    public TermsPage clickTerms(){
        terms.click();
        return new TermsPage(driver);
    }

    public ContactUsPage clickContactUs(){
        contactUs.click();
        return new ContactUsPage(driver);
    }

    public ProductReturnPage clickReturns(){
        returns.click();
        return new ProductReturnPage(driver);
    }

    public SiteMapPage clickSiteMap(){
        siteMap.click();
        return new SiteMapPage(driver);
    }

    public BrandPage clickBrands(){
        brands.click();
        return new BrandPage(driver);
    }

    public GiftCertificatePage clickGiftCertificate(){
        gift.click();
        return  new GiftCertificatePage(driver);
    }

    public AffiliateLoginPage clickAffiliate(){
        affiliate.click();
        return new AffiliateLoginPage(driver);
    }

    public SpecialOffersPage clickSpecialOffers(){
        specials.click();
        return new SpecialOffersPage(driver);
    }

    public LoginPage clickMyAccountFooter(){
        myAccount.click();
        return new LoginPage(driver);
    }

    public LoginPage clickOrderHistory(){
        orderHistory.click();
        return new LoginPage(driver);
    }

    public LoginPage clickWishList(){
        wishList.click();
        return new LoginPage(driver);
    }

    public LoginPage clickNewsletter(){
        newsletter.click();
        return new LoginPage(driver);
    }
}
