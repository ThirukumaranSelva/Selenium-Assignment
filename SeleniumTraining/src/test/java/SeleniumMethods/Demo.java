package SeleniumMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Demo {

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
       // Thread.sleep(10000);
       /* WebElement radioBtn3= driver.findElement(By.xpath("//input[@value='radio3']"));
        radioBtn3.click();
        */

        driver.findElement(By.xpath("//input[@value='radio3']")).click();

        WebElement checkbox2= driver.findElement(By.id("checkBoxOption2"));
        checkbox2.click();
        WebElement checkbox1= driver.findElement(By.id("checkBoxOption1"));
        checkbox1.click();
        WebElement checkbox3= driver.findElement(By.id("checkBoxOption3"));
        checkbox3.click();

        WebElement dropdown=driver.findElement(By.id("dropdown-class-example"));
        dropdown.click();
        Select select=new Select(dropdown);
        select.selectByIndex(2);
        select.selectByVisibleText("Option1");
        select.selectByValue("option3");
        dropdown.click();



    }
}
