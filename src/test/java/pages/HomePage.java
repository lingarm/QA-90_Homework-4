package pages;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public class HomePage {
    private EventFiringWebDriver driver;

    private String homePageURL = "http://prestashop-automation.qatestlab.com.ua";

    public HomePage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(this.homePageURL);
    }
}
