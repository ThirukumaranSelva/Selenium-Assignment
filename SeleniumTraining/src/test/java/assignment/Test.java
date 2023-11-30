package assignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        JavascriptExecutor javascriptExecutor= (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0,1500)", "");

        WebElement element= driver.findElement(By.xpath("//iframe[@id='courses-iframe']"));
        driver.switchTo().frame("iframe-name");
        //Actions actions=new Actions(driver);
        WebElement joinNow= driver.findElement(By.xpath("//a[text()='JOIN NOW'][@class='btn btn-theme btn-sm " +
                "btn-min-block']"));
       // actions.click(joinNow).build().perform();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //joinNow.click();

        javascriptExecutor.executeScript("arguments[0].click();", joinNow);

        driver.switchTo().parentFrame();

        String timeStamp=new SimpleDateFormat("yyyy-MM-dd_hh.mm.ss").format(new Date());
        TakesScreenshot screenshot= (TakesScreenshot) driver;
        File source= screenshot.getScreenshotAs(OutputType.FILE);
        File destination=new File("./screenshotImages/test_"+timeStamp+".png");
        try {
            FileUtils.copyFile(source,destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
