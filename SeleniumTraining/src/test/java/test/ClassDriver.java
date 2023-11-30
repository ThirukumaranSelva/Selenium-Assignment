package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.Collections;

public class ClassDriver {
    public static void main(String[] args) {
        ChromeOptions options = new ChromeOptions();
        //accept-insecureCertificates, ssl
        options.setAcceptInsecureCerts(true);
        //won't wait for page to load
        // options.setPageLoadStrategy(PageLoadStrategy.NONE);
        //wait for initial page to load but won't wait for images, stylesheets, JavaScript (considered full page as
        // loaded as soon as html is downloaded and parsed
        //   options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        //wait for onload event to fire -indicating full page is loaded
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        //maximize window
        options.addArguments("--start-maximized");
        //Remove automation running infobar in browser
        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        //run incognito
        options.addArguments("--incognito");
        //  options.setExperimentalOption("excludeSwitches",Collections.singletonList("load-extension"));
        options.addExtensions(new File("C:\\Users\\Thirukumaran\\Downloads\\Awesome-ChatGPT-Screenshot-Screen" +
                "-Recorder.crx"));
        // options.setExperimentalOption('prefs',{"extensions.ui.developer_mode": true});
        // set proxy
        //options.addArguments("--proxy-server=http://192.168.0.102:8888");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver(options);


        driver.get("https://93.90.204.96/VSM3Test/ui/auth/signin");
        //driver.findElement(By.id("id=\"__username_email\""));


    }
}
