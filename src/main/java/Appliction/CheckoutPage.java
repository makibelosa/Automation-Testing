package Appliction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;

    // Locators for checkout page elements
    By firstNameField = By.id("first-name");
    By lastNameField = By.id("last-name");
    By postalCodeField = By.id("postal-code");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter shipping details
    public void enterCheckoutInformation(String firstName, String lastName, String postalCode) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
        driver.findElement(By.id("last-name")).sendKeys(lastName);
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
    }

    // Method to continue to the next step
    public void clickContinue() {
        driver.findElement(By.id("continue")).click();
    }

    // Method to finish the checkout process
    public void clickFinish() {
        driver.findElement(By.id("finish")).click();
    }
//hgfhiyjk
    // Method to complete the checkout process
    public void completeCheckout(String firstName, String lastName, String postalCode) {
        enterShippingDetails(firstName, lastName, postalCode);
        clickContinue();
        clickFinish();
    }
}
