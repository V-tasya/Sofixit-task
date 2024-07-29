package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class RequestLoan extends Driver {
    private static final String MOCK_INPUT = "Test";

    @Test(priority = 7, dependsOnMethods = "Tests.BillPay.Billpay")
   // @Test(priority = 3, dependsOnMethods = "Tests.Registration.printText")
    public void logIn() throws InterruptedException {
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

        System.out.println("Test 4 'Request Loan'");
    }

    @Test(priority = 8, dependsOnMethods = "logIn")
    public void requestLoan() throws InterruptedException {
        String[] inputs = new String[]{"12", "Test", " ", "1a", "Test1", "*", "https://www.goodhousekeeping.com/life/pets/g4531/cutest-dog-breeds/"};
        String[] fields = new String[]{"amount", "downPayment"};
        for (String input : inputs) {
            WebElement requestLoan = getDriver().findElement(By.xpath("//a[@href='requestloan.htm' and text()='Request Loan']"));
            requestLoan.click();

            for (String field: fields) {
                WebElement element = getDriver().findElement(By.id(field));
                element.clear();
                element.sendKeys(input);
            }

            WebElement applyNow = getDriver().findElement(By.xpath("//input[@type='button' and @class='button' and @value='Apply Now']"));
            applyNow.click();
            Thread.sleep(3000);

            if(input.equals("12")) {
                String text = getDriver().findElement(By.xpath("//h1[@class='title' and text()='Loan Request Processed']")).getText();
                System.out.println("For " + input + " - " + text);
            } else {
                String text1 = getDriver().findElement(By.xpath("//p[@class='error' and contains(text(), 'An internal error has occurred and has been logged')]")).getText();
                System.out.println("For " + input + " - " + text1);
            }
        }
        System.out.println();
    }
}