package TADashboard_TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Common.Utilities;
import Constant.Constant;
import Constant.EnumPreSetDataProfiles;
import Constant.EnumPresetPanels;
import DataObject.TAPage;
import TADashboard.DataProfilesPage;
import TADashboard.LoginPage;
import TADashboard.MainPage;
import TADashboard.PanelPage;
import TestBase.TestBase;

public class TC_PanelPage extends TestBase {
	@Test
	public void TC030_DA_PANEL() {
		System.out.println(
				"TC030_DA_PANEL -  Verify that when \"Choose panels\" form is expanded all pre-set panels are populated and sorted correctly");
		// 1. Navigate to Dashboard login page
		// 2. Login with valid account
		// 3. Click on Add Page button
		// 4. Enter page name to Page Name field.
		// 5. Click OK button
		// 6. Click "choose panels" button

		String pageName = Utilities.uniquePageName("Page ");
		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		mainPage.addPage(pageName, "", 0, "", false);
		PanelPage panelPage = new PanelPage(driver);
		panelPage.unhideChoosePanelsPage();

		// VP: Verify all pre-set panels are populated and sorted correctly

		for (EnumPresetPanels presetPanels : EnumPresetPanels.values()) {
			String panelType = presetPanels.getPanelType();
			String panelDataProfile = presetPanels.getPanelDataProfile();
			softAssert.assertEquals(panelPage.isPresetPanelExisted(panelType, panelDataProfile), true,
					panelDataProfile);
		}

		softAssert.assertEquals(panelPage.isPanelSortedCorrectly("Charts"), true);
		softAssert.assertEquals(panelPage.isPanelSortedCorrectly("Indicators"), true);
		softAssert.assertEquals(panelPage.isPanelSortedCorrectly("Reports"), true);
		softAssert.assertEquals(panelPage.isPanelSortedCorrectly("Heat Maps"), true);

		// Post-condition: Delete the new page

		softAssert.assertAll();
		panelPage.deletePage(pageName);

	}

	@Test
	public void TC031_DA_PANEL() {
		System.out.println(
				"TC031_DA_PANEL -  When \"Add New Panel\" form is on focused all other control/form is disabled or locked");
		// 1. Navigate to Dashboard login page
		// 2. Login with valid account
		// 3. Click Administer link
		// 4. Click Panel link
		// 5. Click Add New link
		// 6. Try to click other controls when Add New Panel dialog is opening

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		mainPage.selectMenuItem("Administer", "Panels");
		PanelPage panelPage = new PanelPage(driver);
		panelPage.getLnkAddNew().click();

		// VP: Verify all control/form are disabled or locked when Add New Panel dialog
		// is opening

		softAssert.assertEquals(panelPage.isDashboardLockedByDialog(), true);
		softAssert.assertAll();

	}

	@Test
	public void TC032_DA_PANEL() {
		System.out.println(
				"TC032_DA_PANEL - Verify that user is unable to create new panel when (*) required field is not filled");

		// 1. Navigate to Dashboard login page
		// 2. Select specific repository
		// 3. Enter valid username and password
		// 4. Click on Login button
		// 5. Click on Administer/Panels link
		// 6. Click on "Add new" link
		// 7. Enter value into Display Name field with special characters except "@"
		// 8. Click on OK button

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);

		mainPage.selectMenuItem("Administer", "Panels");

		PanelPage panelPage = new PanelPage(driver);
		panelPage.getLnkAddNew().click();
		panelPage.getBtnOk().click();

		// VP: Warning message: "Display Name is required field" show up

		softAssert.assertEquals(panelPage.getAlertMessage(), "Display Name is a required field.");

		// Post-condition

		softAssert.assertAll();
	}

	@Test
	public void TC033_DA_PANEL() {
		System.out.println(
				"TC033_DA_PANEL - Verify that no special character except '@' character is allowed to be inputted into \"Display Name\" field");

		String panelFalse = "Logigear#$%";
		String panelTrue = Utilities.uniquePageName("Panel").concat("@");
		String panelSeries = "name";

		// 1. Navigate to Dashboard login page
		// 2. Select specific repository
		// 3. Enter valid username and password
		// 4. Click on Login button
		// 5. Click on Administer/Panels link
		// 6. Click on "Add new" link
		// 7. Click on OK button

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);

		mainPage.selectMenuItem("Administer", "Panels");

		PanelPage panelPage = new PanelPage(driver);
		panelPage.getLnkAddNew().click();
		panelPage.getTxtDisplayName().sendKeys(panelFalse);
		panelPage.getBtnOk().click();

		// VP: Warning message: "Display Name is required field" show up

		softAssert.assertEquals(panelPage.getAlertMessage(),
				"Invalid display name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\"#[]{}=%;");

		// 8. Close Warning Message box
		// 9. Click Add New link
		// 10. Enter value into Display Name field with special character is @

		panelPage.acceptAlertIfAvailable(Constant.ShortTime);
		panelPage.getTxtDisplayName().clear();
		panelPage.getTxtDisplayName().sendKeys(panelTrue);
		panelPage.getCmbSeries().selectByValue(panelSeries);
		panelPage.getBtnOk().click();

		// VP: The new panel is created

		softAssert.assertEquals(panelPage.isPanelCreated(panelTrue), true);

		// Post-condition

		softAssert.assertAll();
	}

	@Test
	public void TC034_DA_PANEL() {
		System.out.println(
				"DA_PANEL_TC034 - Verify that correct panel setting form is displayed with corresponding panel type selected");
		// Pre-Condition: Navigate to Dashboard login page and Login with valid account
		// 1. Click on Administer/Panels link
		// 2. Click on Add new link

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);

		mainPage.selectMenuItem("Administer", "Panels");

		PanelPage panelPage = new PanelPage(driver);
		panelPage.getLnkAddNew().click();

		// VP: Chart panel setting form is displayed "chart setting" under Display Name
		// field

		softAssert.assertEquals(panelPage.getSettingHeader(), "Chart Settings");

		// 3. Select Indicator type

		panelPage.getRbIndicator().click();

		// VP: Chart panel setting form is displayed "Indicator setting" under Display
		// Name field
		softAssert.assertEquals(panelPage.getSettingHeader(), "Indicator Settings");

		// 4. Select Report type

		panelPage.getRbReport().click();

		// Report panel setting form is no longer available -> Remove this check

		// Post-Condition

		softAssert.assertAll();

	}

	@Test
	public void TC035_DA_PANEL() {
		System.out.println(
				"DA_PANEL_TC035 - Verify that user is not allowed to create panel with duplicated \"Display Name\"");

		String panelName = Utilities.uniquePageName("Panel ");
		String panelSeries = "name";

		// Pre-Condition: Navigate to Dashboard login page and Login with valid account
		// 1. Click on Administer/Panels link
		// 2. Click on Add new link

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);

		mainPage.selectMenuItem("Administer", "Panels");
		PanelPage panelPage = new PanelPage(driver);
		panelPage.getLnkAddNew().click();

		// 3. Enter display name to "Display name" field.
		// 4. Click on OK button

		panelPage.getTxtDisplayName().sendKeys(panelName);
		panelPage.getCmbSeries().selectByValue(panelSeries);
		panelPage.getBtnOk().click();
		panelPage.waitForAddingPanel(panelName);

		// 5. Click on Add new link again.
		// 6. Enter display name same with previous display name to "display name"
		// field.

		panelPage.getLnkAddNew().click();
		panelPage.getTxtDisplayName().sendKeys(panelName);
		panelPage.getCmbSeries().selectByValue(panelSeries);
		panelPage.getBtnOk().click();

		// VP: Warning message: "Dupicated panel already exists. Please enter a
		// different name" show up
		softAssert.assertEquals(panelPage.getAlertMessage(),
				panelName + " already exists. Please enter a different name.");

		// Post-condition: Delete created panel
		panelPage.acceptAlertIfAvailable(Constant.ShortTime);
		panelPage.getBtnCancel().click();
		panelPage.deletePanel(panelName);
		softAssert.assertAll();
	}

	@Test
	public void TC036_DA_PANEL() {
		System.out.println(
				"TC036_DA_PANEL - Verify that \"Data Profile\" listing of \"Add New Panel\" and \"Edit Panel\" control/form are in alphabetical order");

		String panelName = Utilities.uniquePageName("Panel ");
		String panelSeries = "name";

		// Pre-Condition: Navigate to Dashboard login page and Login with valid account
		// 1. Click on Administer/Panels link
		// 2. Click on Add new link

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);

		mainPage.selectMenuItem("Administer", "Panels");
		PanelPage panelPage = new PanelPage(driver);
		panelPage.getLnkAddNew().click();

		// VP: Data Profile list is in alphabetical order

		softAssert.assertEquals(panelPage.isItemSortedI(panelPage.getCmbDataProfile()), true);

		// 3. Enter display name to Display Name textbox
		// 4. Click Ok button to create a panel
		// 5. Click on edit link

		panelPage.getTxtDisplayName().sendKeys(panelName);
		panelPage.getCmbSeries().selectByValue(panelSeries);
		panelPage.getBtnOk().click();
		panelPage.waitForAddingPanel(panelName);
		panelPage.clickEditPanel(panelName);

		// VP: Data Profile list is in alphabetical order

		softAssert.assertEquals(panelPage.isItemSortedI(panelPage.getCmbDataProfile()), true);

		// Post-condition: Delete created panel

		panelPage.getBtnCancel().click();
		panelPage.deletePanel(panelName);
		softAssert.assertAll();
	}

	@Test
	public void TC037_DA_PANEL() {
		System.out.println(
				"TC037_DA_PANEL - Verify that newly created data profiles are populated correctly under the \"Data Profile\" dropped down menu in  \"Add New Panel\" and \"Edit Panel\" control/form");

		String dataName = Utilities.uniquePageName("Data ");
		String panelName = Utilities.uniquePageName("Panel ");
		String panelSeries = "name";

		// Pre-Condition: Navigate to Dashboard login page and Login with valid account
		// 1. Click on Administer/Data Profiles link
		// 2. Click on Add new link
		// 3. Enter name to Name textbox
		// 4. Click on Finish button

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);

		mainPage.selectMenuItem("Administer", "Data Profiles");
		DataProfilesPage dataProfile = new DataProfilesPage(driver);

		dataProfile.lnkAddNew().click();
		dataProfile.txtName().sendKeys(dataName);
		dataProfile.btnFinish().click();

		// 5. Click on Administer/Panels link
		// 6. Click on add new link

		mainPage.selectMenuItem("Administer", "Panels");
		PanelPage panelPage = new PanelPage(driver);
		panelPage.getLnkAddNew().click();

		// VP: Data profiles are populated correctly under the "Data Profile" dropped
		// down menu.

		softAssert.assertEquals(panelPage.isProfileExisted(dataName), true);

		// 7. Enter display name to Display Name textbox
		// 8. Click Ok button to create a panel
		// 9. Click on edit link

		panelPage.getTxtDisplayName().sendKeys(panelName);
		panelPage.getCmbSeries().selectByValue(panelSeries);
		panelPage.getBtnOk().click();
		panelPage.waitForAddingPanel(panelName);
		panelPage.clickEditPanel(panelName);

		// VP: Data profiles are populated correctly under the "Data Profile" dropped
		// down menu.

		softAssert.assertEquals(panelPage.isProfileExisted(dataName), true);

		// Post-condition: Delete created panel

		panelPage.getBtnCancel().click();
		panelPage.deletePanel(panelName);
		dataProfile.deleteAllDataProfiles();
		softAssert.assertAll();
	}

	@Test
	public void TC038_DA_PANEL() {
		System.out.println(
				"TC038_DA_PANEL - Verify no special character except '@' character is allowed to be inputted into \"Chart Title\" field");

		String panelName = Utilities.uniquePageName("Panel@ ");
		String chartTitleFalse = Utilities.uniquePageName("Chart#$% ");
		String chartTitleTrue = Utilities.uniquePageName("Chart@ ");
		String panelSeries = "name";

		// 1. Navigate to Dashboard login page
		// 2. Login with valid account
		// 3. Click Administer link
		// 4. Click Panel link
		// 5. Click Add New link
		// 6. Enter value into Display Name field. Enter value into Chart Title field
		// with special characters except "@"
		// 7. Click Ok button

		LoginPage loginPage = new LoginPage(driver);
		MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);
		mainPage.selectMenuItem("Administer", "Panels");

		PanelPage panelPage = new PanelPage(driver);
		panelPage.getLnkAddNew().click();
		panelPage.getTxtDisplayName().sendKeys(panelName);
		panelPage.getCmbSeries().selectByValue(panelSeries);
		panelPage.getTxtChartTitle().sendKeys(chartTitleFalse);
		panelPage.getBtnOk().click();

		// 8.VP: Message "Invalid title name. The name can't contain high ASCII
		// characters or any of following characters: /:*?<>|"#{[]{};" is displayed

		softAssert.assertEquals(panelPage.getAlertMessage(),
				"Invalid title name. The name cannot contain high ASCII characters or any of the following characters: /:*?<>|\"#[]{}=%;");

		// 9. Close Warning Message box
		// 10. Click Add New link
		// 11. Enter value into Display Name field
		// 12. Enter value into Chart Title field with special character is @

		panelPage.acceptAlertIfAvailable(Constant.ShortTime);
		panelPage.getBtnCancel().click();
		panelPage.getLnkAddNew().click();
		panelPage.getTxtDisplayName().sendKeys(panelName);
		panelPage.getCmbSeries().selectByValue(panelSeries);
		panelPage.getTxtChartTitle().sendKeys(chartTitleTrue);
		panelPage.getBtnOk().click();

		// VP: The new panel is created

		softAssert.assertEquals(panelPage.isPanelCreated(panelName), true);

		// Post-condition: Delete the newly created panel

		panelPage.deletePanel(panelName);
		softAssert.assertAll();
	}

	@Test
	public void TC039_DA_PANEL() {
		System.out.println(
				"TC039_DA_PANEL - Verify that \"Category\", \"Series\" and \"Caption\" field are enabled and disabled correctly corresponding to each type of the \"Chart Type\"");
		String pageName = Utilities.uniquePageName("Page ");
		
		//1. Navigate to Dashboard login page
        //2. Select a specific repository 
        //3. Enter valid Username and Password
        //4. Click 'Login' button
        //5. Click 'Add Page' link
        //6. Enter Page Name
        //7. Click 'OK' button


        LoginPage loginPage = new LoginPage(driver);
        MainPage mainPage = loginPage.open().login(Constant.Username, Constant.Password, Constant.DefaultRepo);
        TAPage page = new TAPage(pageName, null, 0, null, false);
        mainPage.addNewPage(page);

        //8. Click 'Choose Panels' button
        //9. Click 'Create new panel' button
        //10. Click 'Chart Type' drop-down menu

        PanelPage panelPage = new PanelPage(driver);
        panelPage.unhideChoosePanelsPage();
        panelPage.getBtnCreateNewPanel().click();

        //11. VP: Check that 'Chart Type' are listed 5 options: 'Pie', 'Single Bar', 'Stacked Bar', 'Group Bar' and 'Line'

		softAssert.assertEquals(panelPage.getCmbChartType().getOptions().size(),5);
		softAssert.assertEquals(panelPage.isItemPresentInCombobox("Chart Type", "Pie", "value"),true);
		softAssert.assertEquals(panelPage.isItemPresentInCombobox("Chart Type", "Single Bar", "value"),true);
		softAssert.assertEquals(panelPage.isItemPresentInCombobox("Chart Type", "Stacked Bar", "value"),true);
		softAssert.assertEquals(panelPage.isItemPresentInCombobox("Chart Type", "Group Bar", "value"),true);
		softAssert.assertEquals(panelPage.isItemPresentInCombobox("Chart Type", "Line", "value"),true);
		
		//Post-Condition
		panelPage.getBtnCancel().click();
		panelPage.deletePage(pageName);
		softAssert.assertAll();
	}
}
