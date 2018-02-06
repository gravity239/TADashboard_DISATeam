package TADashboard;
import java.util.concurrent.TimeUnit;
import com.google.common.base.Stopwatch;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    public WebElement MyFindElement(By by, long timeout)
    {
        WebElement Ele = null;
        Stopwatch stopWatch = Stopwatch.createStarted();
        while (timeout >= 0)
        {
            try
            {
                WebDriverWait wait = new WebDriverWait(_driverGeneralPage, Constant.TimeOut);
                wait.until(ExpectedConditions.visibilityOfElementLocated(by));
                wait.until(ExpectedConditions.elementToBeClickable(by));
                Ele = _driverGeneralPage.findElement(by);
                break;
            }
            catch(StaleElementReferenceException e)
            {
                timeout = timeout - stopWatch.elapsed(TimeUnit.SECONDS);
                MyFindElement(by, timeout);
                stopWatch.stop();
            }
            catch(NullPointerException e)
            {
            	timeout = timeout - stopWatch.elapsed(TimeUnit.SECONDS);
                MyFindElement(by, timeout);
                stopWatch.stop();
            }
            catch(WebDriverException e)
            {
            	timeout = timeout - stopWatch.elapsed(TimeUnit.SECONDS);
                MyFindElement(by, timeout);
                stopWatch.stop();
            }
            catch(IllegalArgumentException e)
            {
            	timeout = timeout - stopWatch.elapsed(TimeUnit.SECONDS);
                MyFindElement(by, timeout);
                stopWatch.stop();
            }
        }
        try {
			stopWatch.stop();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}
	      return Ele;
    }

	public boolean DoesElementExist(By locatorKey) {
		WebDriverWait wait = new WebDriverWait(_driverGeneralPage, Constant.TimeOut);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locatorKey));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
