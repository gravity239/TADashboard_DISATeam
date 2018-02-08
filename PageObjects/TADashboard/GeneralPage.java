package TADashboard;

import java.util.concurrent.TimeUnit;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Constant.Constant;

public class GeneralPage {
	protected WebDriver _driverGeneralPage;

	public GeneralPage(WebDriver driver) {
		this._driverGeneralPage = driver;
		_driverGeneralPage.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public boolean isAlertPresent() {
		try {
			_driverGeneralPage.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	// Move mouse to a element
	public void mouseTo(WebElement element, WebDriver webDriver) {
		Actions builder = new Actions(webDriver);
		Action mouseOverElement = builder.moveToElement(element).build();
		mouseOverElement.perform();
	}

	// Check or uncheck a checkbox
	public void check(WebElement element) {
		boolean isChecked = element.isSelected();
		if (isChecked == false) {
			element.click();
		}
	}

	// Check or uncheck a checkbox
	public void unCheck(WebElement element) {
		boolean isChecked = element.isSelected();
		if (isChecked == true) {
			element.click();
		}
	}
	
	public void selectComboboxValue(Select sElement, String value)
    {
  	  if ((value!= null) && (value!=""))
  	  {  		  
  		sElement.selectByVisibleText(value);
  	  }    	 
    }
	
	public void enterValue(WebElement element, String value)
    {
  	  if (value!= null)
  	  {
  		  element.clear();
  		  element.sendKeys(value);
  	  }
  	      	  
    }

	public void waitForAlertPresent(WebDriverWait wait, WebDriver driver, long timeOut) {
		wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitForElementToBeClickable(WebDriverWait wait, WebDriver driver, By locator, long timeOut) {
		wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}

	public void waitForElementToBeClickable(WebDriverWait wait, WebDriver driver, WebElement element, long timeOut) {
		wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void waitForElementToBeVisible(WebDriverWait wait, WebDriver driver, By locator, long timeOut) {
		wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}

	public void acceptAlertIfAvailable(long timeout) {
		long waitForAlert = System.currentTimeMillis() + timeout;
		boolean boolFound = false;
		do {
			try {
				Alert alert = this._driverGeneralPage.switchTo().alert();
				if (alert != null) {
					alert.accept();
					boolFound = true;
				}
			} catch (UnhandledAlertException ex) {
			}
		} while ((System.currentTimeMillis() < waitForAlert) && (!boolFound));
	}

	// Override FindElement
	public WebElement myFindElement(By by, long timeout) {
		WebElement ele = null;
		Stopwatch stopWatch = Stopwatch.createStarted();
		while (timeout >= 0) {
			try {
				WebDriverWait wait = new WebDriverWait(_driverGeneralPage, Constant.ShortTime);
				wait.until(ExpectedConditions.visibilityOfElementLocated(by));
				wait.until(ExpectedConditions.elementToBeClickable(by));
				ele = _driverGeneralPage.findElement(by);
				if (_driverGeneralPage instanceof JavascriptExecutor) {
					((JavascriptExecutor) _driverGeneralPage).executeScript("arguments[0].style.border='3px solid red'",
							ele);
				}
				break;
			} catch (StaleElementReferenceException e) {
				timeout = timeout - stopWatch.elapsed(TimeUnit.SECONDS);
				myFindElement(by, timeout);
				stopWatch.stop();
			} catch (NullPointerException e) {
				timeout = timeout - stopWatch.elapsed(TimeUnit.SECONDS);
				myFindElement(by, timeout);
				stopWatch.stop();
			} catch (WebDriverException e) {
				timeout = timeout - stopWatch.elapsed(TimeUnit.SECONDS);
				myFindElement(by, timeout);
				stopWatch.stop();
			} catch (IllegalArgumentException e) {
				timeout = timeout - stopWatch.elapsed(TimeUnit.SECONDS);
				myFindElement(by, timeout);
				stopWatch.stop();
			}
		}
		try {
			stopWatch.stop();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
		return ele;
	}

	public boolean isElementExisted(By locatorKey) {
		WebDriverWait wait = new WebDriverWait(_driverGeneralPage, Constant.ShortTime);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locatorKey));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
