package utilities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumUtilities {

	//
	// ***************************************************************************************************************************************************
	// Function name :  sendKeys
	// Description : The function to enter the values on web element
	// Input Parameters : WebDriver,WebElement,String
	// Output Parameters : None	
	// ***************************************************************************************************************************************************
	//
	public static void sendKeys(WebDriver driver,WebElement element,String text)
	{
		try
		{
			//
			// wait for the element before entering the value
			//
			if (waitForElementClickable(driver,element));
			{
				//
				// enter the text value
				//
				element.clear();
				element.sendKeys(text);
			}
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println("Retrying the action due to stale element exception");
			//
			// retry the operation
			//
			sendKeys(driver,element,text);
		}
		catch(Exception e)
		{
			System.out.println("An exception has occured while working on the Web element");
			throw e;
		}
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  selectByText
	// Description : The function to select the value from the drop down
	// Input Parameters : WebDriver,List<WebElement>,String
	// Output Parameters : None
	// ***************************************************************************************************************************************************
	//
	public static void selectByText(WebDriver driver,WebElement element,String text)
	{
		Select dropDownMenu = new Select(element);
		try
		{
			// wait for the element before entering the value
			if (waitForElementClickable(driver,element));
			{
				dropDownMenu.selectByVisibleText(text);
			}
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println("Retrying the action due to stale element exception");
			//
			// retry the operation
			//
			//sendKeys(driver,element,text);
		}
		catch(Exception e)
		{
			System.out.println("An exception has occured while working on the Web element");
			throw e;
		}
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  click
	// Description : The function to click on an web element
	// Input Parameters : WebDriver,WebElement
	// Output Parameters : None	
	// ***************************************************************************************************************************************************
	//
	public static void click(WebDriver driver,WebElement element) throws WebDriverException
	{
		try
		{
			//
			// wait for the element before entering the value
			//
			if (waitForElementClickable(driver,element));
			{
				//
				// click on the web element
				//
				element.click();
			}
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println("Retrying the action due to stale element exception");

			//
			// retry the operation
			//
			click(driver,element);
		}
		catch(ElementClickInterceptedException e)
		{
			System.out.println("The web driver couldn't click using regular click.Retrying the action using JS click");

			//
			// retry the operation
			//
			jsClick(driver,element);
		}
		catch(Exception e)
		{
			System.out.println("An exception has occured while working on the Web element");
			throw e;
		}
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  jsClick
	// Description : The function to click on an web element using JavaScript Executor
	// Input Parameters : WebDriver,WebElement
	// Output Parameters : None	
	// ***************************************************************************************************************************************************
	//
	public static void jsClick(WebDriver driver,WebElement element)
	{
		try
		{
			JavascriptExecutor javascriptExecutor = (JavascriptExecutor)driver;
			javascriptExecutor.executeScript("arguments[0].click()", element);
		}
		catch(StaleElementReferenceException e)
		{
			System.out.println("Retrying the action due to stale element exception");

			//
			// retry the operation
			//
			jsClick(driver,element);
		}
		catch(ElementClickInterceptedException e)
		{
			System.out.println("The web driver couldn't click using regular click.Retrying the action using JS click");

			//
			// retry the operation
			//
			click(driver,element);
		}
		catch(Exception e)
		{
			System.out.println("An exception has occured while working on the Web element");
			throw e;
		}
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  waitForElementDisplayed
	// Description : The function to wait for a web element to be displayed
	// Input Parameters : WebDriver,WebElement
	// Output Parameters : boolean	
	// ***************************************************************************************************************************************************
	//
	public static boolean waitForElementDisplayed(WebDriver driver,WebElement element)
	{
		boolean b_found =  false;

		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigSupplier.getInstance().getTimeOut())));
		try
		{
		//
		// wait for the visibility of the element
		//
		wait.until(ExpectedConditions.visibilityOf(element));
		b_found =  true;
		}
		catch(TimeoutException timeoutException)
		{
			System.out.println("The Element didn't load within the specified timeout value" + timeoutException.toString());
			timeoutException.printStackTrace();
		}

		return b_found;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  waitForElementsDisplayed
	// Description : The function to wait for a list of web elements to be displayed
	// Input Parameters : WebDriver,List of WebElements
	// Output Parameters : boolean	
	// ***************************************************************************************************************************************************
	//
	public static boolean waitForElementsDisplayed(WebDriver driver,List<WebElement> elements)
	{
		boolean b_found =  false;

		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigSupplier.getInstance().getTimeOut())));
		try
		{
		//
		// wait for the visibility of all the elements
		//
		wait.until(ExpectedConditions.visibilityOfAllElements(elements));
		b_found =  true;
		}
		catch(TimeoutException timeoutException)
		{
			System.out.println("The Element didn't load within the specified timeout value" + timeoutException.toString());
			timeoutException.printStackTrace();
		}
		return b_found;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  waitForElementClickable
	// Description : The function to wait for a web element to be clickable
	// Input Parameters : WebDriver,WebElement
	// Output Parameters : boolean	
	// ***************************************************************************************************************************************************
	//
	public static boolean waitForElementClickable(WebDriver driver,WebElement element)
	{
		boolean b_found =  false;

		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigSupplier.getInstance().getTimeOut())));
		try
		{
		//
		// wait for the element to perform click operation
		//
		wait.until(ExpectedConditions.elementToBeClickable(element));

		b_found =  true;
		}
		catch(TimeoutException timeoutException)
		{
			System.out.println("The Element clickable property cannot be verified within the specified timeout value" + timeoutException.toString());
			timeoutException.printStackTrace();
		}
		return b_found;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  getAttributeValue
	// Description : The function to retrieve the attribute value of web element
	// Input Parameters : WebDriver,WebElement
	// Output Parameters : String
	// ***************************************************************************************************************************************************
	//
	public static String getAttributeValue(WebDriver driver,WebElement element,String attribute_name)
	{
		String attributeVal = "";

		try
		{
			//
			// wait for the element before entering the value
			//
			if (waitForElementDisplayed(driver,element));
			{
				attributeVal = element.getAttribute(attribute_name);
			}
		}
		catch(TimeoutException timeoutException)
		{
			System.out.println("The Element didn't load within the specified timeout value" + timeoutException.toString());
			timeoutException.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("An exception has occured while working on the Web element");
			throw e;
		}
		return attributeVal;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  getElementText
	// Description : The function to retrieve the contents of web element
	// Input Parameters : WebDriver,WebElement
	// Output Parameters : String	
	// ***************************************************************************************************************************************************
	//
	public static String getElementText(WebDriver driver,WebElement element)
	{
		String elementText = "";

		try
		{
			//
			// wait for the element before entering the value
			//
			if (waitForElementDisplayed(driver,element));
			{
				elementText = element.getText().trim();
			}
		}
		catch(TimeoutException timeoutException)
		{
			System.out.println("The Element didn't load within the specified timeout value" + timeoutException.toString());
			timeoutException.printStackTrace();
		}
		catch(Exception e)
		{
			System.out.println("An exception has occured while working on the Web element");
			throw e;
		}
		return elementText;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  validateBrowserTitle
	// Description : The function to validate if the browser contains the specified title text
	// Input Parameters : WebDriver,String
	// Output Parameters : boolean	
	// ***************************************************************************************************************************************************
	//
	public static boolean validateBrowserTitle(WebDriver driver,String title)
	{
		boolean b_found	= false;
		WebDriverWait wait =  new WebDriverWait(driver, Duration.ofSeconds(Long.parseLong(ConfigSupplier.getInstance().getTimeOut())));
		try
		{
		//
		// wait for the browser title to contain the substring
		//
			b_found = wait.until(ExpectedConditions.titleContains(title));
		}
		catch(TimeoutException timeoutException)
		{
			System.out.println("The Element didn't load within the specified timeout value" + timeoutException.toString());
			timeoutException.printStackTrace();
		}
		return b_found;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  openBrowser
	// Description : The function to open the browser using the given url
	// Input Parameters : WebDriver,String
	// Output Parameters : WebDriver	
	// ***************************************************************************************************************************************************
	//
	public static WebDriver openBrowser(WebDriver driver,String url)
	{
		try
		{
			driver.get(url);
			System.out.println("The title of the url is " + driver.getTitle());
		}
		catch(WebDriverException e)
		{
			System.out.println("An exception has occured while opening the URL");
			throw e;
		}
		return driver;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  swictchToIframe
	// Description : The function to switch to an IFrame
	// Input Parameters : WebDriver,String
	// Output Parameters : WebDriver	
	// ***************************************************************************************************************************************************
	//
	public static WebDriver swictchToIframe(WebDriver driver,String nameOrID)
	{
		try
		{
			driver.switchTo().frame(nameOrID);
		}
		catch(NoSuchFrameException e)
		{
			System.out.println("A frame with the specified name or Id " + nameOrID+ " couldn't be located");
			throw e;
		}
		catch(Exception e)
		{
			System.out.println("An exception has occured while working on the Web element");
			throw e;
		}
		return driver;
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  convertToMonthYear
	// Description : The function to convert the date string into Month Year format Ex:August 2023
	// Input Parameters : date string
	// Output Parameters : Return the parsed date in "Month Year" format
	// ***************************************************************************************************************************************************
	//
	public static String convertToMonthYear(String date)
	{
		try
		{
			DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
			LocalDate localDate = LocalDate.parse(date,dateTimeInputFormatter);
			DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
			return localDate.format(dateTimeOutputFormatter);
		}
		catch(DateTimeParseException e)
		{
			System.out.println("Unable to parse the date - " + date + ". Input the string in format dd MMM yyyy.");
			throw e;
		}
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  isDateLessThanCurrent
	// Description : The function verifies if the input date is less than current date and return boolean.
	// Input Parameters : date string
	// Output Parameters : Returns boolean
	// ***************************************************************************************************************************************************
	//
	public static boolean isDateNotLessThanCurrent(String date)
	{
		try
		{
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
			LocalDate parsedDate = LocalDate.parse(date,dateTimeFormatter);
			LocalDate currentDate = LocalDate.now();
			return !parsedDate.isBefore(currentDate);
		}
		catch(DateTimeParseException e)
		{
			System.out.println("Unable to parse the date - " + date + ". Input the string in format dd MMM yyyy.");
			return false;
		}
	}
	//
	// ***************************************************************************************************************************************************
	// Function name :  containsDayMonth
	// Description : The function validates if the give input date contains the target Day Month(MMM YYYY)
	// Input Parameters : date string
	// Output Parameters : Returns boolean
	// ***************************************************************************************************************************************************
	//
	public static boolean containsDayMonth(String inputDate,String targetDayMonth)
	{
		String dateArr[] = inputDate.split(" ");

		if (dateArr.length >= 2) {
			String day = dateArr[0].replaceAll("^0+", "");
			String dayMonth = day + " " + dateArr[1];
			return targetDayMonth.contains(dayMonth);
		}
		return false;
	}
	}
