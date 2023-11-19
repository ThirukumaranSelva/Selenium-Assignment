package com.qa.seleniumAssignment;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class NaturesBasket {
    public static String url = "https://www.naturesbasket.co.in/";

    public static ChromeOptions chromeOptions(ChromeOptions options) {
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--incognito");
        options.addArguments("--start-maximized");
        return options;
    }

    public static void wait(WebDriverWait wait, WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static void click(Actions actions, WebElement element) {
        actions.moveToElement(element).click().perform();
    }

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        WebDriver driver = new ChromeDriver(chromeOptions(options));
//Open url
              driver.get(url);

//wait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        Actions actions = new Actions(driver);
        JavascriptExecutor js = (JavascriptExecutor) driver;
//Click on cancel button - Popup
        WebElement cancelButton = driver.findElement(By.xpath("//div[@id='btnClosePopUpBox']"));
        wait(wait, cancelButton);
        click(actions, cancelButton);
//change location to Kolkata
        WebElement locationDropdown = driver.findElement(By.xpath("//select[@name='ctl00$ddlCitySearch" +
                "']"));
        Select select = new Select(locationDropdown);
        select.selectByVisibleText("Kolkata");
//Click yes button to load the kolkata location page
        WebElement changeLocationYesBtn = driver.findElement(By.xpath("//div[@onclick='changeCity()']"));
        click(actions, changeLocationYesBtn);
//Click on cancel button - Popup
        WebElement cancel = driver.findElement(By.xpath("//div[@id='btnClosePopUpBox']"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='btnClosePopUpBox']")));
        click(actions, cancel);

        WebElement searchBar = driver.findElement(By.xpath("//input[@name='ctl00$txtMasterSearch1']"));
        WebElement chocolate = driver.findElement(By.xpath("//ul[@id=\"ctl00_NonPanIndia\"]//li[4]//a[text()" +
                "='Chocolates']"));
        actions.moveToElement(searchBar).click(chocolate).build().perform();


        js.executeScript("window.scrollBy(0,150)");

        WebElement addCart = driver.findElement(By.xpath("(//div[@class='search_AddCart1 '])[1]"));
        js.executeScript("arguments[0].click();", addCart);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebElement popupBox = driver.findElement(By.xpath("//div[@id='divPagePopupContainer']"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='divPagePopupContainer']")));
        WebElement textBox = driver.findElement(By.xpath("//input[@id='txt']"));
        wait.until(ExpectedConditions.visibilityOf(textBox));
        WebElement submitBtn = driver.findElement(By.xpath("//input[@id='btnAddPin']"));
        wait.until(ExpectedConditions.visibilityOf(submitBtn));

        actions.moveToElement(popupBox)
                .click(textBox)
                .sendKeys(Keys.NUMPAD7, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD0, Keys.NUMPAD1)
                .click(submitBtn)
                .perform();

        WebElement okBtn = driver.findElement(By.xpath("//input[@value=\"OK\"]"));
        wait.until(ExpectedConditions.visibilityOf(okBtn));
        actions.click(okBtn).perform();
        js.executeScript("window.scrollBy(0,150)");

        WebElement addBtn = driver.findElement(By.xpath("(//li[@class='qty-inc'][normalize-space()='+'])[1]"));
        wait.until(ExpectedConditions.visibilityOf(addBtn));
        js.executeScript("arguments[0].click();", addBtn);


        WebElement chocolateLink = driver.findElement(By.xpath("//a[text()='Ferrero Rocher Gift Pack 300G (16 P..']"));
        wait.until(ExpectedConditions.visibilityOf(chocolateLink));
        chocolateLink.click();

        WebElement chocolateName = driver.findElement(By.xpath("//h1[text()='Ferrero Rocher Gift Pack 300G (16 Pc)']"));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h1[text()='Ferrero Rocher Gift Pack 300G (16 Pc)']")));
        String name = chocolateName.getText();
        System.out.println("Name of the Chocolate is: " + name);
        WebElement backToPreviousScreen = driver.findElement(By.xpath("//span[@id='ctl00_ContentPlaceHolder1_lblsubcategoryName']"));
        wait.until(ExpectedConditions.visibilityOf(backToPreviousScreen));
        backToPreviousScreen.click();


        js.executeScript("window.scrollBy(0,2000)");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        WebElement emailID = driver.findElement(By.xpath("//input[@name='ctl00$txtNewletter']"));
        wait.until(ExpectedConditions.visibilityOf(emailID));
        actions.click(emailID).sendKeys("Test@gmail.com").build().perform();
        emailID.clear();
        actions.click(emailID).sendKeys(name).build().perform();
        actions.keyDown(emailID,Keys.CONTROL).sendKeys("ac").build().perform();

        String date=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd_HH.mm.ss"));

        //String fileName=Keys.CONTROL+"v";
        String fileName;
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable contents = clipboard.getContents(null);
        try {
            fileName = (String) contents.getTransferData(DataFlavor.stringFlavor);
        } catch (UnsupportedFlavorException | IOException e) {
            throw new RuntimeException(e);
        }
        String screenshotLocation=
                System.getProperty("user.dir")+ File.separator+"Screenshots"+File.separator+fileName+date+".png";
        TakesScreenshot screenshot= (TakesScreenshot) driver;
        File source=screenshot.getScreenshotAs(OutputType.FILE);
        File destination=new File(screenshotLocation);
        try {
            FileUtils.copyFile(source,destination);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        driver.quit();

    }
}
