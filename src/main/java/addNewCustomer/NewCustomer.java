package addNewCustomer;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NewCustomer {

	WebDriver driver;
    By dashboardHeaderElement;

	@Before
	public void init() {

		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.get("https://techfios.com/billing/?ng=dashboard/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}

	@Test	
	public void loginTest() {
		
		//storing webElement using By Class
		By userNameLocator = By.xpath("//input[@id=\"username\"]");
		By passWordLocator = By.xpath("//input[@id=\"password\"]");
		By signInButtonLocator = By.xpath("/html/body/div/div/div/form/div[3]/button");
		By dashBoardHeaderLocator = By.xpath("//h2[contains(text,'Dashboard'])");
		
		WebElement userNameElement = driver.findElement(By.xpath("//input[@id=\"username\"]"));
		WebElement passWordElement = driver.findElement(By.xpath("//input[@id=\"password\"]"));
		WebElement signInButtonElement = driver.findElement(By.xpath("/html/body/div/div/div/form/div[3]/button"));
		
		
		driver.findElement(userNameLocator).clear();
		driver.findElement(userNameLocator).sendKeys("demo@techfios.com");
		driver.findElement(passWordLocator).sendKeys("abc123");
		driver.findElement(signInButtonLocator).click();
		
		
		boolean pageTitleDisplayStatus;
		try {
			WebElement dashboardHeaderElement = driver.findElement(By.xpath("//h2[contains(text(),'Dashboard')]"));
			pageTitleDisplayStatus = true;
		} catch(Exception e) {
			pageTitleDisplayStatus = false;
			e.printStackTrace();
		}
		
		
		
		WebDriverWait wait = new WebDriverWait(driver, 15);
		wait.until(ExpectedConditions.visibilityOfElementLocated((By) dashboardHeaderElement));
		
		Assert.assertTrue("Dashboard page not found", pageTitleDisplayStatus );
	
}

	@After
	public void CustomerAccount() throws InterruptedException {

		driver.findElement(By.xpath("//ul[@id=\"side-menu\"]/li[3]/a/span[1]")).click();
		
		driver.get("https://techfios.com/billing/?ng=contacts/add/");
		driver.findElement(By.xpath("//input[@id=\"account\"]")).sendKeys("Parvez Ahmed");
		driver.findElement(By.xpath("//select[@id=\"cid\"]/option[49]")).click();
		driver.findElement(By.xpath("//input[@id=\"email\"]")).sendKeys("parvez_ahmed2005@yahoo.com");
		driver.findElement(By.xpath("//input[@id=\"phone\"]")).sendKeys("214-518-8322");
		driver.findElement(By.xpath("//input[@id=\"address\"]")).sendKeys("Mckinney Ranch Pkwy");
		driver.findElement(By.xpath("//input[@id=\"city\"]")).sendKeys("Mckinney");
		driver.findElement(By.xpath("//input[@id=\"state\"]")).sendKeys("Texas");
		driver.findElement(By.xpath("//input[@id=\"zip\"]")).sendKeys("75070");
		driver.findElement(By.xpath("//select[@id=\"country\"]/option[233]")).click();
		driver.findElement(By.xpath("//button[@id=\"submit\"]")).click();
		Thread.sleep(1000);
		driver.get("https://techfios.com/billing/?ng=contacts/list/");
		driver.findElement(By.xpath("//input[@id=\"foo_filter\"]")).sendKeys("Parvez Ahmed");
		 
		
		

	}

}
