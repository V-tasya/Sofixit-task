package Tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.time.Duration;

public class Driver {

    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @BeforeClass
    public void initializeDriver() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\37529\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://parabank.parasoft.com/");
        Thread.sleep(4000);
    }

    @AfterClass
    public void quiteDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}