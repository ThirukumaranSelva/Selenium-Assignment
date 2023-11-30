package SeleniumMethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SelectPractice {
    public static void main(String[] args) throws IOException {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);
        driver.get("https://demo.guru99.com/test/newtours/register.php");
        WebElement dropdown = driver.findElement(By.name("country"));
        //dropdown.click();
        Select select = new Select(dropdown);
        select.selectByIndex(10);
        select.selectByValue("EGYPT");
        select.selectByVisibleText("JAPAN");
        System.out.println("Multiple selection allowed: " + select.isMultiple());
        List<WebElement> list = select.getOptions();
        System.out.println("List of Options available in dropdown:");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            System.out.println("         " + list.get(i).getText());
            if (list.get(i).getText().equals("INDIA")) {
                System.out.println("***********True***********");
            }
        }

        driver.get("https://output.jsbin.com/osebed/2");
        WebElement element = driver.findElement(By.id("fruits"));
        Select select1 = new Select(element);
        select1.selectByIndex(0);
        select1.selectByVisibleText("Apple");
        select1.deselectAll();
        select1.selectByIndex(1);
        select1.selectByIndex(2);
        select1.deselectByVisibleText("Orange");
        select1.deselectByIndex(0);
        System.out.println("Multiple selection allowed: " + select1.isMultiple());
        List<WebElement> e = select1.getOptions();
        System.out.println("List of Options available in dropdown:");
        int s = e.size();
        for (int i = 0; i < s; i++) {
            System.out.println("         " + e.get(i).getText());
        }

        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-dd-MM hh:mm:ss"));
        System.out.println(time);
        TakesScreenshot screenshot= (TakesScreenshot) driver;
        File file= screenshot.getScreenshotAs(OutputType.FILE);
        File fileInput=new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"screenshots"+File.separator+time+".png");

        FileUtils.copyFile(file,fileInput);
        driver.quit();
    }
}
