package SeleniumMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Practice1 {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://zero.webappsecurity.com/login.html");
        System.out.println("Current url: " + driver.getCurrentUrl());
        System.out.println("Title: " + driver.getTitle());

        List<WebElement> list = driver.findElements(By.xpath("//select[@id='dropdown-class-example']//option"));
        for (WebElement e:list){
            System.out.println(e.getText());
        }

        WebElement list1 = driver.findElement(By.id("dropdown-class-example"));
       Select select=new Select(list1);
       select.selectByIndex(2);

        String source = driver.getPageSource();
        System.out.println(source);
        driver.manage().window().fullscreen();
        driver.manage().window().minimize();
        driver.manage().window().maximize();


        driver.navigate().to("https://rahulshettyacademy.com/AutomationPractice/");
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();

        driver.close();
        driver.quit();

        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        driver.findElement(By.id("openwindow")).click();
        String window = driver.getWindowHandle();

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
        System.out.println("New URL:" + driver.getCurrentUrl());
        driver.close();
        driver.switchTo().window(window);

        System.out.println("Old URL:" + driver.getCurrentUrl());

        driver.findElement(By.id("alertbtn")).click();
        String alert = driver.switchTo().alert().getText();
        System.out.println("Alert:" + alert);
        driver.switchTo().alert().accept();

        driver.findElement(By.id("confirmbtn")).click();
        String confirmAlert = driver.switchTo().alert().getText();
        System.out.println("Confirm Alert:" + confirmAlert);
        driver.switchTo().alert().dismiss();

        driver.switchTo().activeElement();

        /*
        driver.findElement(By.id("opentab")).click();
//        Actions actions=new Actions(driver);
//        actions.keyDown(Keys.CONTROL).sendKeys(Keys.TAB).keyUp(Keys.CONTROL).build().perform();
//        System.out.println(driver.getCurrentUrl());

        String tab = driver.getWindowHandle();

        Set<String> tabs = driver.getWindowHandles();
        Iterator<String> iteratorTab = tabs.iterator();
        String newTab;
        while (iteratorTab.hasNext()) {
            newTab = iteratorTab.next();
            if (newTab != window) {
                driver.switchTo().window(newTab);
            } else {
                driver.switchTo().window(tab);
            }
        }
        System.out.println("New Tab:" + driver.getCurrentUrl());
        driver.switchTo().window(tab);
         */

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement newTextBox = driver.findElement(By.id("show-textbox"));
        newTextBox.click();
        WebElement textBox = driver.findElement(By.id("displayed-text"));
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(textBox));
        textBox.sendKeys("Hey,I am Here!");
        textBox.clear();
        textBox.sendKeys("After Clear: Hello....");

        WebElement checkbox = driver.findElement(By.id("checkBoxOption1"));
        boolean b = checkbox.isSelected();
        if (b) {
            System.out.println("Test Pass");
        } else {
            System.out.println("Fail- It's Unchecked");
            checkbox.click();
            System.out.println("Now it's selected");
        }
        driver.quit();
    }
}
