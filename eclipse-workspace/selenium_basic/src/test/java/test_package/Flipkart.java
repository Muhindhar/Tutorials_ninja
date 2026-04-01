package test_package;
import org.openqa.selenium.*;
import org.openqa.*;
import org.openqa.selenium.chrome.*;
public class Flipkart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

			WebDriver driver = new ChromeDriver();
			driver.get("https://www.flipkart.com/");

			String title = driver.getTitle();
			System.out.println(title);
			System.out.println("Title Length : "+title.length());
			
			
			String url = driver.getCurrentUrl();
			String exp = "https://www.flipkart.com/";
			
			if (exp.equals(url)) {
				System.out.println("it is correct URL");
			}else {
				System.out.println("not a correct URL");
			}

			
			String page = driver.getPageSource();
			
			System.out.println("PAGE LENGTH : "+page.length());
			driver.close();
		}
}
