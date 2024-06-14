package Appliction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ConfirmationPage {
    private WebDriver driver;

    // Locators
    private By orderConfirmationMessage = By.cssSelector(".complete-header");
    private By backHomeButton = By.id("back-to-products");

    public ConfirmationPage(WebDriver driver) {
        this.driver = driver;
    }

    // Method to get the order confirmation message
    public String getOrderConfirmationMessage() {
        return driver.findElement(orderConfirmationMessage).getText();
    }

    // Method to go back to home page after order confirmation
    public void clickBackHome() {
        driver.findElement(backHomeButton).click();
    }
}
