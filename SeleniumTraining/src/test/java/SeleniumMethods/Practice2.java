package SeleniumMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;


public class Practice2 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.findElement(By.id("openwindow")).click();
        String window = driver.getWindowHandle();
/*
        System.out.println(driver.getCurrentUrl());
        Set<String> windows = driver.getWindowHandles();
        Iterator<String> iterator = windows.iterator();
        String newWindow;
        while (iterator.hasNext()) {
            newWindow = iterator.next();
            if (newWindow != window) {
                driver.switchTo().window(newWindow);
            } else {
                driver.switchTo().window(window);
            }
        }
        System.out.println(driver.getCurrentUrl());
        */

        driver.findElement(By.id("autocomplete")).sendKeys("IND");
        Thread.sleep(3000);
       List< WebElement> country=driver.findElements(By.xpath("//ul[@id='ui-id-1']//div"));
       for (WebElement e:country){
          // System.out.println(e.getText());
           if (e.getText().equalsIgnoreCase("British Indian Ocean Territory")){
               e.click();
           }
       }
       //wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@id='ui-id-1']/li/div")));

        FluentWait<WebDriver> wait=
                new FluentWait<>(driver)
                        .pollingEvery(Duration.ofSeconds(2))
                        .withTimeout(Duration.ofSeconds(10))
                        .ignoring(NoSuchElementException.class);

    }
}