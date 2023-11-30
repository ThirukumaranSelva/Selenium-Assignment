package SeleniumMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Test1 {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.leafground.com/select.xhtml");
        WebElement country = driver.findElement(By.id("j_idt87:auto-complete_input"));
        country.sendKeys("App");
        //   Thread.sleep(5000);
        //   driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='ui-autocomplete-items " +
                "ui-autocomplete-list ui-widget-content ui-widget ui-corner-all ui-helper-reset']//li")));
        List<WebElement> options = driver.findElements(By.xpath("//ul[@class='ui-autocomplete-items " +
                "ui-autocomplete-list ui-widget-content ui-widget ui-corner-all ui-helper-reset']//li"));

        for (WebElement e : options) {
            System.out.println(e.getText());
            if (e.getText().equalsIgnoreCase("Appium")) {
                e.click();
            }
        }

    }
}
