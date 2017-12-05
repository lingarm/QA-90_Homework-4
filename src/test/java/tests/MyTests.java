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
        System.out.println("Before Class");
        this.driver = getConfiguredDriver();
        this.loginPage = new LoginPage(driver);
        //this.dashboardPage = new DashboardPage(driver);
        //this.newProductPage = new NewProductPage(driver);
    }

        @Test
        public void first() {
            System.out.println("First");
        }

        @Test(dataProvider = "login")
        public void login(String email, String password) {
        try {
            System.out.println("Test Class");
            //LoginPage loginPage = new LoginPage();
            this.loginPage.open();
            this.loginPage.fillEmailInput(email);
            this.loginPage.fillPassInput(password);
            this.loginPage.clickLoginBtn();
        }
        catch(Exception ex) {
            //quitDriver(driver);
        }
    }

    @Test(dependsOnMethods = "login", dataProvider = "product")
    public void addNewProduct(String number, String name, String quantity, String price, Integer properties) {
        dashboardPage = new DashboardPage(driver);

        dashboardPage.clickProduct();
        dashboardPage.clickNewProduct();

        product = new Product(number, name, quantity, price, properties);
        newProductPage = new NewProductPage(driver, product);

        newProductPage.addProductName();
        newProductPage.addproductQuantity();
        newProductPage.addProductPrice();
        newProductPage.clickProductActivate();
        newProductPage.isSettingsUpdatedMessage();
        newProductPage.closeSettingsUpdatedMessage();
        newProductPage.clickProductSave();
        newProductPage.isSettingsUpdatedMessage();
        newProductPage.closeSettingsUpdatedMessage();
    }

    @Test(dependsOnMethods = "addNewProduct")
    public void checkProduct() {
        homePage = new HomePage(driver);

        homePage.open();

        allProductsPage = new AllProductsPage(driver, this.product);

        allProductsPage.clickAllProductsLink();
        allProductsPage.isMyProductDisplaying();
        allProductsPage.openMyProduct();
        allProductsPage.checkMyProductName();
    }

    @Test
    public void third() {
        System.out.println("Third");
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After Class");
        //quitDriver(driver);
    }
}
