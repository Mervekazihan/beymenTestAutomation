package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static util.Constants.TEXT_FILE_NAME;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
    }

    public void closePopups(){
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(9));
        wait.until(d -> driver.findElement(By.xpath("//*[@id=\"onetrust-reject-all-handler\"]")).isDisplayed());
        driver.findElement(By.xpath("//*[@id=\"onetrust-reject-all-handler\"]")).click();
        driver.findElement(By.xpath("/html/body/div[3]/div[5]/div[2]/div/div[1]/button")).click();
    }

    public void fakeSearch(String searchValue){
        driver.findElement(By.xpath("/html/body/header/div/div/div[2]/div/div/input")).sendKeys(searchValue);
        driver.findElement(By.xpath("/html/body/header/div[2]/div[1]/div/div/div[2]/div/form/div/button[2]")).click();
    }

    public void search(String searchValue) throws IOException {
        WebElement searchField = driver.findElement(By.xpath("/html/body/header/div/div/div[2]/div/div/input"));
        searchField.sendKeys(searchValue);
        searchField.sendKeys(Keys.ENTER);
        WebElement product = selectRandomProduct();
        writeFile(product.getText());
        System.out.println("Gülay : " + product.getText());
        System.out.println("Mustafa : " + product.findElement(By.className("m-productCard__newPrice")).getText());
    }

    public WebElement selectRandomProduct(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        List<WebElement> products = driver.findElements(By.className("o-productCard"));
        Random random = new Random();
        int selectRandomProductNumber = random.nextInt(products.size());
        if (selectRandomProductNumber == 0)
            selectRandomProductNumber = selectRandomProductNumber + 1;
        WebElement selectedProduct = products.get(selectRandomProductNumber);
        return selectedProduct;
    }

    public void writeFile(String value) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(TEXT_FILE_NAME));
        writer.write(value);
        writer.close();
    }
}
