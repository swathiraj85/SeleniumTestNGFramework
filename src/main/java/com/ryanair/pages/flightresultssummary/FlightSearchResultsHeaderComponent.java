package com.ryanair.pages.flightresultssummary;

import com.ryanair.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.SeleniumUtilities;

public class FlightSearchResultsHeaderComponent extends BasePage
{
    @FindBy(xpath="//*[contains(@class,'flight-icon')]//preceding-sibling::*[contains(@class,'title')]")
    private WebElement fromAirport;
    @FindBy(xpath="//*[contains(@class,'flight-icon')]//following-sibling::*[contains(@class,'title')]")
    private WebElement toAirport;

    @FindBy(xpath="//button[contains(text(),'Edit search')]")
    private WebElement editSearch;

    @FindBy(css="[class*='details__bottom-bar']")
    private WebElement tripDetails;

    @FindBy(css="[class*='details__bottom-bar'] > span:nth-child(2)")
    private WebElement returnDate;

    public FlightSearchResultsHeaderComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public String getDepartureCity()
    {
        return SeleniumUtilities.getElementText(driver,fromAirport);
    }
    public String getArrivalCity()
    {
        return SeleniumUtilities.getElementText(driver,toAirport);
    }
    public String getTripDetails()
    {
        return SeleniumUtilities.getElementText(driver,tripDetails);
    }
    private String[] parseTripDetails()
    {
        // split the trip details by newline character
        String arr_tripDtls[] = getTripDetails().split("\\n");
        return arr_tripDtls;
    }
    public String getTripType()
    {
        String tripType;
        String arrTripDtls[] = parseTripDetails();

        if(arrTripDtls.length>0)
        {
            tripType =  arrTripDtls[0].trim();
        }
        else
        {
            throw new RuntimeException("Array length not as expected");
        }
        return tripType;
    }
    public String getOnwardDate()
    {
        String onwardDate;
        String arrTripDtls[] = parseTripDetails();

        try {
            onwardDate = arrTripDtls[1].trim();
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Array length not as expected.Expected length to be >1.Actual " + arrTripDtls.length);
            throw e;
        }
        return onwardDate;
    }
    public String getReturnDate()
    {
        String returnDate = null;
        String arrTripDtls[] = parseTripDetails();

        String tripType = getTripType();
        if(tripType.equalsIgnoreCase("return"))
        {
            try {
                returnDate = arrTripDtls[2].trim();
            }
            catch(ArrayIndexOutOfBoundsException e)
            {
                System.out.println("Array length not as expected.Expected length to be >2.Actual " + arrTripDtls.length);
                throw e;
            }
        }
        return returnDate.replaceAll("^-+","").trim();
    }
    public String getPassengersCount(String tripType)
    {
        String passengersCount;
        String arrTripDtls[] = parseTripDetails();

        try {
            if(tripType.equalsIgnoreCase("oneway"))
            {
                passengersCount = arrTripDtls[2].trim();
                return passengersCount;
            }
            passengersCount = arrTripDtls[3].trim();
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            System.out.println("Array length not as expected.Expected length to be >3.Actual " + arrTripDtls.length);
            throw e;
        }
        return passengersCount;
    }

}
