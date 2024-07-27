package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class TransferFounds extends Driver{

    //@Test(priority = 2, dependsOnMethods = {"registration"})
    public void transfer() throws InterruptedException {
        WebElement login = getDriver().findElement(By.xpath("//input[@type='text' and @class='input' and @name='username']"));
        login.click();
        Thread.sleep(4000);

    }

}
