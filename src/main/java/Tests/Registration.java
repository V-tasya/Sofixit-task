package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.time.Instant;

public class Registration extends Driver {

    String input = "Test";
    String time;
    public String time() {
        Instant now = Instant.now();
        long unixtime = now.getEpochSecond();
        time = String.valueOf(unixtime);
        return time;
    }

    @Test(priority = 1)
    public void registration() throws InterruptedException {
        WebElement register = getDriver().findElement(By.xpath("//a[@href='register.htm']"));
        register.click();
        Thread.sleep(4000);

        WebElement firstName = getDriver().findElement(By.id("customer.firstName"));
        firstName.sendKeys(input);

        WebElement lastName = getDriver().findElement(By.id("customer.lastName"));
        lastName.sendKeys(input);

        WebElement address = getDriver().findElement(By.id("customer.address.street"));
        address.sendKeys(input);

        WebElement city = getDriver().findElement(By.id("customer.address.city"));
        city.sendKeys(input);

        WebElement state = getDriver().findElement(By.id("customer.address.state"));
        state.sendKeys(input);

        WebElement zipCode = getDriver().findElement(By.id("customer.address.zipCode"));
        zipCode.sendKeys(input);

        WebElement phone = getDriver().findElement(By.id("customer.phoneNumber"));
        phone.sendKeys(input);

        WebElement ssn = getDriver().findElement(By.id("customer.ssn"));
        ssn.sendKeys(input);

        WebElement userName = getDriver().findElement(By.id("customer.username"));
        userName.sendKeys(time());

        WebElement password = getDriver().findElement(By.id("customer.password"));
        password.sendKeys(input);

        WebElement confirm = getDriver().findElement(By.id("repeatedPassword"));
        confirm.sendKeys(input);
        Thread.sleep(4000);

        WebElement regist = getDriver().findElement(By.xpath("//input[@type='submit' and @value='Register']"));
        regist.click();
        Thread.sleep(4000);
    }
}
