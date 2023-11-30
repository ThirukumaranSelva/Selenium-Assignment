package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class XpathAssignment {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        /*
        * //tagname[@attribute='value']
        * //div[@id='name']
        * //div[text()='']
        * //div[contains(text(),'')]
        * //div[starts-with(text(),'')]
        * //div[@classname='']
        * //div[text()='']
        * */

        String url = "https://www.practo.com/search/doctors?results_type=doctor&q=%5B%7B%22word%22%3A%22Dentist%22%2C" +
                "%22autocompleted%22%3Atrue%2C%22category%22%3A%22subspeciality%22%7D%5D&city=Bangalore";
        driver.get(url);

        String doctorName = "Dr. Baswaraj Biradar";
        WebElement appointment = driver.findElement(By.xpath("//h2[text()='" + doctorName + "']//following::button[text()='Book " +
                "Appointment']"));
        appointment.click();

        WebElement doctorAppointment=
                driver.findElement(By.xpath("//h2[text()='Dr. Sunil Rao']//following::button[text()='Book " +
                        "Appointment']"));
        doctorAppointment.click();

    }
}
