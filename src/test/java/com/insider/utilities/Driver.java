package com.insider.utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver() {
    }

    private static InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();

    public static WebDriver getDriver() {

        if (driverPool.get() == null) {

            String browserType = ConfigurationReader.getProperty("browser");


            switch (browserType) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                    break;

                case "firefox":
                    System.setProperty("webdriver.gecko.driver", "geckodriver.exe");
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
                    break;

            }

        }

        return driverPool.get();

    }

    public static void closeDriver() {
        if (driverPool.get() != null){
            driverPool.get().quit();
            driverPool.remove();
        }
    }
}
