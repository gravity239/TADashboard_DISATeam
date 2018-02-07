package DataObject;
import org.testng.annotations.DataProvider;

public class DataObject {
	
	@DataProvider(name="LoginCredentials")
    public Object[][] getDataFromDataprovider(){
    return new Object[][] 
    	{
            { "administrator", "" },
            { "test1", "test@#" },
            { "test@!", "test" }
        };

    }

}
