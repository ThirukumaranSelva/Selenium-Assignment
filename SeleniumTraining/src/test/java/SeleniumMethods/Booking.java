package SeleniumMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Booking {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.airindia.com/in/en/book/search-flights.html");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement fromDropdown=driver.findElement(By.xpath("//input[@id=\"From\"]"));

        Wait<WebDriver> wait=new WebDriverWait(driver, Duration.ofSeconds(60));
        wait.until(ExpectedConditions.visibilityOf(fromDropdown));
        fromDropdown.click();
        fromDropdown.sendKeys("Del");

        List<WebElement> countries=driver.findElements(By.xpath("//div [@class='row airport-country-detail']"));

      //  System.out.println(driver.getPageSource());
        wait.until(ExpectedConditions.visibilityOfAllElements(countries));
        for (WebElement e:countries){
            System.out.println(e.getText());
            if (e.getText().trim().equalsIgnoreCase("Delhi, India, IN")){
                e.click();
            }
        }
    }
}
