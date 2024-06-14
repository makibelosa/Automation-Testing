import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumTest {
    public static ChromeOptions options;
    public static WebDriver driver;

    @BeforeTest
    void Setup() {
        // Initialize ChromeOptions
        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Set the path for the ChromeDriver
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/test/resources/chromedriver.exe");

        // Initialize WebDriver with ChromeOptions
        driver = new ChromeDriver(options);

        // Open the URL
        driver.get("https://www.saucedemo.com/");
    }

    @Test
    void teststeps() {
        // Find the username field by its XPath and enter text
        driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("problem_user");

        // Find the username field by its XPath and enter text
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");


        // Find the login button by its XPath and click it
        driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
    }
}
