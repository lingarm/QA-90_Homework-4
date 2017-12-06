package pages;

import objects.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.BaseTest;

public class AllProductsPage {
    private EventFiringWebDriver driver;
    private Product product;
    private By productURL;
    private By nextLink = By.xpath("//a[@rel='next']");

    private By allProductsLink = By.xpath("//*[@class='all-product-link pull-xs-left pull-md-right h4']");

    public AllProductsPage(EventFiringWebDriver driver, Product product) {
        this.driver = driver;
        this.product = product;
        this.productURL = By.xpath("//h1//a[contains(@href,'" + this.product.getNumber() + "')]");
    }

    public void clickAllProductsLink() {
        BaseTest.click(driver, allProductsLink);
    }

    public void isMyProductDisplaying() {
        String nextLinkClassName = driver.findElement(nextLink).getAttribute("class");
        System.out.println("String class = " + nextLinkClassName);
        Boolean flag = false;
        do {
            System.out.println("Внутри цикла while");
            if(BaseTest.find(driver, this.productURL)) {
                flag = true;
            }
            else {
                if(!nextLinkClassName.contains("disabled")) {
                    BaseTest.click(driver, nextLink);
                }
                else {
                    System.out.println("The product " + this.product.getName() + " is not displayed on the page");
                    break;
                }
            }
        }while(flag == false);
    }

    public void openMyProduct() {
        BaseTest.click(driver, productURL);
    }
}
