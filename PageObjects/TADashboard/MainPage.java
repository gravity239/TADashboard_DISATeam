package TADashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import Constant.Constant;
import Common.Utilities;

public class MainPage extends GeneralPage {
	private WebDriver _driverMainPage;

	private static final String _lnkMainMenu = "//li[@class='sep']/parent::*/../a[contains(.,'%1$s')]";
	private static final String _lnkSubMenu = "//li[@class='sep']/parent::*/../a[contains(.,'%1$s')]/following::a[contains(.,'%2$s')]";
	private static final String _lnkSettingItem = "//li[@class='mn-setting']//a[ .='%1$s']";
	private static final String _lnkPage = "//a[.='%1$s']";
	private static final String _lnkParentPage = "//a[.='%1$s']/../parent::ul/parent::li/a";
	private static final By _lnkAccount = By.xpath("//a[@href='#Welcome']");
	private static final By _lnkLogout = By.xpath("//a[@href='logout.do']");
	private static final By _lblRepositoryName = By.xpath("//a[@href='#Repository']/span");
	private static final By _tabSetting = By.xpath("//li[@class='mn-setting']");
	private static final By _chbPublic = By.xpath("//input[@id='ispublic']");
	private static final By _txtNewPageName = By.xpath("//div[@id='div_popup']//input[@class='page_txt_name']");
	private static final By _cmbParentPage = By.xpath("//div[@id='div_popup']//input[@class='page_txt_name']");
	private static final By _cmbPageDisplayAfter = By.xpath("//div[@id='div_popup']//select[@id='afterpage']");
	private static final By _cbmNumberOfColumns = By.xpath("//div[@id='div_popup']//select[@id='columnnumber']");
	private static final By _btnPageOK = By.xpath("//div[@id='div_popup']//input[contains(@onclick,'doAddPage')]");
	private static final By _btnPageCancel = By.xpath("//div[@id='div_popup']//input[contains(@onclick,'closeWindow')]");
	private static final By _dlgPopupHeader = By.xpath("//div[@id='div_popup']//td[@class='ptc']/h2");
	
	public WebElement getLnkAccount() {
		return MyFindElement(_lnkAccount, Constant.TimeOut);
	}
	
	public WebElement getLnkLogout() {
		return MyFindElement(_lnkLogout, Constant.TimeOut);
	}
	
	public WebElement getLblRepositoryName() {
		return MyFindElement(_lblRepositoryName, Constant.TimeOut);
	}
	
	public WebElement getTabSetting() {
		return MyFindElement(_tabSetting, Constant.TimeOut);
	}
	
	public WebElement getChbPublic() {
		return MyFindElement(_chbPublic, Constant.TimeOut);
	}
	
	public WebElement getTxtNewPageName() {
		return MyFindElement(_txtNewPageName, Constant.TimeOut);
	}
	
	public WebElement getCmbParentPage() {
		return MyFindElement(_cmbParentPage, Constant.TimeOut);
	}
	
	public WebElement getCmbPageDisplayAfter() {
		return MyFindElement(_cmbPageDisplayAfter, Constant.TimeOut);
	}
	
	public WebElement getCbmNumberOfColumns() {
		return MyFindElement(_cbmNumberOfColumns, Constant.TimeOut);
	}
	
	public WebElement getBtnPageOK() {
		return MyFindElement(_btnPageOK, Constant.TimeOut);
	}
	
	public WebElement getBtnPageCancel() {
		return MyFindElement(_btnPageCancel, Constant.TimeOut);
	}
	
	public WebElement getDlgPopupHeader() {
		return MyFindElement(_dlgPopupHeader, Constant.TimeOut);
	}

	public MainPage(WebDriver driver) {
		super(driver);
		this._driverMainPage = driver;
	}

	// Determine if dashboard mainpage displayed
	public boolean IsDashboardMainpageDisplayed() {
		boolean foundDashboardMainpage = getLnkAccount().isDisplayed();
		return foundDashboardMainpage;
	}

	// Log out from TA Dashboard page
	public LoginPage Logout() {
		WebDriverWait wait = new WebDriverWait(_driverMainPage, 10);
		wait.until(ExpectedConditions.elementToBeClickable(getLnkAccount()));
		Actions builder = new Actions(_driverMainPage);
		Action mouseOverLnkAccount = builder.moveToElement(getLnkAccount()).build();
		mouseOverLnkAccount.perform();
		getLnkLogout().click();
		return new LoginPage(_driverMainPage);
	}

	// Select the menu item
	public void SelectMenuItem(String mainMenu, String subMenu) {

		WebElement LnkMainMenu = MyFindElement(By.xpath(String.format(_lnkMainMenu, mainMenu)), Constant.TimeOut);
		// Actions builder = new Actions(_driverMainPage);
		// Action mouseOverLnkMainMenu = builder
		// .moveToElement(LnkMainMenu)
		// .build();
		// mouseOverLnkMainMenu.perform();
		Utilities.MouseTo(LnkMainMenu, _driverMainPage);
		WebElement LnkSubMenu = MyFindElement(By.xpath(String.format(_lnkSubMenu, mainMenu, subMenu)), Constant.TimeOut);
		LnkSubMenu.click();
	}

	// Switch the repository which the user wants to work on
	public void ChooseRepository(String repositoryName) {
		SelectMenuItem("Repository", repositoryName);
		WebDriverWait wait = new WebDriverWait(_driverMainPage, Constant.TimeOut);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(_lnkMainMenu, repositoryName))));
	}

	// Determines if alert dialog displayed
	public boolean isAlertPresent() {
		try {
			_driverMainPage.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	// Get the name of the repository.
	public String GetRepositoryName() {
		return getLblRepositoryName().getText();
	}

	// Select settings of general setting menu.
	public MainPage SelectGeneralSetting(String item) {
		Utilities.MouseTo(getTabSetting(), _driverMainPage);
		WebElement settingItem = _driverMainPage.findElement(By.xpath(String.format(_lnkSettingItem, item)));
		settingItem.click();
		return this;
	}

	// Determines if dashboard is locked by dialog
	public boolean IsDashboardLockedByDialog() {
		return getTabSetting().isEnabled();
	}

	public MainPage AddPage(String pageName, String parentPage, int numberOfColumn, String displayAferPage,
			boolean publicCheckBox) {
		this.SelectGeneralSetting("Add Page");
		getTxtNewPageName().sendKeys(pageName);

		if (parentPage != "") 
		{	
			Utilities.SelectItem(getCmbParentPage(), parentPage, "Text");
		}

		if (numberOfColumn != 0) {
			Utilities.SelectItem(getCbmNumberOfColumns(), String.valueOf(numberOfColumn), "Index");
		}

		if (displayAferPage != "") {
			Utilities.SelectItem(getCmbPageDisplayAfter(), displayAferPage, "Text");
		}

		if (publicCheckBox != false) {
			Utilities.Check(getChbPublic());
		}

		getBtnPageOK().click();
		By lnkPage = By.xpath(String.format(_lnkPage, pageName).replace(" ", "\u00A0"));
		if (parentPage != "")
		{
			By lnkParentPage = By.xpath(String.format(_lnkParentPage, pageName).replace(" ", "\u00A0"));
			WebElement LnkParentPage = MyFindElement(lnkParentPage, Constant.TimeOut);
			Utilities.MouseTo(LnkParentPage, _driverMainPage);
		}
			
		WebDriverWait wait = new WebDriverWait(_driverMainPage, Constant.TimeOut);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(lnkPage));
		
		return this;
	}
	
	// Determines if a page is next to another page.
	public boolean IsPageNextToPage(String currentPage, String nextPage) {
		 boolean isPageNextToPage = false;
		By current = By.xpath("//a[.='" + nextPage.replace(" ", "\u00A0") + "']/parent::*/preceding-sibling::*/a[.='"
				+ currentPage.replace(" ", "\u00A0") + "']");
		if (MyFindElement(current, Constant.TimeOut).getText().equals(currentPage)) 
		{
			isPageNextToPage =  true;			
		}
		return isPageNextToPage;
	}

    // Go to a page.
    public MainPage GotoPage(String pageLink)
    {
        String[] pages = pageLink.split("->");
        if (pages.length == 1)
        {
            By page = By.xpath("//a[.='" + pages[0].replace(" ", "\u00A0") + "']");
            WebElement LnkPage = MyFindElement(page, Constant.TimeOut);
            LnkPage.click();
        }
        else
        {
            int pageIndex = 0;
            while (pageIndex + 1 < pages.length)
            {
                By page = By.xpath("//a[.='" + pages[pageIndex].replace(" ", "\u00A0") + "']");
                WebElement LnkParent = MyFindElement(page, Constant.TimeOut);
                Utilities.MouseTo(LnkParent, _driverMainPage);  
                pageIndex = pageIndex + 1;
                page = By.xpath("//a[.='" + pages[pageIndex].replace(" ", "\u00A0") + "']");
                WebElement LnkPage = MyFindElement(page, Constant.TimeOut);
                if (pageIndex + 1 == pages.length)
                {
                    LnkPage.click();
                }
            }     
        }
        return this;
    }
    
    // Delete a page.
    public MainPage DeletePage(String pageLink)
    {
        GotoPage(pageLink);
        this.SelectGeneralSetting("Delete");
        acceptAlertIfAvailable(Constant.TimeOut);
        String[] pages = pageLink.split("->");
         if (pages.length == 1)
        {
        	By lnkPage = By.xpath("//a[.='" + pages[0].replace(" ", "\u00A0") + "']");
        	WebDriverWait wait = new WebDriverWait(_driverMainPage, Constant.TimeOut);
        	wait.until(ExpectedConditions.invisibilityOfElementLocated(lnkPage));
        }
        else
        {
            int pageIndex = 0;
            while (pageIndex + 1 < pages.length)
            {
            	By page = By.xpath("//a[.='" + pages[pageIndex].replace(" ", "\u00A0") + "']");
                WebElement LnkParent = MyFindElement(page, Constant.TimeOut);
                Utilities.MouseTo(LnkParent, _driverMainPage);
                pageIndex = pageIndex + 1;
                if (pageIndex + 1 == pages.length)
                {
                	By lnkPage = By.xpath("//a[.='" + pages[pageIndex].replace(" ", "\u00A0") + "']");
                    WebDriverWait wait = new WebDriverWait(_driverMainPage, Constant.TimeOut);
                	wait.until(ExpectedConditions.invisibilityOfElementLocated(lnkPage));
                }
            }
        }
        return this;
    }
    
    /// Determine if a page exists
    public boolean DoesPageExist(String pageLink)
    {
        WebDriverWait wait = new WebDriverWait(_driverMainPage, Constant.TimeOut);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(_lnkPage, "Overview"))));
        boolean doesPageExist = false;
        String[] pages = pageLink.split("->");
        if (pages.length == 1)
        {
            By lnkPage = By.xpath("//a[.='" + pages[0].replace(" ", "\u00A0") + "']");
            doesPageExist = this.DoesElementExist(lnkPage);
        }
        else
        {
            int pageIndex = 0;
            while (pageIndex + 1 < pages.length)
            {
                By page = By.xpath("//a[.='" + pages[pageIndex].replace(" ", "\u00A0") + "']");
                WebElement LnkParent = MyFindElement(page, Constant.TimeOut);
                Utilities.MouseTo(LnkParent,_driverMainPage);
                pageIndex = pageIndex + 1;
                By lnkPage = By.xpath("//a[.='" + pages[pageIndex].replace(" ", "\u00A0") + "']");
                {
                doesPageExist = this.DoesElementExist(lnkPage);
                }
            }
        }
        return doesPageExist;
    }
}
