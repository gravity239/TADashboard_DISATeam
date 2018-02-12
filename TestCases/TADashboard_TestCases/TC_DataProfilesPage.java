package TADashboard_TestCases;

import org.testng.annotations.Test;

import Constant.Constant;
import Constant.EnumPreSetDataProfiles;
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
				.login(Constant.Username, Constant.Password, Constant.DefaultRepo).goToDataProfilesPage();

		// 6 VP Check Pre-set Data Profile are populated correctly in profiles page

		for (EnumPreSetDataProfiles preSetDataProfile : EnumPreSetDataProfiles.values()) {
			String dataProfile = preSetDataProfile.getDataProfile();
			String itemType = preSetDataProfile.getItemType();
			String relatedData = preSetDataProfile.getRelatedData();
			softAssert.assertEquals(dataProfilesPage.isDataProfileExisted(dataProfile, itemType, relatedData), true,
					dataProfile);
		}
		softAssert.assertAll();
	}

	@Test
	public void TC069_DA_DP() {
		System.out.println("TC069_DA_DP - Verify all Pre-set Data Profiles are populated correctly");

		// 1 Step Navigate to Dashboard login page
		// 2 Step Select a specific repository
		// 3 Step Enter valid Username and Password
		// 4 Step Click Login
		// 5 Step Click Administer->Data Profiles
		LoginPage loginPage = new LoginPage(driver);
		DataProfilesPage dataProfilesPage = loginPage.open()
				.login(Constant.Username, Constant.Password, Constant.DefaultRepo).goToDataProfilesPage();

		for (EnumPreSetDataProfiles preSetDataProfile : EnumPreSetDataProfiles.values()) {
			String dataProfile = preSetDataProfile.getDataProfile();

			// 6 VP Check there is no 'Delele' or 'Edit' link appears in Action section of
			// Pre-set Data Profiles
			softAssert.assertEquals(dataProfilesPage.isDataProfileButtonExisted(dataProfile, "Delete"), false,
					dataProfile);
			softAssert.assertEquals(dataProfilesPage.isDataProfileButtonExisted(dataProfile, "Edit"), false,
					dataProfile);

			// 7 Step Click on Pre-set Data Profile name
			// 8 VP Check there is no link on Pre-set Data Profile name
			softAssert.assertEquals(dataProfilesPage.isDataProfileLinkExisted(dataProfile), false, dataProfile);

			// 9 VP Check there is no checkbox appears in the left of Pre-set Data Profiles
			softAssert.assertEquals(dataProfilesPage.isDataProfileCheckboxExisted(dataProfile), false, dataProfile);

		}
		softAssert.assertAll();
	}

}
