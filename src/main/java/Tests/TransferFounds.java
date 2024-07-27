package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class TransferFounds {

    private WebDriver driver;
    private static final String MOCK_INPUT = "Test";

    @Test(priority = 3)
    public void loggin() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://parabank.parasoft.com/");
        WebElement login = driver.findElement(By.xpath("//input[@type='text' and @class='input' and @name='username']"));
        login.sendKeys(Global.getUsername());
        Thread.sleep(3000);

        WebElement password = driver.findElement(By.xpath("//input[@type='password' and @class='input' and @name='password']"));
        password.sendKeys(MOCK_INPUT);
        Thread.sleep(3000);

        WebElement logIn = driver.findElement(By.xpath("//input[@type='submit' and @class='button' and @value='Log In']"));
        logIn.click();
        Thread.sleep(3000);

        System.out.println("Test 2");
        String accountOverview = driver.findElement(By.xpath("//h1[contains(@class, 'title') and contains(text(), 'Accounts Overview')]")).getText();
        System.out.println(accountOverview);
    }


}
