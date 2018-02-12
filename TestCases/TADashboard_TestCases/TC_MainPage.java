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

	public TAPage page;
	public TAPage pageChild;

	@AfterMethod
	public void afterMethod_cleanup() {
		if (pageChild != null) {

			MainPage mainPage = new MainPage(driver);
			mainPage.deletePage(page.getPageName() + "->" + pageChild.getPageName());
			pageChild = null;

		}
		if (page != null) {
			String pageName = page.getPageName();
			if (pageName.equals("Overview")) {
				page = null;
			} else {
				MainPage mainPage = new MainPage(driver);
				mainPage.deletePage(pageName);
				page = null;

			}
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
	public void TC016_DA_MP() {
		System.out.println(
				"Verify the newly added main parent page is positioned at the location specified as set with Displayed After field of New Page form on the main page bar Parent Page dropped down menu");
		String pageName = Utilities.uniquePageName("Page");
		// 1. Navigate to Dashboard login page. Login with valid account
		// 2. Go to Global Setting -> Add page. Enter Page Name field
		
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		page = new TAPage(pageName);
		mainPage.addNewPage(page);
		
		// VP check add page successfully
		softAssert.assertEquals(mainPage.isPageExisted(pageName), true);
		
		// Add another page after the first page
		String pageName2 = Utilities.uniquePageName("Page");
		pageChild = new TAPage(pageName2, null, 0, pageName);
		mainPage.addNewPage(pageChild);
		
		// VP check that the second page displays beside the first page
		softAssert.assertEquals(mainPage.isPageNextToPage(pageName, pageName2), true);

	}

	@Test
	public void TC018_DA_MP() {
		System.out.println(
				"Verify non \"Public\" pages can only be accessed and visible to their creators with condition that all parent pages above it are \"Public\"");
		String pageName = Utilities.uniquePageName("Page");
		// 1. Navigate to Dashboard login page. Login with valid account
		// 2. Go to Global Setting -> Add page. Enter Page Name field
		
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		
		// Add a public page
		page = new TAPage(pageName, null, 0, null, true);
		mainPage.addNewPage(page);
		
		// VP check add page successfully
		softAssert.assertEquals(mainPage.isPageExisted(pageName), true);
		// Add another page which is child of the first page.
		
		String pageName2 = Utilities.uniquePageName("Page");
		pageChild = new TAPage(pageName2, pageName, 0, null, false);
		mainPage.addNewPage(pageChild);
		// VP check that the second page displays beside the first page
		softAssert.assertEquals(mainPage.isPageExisted(pageName2), true);
		// logout and login with other account
		mainPage.logout();
		loginPage.login(Constant.OtherUsername, Constant.OtherPassword, Constant.DefaultRepo);
		// VP the childpage is invisible
		softAssert.assertEquals(mainPage.isPageExisted(pageName2), false);
		//Post condition.
		loginPage.logout();
		loginPage.login(Constant.Username, Constant.Password, Constant.DefaultRepo);

	}

	
	@Test
	public void TC019_DA_MP() {
		System.out.println(
				"Verify user is able to edit the \"Public\" setting of any page successfully");
		String pageName = Utilities.uniquePageName("Page");
		// 1. Navigate to Dashboard login page. Login with valid account
		// 2. Go to Global Setting -> Add page. Enter Page Name field
		
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		
		// Add a non-public page
		page = new TAPage(pageName, null, 0, null, false);
		mainPage.addNewPage(page);
		
		// VP check add page successfully
		softAssert.assertEquals(mainPage.isPageExisted(pageName), true);
		// Add another public page
		
		String pageName2 = Utilities.uniquePageName("Page");
		TAPage page2 = new TAPage(pageName2, null, 0, null, true);
		mainPage.addNewPage(page2);
		// VP check that the second page displays beside the first page
		softAssert.assertEquals(mainPage.isPageExisted(pageName2), true);
		// logout and login with other account
		mainPage.logout();
		loginPage.login(Constant.OtherUsername, Constant.OtherPassword, Constant.DefaultRepo);
		// VP the childpage is invisible
		softAssert.assertEquals(mainPage.isPageExisted(pageName2), false);
		//Post condition.
		loginPage.logout();
		loginPage.login(Constant.Username, Constant.Password, Constant.DefaultRepo);

	}

	
}
