import browserManager.DriverFactory;
import browserManager.DriverManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utilities.ConfigSupplier;
import utilities.SeleniumUtilities;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class BaseTest
{
    protected DriverManager driver_manager;
    protected WebDriver driver;
    protected TestData testData;
    protected TestData.FlightDetails flightDetails;
    protected TestData.PassengerCount passengerCount;
    protected List<TestData.Passenger> passengerList;

    protected TestData.Passenger passenger;
    protected ExtentReports extent;
    protected ExtentTest test;


    @BeforeSuite
   public void setReport()
   {
       extent = new ExtentReports();
       ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/index.html");
       extent.attachReporter(spark);
   }
    @BeforeMethod
    public void setupDriver(ITestResult result)
    {
        // prepare the test details
        createTestDtls(result);
        driver_manager = DriverFactory.getManager(ConfigSupplier.getInstance().getCurrentBrowser());
        // initialize input json
        initializeJsonTestDataObject();
        driver_manager.createDriver();
        driver = driver_manager.getDriver();
        SeleniumUtilities.openBrowser(driver, ConfigSupplier.getInstance().getApplicationURL("app_url"));

    }
    @AfterMethod
    public void logStatus(ITestResult result)
    {
        // attach screen shot and log failure for failed scripts.
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
            String filePath;
            driver_manager = DriverFactory.getManager(ConfigSupplier.getInstance().getCurrentBrowser());
            filePath =getScreenShotPath(result.getMethod().getMethodName(),driver_manager.getDriver());
            test.addScreenCaptureFromPath(filePath,result.getMethod().getMethodName());
        }
        extent.flush();
        driver_manager.getDriver().close();
    }
    protected String getScreenShotPath(String testName,WebDriver driver)
    {
        TakesScreenshot takesScreenshot = (TakesScreenshot) driver_manager.getDriver();
        File file_src = takesScreenshot.getScreenshotAs(OutputType.FILE);
        String file_dest = System.getProperty("user.dir") + "/reports/"  + testName + ".png";
        try {
            FileHandler.copy(file_src, new File(file_dest));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file_dest;
    }
    private void createTestDtls(ITestResult result)
    {
        test = extent.createTest(result.getMethod().getMethodName());
        test.log(Status.INFO,test  + result.getMethod().getMethodName() + " Test about to start");
        test = extent.createTest(result.getMethod().getMethodName() , "Description");
    }
    private void initializeJsonTestDataObject()
    {
        try {
            testData = TestData.get(System.getProperty("user.dir") + "/src/test/resources/testData/ryanair.json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        flightDetails = testData.getFlightDetails();
        passengerCount = testData.getPassengerCount();
        passengerList = testData.getPassengerList();
    }
}
