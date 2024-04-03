
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class MiniProject {
	
	// live chat close method 
	public static void liveChatClose(WebDriver driver) {
		try {
			driver.switchTo().frame("livservMiddleFrame");
			driver.findElement(By.xpath("//*[@id=\'td2\']/div")).click();
		}catch (Exception e) {
		}
		
		try {
		driver.findElement(By.id("livchat_close")).click();
		}
		catch (Exception e) {
		}
		
		driver.switchTo().defaultContent();
	}
	
	// navigating to completed projects
	public static void navigate(WebDriver driver) {
		// finding link in href attribute and redirecting it to that page
		String nav = driver.findElement(By.xpath("//*[@id=\"mobile-main-nav\"]/li[5]/a")).getAttribute("href");
		System.out.println(nav);
		driver.navigate().to(nav);
	}
	
	// no of projects method
	public static int noOfProjects(WebDriver driver) {
		// finding all projects and appending all those projects into list and returning total no of projects as list size
		List<WebElement>projects = driver.findElements(By.xpath("//*[@id=\"boosted-tab-0\"]//*[@id=\"module_properties\"]/div"));
		int total = projects.size();
		return total;
		
	}
	
	// project names
	public static List<WebElement> projectNames(WebDriver driver){
		// finding all projects names and appending all those projects names into list and returning that list
		// //*[@id=\"boosted-tab-0\"]//*[@id=\"properties_module_section\"]//div[@class= 'item-listing-wrap hz-item-gallery-js item-listing-wrap-v6 card amadadalda']//*[@class = 'item-title']
		List<WebElement>projectnames = driver.findElements(By.xpath("//*[@id=\"boosted-tab-0\"]//*[@id=\"properties_module_section\"]//div[@class= 'item-listing-wrap hz-item-gallery-js item-listing-wrap-v6 card amadadalda']//*[@class = 'item-title']"));
		return projectnames;
	}
	
	// Enquire now and Contact info
	public static void isContactDisplayed(WebDriver driver) {
		// checking if contact us is displayed or not, if present click on it
		boolean contactus = driver.findElement(By.xpath("//*[span ='Contact Us']")).isDisplayed();
		if(contactus){
		System.out.println("Contact Us Element is present");
		}else {
		System.out.println("Contact Us Element is not present");
		}
		driver.findElement(By.xpath("//*[span ='Contact Us']")).click();
	}
	
	// capturing mail id
	public static String getMail(WebDriver driver) {
		WebElement eid = driver.findElement(By.xpath("//*[text()='marketing@ishahomes.com']"));
		return eid.getText();
	}
	
	// Screenshot
	public static void takeScreenShot(WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File src = ts.getScreenshotAs(OutputType.FILE);
		
		// capturing screenshot of contact us page
		File trg = new File("C:\\Users\\2318835\\eclipse-workspace\\selenium\\2318835_MiniProject\\MiniProject\\screenshots\\Final.png");
		FileUtils.copyFile(src, trg);
	}
	

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		// step - 1
		WebDriver driver = CreateDriver.getDriver();
		
		// READING EXCEL FILE using APACHE POI
		
		FileInputStream file = new FileInputStream("C:\\Users\\2318835\\eclipse-workspace\\selenium\\src\\test\\java\\MiniProject\\testData.xlsx");
		
		XSSFWorkbook workbook = new XSSFWorkbook(file);
 
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		
		XSSFRow row = sheet.getRow(0);
		XSSFCell value = row.getCell(0);
		String baseUrl = value.toString();
				
		driver.get(baseUrl);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
		driver.manage().window().maximize();
		
		// step - 2
		liveChatClose(driver);
		
		// step - 3
		navigate(driver);
		
		// step - 4
		System.out.println("Number of projects "+ noOfProjects(driver));
		
		// step - 5 -> printing first 5 projects names
		List<WebElement> projectList =  projectNames(driver);
		int j=0;
		for(int i = 0; i<projectList.size(); i++)
		{
			 if(j<5) {
				 WebElement e = projectList.get(i);
				 System.out.println((j+1)+" "+e.getText());
				 j++;
			 }
		}
		
		// step - 6
		isContactDisplayed(driver);
		
		// step - 7
		String mailId = getMail(driver);
		System.out.println("Mail id = "+ mailId);
		
		// step - 8
		takeScreenShot(driver);
		
		
		// step - 9
		driver.quit();

	}

}
