package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.BaseTest;

public class DashboardPage {
    private EventFiringWebDriver driver;

    private By catalog = By.id("subtab-AdminCatalog");
    private By products = By.id("subtab-AdminProducts");
    private By newProduct = By.id("page-header-desc-configuration-add");

    public DashboardPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public void clickProduct() {
        BaseTest.move(driver, catalog);
        BaseTest.click(driver, products);
    }

    public void clickNewProduct() {
        BaseTest.click(driver, newProduct);
    }
}
