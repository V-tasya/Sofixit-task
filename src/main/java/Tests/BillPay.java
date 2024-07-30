package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BillPay extends Driver {

    private String MOCK_INPUT = "Test";

    @Test(priority = 5, dependsOnMethods = "Tests.TransferFunds.transferFunds")
    public void loggIn() {
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
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        System.out.println("Test 3 'Bill Pay'");
    }

    @Test(priority = 6, dependsOnMethods = "loggIn")
    public void Billpay() {
        String[] inputs = new String[]{"12", "Test", " ", "1a", "Test1", "*", "https://www.goodhousekeeping.com/life/pets/g4531/cutest-dog-breeds/"};
        String[] fields = new String[]{"payee.name", "payee.address.street", "payee.address.city", "payee.address.state",
                "payee.address.zipCode", "payee.phoneNumber", "payee.accountNumber", "verifyAccount", "amount"};

        WebElement billpay = getDriver().findElement(By.xpath("//a[@href='billpay.htm']"));
        billpay.click();
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        for (String input : inputs) {
            for (String field : fields) {
                WebElement element = getDriver().findElement(By.name(field));
                element.clear();
                element.sendKeys(input);
            }

            WebElement sendPayment = getDriver().findElement(By.xpath("//input[@value='Send Payment']"));
            sendPayment.click();
            getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

            var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            if (input.equals("12")) {
                WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[@class='title' and text()='Bill Payment Complete']")));
                System.out.println("For " + input + " - " + text.getText());
            } else if (input.equals(" ")) {
                WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("validationModel-amount-empty")));
                System.out.println("For " + input + " - " + text.getText());
            } else if (input.equals("1a")) {
                WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class='error' and text()='An internal error has occurred and has been logged.']")));
                System.out.println("For " + input + " - " + text.getText());
            } else {
                WebElement text = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("validationModel-account-invalid")));
                System.out.println("For " + input + " - " + text.getText());
            }

            billpay = getDriver().findElement(By.xpath("//a[@href='billpay.htm']"));
            billpay.click();
            getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

    }
}