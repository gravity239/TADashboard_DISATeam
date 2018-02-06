package TADashboard;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import Constant.Constant;
public class LoginPage extends GeneralPage {
private WebDriver _driverLoginPage;
	
	private static final By _txtUsername = By.xpath("//input[@id='username']");
	private static final By _txtPassword =  By.xpath("//input[@id='password']");
	private static final By _btnLogin =  By.xpath("//div[@class='btn-login']");
	private static final By _cmbRepo =  By.xpath("//select[@id='repository']");
	
	protected WebElement getTxtUsername() {
		return MyFindElement(_txtUsername, Constant.TimeOut);
	}

	protected WebElement getTxtPassword() {
		return MyFindElement(_txtPassword, Constant.TimeOut);
	}    
	
	protected WebElement getBtnLogin() {
		return MyFindElement(_btnLogin, Constant.TimeOut);
	}
	
	protected WebElement getCmbRepo(){
		return MyFindElement(_cmbRepo, Constant.TimeOut);
	}
	
	protected LoginPage(WebDriver driver) 
    {
    	super(driver);
    	this._driverLoginPage = driver;
    }
    
    //Opens Login page of TA Dashboard page
    public LoginPage Open()
    {
    	_driverLoginPage.get(Constant.HomePageURL);
    	return this;
    }
   
    //Login to TA Dashboard page
    public MainPage Login(String username, String password, String repositoryName)
    {
    	Select cmbRepo = new Select(getCmbRepo());
    	cmbRepo.selectByVisibleText(repositoryName);
        getTxtUsername().sendKeys(username);
        getTxtPassword().sendKeys(password);
        getBtnLogin().click();
        return new MainPage(_driverGeneralPage);
    }
    
    // Get the message of the alert dialog.
    public String GetAlertMessage()
    {
        Alert alert = _driverLoginPage.switchTo().alert();
        return alert.getText();
    }
    
}
