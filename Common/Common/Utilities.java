package Common;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class Utilities {
	public static String GetUniqueString()
	{
		String timeStamp = new SimpleDateFormat("HH.mm.ss").format(new Date());
		return timeStamp;
	}
	
	 // Move mouse to a element
    public static void MouseTo(WebElement element, WebDriver webDriver)
    {
    	Actions builder = new Actions(webDriver);
	        Action mouseOverElement = builder
	                 .moveToElement(element)
	                 .build();
	        mouseOverElement.perform();
    }

    // Selects an item.
    public static void SelectItem(WebElement element, String item, String selectby)
    {
    	Select selector = new Select(element);
        if (selectby == "Value")
            selector.selectByValue(item);
        else if (selectby == "Index")
            selector.selectByIndex(Integer.parseInt(item) - 1);
        else if (selectby == "Text")
            selector.selectByVisibleText(item);
    }
    
    // Check or uncheck a checkbox
    public static void Check(WebElement element)
    {
        boolean isChecked = element.isSelected();
        if (isChecked == false)
        {
            element.click();
        }
    }

    // Check or uncheck a checkbox
    public static void UnCheck(WebElement element)
    {
        boolean isChecked = element.isSelected();
        if (isChecked == true)
        {
            element.click();
        }
    }


}
