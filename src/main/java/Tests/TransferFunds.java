package Tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TransferFunds extends Driver  {

    private static final String MOCK_INPUT = "Test";

    @Test(priority = 3, dependsOnMethods = "Tests.Registration.printText")
    public void loggin() {
        WebElement login = getDriver().findElement(By.xpath("//input[@type='text' and @class='input' and @name='username']"));
        login.clear();
        login.sendKeys(Global.getUsername());
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement password = getDriver().findElement(By.xpath("//input[@type='password' and @class='input' and @name='password']"));
        password.clear();
        password.sendKeys(MOCK_INPUT);
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement logIn = getDriver().findElement(By.xpath("//input[@type='submit' and @class='button' and @value='Log In']"));
        logIn.click();
        getDriver().manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

        System.out.println("Test 2 'Transfer Funds'");
    }

    @Test(priority = 4, dependsOnMethods = "loggin")
    public void transferFunds() throws InterruptedException {
        String[] inputs = new String[]{"512", "-3", " ", "5000", "aaa", "6*/", "https://www.goodhousekeeping.com/life/pets/g4531/cutest-dog-breeds/"};
        for (String input : inputs) {
            WebElement transferFounds = getDriver().findElement(By.xpath("//a[@href ='transfer.htm']"));
            transferFounds.click();
            getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            WebElement amount = getDriver().findElement(By.id("amount"));
            amount.clear();
            amount.sendKeys(input);
            getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            WebElement transfer = getDriver().findElement(By.xpath("//input[@type='submit' and @class='button' and @value='Transfer']"));
            transfer.click();

            if (input.equals("512") || input.equals("-3") || input.equals("5000")) {
                Thread.sleep(2000);
                WebElement text = getDriver().findElement(By.xpath("//h1[@class='title' and text()='Transfer Complete!']"));
                System.out.println("For " + input + " - " + text.getText());
            } else if (input.equals("aaa") || input.equals("6*/") || input.equals("https://www.goodhousekeeping.com/life/pets/g4531/cutest-dog-breeds/") || input.equals(" ")) {
                Thread.sleep(2000);
                WebElement errorMessage = getDriver().findElement(By.xpath("//p[@class='error' and contains(text(), 'An internal error has occurred and has been logged')]"));
                System.out.println("For " + input + " - " + errorMessage.getText());
            }
        }
        System.out.println();
    }

}