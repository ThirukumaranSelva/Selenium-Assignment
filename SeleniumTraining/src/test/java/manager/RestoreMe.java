package manager;

import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RestoreMe {
    public static void wait(WebDriver driver,WebElement element){

    }
    public static void main(String[] args) {
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--incognito");


    /* // options.setBrowserVersion("114");
        WebDriver driver=new ChromeDriver(options);
        //System.out.println(options.getBrowserVersion());
        driver.get("https://www.coxedge.com/locations");
        // selenium manager -4.6.0
        DesiredCapabilities ds=new DesiredCapabilities();
        System.out.println( ds.getBrowserVersion());*/

        WebDriver driver=new ChromeDriver(options);
        driver.get("https://dashboard-qa.restoreme.care/login");

        WebElement username= driver.findElement(By.xpath("//input[@placeholder='E.g. Jonathan@gmail.com']"));
      //  username.click();
        username.sendKeys("admin1@nurture.com");
        WebElement password= driver.findElement(By.xpath("//input[@placeholder='Enter your password']"));
        password.sendKeys("@5admin1234");

        WebElement submit= driver.findElement(By.xpath("//button[text()='Login']"));
        submit.click();

       // WebElement title=driver.findElement(By.xpath("//span[@title='Providers']"));
        Wait<WebDriver> wait=new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@title='Providers']")));

        WebElement fullName=driver.findElement(By.id("current-user-full-name"));
        fullName.click();

        Actions actions=new Actions(driver);
        WebElement myProfile=driver.findElement(By.xpath("//a[text()='My Profile']"));
        actions.moveToElement(myProfile).click().perform();

        String name="Mr. Admin One";
        String email="admin1@nurture.com";

        String userName=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Mr. Admin One']"))).getText();

        String emailId=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()" +
                "='admin1@nurture.com']"))).getText();

        if ((name.equalsIgnoreCase(userName))&&(email.equalsIgnoreCase(emailId))){
            System.out.println("Pass");
        }
        else System.out.println("Fail");


    }
}
