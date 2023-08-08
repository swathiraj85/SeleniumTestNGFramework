package com.ryanair.pages.home.searchwidgettabs.flightswidgettab;

import com.ryanair.pages.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.SeleniumUtilities;

import java.security.Key;
import java.util.List;

public class FlightsSearchPage extends BasePage
{
    private CalendarComponent calendarComponent;

    private PassengersComponent passengersComponent;
    @FindBy(id="input-button__departure")
    private WebElement fromText;
    @FindBy(id="input-button__destination")
    private WebElement toText;

    @FindBy(css="button[aria-label='Return trip']")
    private WebElement returnTripOption;

    @FindBy(css="button[aria-label='One way']")
    private WebElement oneWayOption;

    @FindBy(css="button[aria-label='Search']")
    private WebElement searchButton;

    @FindBy(xpath="//*[contains(@class,'list__airports-container')]//descendant::*[@data-ref='airport-item__name']")
    private List<WebElement> pickAirportList;

    @FindBy(css="*[uniqueid*='dates-from']")
    private WebElement departureDate;
    @FindBy(css="*[uniqueid*='dates-to']")
    private WebElement returnDate;

    @FindBy(css="*[data-ref='input-button__passengers']")
    private WebElement passengersButton;

    public FlightsSearchPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public CalendarComponent getCalendarComponent() {
        return new CalendarComponent(driver);
    }
    public PassengersComponent getPassengersComponent() {
        return new PassengersComponent(driver);
    }
    public void enterFromAirport(String from_airport)
    {
        fromText.sendKeys(Keys.CONTROL,"a");
        fromText.sendKeys(Keys.DELETE);
        SeleniumUtilities.sendKeys(driver,fromText,from_airport);
        SeleniumUtilities.waitForElementsDisplayed(driver,pickAirportList);
    }
    public void enterToAirport(String to_airport)
    {
        SeleniumUtilities.sendKeys(driver,toText,to_airport);
        SeleniumUtilities.waitForElementsDisplayed(driver,pickAirportList);
    }
    public boolean pickAirportFromList(String airport_name)
    {
        boolean b_airport_name_found_flag=false;
        for (WebElement element_item : pickAirportList)
        {
            if(SeleniumUtilities.getElementText(driver,element_item).equalsIgnoreCase(airport_name))
            {
                b_airport_name_found_flag = true;
                System.out.println("The airport name" +  airport_name + " matches an item from the suggested list");
                SeleniumUtilities.click(driver,element_item);
                break;
            }
        }
        return b_airport_name_found_flag;
    }
    private void selectReturnOption()
    {
        SeleniumUtilities.click(driver,returnTripOption);
    }
    private void selectOneWayOption()
    {
        SeleniumUtilities.click(driver,oneWayOption);
    }
    public void selectDepartureDate() {
        SeleniumUtilities.waitForElementDisplayed(driver,departureDate);
        SeleniumUtilities.jsClick(driver,departureDate);
    }
    public void selectReturnDate()
    {
        SeleniumUtilities.waitForElementDisplayed(driver,returnDate);
        SeleniumUtilities.jsClick(driver,returnDate);
    }
    public void clickSearch()
    {
        SeleniumUtilities.click(driver,searchButton);
    }

    public void selectTripType(String tripType)
    {
        switch(tripType.toUpperCase())
        {
            case "RETURN":
                selectReturnOption();
                break;
            case "ONEWAY":
                selectOneWayOption();
                break;
            default:
                System.out.println("Invalid fare type : " + tripType);
                throw new IllegalArgumentException("Invalid fare type : " + tripType + ".It can be either return or oneway");
        }
    }
}
