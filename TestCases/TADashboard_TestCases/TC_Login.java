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
  public void TC002_DA_LOGIN() {
	  System.out.println("DA_LOGIN_TC002 - Verify that user fails to login specific repository successfully via Dashboard login page with incorrect credentials.");
	  LoginPage loginpage = new LoginPage (driver);
	  loginpage.Open();
	  loginpage.Login("abc","123",Constant.DefaultRepo);
	  driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS); 
	  String ActualMessage = loginpage.GetAlertMessage();
	  String ExpectedMessage = "Username or password is invalid";
	  System.out.print(ExpectedMessage); 
	  System.out.print (ActualMessage);
	 
	  Assert.assertEquals(ActualMessage, ExpectedMessage);
  }
  
  @Test
  public void TC011_DA_LOGIN() {
	  
	  System.out.println("DA_LOGIN_TC011 - Verify password with special characters is working correctly");	  
	  //1. Navigate to TA Dashboard login page
	  //2. Enter valid username and password
	  //3. Click on "Login" button
	  LoginPage loginPage = new LoginPage(driver);
	  loginPage.Open();
	  MainPage homePage
	  = loginPage.Login(Constant.OtherUsername1,Constant.SpecialPassword,Constant.DefaultRepo);
	  
	  //VP: Verify that TA Dashboard Mainpage appear	  
	  boolean actualResult = homePage.IsDashboardMainpageDisplayed();
	  Assert.assertEquals(actualResult, true, "Dashboard Mainpage is not displayed!");
	  
  }
  
  @Test
  public void TC012_DA_LOGIN() {
	  
	  System.out.println("DA_LOGIN_TC013 - Verify Username with special characters is working correctly");	  
	  //1. Navigate to TA Dashboard login page
	  //2. Enter valid username and password
	  //3. Click on "Login" button
	  LoginPage loginPage = new LoginPage(driver);
	  loginPage.Open();
	  MainPage homePage
	  = loginPage.Login(Constant.SpecialUsername,Constant.OtherPassword,Constant.DefaultRepo);
	  
	  //VP: Verify that TA Dashboard Mainpage appear	  
	  boolean actualResult = homePage.IsDashboardMainpageDisplayed();
	  Assert.assertEquals(actualResult, true, "Dashboard Mainpage is not displayed!");
	  
  }
  
  
  
  
}
