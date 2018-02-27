package TADashboard;

import java.util.ArrayList;
import java.util.List;
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
	private WebDriverWait _driverWaitGeneralPage;

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

	public void selectComboboxValue(Select sElement, String value) {
		if ((value != null) && (value != "")) {
			sElement.selectByVisibleText(value.toString());
		}
	}

	public void waitForAlertPresent(WebDriverWait wait, WebDriver driver, long timeOut) {
		wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void enterValue(WebElement element, String value) {
		if (value != null) {
			element.clear();
			element.sendKeys(value);
		}

	}

	public void waitForElementToBeClickable(WebDriverWait wait, WebDriver driver, WebElement ele, long timeOut) {
		wait = new WebDriverWait(driver, Constant.TimeOut);
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitForElementToBeVisible(WebDriverWait wait, WebDriver driver, By locator, long timeOut) {
		wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public void acceptAlertIfAvailable(long timeout) {
		long waitForAlert = System.currentTimeMillis() + timeout;
		boolean found = false;
		do {
			try {
				Alert alert = this._driverGeneralPage.switchTo().alert();
				if (alert != null) {
					alert.accept();
					found = true;
				}
			} catch (UnhandledAlertException ex) {
			}
		} while ((System.currentTimeMillis() < waitForAlert) && (!found));
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

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// Determine if a item that in a item list exists
	public boolean isItemExisted(Select element, String item) {
		List<WebElement> elements = element.getOptions();
		boolean found = false;
		for (WebElement ele : elements) {
			if (item.equals(ele.getText())) {
				found = true;
				break;
			}
		}
		return found;
	}

	public String getTableCellValue(String tableXpath, int row_number, int column_number) {
		String cellValue = "";
		String xpathString = tableXpath + "/tr[%d]/td[%d]";
		WebElement colElement = myFindElement(By.xpath(String.format(xpathString, row_number, column_number)),
				Constant.ShortTime);
		if (colElement != null) {
			cellValue = colElement.getText();
		}

		return cellValue;
	}
	
	public int getTableRowCount(String tableXpath) {
		String xpathString = tableXpath + "/tr";
		List<WebElement> listOfRows = _driverGeneralPage.findElements(By.xpath(xpathString));
		return listOfRows.size();
	}
	
	public int getTableColumnCountByRow(String tableXpath,int row_number) {
		String xpathString = tableXpath + "/tr[%d]/td";
		List<WebElement> listOfColumns = _driverGeneralPage.findElements(By.xpath(String.format(xpathString,row_number)));
		return listOfColumns.size();
	}

	public String getAlertMessage() {
		this.waitForAlertPresent(_driverWaitGeneralPage, _driverGeneralPage, Constant.TimeOut);
		Alert alert = _driverGeneralPage.switchTo().alert();
		return alert.getText();
	}

	public int getItemIndexInCombobox(Select element, String value) {
		List<WebElement> elements = element.getOptions();
		int found = -1;
		for (WebElement ele : elements) {
			if (value.equals(ele.getText())) {
				found = elements.indexOf(ele);
				break;
			}
		}
		return found;
	}

	public boolean areAllCheckBoxesUnChecked() {
		List<WebElement> checkBoxs = _driverGeneralPage.findElements(By.xpath("//input[@class = 'box']"));

		for (WebElement ele : checkBoxs) {
			if (ele.isSelected()) {
				return false;
			}
		}
		return true;
	}

	public boolean areAllCheckBoxesChecked() {
		List<WebElement> checkBoxs = _driverGeneralPage.findElements(By.xpath("//input[@class = 'box']"));

		for (WebElement ele : checkBoxs) {
			if (ele.isSelected() == false) {
				return false;
			}
		}
		return true;
	}
	
	  public boolean isItemPresentInCombobox(String comboboxName, String comboboxItem,String attribute)
      {
          boolean isItemPresent = false;
          for (WebElement item : _driverGeneralPage.findElements(By.xpath(String.format("//td[contains(text(), %s)]/following-sibling::*/descendant::select" + "/descendant::*",comboboxName))))
          {
              if (attribute == "value")
              {
                  if (item.getAttribute("value") == comboboxItem)
                  {
                      isItemPresent = true;
                  }
              }
              else if(attribute == "text")
              {
                  if (item.getText().trim() == comboboxItem)
                  {
                      isItemPresent = true;
                  }
              }
          }
          return isItemPresent;
      }
	  
	  public int getNumberOfItemsInCombobox(String comboboxName)
      {
          WebElement combo = myFindElement(By.xpath(String.format("//td[contains(text(), %s)]/following-sibling::*/descendant::select", comboboxName)),Constant.TimeOut);
          Select se = new Select(combo);
          List<WebElement> listbox = se.getOptions();
          return listbox.size();
      }
	  
	  public String getSelectedItemOfCombobox(String comboboxName)
      {
          WebElement combo = myFindElement(By.xpath(String.format("//td[contains(text(), %s)]/following-sibling::*/descendant::select", comboboxName)),Constant.TimeOut);
          Select se = new Select(combo);
          String selectedItem = se.getFirstSelectedOption().getText().trim();
          return selectedItem;
      }
	  
	  public int getItemPositionInCombobox(String comboboxName, String comboboxItem)
      {
           int itemPosition = -1;
           if (isItemPresentInCombobox(comboboxName, comboboxItem, "value") == false && isItemPresentInCombobox(comboboxName, comboboxItem, "text") == false)
               return -1;
           else
           {
               for (WebElement item : _driverGeneralPage.findElements(By.xpath(String.format("//td[contains(text(), %s)]/following-sibling::*/descendant::select" + "/descendant::*", comboboxName))))
               {
                   if (item.getAttribute("value") == comboboxItem || item.getText().trim() == comboboxItem)
                       itemPosition = Integer.parseInt(item.getAttribute("index"));
               }
           }
           return itemPosition;
      } 
	  
	  
	  public boolean isItemSortedI(Select selector)
	  {
		  ArrayList<String> listValues = new ArrayList<String>();
		  List<WebElement> items = selector.getOptions();
		    for (WebElement item : items)
		    {
		    	listValues.add(item.getText());
		    }
		  String last = listValues.get(0);
		    for (int i = 1; i < listValues.size(); i++)
		    {
		        String current = listValues.get(i);
		        if (last.compareToIgnoreCase(current) > 0)
		        {
		            return false;
		        }

		        last = current;
		    }

		    return true;
	  }

  }
