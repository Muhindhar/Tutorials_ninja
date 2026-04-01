package test_package;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.*;

public class Demo {

	public static void main(String[] args) {
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.co.in");
		WebElement search=driver.findElement(By.name("q"));
		search.sendKeys("Selenium",Keys.ENTER);
		System.out.println("Page title is : "+driver.getTitle());
		driver.close();
		
	}

}