package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
public class BasePageTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void before(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }
    @AfterEach
    public void down(){
        driver.quit();
    }

    public <T extends BasePage> T goToUrl(T page, String url){
        driver.get(url);
        return page;
    }
}
