package browserManager;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import browserOptions.ChromeBrowserOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static browserOptions.ChromeBrowserOptions.chrome_remote_options;

//***************************************************************************************************************************************************
//  The Chrome driver child class overriding the abstract method of Create driver with its own definition.
//
//****************************************************************************************************************************************************

public class ChromeDriverManager extends DriverManager{

	@Override
	public WebDriver createDriver() {
		if (System.getProperty("BROWSER") != null && System.getProperty("BROWSER").equalsIgnoreCase("chrome"))
		{
			String host = "localhost";
			if(System.getProperty("HUB_HOST") != null){
				host = System.getProperty("HUB_HOST");
			}
			try {
				// get the host detaills from system variable for remote execution on selenium grid
				String hub_url = "http://" + host + ":4444/wd/hub";
				tl_driver.set(new RemoteWebDriver(new URL(hub_url),chrome_remote_options.get()));
			} catch (MalformedURLException e) {
				throw new RuntimeException(e);
			}
		} else {
			tl_driver.set(new ChromeDriver(ChromeBrowserOptions.chrome_options.get()));
		}
		return getDriver();
	}
}
