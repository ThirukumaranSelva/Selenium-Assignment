package cdpCommands;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v119.network.Network;
import org.openqa.selenium.devtools.v119.network.model.Request;
import org.openqa.selenium.devtools.v119.network.model.Response;

import java.util.Optional;

public class NetworkMethods {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver=new ChromeDriver();
        DevTools devTools=driver.getDevTools();
        devTools.createSession();

        driver.get("https://www.browserstack.com/guide/scroll-in-appium");

        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(),requestWillBeSent -> {
           Request req= requestWillBeSent.getRequest();
            System.out.println("Request-Headers: "+req.getHeaders());
        });

        devTools.addListener(Network.responseReceived(),responseReceived -> {
           Response res=responseReceived.getResponse();
            System.out.println("Response Url: "+res.getUrl());
            System.out.println("Response Status: "+res.getStatus());
        });
    }
}
