package Appliction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {
    WebDriver driver;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to enter shipping details
    public void enterShippingDetails(String firstName, String lastName, String postalCode) {
        driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys("Samuel");
        driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys("Ramolotsha");
        driver.findElement(By.xpath("//*[@id=\"postal-code\"]")).sendKeys("1458");

    }

    // Method to continue to the next step
    public void clickContinue() {
        driver.findElement(By.id("continue")).click();
    }

    // Method to finish the checkout process
    public void clickFinish() {
        driver.findElement(By.id("finish")).click();
    }

    // Method to complete the checkout process
    public void completeCheckout(String firstName, String lastName, String postalCode) {
        enterShippingDetails(firstName, lastName, postalCode);
        clickContinue();
        clickFinish();
    }
}



