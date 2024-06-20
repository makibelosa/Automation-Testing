package test;

import Appliction.LoginPage;
import Application.ProductsPage;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;
import utilities.ExcelUtils;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {
        String filePath = "src/test/resources/exel.xlsx";  // Corrected file path
        String sheetName = "Sheet1"; // Change as necessary

        ExcelUtils excel = new ExcelUtils(filePath, sheetName);
        int rowCount = excel.getRowCount();
        int colCount = excel.getColumnCount();

        Object[][] data = new Object[rowCount - 1][colCount]; // Skipping the header row

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i - 1][j] = excel.getCellData(i, j);
            }
        }

        excel.closeWorkbook();
        return data;
    }

    @Test(dataProvider = "loginData")
    public void invalidLoginTest(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Thread.sleep(2000);

        // Verify login was not successful
        Assert.assertTrue(driver.findElements(By.cssSelector(".error-message-container")).size() > 0, "Error message not displayed");

        // No further action as this is for invalid login test
    }

    @Test(dataProvider = "loginData")
    public void loginAndAddRemoveProductTest(String username, String password) throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        Thread.sleep(2000);

        // Verify login was successful
        Assert.assertFalse(driver.findElements(By.cssSelector(".error-message-container")).size() > 0, "Error message displayed");

        // Navigate to Products Page
        ProductsPage productsPage = new ProductsPage(driver);

        // Perform action to add the Sauce Labs Backpack to cart
        productsPage.addSauceLabsBackpackToCart();

        // Verify product is added to the cart
        Assert.assertTrue(driver.findElement(By.id("remove-sauce-labs-backpack")).isDisplayed(), "Product not added to cart");

        // Navigate to shopping cart
        productsPage.goToShoppingCart();

        // Verify the cart page is displayed
        Assert.assertTrue(driver.findElement(By.cssSelector(".cart_list")).isDisplayed(), "Cart page not displayed");

        // Close the browser
        driver.quit();
    }
}
