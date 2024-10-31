package Appliction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private WebDriver driver;

    // Locators
    private By cartItems = By.className("cart_item");
    private By checkoutButton = By.xpath("//*[@id=\"checkout\"]");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to count the number of items in the cart
    public int getItemCount() {
        return driver.findElements(cartItems).size();
    }

    // Method to proceed to checkout
    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }
}
