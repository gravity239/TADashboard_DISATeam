package TADashboard_TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Common.Utilities;
import TADashboard.LoginPage;
import TADashboard.MainPage;
import TestBase.TestBase;
import Constant.Constant;
import DataObject.TAPage;
import java.util.ArrayList;
import java.util.concurrent.locks.Condition;

public class TC_MainPage extends TestBase {

	public ArrayList<String> pages = new ArrayList<String>();

	@AfterMethod
	public void afterMethod_cleanup() {
		for (int i = 0; i < pages.size(); i++) {
			MainPage mainPage = new MainPage(driver);
			mainPage.deletePage(pages.get(i));
		}
		pages.clear();
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

		pages.add(pageName);
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
		pages.add(pageName);
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
		TAPage page = new TAPage(pageName);
		mainPage.addNewPage(page);

		// VP check add page successfully
		softAssert.assertEquals(mainPage.isPageExisted(pageName), true);

		// Add another page after the first page
		String pageName2 = Utilities.uniquePageName("Page");
		TAPage pageChild = new TAPage(pageName2, null, 0, pageName);
		mainPage.addNewPage(pageChild);

		// VP check that the second page displays beside the first page
		softAssert.assertEquals(mainPage.isPageNextToPage(pageName, pageName2), true);
		// Post Condition
		pages.add(pageName + "->" + pageName2);
		pages.add(pageName);

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
		TAPage page = new TAPage(pageName, null, 0, null, true);
		mainPage.addNewPage(page);

		// VP check add page successfully
		softAssert.assertEquals(mainPage.isPageExisted(pageName), true);
		// Add another page which is child of the first page.

		String pageName2 = Utilities.uniquePageName("Page");
		TAPage pageChild = new TAPage(pageName2, pageName, 0, null, false);
		mainPage.addNewPage(pageChild);
		// VP check that the second page displays
		softAssert.assertEquals(mainPage.isPageExisted(pageName2), true);
		// logout and login with other account
		mainPage.logout();
		loginPage.login(Constant.OtherUsername, Constant.OtherPassword, Constant.DefaultRepo);
		// VP the childpage is invisible
		softAssert.assertEquals(mainPage.isPageExisted(pageName2), false);
		// Post condition.
		loginPage.logout();
		loginPage.login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		pages.add(pageName + "->" + pageName2);
		pages.add(pageName);

	}

	@Test
	public void TC019_DA_MP() {
		System.out.println("Verify user is able to edit the \"Public\" setting of any page successfully");
		String pageName = Utilities.uniquePageName("Page");
		// 1. Navigate to Dashboard login page. Login with valid account
		// 2. Go to Global Setting -> Add page. Enter Page Name field

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);

		// Add a non-public page
		TAPage page = new TAPage(pageName, null, 0, null, false);
		mainPage.addNewPage(page);

		// VP check add page successfully
		softAssert.assertEquals(mainPage.isPageExisted(pageName), true);

		// Add another public page
		String pageName2 = Utilities.uniquePageName("Page");
		TAPage page2 = new TAPage(pageName2, null, 0, null, true);
		mainPage.addNewPage(page2);

		// VP check that the second page displays
		softAssert.assertEquals(mainPage.isPageExisted(pageName2), true);

		// edit page and page2
		page.setPublic(true);
		mainPage.editPage(pageName, page);
		page2.setPublic(false);
		mainPage.editPage(pageName2, page2);
		mainPage.logout();

		// login to other account
		loginPage.login(Constant.OtherUsername, Constant.OtherPassword, Constant.DefaultRepo);

		// VP the page is is visibled and can be accessed

		softAssert.assertEquals(mainPage.isPageExisted(pageName), true);
		mainPage.gotoPage(pageName);

		// VP the page2 is invisible.

		softAssert.assertEquals(mainPage.isPageExisted(pageName2), false);

		// Post condition.
		loginPage.logout();
		loginPage.login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		pages.add(pageName);
		pages.add(pageName2);

	}

	@Test
	public void TC020_DA_MP() {
		System.out.println(
				"Verify user can remove any main parent page except \"Overview\" page successfully and the order of pages stays persistent as long as there is not children page under it");
		String pageName = Utilities.uniquePageName("Page");
		// 1. Navigate to Dashboard login page. Login with valid account
		// 2. Go to Global Setting -> Add page. Enter Page Name field

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);

		// Add a non-public page
		TAPage page1 = new TAPage(pageName, null, 0, null, false);
		mainPage.addNewPage(page1);

		// VP check add page successfully
		softAssert.assertEquals(mainPage.isPageExisted(pageName), true);

		// Add child page
		String pageName2 = Utilities.uniquePageName("Page");
		TAPage page2 = new TAPage(pageName2, pageName, 0, null, false);
		mainPage.addNewPage(page2);

		// VP check that the child page displays
		softAssert.assertEquals(mainPage.isPageExisted(pageName2), true);

		// delete parent page and verify displayed message.
		mainPage.gotoPage(pageName);
		mainPage.selectGeneralSetting("Delete");
		softAssert.assertEquals(mainPage.getAlertMessage(), Constant.deletePageConfirmation);
		mainPage.acceptAlertIfAvailable(Constant.TimeOut);
		softAssert.assertEquals(mainPage.getAlertMessage(), String.format(Constant.unableDeletePage, pageName));
		mainPage.acceptAlertIfAvailable(Constant.TimeOut);

		// delete childpage and verify displayed message.
		mainPage.gotoPage(pageName + "->" + pageName2);
		mainPage.selectGeneralSetting("Delete");
		softAssert.assertEquals(mainPage.getAlertMessage(), "Are you sure you want to delete this page?");
		mainPage.acceptAlertIfAvailable(Constant.TimeOut);

		// VP that childpage is deleted
		softAssert.assertEquals(mainPage.isPageExisted(pageName2), false);

		// delete parentpage again.
		mainPage.deletePage(pageName);

		// Vp parent page is deleted
		softAssert.assertEquals(mainPage.isPageExisted(pageName), false);

		// Goto Overview and check that there is no delete icon.
		mainPage.gotoPage("Overview");
		softAssert.assertEquals(mainPage._lnkDeletePage().isDisplayed(), false);
	}

	@Test
	public void TC021_DA_MP() {
		System.out.println("Verify user is able to add additional sibbling pages to the parent page successfully");
		String pageName = Utilities.uniquePageName("Page");
		// 1. Navigate to Dashboard login page. Login with valid account
		// 2. Go to Global Setting -> Add page. Enter Page Name field

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		TAPage page = new TAPage(pageName);
		mainPage.addNewPage(page);

		// VP check add page successfully
		softAssert.assertEquals(mainPage.isPageExisted(pageName), true);

		// Add another page which is child of the first page
		String pageName2 = Utilities.uniquePageName("Page");
		TAPage pageChild = new TAPage(pageName2, pageName, 0, null);
		mainPage.addNewPage(pageChild);

		// Add another page which is child of the first page
		String pageName3 = Utilities.uniquePageName("Page");
		TAPage pageChild2 = new TAPage(pageName3, pageName, 0, null);
		mainPage.addNewPage(pageChild2);

		// VP check that the third page added
		softAssert.assertEquals(mainPage.isPageExisted(pageName3), true);
		// Post Condition
		pages.add(pageName + "->" + pageName2);
		pages.add(pageName + "->" + pageName3);
		pages.add(pageName);

	}

	@Test
	public void TC022_DA_MP() {
		System.out
				.println("Verify user is able to add additional sibbling page levels to the parent page successfully.");
		String pageName = Utilities.uniquePageName("Page");
		// 1. Navigate to Dashboard login page. Login with valid account
		// 2. Go to Global Setting -> Add page. Enter Page Name field
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		mainPage.addPage(pageName, "Overview", 0, "", true);
		// VP Check Page added successfully
		mainPage.isPageExisted(pageName);
		pages.add(pageName);
		softAssert.assertAll();

	}
}
