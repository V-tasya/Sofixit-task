package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class TransferFunds {

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
    }

    @Test(priority = 4)
    public void transferFunds() throws InterruptedException {
        String[] inputs = new String[]{"512", "-3", "5000", "aaa", "6*/"};
        for (String input : inputs) {
            WebElement transferFounds = driver.findElement(By.xpath("//a[@href ='transfer.htm']"));
            transferFounds.click();
            Thread.sleep(3000);

            WebElement amount = driver.findElement(By.id("amount"));

            amount.sendKeys(input);
            Thread.sleep(3000);

            WebElement transfer = driver.findElement(By.xpath("//input[@type='submit' and @class='button' and @value='Transfer']"));
            transfer.click();
            Thread.sleep(3000);
            if (input.equals("512") || input.equals("-3") || input.equals("5000")) {
                String transferOutput = driver.findElement(By.xpath("//h1[@class='title' and text()='Transfer Complete!']")).getText();
                System.out.println("For " + input + " " + transferOutput);
            } else if (input.equals("aaa") || input.equals("6*/")) {
               WebElement errorMessage = driver.findElement(By.xpath("//p[@class='error' and contains(text(), 'An internal error has occurred and has been logged')]"));
               System.out.println("For " + input + " " + errorMessage.getText());
            }
        }
    }
    @Test(priority = 5)
    public void quiteDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

}
