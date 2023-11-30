package assignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Testing {
    public static void main(String[] args) throws InterruptedException {
        ChromeOptions options=new ChromeOptions();
        options.addExtensions(new File("APP.crx"));
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver(options);
        driver.manage().window().maximize();
        Actions actions=new Actions(driver);
       driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        //driver.navigate().back();
 /*   //    driver.get("https://rahulshettyacademy.com/AutomationPractice/");
  //      driver.navigate().back();
        String url="https://rahulshettyacademy.com/";
        driver.navigate().to("https://google.com/");
        driver.navigate().back();
//        driver.navigate().forward();
//        driver.navigate().to(url);
//        driver.navigate().refresh();*/

        String parent =driver.getWindowHandle();
        Set<String> windows= driver.getWindowHandles();
        Iterator<String> iterator=windows.iterator();
            while (iterator.hasNext()){
                if (iterator.next().equals(parent)) {
                    driver.switchTo().window(parent);
                }
            }


        actions.keyDown(Keys.ALT).keyUp(Keys.SHIFT).sendKeys("p").keyUp(Keys.ALT).keyUp(Keys.SHIFT).perform();
      /*  actions.moveToLocation(1415,40).click().perform();

        driver.get("https://sqa.stackexchange.com/questions/12500/how-to-send-keyboard-shortcuts-to-browser-using-selenium-and-nodejs");
        List<WebElement> list = driver.findElements(By.xpath("//select[@id='dropdown-class-example']//option"));
//        Select select=new Select(list);
        for (WebElement e:list){
            System.out.println(e.getText());
        }
        WebElement list1 = driver.findElement(By.id("dropdown-class-example"));
        Select select=new Select(list1);
        select.selectByIndex(2);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.SHIFT+"P");
   */
        driver.manage().getCookies();
    }
}
