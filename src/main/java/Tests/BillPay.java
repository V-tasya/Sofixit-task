package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class BillPay extends Driver {

    private String MOCK_INPUT = "Test";

    @Test(priority = 5, dependsOnMethods = "Tests.TransferFunds.transferFunds")
    public void loggIn() throws InterruptedException {
        WebElement login = getDriver().findElement(By.xpath("//input[@type='text' and @class='input' and @name='username']"));
        login.clear();
        login.sendKeys(Global.getUsername());
        Thread.sleep(3000);

        WebElement password = getDriver().findElement(By.xpath("//input[@type='password' and @class='input' and @name='password']"));
        password.clear();
        password.sendKeys(MOCK_INPUT);
        Thread.sleep(3000);

        WebElement logIn = getDriver().findElement(By.xpath("//input[@type='submit' and @class='button' and @value='Log In']"));
        logIn.click();
        Thread.sleep(3000);

        System.out.println("Test 3 'Bill Pay'");
    }

    @Test(priority = 6, dependsOnMethods = "loggIn")
    public void Billpay() throws InterruptedException {
        String[] inputs = new String[]{"12", "Test", " ", "1a", "Test1", "*", "https://www.goodhousekeeping.com/life/pets/g4531/cutest-dog-breeds/"};
        String[] fields = new String[]{"payee.name", "payee.address.street", "payee.address.city", "payee.address.state",
                "payee.address.zipCode", "payee.phoneNumber", "payee.accountNumber", "verifyAccount", "amount"};

        WebElement billpay = getDriver().findElement(By.xpath("//a[@href='billpay.htm']"));
        billpay.click();
        Thread.sleep(3000);

        for (String input : inputs) {
            for (String field : fields) {
                WebElement element = getDriver().findElement(By.name(field));
                element.clear();
                element.sendKeys(input);
            }

            WebElement sendPayment = getDriver().findElement(By.xpath("//input[@value='Send Payment']"));
            sendPayment.click();
            Thread.sleep(3000);

            if (input.equals("12")) {
                String text = getDriver().findElement(By.xpath("//h1[@class='title' and text()='Bill Payment Complete']")).getText();
                System.out.println("For " + input + " - " + text);
            } else if (input.equals(" ")) {
                String text2 = getDriver().findElement(By.id("validationModel-amount-empty")).getText();
                System.out.println("For " + input + " - " + text2);
            } else if (input.equals("1a")) {
                String text3 = getDriver().findElement(By.xpath("//p[@class='error' and text()='An internal error has occurred and has been logged.']")).getText();
                System.out.println("For " + input + " - " + text3);
            } else {
                String text4 = getDriver().findElement(By.id("validationModel-account-invalid")).getText();
                System.out.println("For " + input + " - " + text4);
            }

            billpay = getDriver().findElement(By.xpath("//a[@href='billpay.htm']"));
            billpay.click();
            Thread.sleep(3000);
            System.out.println();
        }
    }
}