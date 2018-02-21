package TADashboard_TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import TADashboard.DataProfilesPage;
import TADashboard.LoginPage;
import TADashboard.MainPage;
import TADashboard.PanelPage;
import TestBase.TestBase;

public class TC_PanelPage extends TestBase {

	@AfterMethod
	public void afterMethod_cleanup() {
		softAssert.assertAll();
		driver.quit();
	}

	@Test
	public void TC030_DA_PANEL() {
		System.out.println("TC030_DA_PANEL -  Verify that when \"Choose panels\" form is expanded all pre-set panels are populated and sorted correctly");
		//1. Navigate to Dashboard login page
        //2. Login with valid account
        //3. Click on Add Page button
        //4. Enter page name to Page Name field.
        //5. Click OK button
        //6. Click "choose panels" button
		
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		mainPage.addPage(Utilities.uniquePageName("Page "), "", 0, "", false);
		PanelPage panelPage = new PanelPage(driver);
        panelPage.unhideChoosePanelsPage();
	}
}
