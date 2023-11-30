package SeleniumMethods;

import com.assertthat.selenium_shutterbug.core.Capture;
import com.assertthat.selenium_shutterbug.core.Shutterbug;
import com.google.common.io.Files;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.AddHasFullPageScreenshot;
import org.openqa.selenium.firefox.HasFullPageScreenshot;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.ShootingStrategy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

public class Sample {
    public static void main(String[] args) throws InterruptedException, IOException, UnsupportedFlavorException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
/*
        driver.get("https://extendsclass.com/text-compare.html");
        Actions actions=new Actions(driver);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement source=driver.findElement(By.xpath("//*[@id='dropZone']//div[5]//pre/span"));
        actions.keyDown(source,Keys.CONTROL).sendKeys("a").sendKeys("c").release().build().perform();
        Thread.sleep(5000);
        WebElement destination= driver.findElement(By.xpath("//span[text()='File comparison can be very difficult, " +
                "']"));
        actions.keyDown(destination,Keys.CONTROL).sendKeys("a").sendKeys(Keys.BACK_SPACE).sendKeys("v").perform();
*/


     /*   driver.get("https://jqueryui.com/draggable/");
       WebElement demos= driver.findElement(By.xpath("//a[text()='Demos']"));
       Actions actions=new Actions(driver);
       actions.moveToElement(demos).build().perform();*/

//        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
//        WebElement frame=driver.findElement(By.xpath("//iframe[@id='courses-iframe']"));
//        driver.switchTo().frame(frame);
//        WebElement joinNow=driver.findElement(By.xpath("//a[text()='JOIN NOW']"));
//        joinNow.click();


        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(driver);
        String name = "Thiru1";
        driver.get("https://www.naturesbasket.co.in/Online-grocery-shopping/Chocolates-Confectionary-Desserts" +
                "/Chocolates-/Gift-Packs-Bouquets/567_0_0");
        js.executeScript("window.scrollBy(0,2000)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebElement emailID = driver.findElement(By.xpath("//input[@name='ctl00$txtNewletter']"));
        wait.until(ExpectedConditions.visibilityOf(emailID));
        actions.click(emailID).sendKeys("Test@gmail.com").build().perform();
        emailID.clear();
        //  js.executeScript("arguments[0].setAttribute('value', 'name')", emailID);
        actions.click(emailID).sendKeys("Thiru").build().perform();
        actions.keyDown(emailID, Keys.CONTROL).sendKeys("ac").keyUp(Keys.CONTROL).build().perform();
        // emailID.clear();
        // emailID.sendKeys("TEsting");
        actions.click(emailID).sendKeys(emailID, "Test@gmail.com").build().perform();

        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        String x = (String) contents.getTransferData(DataFlavor.stringFlavor);
        System.out.println(x);

        TakesScreenshot screenshot = (TakesScreenshot) driver;

        String s = System.getProperty("user.dir") + File.separator + "screenshotImages" + File.separator + name + ".png";
        //     Shutterbug.shootPage(driver, Capture.FULL,true).save(s);


      //  Screenshot aShot =                new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
//        ImageIO.write(aShot.getImage(), "png", new File(s));
//
//        File sc =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//        FileUtils.copyFile(sc, new File("screenshot.png"));

        Screenshot aShot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
       String pdfPath=System.getProperty("user.dir")+File.separator+"screenshotImages"+File.separator+name+".pdf";
        Document document=new Document(PageSize.A4,0,0,0,0);
        Image image;
        try {
            PdfWriter.getInstance(document,new FileOutputStream(pdfPath));
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        document.open();
        try {
            image=Image.getInstance(aShot.getImage(),null);
            image.setAbsolutePosition(0, 0);
            image.scaleToFit(PageSize.A4);
        } catch (BadElementException e) {
            throw new RuntimeException(e);
        }
        try {
            document.add(image);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        document.close();
        driver.quit();

    }
}
