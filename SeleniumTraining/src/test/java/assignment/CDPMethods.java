package assignment;

import com.google.common.collect.ImmutableMap;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v119.browser.Browser;
import org.openqa.selenium.devtools.v119.emulation.Emulation;
import org.openqa.selenium.devtools.v119.network.Network;
import org.openqa.selenium.devtools.v119.network.model.Request;
import org.openqa.selenium.devtools.v119.network.model.Response;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class CDPMethods {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--incognito");
        ChromeDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://qa.coxedge.com/");

        Set<Cookie> cookiesBefore = driver.manage().getCookies();
        System.out.println("Before: "+cookiesBefore);
     //   cookiesBefore.forEach(System.out::println);
        driver.getDevTools().createSession();
        //driver.getDevTools().getDomains();
        driver.getDevTools().send(Network.clearBrowserCookies());
        Set<Cookie> cookiesAfter = driver.manage().getCookies();
        System.out.println("After: "+cookiesAfter);

        driver.getDevTools().send(Emulation.setDeviceMetricsOverride(600,1000,50,true, Optional.empty(),
                Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty()
                ,Optional.empty(),Optional.empty()));

        DevTools devTool=driver.getDevTools();

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", ImmutableMap.of(
                "width", 500,
                "height", 600,
                "deviceScaleFactor", 50,
                "mobile", true));

         //browser version
        Browser.GetVersionResponse browser = devTool.send(Browser.getVersion());
        System.out.println("Browser Version : "+browser.getProduct());
        System.out.println("User Agent : "+browser.getUserAgent());

        /*Map coordinates = Map.of(
                "latitude", 30.3079823,
                "longitude", -97.893803,
                "accuracy", 1
        );
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        //Refresh if already on the page
        driver.navigate().refresh();*/


        devTool.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTool.addListener(Network.responseReceived(), requestConsumer ->{
            Response response = requestConsumer.getResponse();
            System.out.println(response.getUrl());
        });

        //  RequestId[] requestId = new RequestId[1];

     /*   devTool.addListener(Network.responseReceived(), responseConsumer -> {
            Response response = responseConsumer.getResponse();
            //  requestId[0] = responseConsumer.getRequestId();
            if( response.getUrl().contains("ws_api.php?")) {
                System.out.println(response.getStatus() + " "+ response.getUrl());
               *//* Integer value=200;
                Assert.assertEquals("NOT EQUAL!",value,response.getStatus());*//*
                //     String responseBody = devTool.send(Network.getResponseBody(requestId[0] )).getBody();
                //  System.out.println(responseBody);
                System.out.println("End of response");
            }
        });
*/
        //driver.getDevTools().disconnectSession();

      //  driver.getDevTools().getCdpSession();
    }
}
