package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Test1 {
    public static void main(String[] args) {
        /*DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.acceptInsecureCerts();
        desiredCapabilities.setAcceptInsecureCerts(true);
        desiredCapabilities.setCapability("acceptInsecureCerts",true);
        */

       //1. For Chrome Driver
        WebDriverManager.chromiumdriver().setup();
        ChromeOptions chromeOptions=new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        WebDriver chromeDriver = new ChromeDriver(chromeOptions);

        chromeDriver.get("https://93.90.204.96/VSM3Test/ui/auth/signin");
        chromeDriver.manage().window().maximize();

        //2. For FireFox Driver
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(true);

       // WebDriverManager.firefoxdriver().setup();
     //   WebDriverManager.chromiumdriver().setup();
        WebDriver firefoxDriver = new FirefoxDriver(firefoxOptions);
        firefoxDriver.get("https://93.90.204.96/VSM3Test/ui/auth/signin");
        firefoxDriver.manage().window().maximize();

        //3. For Edge Driver

        EdgeOptions edgeOptions= new EdgeOptions();
        edgeOptions.setAcceptInsecureCerts(true);

      //  WebDriverManager.edgedriver().setup();
        WebDriver edgeDriver=new EdgeDriver(edgeOptions);

        edgeDriver.get("https://93.90.204.96/VSM3Test/ui/auth/signin");
        edgeDriver.manage().window().maximize();


    }
}
