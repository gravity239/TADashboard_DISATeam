package TADashboard;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class PanelPage extends MainPage {
	private WebDriver _driverPanelPage;
	private WebDriverWait _driveWaitPanelPage;
	private static final By _tabDisplaySetting = By.xpath("//a[@href='#tabs-displaySettings']");
	private static final By _tabFilter = By.xpath("//a[@href='#tabs-data']");
	private static final By _rbChart = By.xpath("//label[contains(.,'Chart')]/input[contains(@id,'radPanelType')]");
	private static final By _rbIndicator = By
			.xpath("//label[contains(.,'Indicator')]/input[contains(@id,'radPanelType')]");
	private static final By _rbReport = By.xpath("//label[contains(.,'Report')]/input[contains(@id,'radPanelType')]");
	private static final By _rbHeatMap = By
			.xpath("//label[contains(.,'Heat Map')]/input[contains(@id,'radPanelType')]");
	private static final By _cmbDataProfile = By.xpath("//select[@id='cbbProfile']");
	private static final By _txtDisplayName = By.xpath("//input[@id='txtDisplayName']");
	private static final By _lnkAddNew = By.xpath("//a[contains(@href,'openAddPanel')]");
	private static final By _lblSettingHeader = By.xpath("//fieldset[@id='fdSettings']/legend");
	private static final By _txtChartTitle = By.xpath("//input[@id='txtChartTitle']");
	private static final By _btnCreateNewPanel = By.xpath("//div[@class='cpbutton']/span[.='Create new panel']");
	private static final By _cmbChartType = By.xpath("//select[@id='cbbChartType']");
	private static final By _cmbCategory = By.xpath("//select[@id='cbbCategoryField']");
	private static final By _txtCaptionNextToCategory = By.xpath("//input[@id='txtCategoryXAxis']");
	private static final By _cmbSeries = By.xpath("//select[@id='cbbSeriesField']");
	private static final By _txtCaptionNextToSeries = By.xpath("//input[@id='txtValueYAxis']");
	private static final By _chbShowTitle = By.xpath("//input[@id='chkShowTitle']");
	private static final By _rbLegendsNone = By.xpath("//input[@id='radPlacementNone']");
	private static final By _rbLegendsTop = By.xpath("//input[@id='radPlacementTop']");
	private static final By _rbLegendsRight = By.xpath("//input[@id='radPlacementRight']");
	private static final By _rbLegendsBottom = By.xpath("//input[@id='radPlacementBottom']");
	private static final By _rbLegendsLeft = By.xpath("//input[@id='radPlacementLeft']");
	private static final By _rbStyle2D = By.xpath("//input[@id='rdoChartStyle2D']");
	private static final By _rbStyle3D = By.xpath("//input[@id='rdoChartStyle3D']");
	private static final By _cmbSelectPage = By.xpath("//select[@id='cbbPages']");
	private static final By _chbDataLabelsSeries = By.xpath("//input[@id='chkSeriesName']");
	private static final By _chbDataLabelsCategories = By.xpath("//input[@id='chkCategoriesName']");
	private static final By _chbDataLabelsValue = By.xpath("//input[@id='chkValue']");
	private static final By _chbDataLabelsPercentage = By.xpath("//input[@id='chkPercentage']");
	private static final By _lblPanelDialog = By.xpath("//span[@id='ui-dialog-title-div_panelPopup']");
	private static final By _txtFrom = By.xpath("input[@id='criteria'");
	private static final By _lbColor = By.xpath("input[@id='txtColor'");
	private static final By _cmbStatisticOn = By.xpath("//select[@id='cbbStatisticOn'");
	private static final By _cmbSeriesValue = By.xpath("//select[@id='cbbSeriesValue'");
	private static final By _cmbStatisticField = By.xpath("//select[@id='cbbStatField'");
	private static final By _cmbStatisticFieldValue = By.xpath("//select[@id='cbbStatFieldValue'");
	private static final By _rbSetAsHeatValue = By.xpath("//input[@id='radHeatValue_default']");
	private static final String _panelTypeInAddNewDialog = "//table[@id='infoSettings']//td[.='Type']/following-sibling::td/descendant::input";
	private static final String _panelTypeInEditDialog = "//table[@id='infoSettings']//td[.='Type']/following-sibling::td/label[@class='panel_setting_paneltype']";
	private static final String _panelTypeToSelect = "//label[contains(.,'{0}')]/input[contains(@id,'radPanelType')]";
	private static final By _btnCancel = By
			.xpath("//div[@class='ui-dialog editpanelDlg' and contains(@style,'display: block')]//input[@id='Cancel']");
	private static final By _cmbSelectpage = By.xpath("//select[@id='cbbPages']");
	private static final By _txtHeight = By.xpath("//input[@id='txtHeight']");
	private static final By _txtFolder = By.xpath("//input[@id='txtFolder']");
	private static final By _btnOk = By
			.xpath("//div[@class='ui-dialog editpanelDlg' and contains(@style,'display: block')]//input[@id='OK']");
	private static final By _btnSelectFolder = By.xpath("//a[contains(@href,'treeFolder')]");

	public WebElement getTabDisplaySetting() {
		return myFindElement(_tabDisplaySetting, Constant.TimeOut);
	}

	public WebElement getTabFilter() {
		return myFindElement(_tabFilter, Constant.TimeOut);
	}

	public WebElement getRbChart() {
		return myFindElement(_rbChart, Constant.TimeOut);
	}

	public WebElement RbIndicator() {
		return myFindElement(_rbIndicator, Constant.TimeOut);
	}

	public WebElement getRbReport() {
		return myFindElement(_rbReport, Constant.TimeOut);
	}

	public WebElement getRbHeatMap() {
		return myFindElement(_rbHeatMap, Constant.TimeOut);
	}

	public Select getCmbDataProfile() {
		return new Select(myFindElement(_cmbDataProfile, Constant.TimeOut));
	}

	public WebElement getTxtDisplayName() {
		return myFindElement(_txtDisplayName, Constant.TimeOut);
	}

	public WebElement getLnkAddNew() {
		return myFindElement(_lnkAddNew, Constant.TimeOut);
	}

	public WebElement getLblSettingHeader() {
		return myFindElement(_lblSettingHeader, Constant.TimeOut);
	}

	public WebElement getTxtChartTitle() {
		return myFindElement(_txtChartTitle, Constant.TimeOut);
	}

	public WebElement getBtnCreateNewPanel() {
		return myFindElement(_btnCreateNewPanel, Constant.TimeOut);
	}

	public Select getCmbChartType() {
		return new Select(myFindElement(_cmbChartType, Constant.TimeOut));
	}

	public Select getCmbCategory() {
		return new Select(myFindElement(_cmbCategory, Constant.TimeOut));
	}

	public WebElement getTxtCaptionNextToCategory() {
		return myFindElement(_txtCaptionNextToCategory, Constant.TimeOut);
	}

	public Select getCmbSeries() {
		return new Select(myFindElement(_cmbSeries, Constant.TimeOut));
	}

	public WebElement getTxtCaptionNextToSeries() {
		return myFindElement(_txtCaptionNextToSeries, Constant.TimeOut);
	}

	public WebElement getChbShowTitle() {
		return myFindElement(_chbShowTitle, Constant.TimeOut);
	}

	public WebElement getRbLegendsNone() {
		return myFindElement(_rbLegendsNone, Constant.TimeOut);
	}

	public WebElement getRbLegendsTop() {
		return myFindElement(_rbLegendsTop, Constant.TimeOut);
	}

	public WebElement getRbLegendsRight() {
		return myFindElement(_rbLegendsRight, Constant.TimeOut);
	}

	public WebElement getRbLegendsBottom() {
		return myFindElement(_rbLegendsBottom, Constant.TimeOut);
	}

	public WebElement getRbLegendsLeft() {
		return myFindElement(_rbLegendsLeft, Constant.TimeOut);
	}

	public WebElement getRbStyle2D() {
		return myFindElement(_rbStyle2D, Constant.TimeOut);
	}

	public WebElement getRbStyle3D() {
		return myFindElement(_rbStyle3D, Constant.TimeOut);
	}

	public Select getCmbSelectPage() {
		return new Select(myFindElement(_cmbSelectPage, Constant.TimeOut));
	}

	public WebElement getChbDataLabelsSeries() {
		return myFindElement(_chbDataLabelsSeries, Constant.TimeOut);
	}

	public WebElement getChbDataLabelsCategories() {
		return myFindElement(_chbDataLabelsCategories, Constant.TimeOut);
	}

	public WebElement getChbDataLabelsValue() {
		return myFindElement(_chbDataLabelsValue, Constant.TimeOut);
	}

	public WebElement getChbDataLabelsPercentage() {
		return myFindElement(_chbDataLabelsPercentage, Constant.TimeOut);
	}

	public WebElement getLblPanelDialog() {
		return myFindElement(_lblPanelDialog, Constant.TimeOut);
	}

	public WebElement getTxtFrom() {
		return myFindElement(_txtFrom, Constant.TimeOut);
	}

	public WebElement getLbColor() {
		return myFindElement(_lbColor, Constant.TimeOut);
	}

	public Select getCmbStatisticOn() {
		return new Select(myFindElement(_cmbStatisticOn, Constant.TimeOut));
	}

	public Select getCmbSeriesValue() {
		return new Select(myFindElement(_cmbSeriesValue, Constant.TimeOut));
	}

	public Select getCmbStatisticField() {
		return new Select(myFindElement(_cmbStatisticField, Constant.TimeOut));
	}

	public Select getCmbStatisticFieldValue() {
		return new Select(myFindElement(_cmbStatisticFieldValue, Constant.TimeOut));
	}

	public WebElement getRbSetAsHeatValue() {
		return myFindElement(_rbSetAsHeatValue, Constant.TimeOut);
	}

	public Select getCmbSelectpage() {
		return new Select(myFindElement(_cmbSelectpage, Constant.TimeOut));
	}

	public WebElement getBtnCancel() {
		return myFindElement(_btnCancel, Constant.TimeOut);
	}

	public WebElement getTxtHeight() {
		return myFindElement(_txtHeight, Constant.TimeOut);
	}

	public WebElement getTxtFolder() {
		return myFindElement(_txtFolder, Constant.TimeOut);
	}

	public WebElement getBtnOk() {
		return myFindElement(_btnOk, Constant.TimeOut);
	}

	public WebElement getBtnSelectFolder() {
		return myFindElement(_btnSelectFolder, Constant.TimeOut);
	}

	public PanelPage(WebDriver driver) {
		super(driver);
		this._driverPanelPage = driver;
	}

	/// Determines if a profile exists
	public boolean isProfileExisted(String profileName) {
		return this.isItemExisted(getCmbDataProfile(), profileName);
	}

	// Determines if a panel is created.
	public boolean isPanelCreated(String panelName) {
		By panel = By.xpath("//a[.='" + panelName + "']");
		return this.isElementExisted(panel);
	}

	// Get header of the setting
	public String getSettingHeader() {
		return getLblSettingHeader().getText();
	}

	// Wait for adding panel.
	public PanelPage waitForAddingPanel(String panelName) {
		By panel = By.xpath("//a[.='" + panelName + "']");
		this.isElementExisted(panel);
		this.isElementExisted(_lnkAddNew);
		return this;
	}

	// Click Edit Panel link
	public void clickEditPanel(String panelName) {
		By xpath = By.xpath("//a[.='" + panelName + "']/ancestor::tr//a[.='Edit']");
		myFindElement(xpath, Constant.TimeOut).click();
		this.isElementExisted(_txtDisplayName);
	}

	// Click Delete Panel link
	public void clickDeletePanel(String panelName) {
		By xpath = By.xpath("//a[.='" + panelName + "']/ancestor::tr//a[.='Delete']");
		myFindElement(xpath, Constant.TimeOut).click();
		this.waitForAlertPresent(_driveWaitPanelPage, _driverPanelPage, Constant.TimeOut);
	}

	// Delete a panel
	public PanelPage deletePanel(String panelName) {
		this.selectMenuItem("Administer", "Panels");
		clickDeletePanel(panelName);
		this.acceptAlertIfAvailable(Constant.TimeOut);
		return this;
	}

	// Return the type of Panel.
	public String getTypeOfPanel() {
		String typeOfPanel = "";
		if (getLblPanelDialog().getText().equals("Add New Panel")) {
			List<WebElement> radioButtonGroup = _driverPanelPage.findElements(By.xpath(_panelTypeInAddNewDialog));
			for (WebElement radioButton : radioButtonGroup) {
				if (radioButton.isSelected() == true) {
					String index = radioButton.getAttribute("value");
					if (index == "1")
						typeOfPanel = "Chart";
					else if (index == "2")
						typeOfPanel = "Indicator";
					else if (index == "3")
						typeOfPanel = "Report";
					else if (index == "4")
						typeOfPanel = "Heat Map";
				}
			}
		} else {
			WebElement LabelPanelType = myFindElement(By.xpath(_panelTypeInEditDialog), Constant.TimeOut);
			typeOfPanel = LabelPanelType.getText();
		}
		return typeOfPanel;
	}

}
