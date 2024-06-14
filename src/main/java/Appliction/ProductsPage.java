package Appliction;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    private WebDriver driver;

    // Locators
    private By addToCartButtonSauceLabsBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By addToCartButtonSauceLabsBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    // Add more locators for other products as needed

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to add Sauce Labs Backpack to cart
    public void addSauceLabsBackpackToCart() {
        driver.findElement(addToCartButtonSauceLabsBackpack).click();
    }

    // Method to add Sauce Labs Bike Light to cart
    public void addSauceLabsBikeLightToCart() {
        driver.findElement(addToCartButtonSauceLabsBikeLight).click();
    }
}
