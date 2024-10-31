package test;

import Appliction.*;
import Utilities.ExcelUtils;
import Utilities.ScreenshotUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.*;
import reporting.ExtentReportManager;

import java.io.IOException;

public class LoginTest extends BaseTest {
    private ScreenshotUtils screenshotUtils;
    private ExtentReports extent;

    @BeforeTest
    public void setUpTest() {
        screenshotUtils = new ScreenshotUtils(driver);
        extent = ExtentReportManager.getInstance(); // Initialize ExtentReports using ExtentReportManager
    }

    @AfterTest
    public void tearDownTest() {
        // Flush the ExtentReports
        if (extent != null) {
            extent.flush();
        }
    }

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
    public void loginAndAddRemoveProductTest(String username, String password) throws InterruptedException, IOException {
        ExtentTest test = extent.createTest("Login and Add/Remove Product Test");
        ProductsPage productsPage = new ProductsPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        CartPage cartPage = new CartPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        try {
            test.log(Status.INFO, "Starting test with username: " + username);

            // First Lodin
            Thread.sleep(2000);
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLogin();
            Thread.sleep(2000);
            test.log(Status.INFO, "Login attempt completed")
                    .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("Login"));

            // Verify login was successful
            Assert.assertFalse(driver.findElements(By.cssSelector(".error-message-container")).size() > 0, "Error message displayed");

            System.out.println("Navigated to Products Page");


            // Add any item to cart
            productsPage.selectItemAndAddToCart("Sauce Labs Backpack");
            Thread.sleep(4000);
            test.log(Status.INFO, "Added Sauce Labs Backpack to cart")
                    .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("AddProduct"));

            // Log out
            Thread.sleep(2000);
            productsPage.logout();
            Thread.sleep(2000);
            System.out.println("Logged out successfully");
            test.log(Status.INFO, "Logged out successfully")
                    .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("logoutLink"));
            Thread.sleep(2000);

            // Second Login
            Thread.sleep(2000);
            loginPage.enterUsername(username);
            loginPage.enterPassword(password);
            loginPage.clickLogin();
            //Thread.sleep(2000);
            test.log(Status.INFO, "Login attempt completed")
                    .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("Login"));
            Thread.sleep(2000);

            // Verify login was successful
            Assert.assertFalse(driver.findElements(By.cssSelector(".error-message-container")).size() > 0, "Error message displayed");

            // Navigate to Products Page
            System.out.println("Navigated to Products Page");

            // Perform action to add the Sauce Labs Backpack to cart
            Thread.sleep(2000);
            productsPage.addSauceLabsOnesieToCart();
           // Thread.sleep(4000);
            test.log(Status.INFO, "Added Sauce Labs Onesie to cart")
                    .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("AddProduct"));
            Thread.sleep(2000);

            // Verify product is added to the cart
            Thread.sleep(2000);
            if (!driver.findElement(By.id("remove-sauce-labs-onesie")).isDisplayed()) {
                test.log(Status.FAIL, "Product not added to cart")
                        .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("ProductNotAdded"));
                Assert.fail("Product not added to cart");
                Thread.sleep(2000);
            }
            test.log(Status.PASS, "Verified product added to cart");

            // Navigate to the shopping cart
            Thread.sleep(2000);
            productsPage.goToShoppingCart();
            //Thread.sleep(4000);
            test.log(Status.INFO, "Navigated to shopping cart")
                    .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("CartPage"));
            Thread.sleep(2000);

            // Verify the cart page is displayed
            Thread.sleep(2000);
            if (!driver.findElement(By.cssSelector(".cart_list")).isDisplayed()) {
                test.log(Status.FAIL, "Cart page not displayed")
                        .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("CartPageNotDisplayed"));
                Assert.fail("Cart page not displayed");
                Thread.sleep(2000);
            }
            test.log(Status.PASS, "Verified cart page is displayed");

            // Proceed to checkout
            Thread.sleep(2000);
            cartPage.proceedToCheckout();
            //Thread.sleep(4000);
            test.log(Status.INFO, "Proceeded to checkout")
                    .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("CheckoutPage"));
            Thread.sleep(2000);

            // Verify the checkout page is displayed
            Thread.sleep(2000);
            if (!driver.findElement(By.id("checkout_info_container")).isDisplayed()) {
                test.log(Status.FAIL, "Checkout page not displayed")
                        .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("CheckoutPageNotDisplayed"));
                Assert.fail("Checkout page not displayed");
                Thread.sleep(2000);
            }

            // Enter shipping details and proceed
            Thread.sleep(2000);
            checkoutPage.enterShippingDetails("Samuel", "Ramolotsha", "1458");
            Thread.sleep(2000);
            checkoutPage.clickContinue();
            Thread.sleep(2000);
            test.log(Status.INFO, "Entered shipping details and continued")
                    .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("ShippingDetails"));

            // Finish the checkout process
            Thread.sleep(2000);
            checkoutPage.clickFinish();
            Thread.sleep(2000);
            test.log(Status.INFO, "Finished the checkout process")
                    .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("FinishCheckout"));
            Thread.sleep(2000);

            // Verify the checkout completion

            if (!driver.findElement(By.cssSelector(".complete-header")).isDisplayed()) {
                test.log(Status.FAIL, "Checkout process not completed")
                        .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("CheckoutNotCompleted"));
                Assert.fail("Checkout process not completed");
            }

            ConfirmationPage confirmationPage = new ConfirmationPage(driver);
            // Verify the order confirmation message
            Thread.sleep(2000);
            String confirmationMessage = confirmationPage.getOrderConfirmationMessage();
            Thread.sleep(2000);
            if (!"THANK YOU FOR YOUR ORDER".equals(confirmationMessage)) {
                test.log(Status.FAIL, "Order confirmation message not displayed")
                        .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("OrderConfirmationNotDisplayed"));
                Assert.fail("Order confirmation message not displayed");
                Thread.sleep(2000);
            }
            test.log(Status.PASS, "Verified order confirmation message");

            // Go back to home page
            Thread.sleep(2000);
            confirmationPage.clickBackHome();
            Thread.sleep(2000);
            test.log(Status.INFO, "Navigated back to home page")
                    .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("BackHome"));
            Thread.sleep(2000);
        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed with exception: " + e.getMessage())
                    .addScreenCaptureFromPath(screenshotUtils.takeScreenshot("Exception"));
            throw e;
        }
    }
}