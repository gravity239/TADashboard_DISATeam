package TADashboard_TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import Constant.Constant;
import TADashboard.LoginPage;
import TADashboard.MainPage;
import TestBase.TestBase;


public class TC_Login extends TestBase{
  

  @Test
  public void DA_LOGIN_TR001() {
	  
	  System.out.println("DA_LOGIN_TC001 - Verify that user can login specific repository successfully via Dashboard login page with correct credentials.");	  
	  //1. Navigate to TA Dashboard login page
	  //2. Enter valid username and password
	  //3. Click on "Login" button
	  LoginPage loginPage = new LoginPage(driver);
	  loginPage.Open();
	  MainPage homePage
	  = loginPage.Login(Constant.Username,Constant.Password,Constant.DefaultRepo);
	  
	  //VP: Verify that TA Dashboard Mainpage appear	  
	  boolean actualResult = homePage.IsDashboardMainpageDisplayed();
	  Assert.assertEquals(actualResult, true, "Dashboard Mainpage is not displayed!");
  
  }
  
}
