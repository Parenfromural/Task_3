package org.example.pageobjects;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;

public class WebDriverFactory {

    public static WebDriver createDriver(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        } else if (browser.equalsIgnoreCase("yandex")) {
            ChromeOptions options = new ChromeOptions();
            options.setBinary("/Applications/Yandex.app/Contents/MacOS/Yandex");
            String driverPath = System.getProperty("user.dir") + "/src/main/resources/drivers/chromedriver-mac-arm64/chromedriver";

            var service = new org.openqa.selenium.chrome.ChromeDriverService.Builder()
                    .usingDriverExecutable(new File(driverPath))
                    .usingAnyFreePort()
                    .build();

            return new ChromeDriver(service, options);
        } else {
            throw new IllegalArgumentException("Неизвестный браузер: " + browser);
        }
    }
}