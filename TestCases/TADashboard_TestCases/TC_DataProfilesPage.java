package TADashboard_TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import Common.Utilities;
import Constant.Constant;
import Constant.EnumItemType;
import Constant.EnumPreSetDataProfiles;
import TADashboard.DataProfilesPage;
import TADashboard.LoginPage;
import TestBase.TestBase;

public class TC_DataProfilesPage extends TestBase {

	@AfterMethod
	public void afterMethod_cleanup() {

		DataProfilesPage dataProfilesPage = new DataProfilesPage(driver);
		dataProfilesPage.deleteAllDataProfiles();
		softAssert.assertAll();
		driver.quit();
	}

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
		// softAssert.assertAll();
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
		// softAssert.assertAll();
	}

	@Test
	public void TC070_DA_DP() {
		System.out.println("TC070_DA_DP - Verify Data Profiles are listed alphabetically");

		// 1 Step Navigate to Dashboard login page
		// 2 Step Select a specific repository
		// 3 Step Enter valid Username and Password
		// 4 Step Click Login
		// 5 Step Click Administer->Data Profiles
		LoginPage loginPage = new LoginPage(driver);
		DataProfilesPage dataProfilesPage = loginPage.open()
				.login(Constant.Username, Constant.Password, Constant.DefaultRepo).goToDataProfilesPage();

		// 6 VP Check Data Profiles are listed alphabetically

		softAssert.assertEquals(dataProfilesPage.isTableOrderByAscending(2), true);
		// softAssert.assertAll();
	}

	@Test
	public void TC071_DA_DP() {
		System.out.println("TC071_DA_DP - Verify Check Boxes are only present for non-preset Data Profiles.");

		// 1 Step Navigate to Dashboard login page
		// 2 Step Select a specific repository
		// 3 Step Enter valid Username and Password
		// 4 Step Click Login
		// 5 Step Click Administer->Data Profiles
		LoginPage loginPage = new LoginPage(driver);
		DataProfilesPage dataProfilesPage = loginPage.open()
				.login(Constant.Username, Constant.Password, Constant.DefaultRepo).goToDataProfilesPage();

		// 6 Step Create a new Data Profile
		// 7 Step Back to Data Profiles page
		String dataProfile = Utilities.uniquePageName("Data Profile ");
		dataProfilesPage.addNewDataProfile(dataProfile, null, null);

		// 8 VP Check Check Boxes are only present for non-preset Data Profiles.
		softAssert.assertEquals(dataProfilesPage.isDataProfileCheckboxExisted(dataProfile), true, dataProfile);

		for (EnumPreSetDataProfiles preSetDataProfiles : EnumPreSetDataProfiles.values()) {
			String preSetDataProfile = preSetDataProfiles.getDataProfile();
			softAssert.assertEquals(dataProfilesPage.isDataProfileCheckboxExisted(preSetDataProfile), false,
					dataProfile);
		}
		// softAssert.assertAll();
	}

	@Test
	public void TC072_DA_DP() {
		System.out.println(
				"TC072_DA_DP - Verify user is unable to proceed to next step or finish creating data profile if  \"Name *\" field is left empty");

		// 1 Step Log in Dashboard
		// 2 Step Navigate to Data Profiles page
		LoginPage loginPage = new LoginPage(driver);
		DataProfilesPage dataProfilesPage = loginPage.open()
				.login(Constant.Username, Constant.Password, Constant.DefaultRepo).goToDataProfilesPage();

		// 3 Step Click on "Add New"
		dataProfilesPage.lnkAddNew().click();

		// 4 Step Click on "Next Button"
		dataProfilesPage.btnNext().click();

		// 5 VP Check dialog message "Please input profile name" appears
		softAssert.assertEquals(dataProfilesPage.getAlertMessage(), Constant.inputDataProfileName);
		dataProfilesPage.acceptAlertIfAvailable(Constant.ShortTime);

		// 6 4 Step Click on "Finish Button"
		dataProfilesPage.btnFinish().click();

		// 7 VP Check dialog message "Please input profile name" appears
		softAssert.assertEquals(dataProfilesPage.getAlertMessage(), Constant.inputDataProfileName);

		// softAssert.assertAll();
	}

	@Test
	public void TC073_DA_DP() {
		System.out.println(
				"TC073_DA_DP - Verify special characters ' /:*?<>|\"#[ ]{}=%; 'is not allowed for input to \"Name *\" field");

		// 1 Step Log in Dashboard
		// 2 Step Navigate to Data Profiles page
		LoginPage loginPage = new LoginPage(driver);
		DataProfilesPage dataProfilesPage = loginPage.open()
				.login(Constant.Username, Constant.Password, Constant.DefaultRepo).goToDataProfilesPage();

		// 3 Step Click on "Add New"
		// 4 Step Input special character
		String specialString = "/<>|\"#[ ]{}=%;";
		dataProfilesPage.addNewDataProfile(specialString, null, null);

		// 5 VP Check dialog message indicates invalid characters: /:*?<>|"#[ ]{}=%; is
		// not allowed as input for name field appears
		softAssert.assertEquals(dataProfilesPage.getAlertMessage(), Constant.inputDPwithSpecialCharacter);
		// softAssert.assertAll();
	}

	@Test
	public void TC074_DA_DP() {
		System.out.println("TC074_DA_DP - Verify Data Profile names are not case sensitive");

		// 1 Step Log in Dashboard
		// 2 Step Navigate to Data Profiles page
		LoginPage loginPage = new LoginPage(driver);
		DataProfilesPage dataProfilesPage = loginPage.open()
				.login(Constant.Username, Constant.Password, Constant.DefaultRepo).goToDataProfilesPage();

		// Pre-Condition Data profile name "a" is already created
		String dataProfile = Utilities.uniquePageName("TC074_DA_DP ");
		dataProfilesPage.addNewDataProfile(dataProfile.toLowerCase(), null, null);

		// 3 Step Click on "Add New"
		// 4 Step Input charater 'A' into "Name *" field
		dataProfilesPage.addNewDataProfile(dataProfile.toUpperCase(), null, null);

		// 5 VP Check dialog message indicates invalid characters: /:*?<>|"#[ ]{}=%; is
		// not allowed as input for name field appears
		softAssert.assertEquals(dataProfilesPage.getAlertMessage(), Constant.dataProfileNameAlreadyExists);

	}

	@Test
	public void TC075_DA_DP() {
		System.out.println(
				"TC075_DA_DP - Verify all data profile types are listed under \"Item Type\" dropped down menu");

		// 1 Step Navigate to Dashboard login page
		// 2 Step Select a specific repository
		// 3 Step Enter valid Username and Password
		// 4 Step Click Login
		// 5 Step Click Administer->Data Profiles
		LoginPage loginPage = new LoginPage(driver);
		DataProfilesPage dataProfilesPage = loginPage.open()
				.login(Constant.Username, Constant.Password, Constant.DefaultRepo).goToDataProfilesPage();

		// 6 Click 'Add New' link
		dataProfilesPage.lnkAddNew().click();

		// 7 VP "Check all data profile types are listed under ""Item Type"" dropped
		// down menu in create profile
		// page"
		for (EnumItemType itemTypes : EnumItemType.values()) {
			String itemType = itemTypes.getItemType();
			softAssert.assertEquals(dataProfilesPage.isItemExisted(dataProfilesPage.cmbItemType(), itemType), true,
					itemType);
		}
		// softAssert.assertAll();
	}

	@Test
	public void TC076_DA_DP() {
		System.out.println(
				"TC076_DA_DP - Verify all data profile types are listed in priority order under \"Item Type\" dropped down menu");

		// 1 Step Log in Dashboard
		// 2 Step Navigate to Data Profiles page
		LoginPage loginPage = new LoginPage(driver);
		DataProfilesPage dataProfilesPage = loginPage.open()
				.login(Constant.Username, Constant.Password, Constant.DefaultRepo).goToDataProfilesPage();

		// 3 Step Click on "Add New"
		dataProfilesPage.lnkAddNew().click();

		// 4 Step Click on "Item Type" dropped down menu
		// 5 VP Check "Item Type" items are listed in priority order
		softAssert.assertEquals(dataProfilesPage.isItemTypeOrderByPriority(), true);
		// softAssert.assertAll();
	}

	@Test
	public void TC078_DA_DP() {
		System.out.println(
				"TC078_DA_DP - Verify default settings are applied correctly for newly created data profiles if user only set up \"General Settings\" page and finishes.");

		// 1 Step Log in Dashboard
		// 2 Step Navigate to Data Profiles page
		LoginPage loginPage = new LoginPage(driver);
		DataProfilesPage dataProfilesPage = loginPage.open()
				.login(Constant.Username, Constant.Password, Constant.DefaultRepo).goToDataProfilesPage();

		// 3 Step Click on "Add New"
		// 4 Step Input to "Name *" field
		// 5 Step Click "Item Type" and choose an item
		// 6 Step Click "Finish" button
		String dataProfile = Utilities.uniquePageName("Data Profile ");
		dataProfilesPage.addNewDataProfile(dataProfile, "test modules", null);

		// 7 Step Click on the newly created data profile DA_DP_TC078
		dataProfilesPage.clickOnDataProfileLink(dataProfile);
		// 8 VP Check the setting of General Settings Page "Name *": Test1, "Item Type":
		// Test Modules, Related Data: None
		softAssert.assertEquals(dataProfilesPage.checkDataProfileGeneralSetting(dataProfile, "Test Modules", "None"),
				true);

		// 9 Step Click Next Button
		dataProfilesPage.btnNext().click();
		// 10 VP Check the setting of Display Fields Page All check boxes are un-checked
		softAssert.assertEquals(dataProfilesPage.areAllCheckBoxesUnChecked(), true);

		// 11 Step Click Next Button
		dataProfilesPage.btnNext().click();
		// 12 VP Check the setting of Sort Fields Page Empty Sort Criteria list

		// 13 Step Click Next Button
		dataProfilesPage.btnNext().click();
		// 14 VP Check the setting of Filter Fields Page Empty Filter list
		softAssert.assertEquals(dataProfilesPage.getNumberOfItemInListBox(dataProfilesPage.lbFilterList()), 0);

		// 15 Step Click Next Button
		dataProfilesPage.btnNext().click();
		// 16 VP Check the setting of Statistic Page All check boxes are un-checked
		softAssert.assertEquals(dataProfilesPage.areAllCheckBoxesUnChecked(), true);

		// softAssert.assertAll();
	}

	@Test
	public void TC082_DA_DP() {
		System.out.println("TC082_DA_DP - Verify Check All / Uncheck All Links are working correctly");

		// 1 Step Navigate to Dashboard login page
		// 2 Step Log in with valid account
		// 3 Step Click on "Administer" llink
		// 4 Step Click on "Data Profiles" link
		LoginPage loginPage = new LoginPage(driver);
		DataProfilesPage dataProfilesPage = loginPage.open()
				.login(Constant.Username, Constant.Password, Constant.DefaultRepo).goToDataProfilesPage();

		// 5 Step Click "Add New" link
		dataProfilesPage.lnkAddNew().click();

		// 6 Step Enter Name field
		String dataProfile = Utilities.uniquePageName("DA_DP_TO082 ");
		dataProfilesPage.enterValue(dataProfilesPage.txtName(), dataProfile);

		// 7 Step Click on "Next" button
		dataProfilesPage.btnNext().click();

		// 8 Step Click on "Check All" link
		dataProfilesPage.lnkCheckAll().click();

		// 9 VP Verify all checkbox is checked
		softAssert.assertEquals(dataProfilesPage.areAllCheckBoxesChecked(), true);

		// 10 Step Click on "Uncheck All" link
		dataProfilesPage.lnkUnCheckAll().click();

		// 11 VP Verify all checkbox is unchecked
		softAssert.assertEquals(dataProfilesPage.areAllCheckBoxesUnChecked(), true);

		// softAssert.assertAll();
	}

}
