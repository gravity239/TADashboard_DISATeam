package TADashboard_TestCases;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import Constant.Constant;
import TADashboard.LoginPage;
import TADashboard.MainPage;
import TestBase.TestBase;

public class TC_Login_DataProvider extends TestBase {

	@Test(dataProvider = "LoginCredentials")
	public void temp(String userName, String passWord, boolean status, String message) {

		LoginPage loginPage = new LoginPage(driver);
		loginPage.open();
		MainPage homePage = loginPage.login(userName, passWord, Constant.DefaultRepo);
		if (status == true) {
			// VP: Verify that TA Dashboard Mainpage appear
			boolean actualResult = homePage.isDashboardDisplayed();
			Assert.assertEquals(actualResult, true, "Dashboard Mainpage is not displayed!");
			homePage.logout();
		} else {
			    String actualMessage = loginPage.getAlertMessage();
				Assert.assertEquals(actualMessage,message );
			}
		
	}

	@DataProvider(name = "LoginCredentials")
	public Object[][] getDataFromDataprovider() {
		return new Object[][] { 
			    { "administrator", "", true, "" },
				{ "invalid", "invalid", false, Constant.invalidUserandPwd },
				{ "administrator", "invalid", false, Constant.invalidUserandPwd },
				{ "test", "TEST", true, "" },
				{ "test", "test", false, Constant.invalidUserandPwd }, 
				{ "TEST", "TEST", true, "" },
				{ "test1", "test@#", true, "" }, 
				{ "test@!", "test", true, "" },
				{ "", "", false,Constant.inputUserName}

		};
	}
}

