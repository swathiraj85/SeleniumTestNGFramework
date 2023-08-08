package browserManager;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

//***************************************************************************************************************************************************
// The DriverFactory class is responsible for supplying the test class with an active instance of webdriver class requested.The test class is restricted 
// to create a driver instance on its own. 
//
//****************************************************************************************************************************************************
public class DriverFactory 
{
//
// declared as static final to access as Class member with out overriding the members
//
private static final Map<String,Supplier<DriverManager>> driverMap= new HashMap<String,Supplier<DriverManager>>();

private static final Supplier<DriverManager> edgeSupplier=()->
{
	return new EdgeDriverManager();
};
private static final Supplier<DriverManager> chromeSupplier=()->
{
	return new ChromeDriverManager();
};
static
{
	driverMap.put("chrome", chromeSupplier);
	driverMap.put("edge", edgeSupplier);
}
public static final DriverManager getManager(String browser)
{
	DriverManager driver_manager=null;
	if(browser.equalsIgnoreCase("chrome")||browser.equalsIgnoreCase("edge"))
	{
		driver_manager = driverMap.get(browser.toLowerCase()).get();
	}
	else
	{
	//	Assert.assertTrue(false, "The browser name is invalid.Please enter either chrome/edge");
	}
	return driver_manager;
}
}
