package browserManager;

import org.openqa.selenium.WebDriver;

//***************************************************************************************************************************************************
// The class is designed as an abstract driver class with abstract method for Create driver  which can be overridden by the child class.
//
//****************************************************************************************************************************************************
public abstract class DriverManager 
{
//
// The WebDriver is created as an instance of type ThreadLocal to support testing the concurrent Threads
//
protected static ThreadLocal<WebDriver> tl_driver = new ThreadLocal<WebDriver>();
abstract public WebDriver createDriver();
public synchronized WebDriver getDriver() {
	
	return tl_driver.get();
}
}
