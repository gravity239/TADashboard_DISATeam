package Common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilities {
	public static String uniquePageName(String pageName) {
		return pageName.concat(new SimpleDateFormat("HH.mm.ss").format(new Date()));
	}
}
