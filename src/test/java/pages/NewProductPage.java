package pages;

import objects.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.BaseTest;

public class NewProductPage {
    private EventFiringWebDriver driver;
    private Product product;

    private By productNameInput = By.id("form_step1_name_1");
    private By productQuantityInput = By.id("form_step1_qty_0_shortcut");
    private By productPriceInput = By.id("form_step1_price_shortcut");
    private By productActivate = By.className("switch-input ");
    private By productSettingsUpdatedMessage = By.className("growl-message");
    private By productSettingsUpdatedMessageClose = By.className("growl-close");
    private By productSaveBtn = By.xpath("//*[@class='btn btn-primary js-btn-save']");

    public NewProductPage(EventFiringWebDriver driver, Product product) {
        this.driver = driver;
        this.product = product;
    }

    public void addProductName() {
        BaseTest.sendKeys(driver, productNameInput, product.getName());
    }

    public void addproductQuantity() {
        BaseTest.sendKeys(driver, productQuantityInput, product.getQuantity());
    }

    public void addProductPrice() {
        BaseTest.sendKeys(driver, productPriceInput, product.getPrice());
    }

    public void clickProductActivate() {
        BaseTest.click(driver, productActivate);
    }

    public void isSettingsUpdatedMessage() {
        BaseTest.find(driver, productSettingsUpdatedMessage);
    }

    public void closeSettingsUpdatedMessage () {
        BaseTest.click(driver, productSettingsUpdatedMessageClose);
    }

    public void clickProductSave() {
        BaseTest.click(driver, productSaveBtn);
    }
}
