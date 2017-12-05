package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;


public abstract class BaseTest {

    /*public enum Actions { Click, Move, Find, SendKeys }*/

    private static WebDriver getDriver() {
        String browser = Properties.getBrowser();
        /*switch (browser) {
            case "chrome":
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(BaseTest.class.getResource( "/chromedriver.exe").getFile()).getPath());
                return new ChromeDriver();
            case "firefox":
            case "ie":
            case "internet explorer":
            default:
                System.setProperty(
                        "webdriver.chrome.driver",
                        new File(BaseTest.class.getResource("/chromedriver.exe").getFile()).getPath());
                return new ChromeDriver();
        }*/
        System.setProperty(
                "webdriver.chrome.driver",
                new File(BaseTest.class.getResource("/chromedriver.exe").getFile()).getPath());
        return new ChromeDriver();
    }

    public static EventFiringWebDriver getConfiguredDriver() {
        WebDriver driver = getDriver();
        driver.manage().window().maximize();
        EventFiringWebDriver webDriver = new EventFiringWebDriver(driver);
        webDriver.register(new EventHandler());
        return webDriver;
    }

    /*public Boolean waitAndDo(WebDriver driver, By element, Actions action) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

        switch(action) {
            case Find:
                return true;
            case Move:
                return true;
            case Click:
                driver.findElement(element).click();
                return true;
            case SendKeys:
                return true;
            default:
                return false;
        }
    }*/

    private static void wait(WebDriver driver, By element) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public static boolean find(WebDriver driver, By element) {
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        wait(driver, element);
        if(driver.findElement(element).isDisplayed()) {return true;}
        else {return false;}
    }

    public static void click(WebDriver driver, By element) {
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        wait(driver, element);
        driver.findElement(element).click();
    }

    public static void move(WebDriver driver, By element) {
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        wait(driver, element);
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(element), 1, 1).build().perform();
    }

    public static void sendKeys(WebDriver driver, By element, String message) {
        //WebDriverWait wait = new WebDriverWait(driver, 10);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        wait(driver, element);
        driver.findElement(element).sendKeys(Keys.CONTROL+"a");
        driver.findElement(element).sendKeys(message);
    }

    public static void quitDriver(WebDriver driver) {
        driver.quit();
    }
}