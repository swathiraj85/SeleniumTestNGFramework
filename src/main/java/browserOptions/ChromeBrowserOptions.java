package browserOptions;

import java.util.function.Supplier;

import org.openqa.selenium.chrome.ChromeOptions;


//***************************************************************************************************************************************************
//       The Browser Options for Chromer Webdriver is specified in this class
//****************************************************************************************************************************************************

public class ChromeBrowserOptions {

	public static final Supplier<ChromeOptions> chrome_options = ()->
	{
		ChromeOptions  chromeOptions	= new ChromeOptions();
		chromeOptions.addArguments("start-maximized");
		return chromeOptions;
		
	};
	public static final Supplier<ChromeOptions> chrome_remote_options = ()->
	{
		ChromeOptions  chromeOptions	= new ChromeOptions();
		chromeOptions.setCapability("platformName","Linux");
		chromeOptions.addArguments("--ignore-ssl-errors=yes");
		chromeOptions.addArguments("--ignore-certificate-errors");
		return chromeOptions;

	};
}
