package pages;

import objects.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.Assert;

public class PersonalProductPage {
    private EventFiringWebDriver driver;
    private Product product;
    private By nameTag = By.xpath("//h1[@itemprop='name']");
    private By quantityTag = By.xpath("//div[@class='product-quantities']/span");
    private By priceTag = By.xpath("//span[@itemprop='price']");
    private String nameValue;
    private String quantityValue;
    private  String priceValue;

    public PersonalProductPage(EventFiringWebDriver driver, Product product) {
        this.driver = driver;
        this.product = product;
        this.nameValue = this.driver.findElement(nameTag).getText();
        this.quantityValue = this.driver.findElement(quantityTag).getText().replaceAll("[^0-9]", "");
        this.priceValue = this.driver.findElement(priceTag).getAttribute("content");
}

    public void checkMyProductName() {
        Assert.assertEquals(nameValue.toUpperCase(), (product.getName()).toUpperCase(), "name");
    }

    public void checkMyProductQuantity() {
        Assert.assertEquals(quantityValue, product.getQuantity(), "quantity");
    }

    public void checkMyProductPrice() {
        Assert.assertEquals(priceValue, product.getPrice(), "price");
    }
}
