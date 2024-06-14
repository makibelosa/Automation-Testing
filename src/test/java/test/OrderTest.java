package test;

import Appliction.*;
import Data.TestData;
import Utilities.ExelReader;
import org.testng.annotations.Test;
import org.testng.Assert;

public class OrderTest extends BaseTest {
    @Test
    public void validLoginAndOrderTest() throws Exception {
        ExelReader excelReader = new ExelReader("src/test/resources/excel.xlsx");
        String username = excelReader.getCellData("Sheet1", 1, 0);
        String password = excelReader.getCellData("Sheet1", 1, 1);
       /* String firstName = excelReader.getCellData("Sheet1", 1, 2);
        String lastName = excelReader.getCellData("Sheet1", 1, 3);
        String postalCode = excelReader.getCellData("Sheet1", 1, 4);*/

        TestData testData = new TestData(username, password);

        LoginPage loginPage = new LoginPage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);
        ConfirmationPage confirmationPage = new ConfirmationPage(driver);

        loginPage.enterUsername(testData.getUsername());
        loginPage.enterPassword(testData.getPassword());
        loginPage.clickLogin();

        productsPage.addSauceLabsBackpackToCart();

        // Validate item is added to cart
        int itemCount = cartPage.getItemCount();
        Assert.assertEquals(itemCount, 1, "Item count in the cart is not as expected.");

        cartPage.proceedToCheckout();

       // checkoutPage.enterCheckoutInformation("//*[@id=\"first-name\"]", "//*[@id=\"last-name\"]", "//*[@id=\"postal-code\"]");
        checkoutPage.clickContinue();
        checkoutPage.clickFinish();

        // Validate the order confirmation message
        String confirmationMessage = confirmationPage.getOrderConfirmationMessage();
        Assert.assertEquals(confirmationMessage, "THANK YOU FOR YOUR ORDER", "Order confirmation message is not as expected.");

        // Go back to the home page
        confirmationPage.clickBackHome();

        // Add further validations for the home page if necessary
    }
}
