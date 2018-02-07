package Common;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utilities {
	public static String getUniqueString() {
		return new SimpleDateFormat("HH.mm.ss").format(new Date());
	}

	// Move mouse to a element
	public static void mouseTo(WebElement element, WebDriver webDriver) {
		Actions builder = new Actions(webDriver);
		Action mouseOverElement = builder.moveToElement(element).build();
		mouseOverElement.perform();
	}

	// Check or uncheck a checkbox
	public static void check(WebElement element) {
		boolean isChecked = element.isSelected();
		if (isChecked == false) {
			element.click();
		}
	}

	// Check or uncheck a checkbox
	public static void unCheck(WebElement element) {
		boolean isChecked = element.isSelected();
		if (isChecked == true) {
			element.click();
		}
	}

}
