package assignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ActionsPractice {

    public static void actionMethod(WebDriver driver) {
        //Copy name of the header Section and paste in the Search bar
        driver.get("https://jqueryui.com/demos/");
        WebElement source = driver.findElement(By.xpath("//h1[text()='jQuery UI Demos']"));
        Actions actions = new Actions(driver);
        actions.doubleClick(source).keyDown(source, Keys.CONTROL).sendKeys("c").perform();

        WebElement destination = driver.findElement(By.xpath("//input[@name='s']"));
        actions.keyDown(destination, Keys.CONTROL).sendKeys("v").perform();

        WebElement close = driver.findElement(By.xpath("//*[@class=\"tsmb-icon-close\"]"));
        close.click();
        screenshot(driver);
    }

    public static void hoverOnEachMenu(WebDriver driver) {
        //iterate Through Each Menu's - hoverOnEachMenu
        driver.get("https://jqueryui.com/draggable/");
        List<WebElement> allHeaders = driver.findElements(By.xpath("//ul[@id='menu-top']//li"));
        Actions actions = new Actions(driver);
        for (WebElement e : allHeaders) {
            actions.moveToElement(e).build().perform();
        }
        screenshot(driver);
    }

    public static void draggableWithIFrame(WebDriver driver) {
        driver.get("https://jqueryui.com/draggable/");
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@class=\"demo-frame\"]")));
        WebElement dragElement = driver.findElement(By.xpath("//div[@id=\"draggable\"]"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(dragElement).moveToElement(dragElement, 200, 50).release().build().perform();
        driver.switchTo().parentFrame();
        WebElement textOutsideFrame = driver.findElement(By.xpath("//p[starts-with(text(),'Allow elements to be')]"));
        System.out.println("Text Outside Frame is: " + textOutsideFrame.getText());
        screenshot(driver);
    }

    public static void dragAndDropWithIFrameIndex(WebDriver driver) {
        driver.get("https://jqueryui.com/droppable/");
        driver.switchTo().frame(0);
        WebElement source = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement destination = driver.findElement(By.xpath("//div[@id='droppable']"));
        Actions actions = new Actions(driver);
        actions.dragAndDrop(source, destination).build().perform();
        driver.switchTo().parentFrame();
        screenshot(driver);
    }

    public static void resizableWithIFrameIndex(WebDriver driver) {
        driver.get("https://jqueryui.com/resizable/");
        driver.switchTo().frame(0);
        WebElement source = driver.findElement(By.xpath("//*[@class='ui-resizable-handle ui-resizable-se ui-icon ui-icon-gripsmall-diagonal-se']"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(source).moveByOffset(300, 60).release().build().perform();
        driver.switchTo().parentFrame();
         screenshot(driver);
    }

    public static void selectableWithIFrameIndex(WebDriver driver) {
        driver.get("https://jqueryui.com/selectable/");
        driver.switchTo().frame(0);
        List<WebElement> source = driver.findElements(By.xpath("//ol[@id='selectable']//li"));
        Actions actions = new Actions(driver);
        for (WebElement e : source) {
            if (e.getText().equalsIgnoreCase("Item 2") || e.getText().equalsIgnoreCase("Item 4")) {
                continue;
            } else actions.keyDown(Keys.CONTROL).click(e).perform();
        }
        driver.switchTo().parentFrame();
        screenshot(driver);
    }

    public static void sortingWithIFrameIndex(WebDriver driver) {
        driver.get("https://jqueryui.com/sortable/");
        driver.switchTo().frame(0);
        WebElement source = driver.findElement(By.xpath("//ul[@id='sortable']//li[1]"));
        WebElement destination = driver.findElement(By.xpath("//ul[@id='sortable']//li[4]"));
        Actions actions = new Actions(driver);
        actions.clickAndHold(source).moveByOffset(0, 60).release(source).perform();
        // actions.clickAndHold(destination).moveByOffset(0,-60).release(destination).perform();
        driver.switchTo().parentFrame();
        screenshot(driver);
    }

    public static String dateTime() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_hh_mm_ss"));
    }

    public static void screenshot(WebDriver driver) {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File source = screenshot.getScreenshotAs(OutputType.FILE);
        String screenshotLocation =
                System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator +
                        "screenshotImages" + File.separator + "screenshot_" + dateTime() + ".jpg";
        System.out.println("Location Screenshot: " + screenshotLocation);
        File destination = new File(screenshotLocation);
        try {
            FileUtils.copyFile(source, destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void quit(WebDriver driver) {
        driver.quit();
    }

    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        actionMethod(driver);
        hoverOnEachMenu(driver);
        draggableWithIFrame(driver);
        dragAndDropWithIFrameIndex(driver);
        resizableWithIFrameIndex(driver);
        selectableWithIFrameIndex(driver);
        sortingWithIFrameIndex(driver);
        quit(driver);

        System.out.println(dateTime());

    }
}
