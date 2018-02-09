package TADashboard_TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Common.Utilities;
import TADashboard.LoginPage;
import TADashboard.MainPage;
import TestBase.TestBase;
import Constant.Constant;
import DataObject.TAPage;

public class TC_MainPage extends TestBase {

				
	public	TAPage page= new TAPage("page1");
	public	TAPage pageParent= new TAPage("page2");	
		
		
	
	@AfterMethod
	public void afterMethod_cleanup() {
		if (pageParent != null) {

			MainPage mainPage = new MainPage(driver);
			mainPage.deletePage(pageParent.getPageName());

		}
		if (page != null) {
			MainPage mainPage = new MainPage(driver);
			mainPage.deletePage(page.getPageName());

		}

		driver.quit();
	}

	@Test
	public void TC015_DA_MP() {
		System.out.println(
				"TC015_DA_MP - Verify that user is able to add additional pages besides \"Overview\" page successfully");

		String pageName = Utilities.uniquePageName("Page");
		// 1. Navigate to Dashboard login page. Login with valid account
		// 2. Go to Global Setting -> Add page. Enter Page Name field

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);

		mainPage.addPage(pageName, "", 0, "", false);

		// VP: New page is displayed besides "Overview" page

		boolean isPageNextToPage = mainPage.isPageNextToPage("Overview", pageName);
		softAssert.assertEquals(isPageNextToPage, true, "\nThe new page isn't displayed besides \"Overview\" page");

		// Post-condition: Delete newly added page

		mainPage.deletePage(pageName);
		softAssert.assertAll();
	}

	@Test
	public void TC017_DA_MP() {
		System.out.println("Verify \"Public\" pages can be visible and accessed by all users of working repository");
		String pageName = Utilities.uniquePageName("Page");
		// 1. Navigate to Dashboard login page. Login with valid account
		// 2. Go to Global Setting -> Add page. Enter Page Name field
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		mainPage.addPage(pageName, "", 0, "", true);
		// VP Check Page added successfully
		mainPage.isPageExisted(pageName);
		mainPage.logout();
		// Login with other accounts
		loginPage.login(Constant.OtherUsername, Constant.OtherPassword, Constant.DefaultRepo);

		softAssert.assertEquals(mainPage.isPageExisted(pageName), true);
		// Post-condition: Delete newly added page
		mainPage.logout();
		loginPage.login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		mainPage.deletePage(pageName);
		softAssert.assertAll();

	}
	
	@Test
	public void TC016_DA_MP()
	{
		System.out.println(
				"Verify the newly added main parent page is positioned at the location specified as set with Displayed After field of New Page form on the main page bar Parent Page dropped down menu");
		String pageName = Utilities.uniquePageName("Page");
		// 1. Navigate to Dashboard login page. Login with valid account
		// 2. Go to Global Setting -> Add page. Enter Page Name field
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		TAPage originPage = new TAPage(pageName);
		mainPage.addNewPage(originPage);
		//VP check add page successfully
		softAssert.assertEquals(mainPage.isPageExisted(pageName), true);
		//Add another page after the first page
		String pageName2 = Utilities.uniquePageName("Page");
		TAPage secondPage = new TAPage(pageName2, null,0,pageName);
		mainPage.addNewPage(secondPage);
		//VP check that the second page displays beside the first page
		softAssert.assertEquals(mainPage.isPageNextToPage(pageName, pageName2),true);
		//Post condition
		page = originPage;
		pageParent=secondPage;
		
		

	}
	
}
