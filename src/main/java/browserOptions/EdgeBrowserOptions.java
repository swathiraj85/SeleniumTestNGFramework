package browserOptions;

import java.util.function.Supplier;

import org.openqa.selenium.edge.EdgeOptions;


//***************************************************************************************************************************************************
//     The Browser Options for Edge Webdriver is specified in this class
//****************************************************************************************************************************************************

public class EdgeBrowserOptions {

	public static final Supplier<EdgeOptions> edgeOptions =()->
	{
		EdgeOptions edgeOptions =  new EdgeOptions();
		edgeOptions.addArguments("--headless");
		edgeOptions.addArguments("start-maximized");
		return edgeOptions;
	};
	
	
	
}
