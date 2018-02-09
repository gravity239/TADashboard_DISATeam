package TADashboard_TestCases;

import org.testng.annotations.Test;

import Constant.Constant;
import TADashboard.DataProfilesPage;
import TADashboard.LoginPage;
import TestBase.TestBase;

public class TC_DataProfilesPage extends TestBase {

	@Test
	public void TC068_DA_DP() {
		System.out.println("TC068_DA_DP - Verify all Pre-set Data Profiles are populated correctly");

		// 1 Step Navigate to Dashboard login page
		// 2 Step Select a specific repository
		// 3 Step Enter valid Username and Password
		// 4 Step Click Login
		// 5 Step Click Administer->Data Profiles
		LoginPage loginPage = new LoginPage(driver);
		DataProfilesPage dataProfilesPage = loginPage.open()
				.login(Constant.Username, Constant.Password, Constant.DefaultRepo)
				.goToDataProfilesPage();

		// 6 VP Check Pre-set Data Profile are populated correctly in profiles page
		String dataProfile = "Action Implementation By Status";
		String itemType = "Action";
		String relatedData = "";

		softAssert.assertEquals(dataProfilesPage.isDataProfileExisted(dataProfile, itemType, relatedData), true);
	}
}
