package com.ryanair.pages.flightresultssummary;

import com.ryanair.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.SeleniumUtilities;

import java.util.List;

public class FlightSearchResultsSummaryPage extends BasePage {

    private FlightFaresComponent flightFaresComponent;

    private PassengerDetailComponent passengerDetailComponent;

    private FlightSearchResultsHeaderComponent flightSearchResultsHeaderComponent;

    @FindBy(css="[data-ref*='outbound'] flight-card-new")
    private List<WebElement> outboundJourneyTable;

    @FindBy(css="[data-ref*='inbound'] flight-card-new")
    private List<WebElement> inboundJourneyTable;

    @FindBy(css="[data-ref*='outbound'] flight-card-new flights-price-simple")
    private List<WebElement> outboundFlightByPrice;

    @FindBy(css="[data-ref*='outbound'] flight-card-new button")
    private List<WebElement> outboundFlightSelect;

    @FindBy(css="[data-ref*='outbound'] [data-ref='flight-segment.departure']")
    private List<WebElement> outboundFlightByDepartureTime;

    @FindBy(css="[data-ref*='outbound'] [data-ref='flight-segment.arrival']")
    private List<WebElement> outboundFlightByArrivalTime;

    @FindBy(css="[data-ref*='inbound'] flight-card-new flights-price-simple")
    private List<WebElement> inboundFlightByPrice;
    @FindBy(css="[data-ref*='inbound'] flight-card-new button")
    private List<WebElement> inboundFlightSelect;

    @FindBy(css="[data-ref*='inbound'] [data-ref='flight-segment.departure']")
    private List<WebElement> inboundFlightByDepartureTime;

    @FindBy(css="[data-ref*='inbound'] [data-ref='flight-segment.arrival']")
    private List<WebElement> inboundFlightByArrivalTime;
    @FindBy(css="flights-summary-container [class*='done-tick__icon']")
    private WebElement flightsSummaryGreenTick;

    public FlightSearchResultsSummaryPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public FlightFaresComponent getFlightFaresComponent() {
        return new FlightFaresComponent(driver);
    }

    public PassengerDetailComponent getPassengerDetailComponent() {
        return new PassengerDetailComponent(driver);
    }

    public FlightSearchResultsHeaderComponent getFlightSearchResultsHeaderComponent() {
        return new FlightSearchResultsHeaderComponent(driver);
    }
    public void selectFirstOnwardFlight()
    {
        selectFirstAvailableFlight(outboundFlightSelect);
    }
    public void selectFirstReturnFlight()
    {
        selectFirstAvailableFlight(inboundFlightSelect);
    }
    private void selectFirstAvailableFlight(List<WebElement> elementList)
    {
        SeleniumUtilities.waitForElementsDisplayed(driver,elementList);
        if(elementList.size() == 0)
        {
            throw new IllegalArgumentException("The flight list is empty.Need atleast 1 available trip for selection.");
        }
       for(int i=0;i<elementList.size();i++)
       {
           SeleniumUtilities.click(driver,elementList.get(i));
           break;
       }
    }
    public boolean isGreenTickDisplayedForSelectedFlights()
    {
        return SeleniumUtilities.waitForElementDisplayed(driver,flightsSummaryGreenTick)?true:false;
    }
}
