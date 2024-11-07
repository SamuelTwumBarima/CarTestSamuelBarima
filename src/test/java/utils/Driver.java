package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {

    protected static WebDriver driver = getDriver();

    protected static WebDriver getDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-popup-blocking");

        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        Runtime.getRuntime().addShutdownHook(new Thread("Driver shutdown") {
            public void run() {
                //driver.quit();
            }
        });

        return driver;
    }

    public static void closeAlertIfPresent() {
        try {
            Alert alert = driver.switchTo().alert();
            alert.dismiss();
            System.out.println("Alert dismissed.");
        } catch (NoAlertPresentException e) {
            System.out.println("No alert to close.");
        }
    }

}
