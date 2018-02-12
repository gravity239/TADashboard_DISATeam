package TADashboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class DataProfilesPage extends MainPage {

	private WebDriver _driverDPPage;
	private WebDriverWait _driverWaitMainPage;

	public DataProfilesPage(WebDriver driver) {
		super(driver);
		this._driverDPPage = driver;
	}

	private static final By _lnkAddNew = By.xpath("//a[.='Add New']");
	private static final By _txtName = By.xpath("//input[@id='txtProfileName']");
	private static final By _cmbItemType = By.xpath("//select[@id ='cbbEntityType']");
	private static final By _cmbRelatedData = By.xpath("//select[@id ='cbbSubReport']");
	private static final By _btnNext = By.xpath("//input[@value='Next']");
	private static final By _btnFinish = By.xpath("//input[@value='Finish']");
	private static final By _btnCancel = By.xpath("//input[@value='Cancel']");
	private static final By _lnkDelete = By.xpath("//a[.='Delete']");
	private static final By _tabGenenalSettings = By.xpath("//li[.='General Settings']");
	private static final By _tabDisplayFields = By.xpath("//li[.='Display Fields']");
	private static final By _tabSortFields = By.xpath("//li[.='Sort Fields']");
	private static final By _tabFilterFields = By.xpath("//li[.='Filter Fields']");
	private static final By _tabStatisticFields = By.xpath("//li[.='Statistic Fields']");
	private static final By _tblProfileSettings = By.xpath("//table[@id='profilesettings']/tbody/tr");
	private static final By _lbFilterList = By.xpath("//select[@id = 'listCondition']");
	private static final String _tabDataProfiles = "//table[@class='GridView']/tbody/";
	private static final String _dataProfile = "//table/tbody/tr/td[.='%s']/following-sibling::td[.='%2$s']/following-sibling::td[.=\"%3$s\"]";
	private static final String _dataProfileButton = "//table/tbody/tr/td[.='%s']/following-sibling::td/a[.='%s']";
	private static final String _dataProfileLink = "//table/tbody/tr/td/a[.='%s']";
	private static final String _dataProfileCheckbox = "//table/tbody/tr/td[.='%s']/preceding-sibling::td/input[@type='checkbox']";

	public WebElement lnkAddNew() {
		return myFindElement(_lnkAddNew, Constant.ShortTime);
	}

	public WebElement txtName() {
		return myFindElement(_txtName, Constant.ShortTime);
	}

	public Select cmbItemType() {
		return new Select(myFindElement(_cmbItemType, Constant.ShortTime));
	}

	public Select cmbRelatedData() {
		return new Select(myFindElement(_cmbRelatedData, Constant.ShortTime));
	}

	public WebElement btnNext() {
		return myFindElement(_btnNext, Constant.ShortTime);
	}

	public WebElement btnFinish() {
		return myFindElement(_btnFinish, Constant.ShortTime);
	}

	public WebElement btnCancel() {
		return myFindElement(_btnCancel, Constant.ShortTime);
	}

	public WebElement lnkDelete() {
		return myFindElement(_lnkDelete, Constant.ShortTime);
	}

	public WebElement tabGenenalSettings() {
		return myFindElement(_tabGenenalSettings, Constant.ShortTime);
	}

	public WebElement tabDisplayFields() {
		return myFindElement(_tabDisplayFields, Constant.ShortTime);
	}

	public WebElement tabSortFields() {
		return myFindElement(_tabSortFields, Constant.ShortTime);
	}

	public WebElement tabFilterFields() {
		return myFindElement(_tabFilterFields, Constant.ShortTime);
	}

	public WebElement tabStatisticFields() {
		return myFindElement(_tabStatisticFields, Constant.ShortTime);
	}

	public WebElement tblProfileSettings() {
		return myFindElement(_tblProfileSettings, Constant.ShortTime);
	}

	public WebElement lbFilterList() {
		return myFindElement(_lbFilterList, Constant.ShortTime);
	}

	public boolean isDataProfileExisted(String dataProfile, String itemType, String relatedData) {
		String locator = String.format(_dataProfile, dataProfile, itemType, relatedData).replace(" ", "\u00A0");
		By dataProfileLocator = By.xpath(locator);
		return isElementExisted(dataProfileLocator);
	}

	public boolean isDataProfileLinkExisted(String dataProfile) {
		String locator = String.format(_dataProfileLink, dataProfile).replace(" ", "\u00A0");
		By dataProfileLocator = By.xpath(locator);
		return isElementExisted(dataProfileLocator);
	}

	public boolean isDataProfileButtonExisted(String dataProfile, String button) {
		String locator = String.format(_dataProfileButton, dataProfile, button).replace(" ", "\u00A0");
		By dataProfileLocator = By.xpath(locator);
		return isElementExisted(dataProfileLocator);
	}

	public boolean isDataProfileCheckboxExisted(String dataProfile) {
		String locator = String.format(_dataProfileCheckbox, dataProfile).replace(" ", "\u00A0");
		By dataProfileLocator = By.xpath(locator);
		return isElementExisted(dataProfileLocator);
	}

	public boolean isTableOrderByAscending(int colum_number) {
		int startRow = 2;
		String cellValue = getTableCellValue(_tabDataProfiles, startRow, colum_number);
		String cellValueAfter = getTableCellValue(_tabDataProfiles, startRow + 1, colum_number);	

		while (cellValueAfter != "") {
			if (cellValue.compareTo(cellValueAfter)>0) {
				return false;
			} else {
				startRow = startRow + 1;
				cellValue = getTableCellValue(_tabDataProfiles, startRow, colum_number);
				cellValueAfter = getTableCellValue(_tabDataProfiles, startRow + 1, colum_number);
			}
		}
		return true;
	}
	
	public void addNewDataProfile(String dataProfileName, String itemType, String relatedData) {
		lnkAddNew().click();
		enterValue(txtName(), dataProfileName);
		selectComboboxValue(cmbItemType(), itemType);
		selectComboboxValue(cmbRelatedData(), relatedData);
		btnFinish().click();
	}
	
	
}
