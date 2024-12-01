package org.example;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MtsByTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\stepa\\IdeaProjects\\Lesson15\\src\\main\\resources\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.get("https://mts.by/");
    }

    @Test
    void testBlockTitle() {
        WebElement blockTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/h2")));
        Assertions.assertEquals("Онлайн пополнение\nбез комиссии", blockTitle.getText(), "Block title is incorrect");
    }

    @Test
    void testPaymentLogos() {
        int logoCount = driver.findElements(By.className("pay__partners")).size();
        Assertions.assertTrue(logoCount > 0, "No payment system logos found");
    }

    @Test
    void testMoreDetailsLink() {
        WebElement link = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pay-section\"]/div/div/div[2]/section/div/a")));
        link.click();
        Assertions.assertEquals("https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/", driver.getCurrentUrl(), "Ссылка не работает");

    }

    @Test
    void testContinueButton() {

        WebElement phoneNumberField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-phone")));
        phoneNumberField.sendKeys("297777777");
        WebElement summ = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("connection-sum")));
        summ.sendKeys("400");

        WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"pay-connection\"]/button")));
        continueButton.click();

    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
