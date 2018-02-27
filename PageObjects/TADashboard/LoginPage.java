package TADashboard;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import Constant.Constant;

public class LoginPage extends MainPage {
	private WebDriver _driverLoginPage;	

	private static final By _txtUsername = By.xpath("//input[@id='username']");
	private static final By _txtPassword = By.xpath("//input[@id='password']");
	private static final By _btnLogin = By.xpath("//div[@class='btn-login']");
	private static final By _cmbRepo = By.xpath("//select[@id='repository']");

	protected WebElement getTxtUsername() {
		return myFindElement(_txtUsername, Constant.TimeOut);
	}

	protected WebElement getTxtPassword() {
		return myFindElement(_txtPassword, Constant.TimeOut);
	}

	protected WebElement getBtnLogin() {
		return myFindElement(_btnLogin, Constant.TimeOut);
	}

	protected Select getCmbRepo() {
		return new Select(myFindElement(_cmbRepo, Constant.TimeOut));
	}

	public LoginPage(WebDriver driver) {
		super(driver);
		this._driverLoginPage = driver;
	}

	// Opens Login page of TA Dashboard page
	public LoginPage open() {
		_driverLoginPage.get(Constant.HomePageURL);
		return this;
	}

	// Login to TA Dashboard page
	public MainPage login(String username, String password, String repositoryName) {
		getCmbRepo().selectByVisibleText(repositoryName);
		getTxtUsername().sendKeys(username);
		getTxtPassword().sendKeys(password);
		getBtnLogin().click();
		return new MainPage(_driverLoginPage);
	}

}
