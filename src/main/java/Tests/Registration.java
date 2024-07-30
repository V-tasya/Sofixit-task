package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class Registration extends Driver {
    private static final String MOCK_INPUT = "Test";

    @Test(priority = 1)
    public void registration() throws InterruptedException {
        WebElement register = getDriver().findElement(By.xpath("//a[@href='register.htm']"));
        register.click();
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        String[] webElements = new String[]{"customer.firstName", "customer.lastName", "customer.address.street",
                "customer.address.city", "customer.address.state", "customer.address.zipCode", "customer.phoneNumber",
                "customer.ssn", "customer.username", "customer.password", "repeatedPassword"
        };

        for (String webElement : webElements) {
            if (webElement.equals("customer.username")) {
                WebElement userName = getDriver().findElement(By.id(webElement));
                userName.clear();
                userName.sendKeys(Global.getUsername());
            } else {
                WebElement firstName = getDriver().findElement(By.id(webElement));
                firstName.clear();
                firstName.sendKeys(MOCK_INPUT);
            }
        }

        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement regist = getDriver().findElement(By.xpath("//input[@type='submit' and @value='Register']"));
        regist.click();
        getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test(priority = 2, dependsOnMethods = "registration")
    public void printText() {
        System.out.println("Test 1 'Registration'");
        String loggedIn = getDriver().findElement(By.xpath("//p[text()='Your account was created successfully. You are now logged in.']")).getText();
        System.out.println(loggedIn);
        System.out.println();
    }
}