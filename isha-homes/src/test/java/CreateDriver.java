
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class CreateDriver {

	static WebDriver driver;
		
		public static WebDriver getDriver() {
			driver = new ChromeDriver();
//			driver.get("https://ishahomes.com/");
			return driver;
		}
}
