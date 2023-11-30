package cdpCommands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v119.fetch.Fetch;
import org.openqa.selenium.devtools.v119.network.model.Request;

import java.util.HashMap;
import java.util.Optional;

public class FetchMethods {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));

        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            Request request = requestPaused.getRequest();
            if (request.getUrl().contains("shetty")) {
              String s=  request.getUrl().replace("=shetty", "=BadGuy");
                System.out.println("Mocked url: "+s);
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.empty(), Optional.empty(),
                        Optional.empty(),
                        Optional.empty(), Optional.empty()));
            } else {
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.empty(), Optional.empty(),
                        Optional.empty(),
                        Optional.empty(), Optional.empty()));
            }
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        WebElement lib = driver.findElement(By.xpath("//button[text()=' Virtual Library ']"));
        lib.click();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

       // driver.quit();
    }
}
