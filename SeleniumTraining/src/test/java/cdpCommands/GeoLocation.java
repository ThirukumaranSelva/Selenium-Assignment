package cdpCommands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;

import java.time.Duration;
import java.util.HashMap;

public class GeoLocation {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver=new ChromeDriver();
        DevTools devTools=driver.getDevTools();
        devTools.createSession();

        HashMap<String , Object> map=new HashMap<>();
        map.put("latitude",40.43);
        map.put("longitude",-3.74);
        map.put("accuracy",1);
        driver.executeCdpCommand("Emulation.setGeolocationOverride",map);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("Wiki"+ Keys.ENTER);
    }
}
