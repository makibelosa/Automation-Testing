package Application;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ProductsPage {
    private WebDriver driver;

    // Locators
    private By addToCartButtonSauceLabsBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By shoppingCartLink = By.id("shopping_cart_container");  // Assuming there's an id for the container

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to add Sauce Labs Backpack to cart
    public void addSauceLabsBackpackToCart() {
        driver.findElement(addToCartButtonSauceLabsBackpack).click();
    }

    // Method to click the shopping cart link
    public void goToShoppingCart() {
        driver.findElement(shoppingCartLink).click();
    }

    // A more reliable way to handle clicking
    public void safeClick(By by) {
        WebElement element = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }
}
