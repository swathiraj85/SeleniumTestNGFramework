package browserManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

import browserOptions.EdgeBrowserOptions;

//***************************************************************************************************************************************************
//  The Edge driver child class overriding the abstract method of Create driver with its own definition.
//
//****************************************************************************************************************************************************
public class EdgeDriverManager extends DriverManager
{
	@Override
	public WebDriver createDriver() {
		tl_driver.set(new EdgeDriver(EdgeBrowserOptions.edgeOptions.get()));
		return getDriver();
		
	}

}
