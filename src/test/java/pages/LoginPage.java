package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import utils.BaseTest;
import utils.Properties;

public class LoginPage {
    private EventFiringWebDriver driver;

    private By emailInput = By.id("email");
    private By passInput = By.id("passwd");
    private By loginBtn = By.name("submitLogin");

    public LoginPage(EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(Properties.getBaseAdminUrl());
    }

    public void fillEmailInput(String email) {
        BaseTest.sendKeys(driver, emailInput, email);
    }

    public void fillPassInput(String password) {
        BaseTest.sendKeys(driver, passInput, password);
    }

    public void clickLoginBtn() {
        driver.findElement(loginBtn).click();
        BaseTest.click(driver, loginBtn);
    }
}
