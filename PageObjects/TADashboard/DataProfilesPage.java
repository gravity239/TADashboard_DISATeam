package TADashboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;


public class DataProfilesPage extends GeneralPage{
	
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

	public WebElement LnkAddNew()
    {
        return myFindElement(_lnkAddNew, Constant.TimeOut);
    }

    public WebElement TxtName()
    {
        return myFindElement(_txtName, Constant.TimeOut);
    }

    public WebElement CmbItemType()
    {
        return myFindElement(_cmbItemType, Constant.TimeOut);
    }

    public WebElement CmbRelatedData()
    {
        return myFindElement(_cmbRelatedData, Constant.TimeOut); 
    }

    public WebElement BtnNext()
    {
          return myFindElement(_btnNext, Constant.TimeOut);
    }

    public WebElement BtnFinish()
    {
          return myFindElement(_btnFinish, Constant.TimeOut);
    }

    public WebElement BtnCancel()
    {
          return myFindElement(_btnCancel, Constant.TimeOut);
    }

    public WebElement LnkDelete()
    {
          return myFindElement(_lnkDelete, Constant.TimeOut);
    }

    public WebElement TabGenenalSettings()
    {
          return myFindElement(_tabGenenalSettings, Constant.TimeOut);
    }
    public WebElement TabDisplayFields()
    {
          return myFindElement(_tabDisplayFields, Constant.TimeOut);
    }
    public WebElement TabSortFields()
    {
          return myFindElement(_tabSortFields, Constant.TimeOut);
    }
    public WebElement TabFilterFields()
    {
          return myFindElement(_tabFilterFields, Constant.TimeOut);
    }

    public WebElement TabStatisticFields()
    {
          return myFindElement(_tabStatisticFields, Constant.TimeOut);
    }

    public WebElement TblProfileSettings()
    {
          return myFindElement(_tblProfileSettings, Constant.TimeOut);
    }

    public WebElement LbFilterList()
    {
          return myFindElement(_lbFilterList, Constant.TimeOut);
    }
    
    
    
}
