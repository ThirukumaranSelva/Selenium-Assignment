package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ClassA {
    public static void main(String[] args) {
        ChromeOptions options=new ChromeOptions();
     // options.setBrowserVersion("114");
        WebDriver driver=new ChromeDriver(options);
        //System.out.println(options.getBrowserVersion());
        driver.get("https://www.coxedge.com/locations");
        // selenium manager -4.6.0
        DesiredCapabilities ds=new DesiredCapabilities();
        System.out.println( ds.getBrowserVersion());

    }
}
