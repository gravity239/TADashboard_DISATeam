package TestModules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Constant.Constant;
import TADashboard.GeneralPage;
import TADashboard.LoginPage;
import TADashboard.MainPage;

public class LoginTM extends TestBase.TestBase  {
  @Test
  public void TC002_login_page_incorrect_credentials() {
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
   
  public void TC003_login_page_incorrect_credentials() {
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
  }

