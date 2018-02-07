package TADashboard_TestCases;

import org.testng.annotations.Test;

import Common.Utilities;
import TADashboard.LoginPage;
import TADashboard.MainPage;
import TestBase.TestBase;
import Constant.Constant;

public class TC_MainPage extends TestBase {

	@Test
	public void TC015_DA_MP() {
		System.out.println(
				"TC015_DA_MP - Verify that user is able to add additional pages besides \"Overview\" page successfully");

		String pageName = Utilities.uniquePageName("Page");
		// 1. Navigate to Dashboard login page. Login with valid account
		// 2. Go to Global Setting -> Add page. Enter Page Name field

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);

		mainPage.AddPage(pageName, "", 0, "", false);

		// VP: New page is displayed besides "Overview" page

		boolean isPageNextToPage = mainPage.IsPageNextToPage("Overview", pageName);
		softAssert.assertEquals(isPageNextToPage, true, "\nThe new page isn't displayed besides \"Overview\" page");

		// Post-condition: Delete newly added page

		mainPage.DeletePage(pageName);
		softAssert.assertAll();
	}
}
