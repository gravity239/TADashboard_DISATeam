package TADashboard_TestCases;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;
import Constant.Constant;
import TADashboard.LoginPage;
import TADashboard.MainPage;
import TestBase.TestBase;


public class TC_Login extends TestBase{
  

  @Test
  public void TC001_DA_LOGIN() {
	  
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
  
  @Test
  public void TC002_DA_LOGIN() 
  {
	  System.out.println("TC002_DA_LOGIN - Verify that user fails to login specific repository successfully via Dashboard login page with incorrect credentials.");
	 
	  //1. Navigate to TA Dashboard login page
	  LoginPage loginPage = new LoginPage(driver);
	  loginPage.Open();
	  
	  //2. Enter invalid username and password
	  //3. Click on "Login" button
	  loginPage.Login("aa","bb",Constant.DefaultRepo);
	  
	  //VP: Verify that Dashboard Error message "Username or password is invalid" appears
	  String expectedMessage = loginPage.GetAlertMessage();
      String actualMessage = "Username or password is invalid";
      Assert.assertEquals(expectedMessage, actualMessage,"\nActual: " + actualMessage + "\nExpected: " + expectedMessage);
  }
  
  @Test
  public void TC003_DA_LOGIN() 
  {
	  System.out.println("TC003_DA_LOGIN - Verify that user fails to log in specific repository successfully via Dashboard login page with correct username and incorrect password.");
	  
	  //1. Navigate to TA Dashboard login page
	  //2. Enter valid username and invalid password
	  //3. Click on "Login" button
	  LoginPage loginPage = new LoginPage(driver);
	  loginPage.Open();
	  loginPage.Login(Constant.Username,"bb",Constant.DefaultRepo);
	  
	  //VP: Verify that Dashboard Error message "Username or password is invalid" appears
	  String expectedMessage = loginPage.GetAlertMessage();
      String actualMessage = "Username or password is invalid";
      Assert.assertEquals(expectedMessage, actualMessage,"\nActual: " + actualMessage + "\nExpected: " + expectedMessage);
  }
     
  @Test
  public void TC013_DA_LOGIN() 
  {
	  System.out.println("TC013_DA_LOGIN - The page works correctly for the case when no input entered to Password and Username field.");
	  
	  //1. Navigate to TA Dashboard login page
	  //2. Click Login button without entering data into Username and Password field
	  LoginPage loginPage = new LoginPage(driver);
	  loginPage.Open();
	  loginPage.Login("","",Constant.DefaultRepo);
	  
	  //VP: There is a message "Please enter username"
	  String expectedMessage = loginPage.GetAlertMessage();
      String actualMessage = "Please enter username";
      Assert.assertEquals(expectedMessage, actualMessage,"\nActual: " + actualMessage + "\nExpected: " + expectedMessage);
  }
	@Test
	public void TC014_DA_LOGIN() 
	{
		  System.out.println("TC014_DA_LOGIN - Verify when \"New Page\" control/form is brought up to focus all other control within Dashboard page are locked and disabled ");
		  
		  //1. Navigate to TA Dashboard login page
		  //2. Login with valid account
		  //3. Click Add Page icon
		  //4. Try to click other controls on Main page when New Page dialog is opening
		  LoginPage loginPage = new LoginPage(driver);
		  loginPage.Open();
		  MainPage mainPage = loginPage.Login(Constant.Username,Constant.Password,Constant.DefaultRepo);
		  mainPage.SelectGeneralSetting("Add Page");
		  
		  //VP: All other controls within Dashboard are locked and disabled
		  boolean actualResult = mainPage.IsDashboardLockedByDialog();
		  Assert.assertEquals(true, actualResult, "Dashboard is not locked by dialog!");
	}
}


