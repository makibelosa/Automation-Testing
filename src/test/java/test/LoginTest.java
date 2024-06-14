package test;

import Appliction.LoginPage;
import Data.TestData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void invalidLoginTest() {
        TestData testData = new TestData("invalidUser", "invalidPass");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUsername(testData.getUsername());
        loginPage.enterPassword(testData.getPassword());
        loginPage.clickLogin();

        Assert.assertTrue(driver.findElement(By.cssSelector(".error-message-container")).isDisplayed(), "Error message not displayed");
    }
}
