package com.example.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;


public class GoogleSearchTest {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }


    @Test
    public void testGoogleSearch() {
        driver.get("http://www.google.com/");
        GoogleSearchPage googleSearchPage = new GoogleSearchPage(driver);

        googleSearchPage.search("Prometheus Group");
        driver.findElement(PromethousLocators.CONTACT_US).click();
        driver.findElement(PromethousLocators.FIRST_NAME).sendKeys("Med");
        driver.findElement(PromethousLocators.LAST_NAME).sendKeys("Math");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement submitButton = driver.findElement(PromethousLocators.SUBMIT_BUTTON);
        js.executeScript("arguments[0].click();", submitButton);

        int requiredFieldsCount = driver.findElements(PromethousLocators.REQUIRED_FIELD_WARNING).size();
        Assert.assertEquals(requiredFieldsCount, 4, "There should be 4 additional required fields.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
