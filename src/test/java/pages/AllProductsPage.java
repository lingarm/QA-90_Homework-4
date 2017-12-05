package pages;

import objects.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;
import utils.BaseTest;

public class AllProductsPage {
    private EventFiringWebDriver driver;
    private Product product;
    private By productURL;

    private By allProductsLink = By.className("all-product-link pull-xs-left pull-md-right h4");

    public AllProductsPage(EventFiringWebDriver driver, Product product) {
        this.driver = driver;
        this.product = product;
        this.productURL = By.xpath("//h1//a[contains(@href,'" + this.product.getNumber() + "')]");
    }

    public void clickAllProductsLink() {
        System.out.println("До");
        BaseTest.click(driver, allProductsLink);
        System.out.println("После");
    }

    public void isMyProductDisplaying() {
        /*if(BaseTest.find(driver, this.productURL)) System.out.println("Продукт найден");
        else System.out.println("Продукт не найден");*/
        Assert.assertTrue(BaseTest.find(driver, this.productURL), "The product " + this.product.getName() + " is not displayed on the page");
    }

    public void openMyProduct() {
        BaseTest.click(driver, productURL);
    }

    public void checkMyProductName() {
        System.out.println("checkMyProductName");
    }
}
