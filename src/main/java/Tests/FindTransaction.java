package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class FindTransaction extends Driver {
    private String id;
    private String formattedToday;

    private static final String MOCK_INPUT = "Test";

    private static final String[] fields = new String[]{"transactionId", "transactionDate", "fromDate", "toDate", "amount"};

    @Test(priority = 9, dependsOnMethods = "Tests.RequestLoan.requestLoan")
    public void login() throws InterruptedException {
        WebElement login = getDriver().findElement(By.xpath("//input[@type='text' and @class='input' and @name='username']"));
        login.clear();
        login.sendKeys(Global.getUsername());
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement password = getDriver().findElement(By.xpath("//input[@type='password' and @class='input' and @name='password']"));
        password.clear();
        password.sendKeys(MOCK_INPUT);
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        WebElement logIn = getDriver().findElement(By.xpath("//input[@type='submit' and @class='button' and @value='Log In']"));
        logIn.click();
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        System.out.println("Test 5 'FindTransaction' - login completed");
    }

    public void findTransaction() throws InterruptedException {
        var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='findtrans.htm' and normalize-space(text())='Find Transactions']")));
        element.click();
        //Thread.sleep(2000);
    }

    public String[] fillInputs() {
        id = getDriver().findElement(By.id("accountId")).getText().trim();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        formattedToday = today.format(formatter);
        String[] inputs = new String[]{"512", id, "Test", " ", "1a", "Test1", "*", "https://www.goodhousekeeping.com/life/pets/g4531/cutest-dog-breeds/", formattedToday};
        return inputs;
    }

   @Test(priority = 10, dependsOnMethods = "login", retryAnalyzer = Retry.class)
    public void findById() throws InterruptedException {
        System.out.println("For field " + fields[0]);
        findTransaction();
        String[] inputs = fillInputs();
        for (String input : inputs) {
            System.out.print("For input " + input + " - ");
            findTransaction();
            WebElement element1 = getDriver().findElement(By.id(fields[0]));
            element1.clear();
            element1.sendKeys(input);

            var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            var find1 = wait.until(ExpectedConditions.elementToBeClickable(By.id("findById")));
            find1.click();

            if (input.equals("512") || input.equals(id)) {
                Thread.sleep(2000);
                WebElement element2 = getDriver().findElement(By.xpath("//*[@id='errorContainer']/p"));
                System.out.print(element2.getText() + "\n");
            } else {
                Thread.sleep(2000);
                String element2 = getDriver().findElement(By.xpath("//*[@id='transactionIdError']")).getText();
                System.out.print(element2 + "\n");
            }
        }
        System.out.println();
    }

   @Test(priority = 11, dependsOnMethods = "findById", retryAnalyzer = Retry.class)
    public void findByDate() throws InterruptedException {
        System.out.println("For field " + fields[1]);
        String[] inputs = fillInputs();
        for (String input : inputs) {
            System.out.print("For input " + input + " - ");
            findTransaction();
            WebElement element1 = getDriver().findElement(By.id(fields[1]));
            element1.clear();
            element1.sendKeys(input);

            var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            var find2 = wait.until(ExpectedConditions.elementToBeClickable(By.id("findByDate")));
            find2.click();
            getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            if (input.equals(formattedToday)) {
                Thread.sleep(2000);
                var wait1 = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                WebElement element2 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='resultContainer']/h1")));
                System.out.print(element2.getText() + "\n");
            } else {
                Thread.sleep(2000);
                String element2 = getDriver().findElement(By.xpath("//*[@id='transactionDateError']")).getText();
                System.out.print(element2 + "\n");
            }
        }
        System.out.println();
    }

    @Test(priority = 12, dependsOnMethods = "findByDate", retryAnalyzer = Retry.class)
    public void findByDateRange() throws InterruptedException {
        System.out.println("For field " + fields[2] + " & " + fields[3]);
        String[] inputs = fillInputs();
        for (String input : inputs) {
            System.out.print("For input " + input + " - ");
            findTransaction();
            WebElement element1 = getDriver().findElement(By.id(fields[2]));
            WebElement element2 = getDriver().findElement(By.id(fields[3]));
            element1.clear();
            element2.clear();
            element1.sendKeys(input);
            element2.sendKeys(input);

            var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
            var find3 = wait.until(ExpectedConditions.elementToBeClickable(By.id("findByDateRange")));
            find3.click();
            getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            if (input.equals(formattedToday)) {
                Thread.sleep(2000);
                var wait2 = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                WebElement element3 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='resultContainer']/h1")));
                System.out.print(element3.getText() + "\n");
            } else {
                Thread.sleep(2000);
                String element3 = getDriver().findElement(By.xpath("//*[@id='dateRangeError']")).getText();
                System.out.print(element3 + "\n");
            }
        }
        System.out.println();
    }

    @Test(priority = 13, dependsOnMethods = "findByDateRange", retryAnalyzer = Retry.class)
    public void findByAmount() throws InterruptedException {
        System.out.println("For field " + fields[4]);
        String[] inputs = fillInputs();
        for (String input : inputs) {
            System.out.print("For input " + input + " - ");
            findTransaction();
            WebElement element1 = getDriver().findElement(By.id(fields[4]));
            element1.clear();
            element1.sendKeys(input);

            var wait = new WebDriverWait(getDriver(), Duration.ofSeconds(20));
            var find4 = wait.until(ExpectedConditions.elementToBeClickable(By.id("findByAmount")));
            find4.click();
            getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

            if (input.equals("512")) {
                Thread.sleep(2000);
                var wait3 = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
                WebElement element3 = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='resultContainer']/h1")));
                System.out.print(element3.getText() + "\n");
            } else {
                Thread.sleep(2000);
                String element2 = getDriver().findElement(By.xpath("//*[@id='amountError']")).getText();
                System.out.print(element2 + "\n");
            }
        }
        System.out.println();
    }

}
