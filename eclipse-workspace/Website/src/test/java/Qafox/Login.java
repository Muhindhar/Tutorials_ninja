package Qafox;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;

public class Login {

    WebDriver driver;
    WebDriverWait wait;
    @Test(dataProvider = "validData", dataProviderClass = com.utilities.ExcelData.class)
    public void validLoginTest(String email, String password) {

        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.xpath("//a[text()='Login']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));

        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Logout")));
        Assert.assertTrue(driver.findElement(By.linkText("Logout")).isDisplayed());

        System.out.println("Valid login successful");
    }

    @Test(dataProvider = "InvalidData", dataProviderClass = com.utilities.ExcelData.class)
    public void invalidLoginTest(String email, String password) {

        driver.findElement(By.xpath("//span[normalize-space()='My Account']")).click();
        driver.findElement(By.xpath("//a[text()='Login']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));

        driver.findElement(By.id("input-email")).sendKeys(email);
        driver.findElement(By.id("input-password")).sendKeys(password);
        driver.findElement(By.xpath("//input[@value='Login']")).click();

        String expected = "Warning: No match for E-Mail Address and/or Password.";

        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert-danger')]")));
        String actual = alert.getText();
        Assert.assertTrue(actual.contains(expected));

        System.out.println("Wrong username and password");
    }

    @Parameters({"search"})
    @Test
    public void searchTest(String search) {
        WebElement searchBox = wait.until( ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='form-control input-lg']")));
        searchBox.sendKeys(search);
        searchBox.sendKeys(Keys.ENTER);
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Just when you thought')]")));
        String actual = result.getText();
        Assert.assertTrue(actual.toLowerCase().contains("imac"));
        System.out.println("Search successful");
    }
    @Parameters({"Insearch"})
    @Test
    public void InvalidsearchTest(String search) {
        WebElement searchBox = wait.until( ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='form-control input-lg']")));
        searchBox.sendKeys(search);
        searchBox.sendKeys(Keys.ENTER);
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'There is no product that matches the search criter')]")));
        String actual = result.getText();
        Assert.assertTrue(actual.toLowerCase().contains("no product"));
        System.out.println("Search unsuccess");
    }

    @BeforeMethod
    public void beforeMethod() {

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://tutorialsninja.com/demo/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }
}