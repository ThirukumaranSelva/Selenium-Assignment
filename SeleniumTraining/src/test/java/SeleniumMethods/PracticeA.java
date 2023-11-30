package SeleniumMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.util.Set;

public class PracticeA {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
    //    driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        driver.get("https://google.com/");
        driver.getTitle();
//        WebElement e=driver.findElement(By.xpath("//legend[text()='Switch Tab Example']"));
//        System.out.println("EEEEE"+e.getText());
      /*
        driver.manage().window().maximize();
        System.out.println("X:"+driver.manage().window().getPosition().x+"Y:"+driver.manage().window().getPosition().y);
        System.out.println(driver.manage().window().getSize().height);
        System.out.println(driver.manage().window().getSize().width);
        System.out.println("X:"+driver.manage().window().getPosition().x+"Y:"+driver.manage().window().getPosition().y);
        driver.manage().window().setPosition(new Point(200,400));
        driver.manage().window().fullscreen();
        */

       WebElement ee= driver.switchTo().activeElement();
       System.out.println(ee.getText());
       driver.switchTo().defaultContent();
     Set<Cookie> cookies=  driver.manage().getCookies();
        System.out.println("Cookies: "+cookies);
        driver.manage().deleteCookie(new Cookie("NID","511=Vg8Lo_VSrRhy_8C6DJbqKf6s34vDCdZuL6UevjpxN-rF8RuVLejmvmJs6mAKniCNXbIjN-0okNxmK_-gcXZG5VSmbJuQJJYIVnGwArMVobSJ4MRYNIZLsqgMXZ4FLQqxYNa8lZsvc7mUg3XEchfnhTRNFK-1_HbElomGFw65FprcT1pWa0gzVpc"));
        System.out.println("Cookies: "+cookies);

     //  driver.manage().deleteAllCookies();

      //  RelativeLocator.with(By.id("")).above(By.xpath(""));

    }
}
