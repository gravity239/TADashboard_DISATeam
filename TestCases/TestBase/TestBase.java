package TestBase;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

public class TestBase {

	String driverPath = "./Executables/chromedriver.exe";
	public WebDriver driver;
	protected SoftAssert softAssert = new SoftAssert();

	@BeforeMethod
	public void beforeMethod_initialize() {
		if (driver.toString().contains("null")) {
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
	}

	@AfterMethod
	public void afterMethod_cleanup() {
		driver.quit();
	}

	@BeforeTest
	public void beforeTest_initialize() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

	@AfterTest
	public void afterTest_cleanup() {
		driver.quit();
	}

}
