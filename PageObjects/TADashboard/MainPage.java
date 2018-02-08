package TADashboard;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
		return myFindElement(_lnkAccount, Constant.TimeOut);
	}
	
	public WebElement getLnkLogout() {
		return myFindElement(_lnkLogout, Constant.TimeOut);
	}
	
	public WebElement getLblRepositoryName() {
		return myFindElement(_lblRepositoryName, Constant.TimeOut);
	}
	
	public WebElement getTabSetting() {
		return myFindElement(_tabSetting, Constant.TimeOut);
	}
	
	public WebElement getChbPublic() {
		return myFindElement(_chbPublic, Constant.TimeOut);
	}
	
	public WebElement getTxtNewPageName() {
		return myFindElement(_txtNewPageName, Constant.TimeOut);
	}
	
	public Select getCmbParentPage() {
		return new Select(myFindElement(_cmbParentPage, Constant.TimeOut));
	}
	
	public Select getCmbPageDisplayAfter() {
		return new Select(myFindElement(_cmbPageDisplayAfter, Constant.TimeOut));
	}
	
	public Select getCbmNumberOfColumns() {
		return new Select(myFindElement(_cbmNumberOfColumns, Constant.TimeOut));
	}
	
	public WebElement getBtnPageOK() {
		return myFindElement(_btnPageOK, Constant.TimeOut);
	}
	
	public WebElement getBtnPageCancel() {
		return myFindElement(_btnPageCancel, Constant.TimeOut);
	}
	
	public WebElement getDlgPopupHeader() {
		return myFindElement(_dlgPopupHeader, Constant.TimeOut);
	}

	public MainPage(WebDriver driver) {
		super(driver);
		this._driverMainPage = driver;
	}

	// Determine if dashboard mainpage displayed
	public boolean isDashboardDisplayed() {
		boolean foundDashboardMainpage = getLnkAccount().isDisplayed();
		return foundDashboardMainpage;
	}

	// Log out from TA Dashboard page
	public LoginPage logout() {
		WebDriverWait wait = new WebDriverWait(_driverMainPage, 10);
		wait.until(ExpectedConditions.elementToBeClickable(getLnkAccount()));
		Actions builder = new Actions(_driverMainPage);
		Action mouseOverLnkAccount = builder.moveToElement(getLnkAccount()).build();
		mouseOverLnkAccount.perform();
		getLnkLogout().click();
		return new LoginPage(_driverMainPage);
	}

	// Select the menu item
	public void selectMenuItem(String mainMenu, String subMenu) {

		WebElement lnkMainMenu = myFindElement(By.xpath(String.format(_lnkMainMenu, mainMenu)), Constant.TimeOut);
		// Actions builder = new Actions(_driverMainPage);
		// Action mouseOverLnkMainMenu = builder
		// .moveToElement(LnkMainMenu)
		// .build();
		// mouseOverLnkMainMenu.perform();
		Utilities.mouseTo(lnkMainMenu, _driverMainPage);
		WebElement lnkSubMenu = myFindElement(By.xpath(String.format(_lnkSubMenu, mainMenu, subMenu)), Constant.TimeOut);
		lnkSubMenu.click();
	}

	// Switch the repository which the user wants to work on
	public void chooseRepository(String repositoryName) {
		selectMenuItem("Repository", repositoryName);
		WebDriverWait wait = new WebDriverWait(_driverMainPage, Constant.TimeOut);
		wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(_lnkMainMenu, repositoryName))));
	}

	// Get the name of the repository.
	public String getRepositoryName() {
		return getLblRepositoryName().getText();
	}

	// Select settings of general setting menu.
	public MainPage selectGeneralSetting(String item) {
		Utilities.mouseTo(getTabSetting(), _driverMainPage);
		WebElement settingItem = _driverMainPage.findElement(By.xpath(String.format(_lnkSettingItem, item)));
		settingItem.click();
		return this;
	}

	// Determines if dashboard is locked by dialog
	public boolean isDashboardLockedByDialog() {
		return getTabSetting().isEnabled();
	}

	public MainPage addPage(String pageName, String parentPage, int numberOfColumn, String displayAferPage,
			boolean publicCheckBox) {
		this.selectGeneralSetting("Add Page");
		getTxtNewPageName().sendKeys(pageName);

		if (parentPage != "") 
		{	
			getCmbParentPage().selectByVisibleText(parentPage);
		}

		if (numberOfColumn != 0) {
			
			getCbmNumberOfColumns().selectByIndex(numberOfColumn);
		}

		if (displayAferPage != "") {
			getCmbPageDisplayAfter().selectByVisibleText(displayAferPage);
		}

		if (publicCheckBox != false) {
			Utilities.check(getChbPublic());
		}

		getBtnPageOK().click();
		By lnkPage = By.xpath(String.format(_lnkPage, pageName).replace(" ", "\u00A0"));
		if (parentPage != "")
		{
			By lnkParentPage = By.xpath(String.format(_lnkParentPage, pageName).replace(" ", "\u00A0"));
			WebElement LnkParentPage = myFindElement(lnkParentPage, Constant.TimeOut);
			Utilities.mouseTo(LnkParentPage, _driverMainPage);
		}
			
		WebDriverWait wait = new WebDriverWait(_driverMainPage, Constant.TimeOut);
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(lnkPage));
		
		return this;
	}
	
	// Determines if a page is next to another page.
	public boolean isPageNextToPage(String currentPage, String nextPage) {
		 boolean isExisted = false;
		By current = By.xpath("//a[.='" + nextPage.replace(" ", "\u00A0") + "']/parent::*/preceding-sibling::*/a[.='"
				+ currentPage.replace(" ", "\u00A0") + "']");
		if (myFindElement(current, Constant.TimeOut).getText().equals(currentPage)) 
		{
			isExisted =  true;			
		}
		return isExisted;
	}

    // Go to a page.
    public MainPage GotoPage(String pageLink)
    {
        String[] pages = pageLink.split("->");
        if (pages.length == 1)
        {
            By page = By.xpath("//a[.='" + pages[0].replace(" ", "\u00A0") + "']");
            WebElement LnkPage = myFindElement(page, Constant.TimeOut);
            LnkPage.click();
        }
        else
        {
            int pageIndex = 0;
            while (pageIndex + 1 < pages.length)
            {
                By page = By.xpath("//a[.='" + pages[pageIndex].replace(" ", "\u00A0") + "']");
                WebElement LnkParent = myFindElement(page, Constant.TimeOut);
                Utilities.mouseTo(LnkParent, _driverMainPage);  
                pageIndex = pageIndex + 1;
                page = By.xpath("//a[.='" + pages[pageIndex].replace(" ", "\u00A0") + "']");
                WebElement LnkPage = myFindElement(page, Constant.TimeOut);
                if (pageIndex + 1 == pages.length)
                {
                    LnkPage.click();
                }
            }     
        }
        return this;
    }
    
    // Delete a page.
    public MainPage deletePage(String pageLink)
    {
        GotoPage(pageLink);
        this.selectGeneralSetting("Delete");
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
                WebElement LnkParent = myFindElement(page, Constant.TimeOut);
                Utilities.mouseTo(LnkParent, _driverMainPage);
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
    public boolean isPageExisted(String pageLink)
    {
        WebDriverWait wait = new WebDriverWait(_driverMainPage, Constant.TimeOut);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(String.format(_lnkPage, "Overview"))));
        boolean isPageExisted = false;
        String[] pages = pageLink.split("->");
        if (pages.length == 1)
        {
			By lnkPage = By.xpath("//a[.='" + pages[0].replace(" ", "\u00A0") + "']");
			isPageExisted = this.isElementExisted(lnkPage);
		} else {
			int pageIndex = 0;
			while (pageIndex + 1 < pages.length) {
                By page = By.xpath("//a[.='" + pages[pageIndex].replace(" ", "\u00A0") + "']");
                WebElement LnkParent = myFindElement(page, Constant.TimeOut);
                Utilities.mouseTo(LnkParent,_driverMainPage);
                pageIndex = pageIndex + 1;
                By lnkPage = By.xpath("//a[.='" + pages[pageIndex].replace(" ", "\u00A0") + "']");
                {
                isPageExisted = this.isElementExisted(lnkPage);
                }
            }
        }
        return isPageExisted;
    }
    
    public DataProfilesPage goToDataProfilesPage() {
		selectMenuItem("Administer", "Data Profiles");		
		return new DataProfilesPage(_driverMainPage);
    }
}
