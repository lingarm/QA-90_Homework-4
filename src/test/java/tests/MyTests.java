package tests;

import objects.Product;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import utils.BaseTest;

import java.sql.Timestamp;

public class MyTests extends BaseTest {

    EventFiringWebDriver driver = null;
    LoginPage loginPage = null;
    DashboardPage dashboardPage = null;
    NewProductPage newProductPage = null;
    HomePage homePage = null;
    AllProductsPage allProductsPage =null;
    PersonalProductPage personalProductPage = null;

    Product product = null;

    @DataProvider(name = "login")
    public Object[][] login() {
        return new Object[][] {
                {"webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw"}};
    }

    @DataProvider(name = "product")
    public  Object[][] newProduct() {
        String number = Long.toString(new Timestamp(System.currentTimeMillis()).getTime());
        String name = "Samus product " + number;
        String quantity = getRandom(1, 100).toString();
        String price = String.format("%.2f", getRandom(0.1, 100.0));
        Integer properties = 0;
        return new Object[][] {
                {number, name, quantity, price, properties}};
    }

    public Integer getRandom(Integer min, Integer max) {
        return min + (int) (Math.random() * max);
    }

    public Double getRandom(Double min, Double max) {
        return min + (Math.random() * max);
    }

    @BeforeClass
    public void beforeClass() {
        this.driver = getConfiguredDriver();
        this.loginPage = new LoginPage(driver);
    }

        @Test(dataProvider = "login")
        public void login(String email, String password) {
        try {
            this.loginPage = new LoginPage(driver);
            this.loginPage.open();
            this.loginPage.fillEmailInput(email);
            this.loginPage.fillPassInput(password);
            this.loginPage.clickLoginBtn();
        }
        catch(Exception ex) {
            quitDriver(driver);
        }
    }

    @Test(dependsOnMethods = "login", dataProvider = "product")
    public void addNewProduct(String number, String name, String quantity, String price, Integer properties) {
        try {
            this.dashboardPage = new DashboardPage(driver);

            this.dashboardPage.clickProduct();
            this.dashboardPage.clickNewProduct();

            this.product = new Product(number, name, quantity, price, properties);
            this.newProductPage = new NewProductPage(driver, product);

            this.newProductPage.addProductName();
            this.newProductPage.addproductQuantity();
            this.newProductPage.addProductPrice();
            this.newProductPage.clickProductActivate();
            this.newProductPage.isSettingsUpdatedMessage();
            this.newProductPage.closeSettingsUpdatedMessage();
            this.newProductPage.clickProductSave();
            this.newProductPage.isSettingsUpdatedMessage();
            this.newProductPage.closeSettingsUpdatedMessage();
        }
        catch(Exception ex) {
            quitDriver(driver);
        }
    }


    @Test(dependsOnMethods = "addNewProduct")
    public void checkProduct() {
        try {
            this.homePage = new HomePage(driver);

            this.homePage.open();

            this.allProductsPage = new AllProductsPage(driver, this.product);

            this.allProductsPage.clickAllProductsLink();
            this.allProductsPage.isMyProductDisplaying();
            this.allProductsPage.openMyProduct();

            this.personalProductPage = new PersonalProductPage(driver, this.product);

            this.personalProductPage.checkMyProductName();
            this.personalProductPage.checkMyProductQuantity();
            this.personalProductPage.checkMyProductPrice();
        }
        catch(Exception ex) {
            quitDriver(driver);
        }
    }

    @AfterClass
    public void afterClass() {
        quitDriver(driver);
    }
}
