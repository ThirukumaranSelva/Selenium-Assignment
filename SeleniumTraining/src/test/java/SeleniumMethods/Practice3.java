package SeleniumMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class Practice3 {
        public static void main(String[] args) {
            WebDriverManager.chromedriver().setup();
            WebDriver driver=new ChromeDriver();
            driver.get("https://www.airindia.com/in/en/book/search-flights.html");
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            Actions actions = new Actions(driver);
            WebElement fromDropdown=driver.findElement(By.xpath("//input[@id=\"From\"]"));

            Wait<WebDriver> wait=new WebDriverWait(driver, Duration.ofSeconds(2));
            wait.until(ExpectedConditions.visibilityOf(fromDropdown));
            fromDropdown.click();
            fromDropdown.sendKeys("Del");


            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='airport-code-detail']")));
            List<WebElement> countries=driver.findElements(By.xpath("//span[@class='airport-code-detail']"));
            for (WebElement e:countries){
                System.out.println(e.getText());
                if (e.getText().equalsIgnoreCase("DEL")){
//               wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div [@class='row " +
//                       "airport-country-detail']")));
                    //e.click();
                    JavascriptExecutor js = (JavascriptExecutor)driver;
                    js.executeScript("arguments[0].click();", e);

                }
            }

           Clipboard toolkit= Toolkit.getDefaultToolkit().getSystemClipboard();
           Transferable str= toolkit.getContents(null);
            try {
               String s= (String) str.getTransferData(DataFlavor.stringFlavor);
            } catch (UnsupportedFlavorException | IOException e) {
                throw new RuntimeException(e);
            }

        }
    }