package assignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.support.locators.RelativeLocator;

public class Selenium {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools=driver.getDevTools();

        driver.get("http://zero.webappsecurity.com/login.html");
        System.out.println("Current url: " + driver.getCurrentUrl());

        WebElement password=
                driver.findElement(RelativeLocator.with(By.xpath("//label[@for=\"user_password\"]")).below(By.xpath(
                        "//label[@for=\"user_login\"]")));
        System.out.println(password.getText());

        System.out.println("Title: " + driver.getTitle());
        WebElement name=driver.findElement(RelativeLocator.RelativeBy.id("user_login"));
        name.sendKeys("Thiru");

        WebElement text=driver.findElement(RelativeLocator.with(By.tagName("label")).toLeftOf(name));
        System.out.println(text.getText());

        WebElement text1=driver.findElement(RelativeLocator.with(By.tagName("label")).below(name));
        System.out.println(text1.getText());

        WebElement element=driver.findElement(RelativeLocator.with(By.id("user_remember_me")).toRightOf(By.xpath(
                "//label[text()='Keep me signed in']")));
        element.click();

        WebElement pass=
                driver.findElement(RelativeLocator.with(By.xpath("//label[text()='Password']"))
                        .above(RelativeLocator.with(By.id("user_remember_me"))));
        System.out.println(pass.getText());

        WebElement forgetPassword=driver.findElement(RelativeLocator.with(By.tagName("a")).near(By.name("submit")));
        System.out.println(forgetPassword.getText());
        forgetPassword.click();

    }

}
