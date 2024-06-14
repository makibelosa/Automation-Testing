package test;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import Utilities.BrowserFactory;

public class BaseTest {
    protected WebDriver driver;
    private boolean keepBrowserOpen;

    @BeforeMethod
    @Parameters({"browser", "url", "keepBrowserOpen"})
    public void setUp(@Optional("chrome") String browser,
                      @Optional("https://www.saucedemo.com/") String url,
                      @Optional("false") String keepBrowserOpen) {
        this.keepBrowserOpen = Boolean.parseBoolean(keepBrowserOpen);
        driver = BrowserFactory.getDriver(browser);
        driver.get(url);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (!keepBrowserOpen && driver != null) {
            driver.quit();
        }
    }
}
