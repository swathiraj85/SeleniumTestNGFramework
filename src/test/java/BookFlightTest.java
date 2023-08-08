import com.aventstack.extentreports.Status;
import com.ryanair.pages.bags.BagsPage;
import com.ryanair.pages.flightresultssummary.FlightSearchResultsSummaryPage;
import com.ryanair.pages.home.HomePage;
import com.ryanair.pages.seatpreference.PassengersSeatPreferencePage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import utilities.SeleniumUtilities;

public class BookFlightTest extends BaseTest
{
    private HomePage homePage;
    private FlightSearchResultsSummaryPage flightSearchResultsSummaryPage;
    private PassengersSeatPreferencePage passengersSeatPreferencePage;
    private BagsPage bagsPage;
    boolean isAirportDisplayed;
    int passenger_count_expected,passenger_counter=0;
    @Test
    public void testFlightBooking()
    {
        Assert.assertTrue(SeleniumUtilities.validateBrowserTitle(driver,"Ryanair"));
        initializePages();
        validateHomePageTitle();
        homePage.getFlightsSearchPage().selectTripType(flightDetails.getTripType());
        originFlightSelection();
        destinationFlightSelection();
        onwardFlightDate();
        returnFlightDate();
        selectPassengerDetails();
        searchForFlights();
        assertDateAndPassengersCount();
        selectOnwardFlight();
        selectReturnFlight();
        selectFareType();
        selectLoginLater();
        enterPassengerDetails();
        selectAvailableSeatsAndContinue();
        isBagsPageDisplayed();
        test.log(Status.PASS,"The Test case Passed sucessfully.");
    }
    private void initializePages()
    {
        homePage = new HomePage(driver);
        flightSearchResultsSummaryPage = new FlightSearchResultsSummaryPage(driver);
        passengersSeatPreferencePage = new PassengersSeatPreferencePage(driver);
        bagsPage = new BagsPage(driver);
    }
    private void validateHomePageTitle() {
        String title = "Ryanair";
        Assert.assertTrue(SeleniumUtilities.validateBrowserTitle(driver, title),"The title not found as expected - " + title);
        homePage.acceptAllCookies();
        System.out.println("The page title " + title + " is displayed as expected.");
        test.log(Status.INFO, "The page title "+ title + " is displayed as expected.");

    }
    private void originFlightSelection()
    {
        homePage.getFlightsSearchPage().enterFromAirport(flightDetails.getOrigin());
        boolean isAirportDisplayed = homePage.getFlightsSearchPage().pickAirportFromList(flightDetails.getOrigin());
        Assert.assertTrue(isAirportDisplayed,"Airport '" + flightDetails.getOrigin() + "' was not found");
        System.out.println("The flight selection " + flightDetails.getOrigin() + " is success .");
        test.log(Status.INFO,"The flight selection " + flightDetails.getOrigin() + " is success .");
    }
    private void destinationFlightSelection()
    {
        homePage.getFlightsSearchPage().enterToAirport(flightDetails.getDestination());
        isAirportDisplayed = homePage.getFlightsSearchPage().pickAirportFromList(flightDetails.getDestination());
        Assert.assertTrue(isAirportDisplayed,"Airport '" + flightDetails.getDestination() + "' was not found.");
        System.out.println("The flight selection " + flightDetails.getDestination() + " is success .");
        test.log(Status.INFO,"The flight selection " + flightDetails.getDestination() + " is success .");
    }
    private void onwardFlightDate()
    {
        homePage.getFlightsSearchPage().selectDepartureDate();
        Assert.assertTrue(homePage.getFlightsSearchPage().getCalendarComponent().selectTargetDate(flightDetails.getDepartureDate()),"The departure date "+ flightDetails.getDepartureDate() + " specified is invalid");
        System.out.println("The onward flight date selection " + flightDetails.getDepartureDate() + " is success .");
        test.log(Status.INFO,"The onward flight date selection " + flightDetails.getDepartureDate() + " is success .");

    }
    private void returnFlightDate()
    {
        homePage.getFlightsSearchPage().selectReturnDate();
        Assert.assertTrue(homePage.getFlightsSearchPage().getCalendarComponent().selectTargetDate(flightDetails.getReturnDate()),"The return date" +flightDetails.getReturnDate() +" specified is invalid");
        System.out.println("The onward flight date selection " + flightDetails.getReturnDate() + " is success .");
        test.log(Status.INFO,"The onward flight date selection " + flightDetails.getReturnDate() + " is success .");
    }
    private void selectPassengerDetails() {
        homePage.getFlightsSearchPage().getPassengersComponent().selectNoOfAdults(passengerCount.getAdults());
        homePage.getFlightsSearchPage().getPassengersComponent().selectNoOfTeens(passengerCount.getTeens());
        homePage.getFlightsSearchPage().getPassengersComponent().clickDone();
        System.out.println("The passenger details selection for " + passengerCount.getAdults() + " adults and " + passengerCount.getTeens() + " teens are success.");
        test.log(Status.INFO,"The passenger details selection for " + passengerCount.getAdults() + " adults and " + passengerCount.getTeens() + " teens are success.");

    }
    private void searchForFlights() {
        homePage.getFlightsSearchPage().clickSearch();
        System.out.println("The flight search is success");
        test.log(Status.INFO,"The flight search is success");
    }
    private void assertDateAndPassengersCount()
    {
        passenger_count_expected = passengerCount.getAdults()+passengerCount.getTeens();
        String actualOnwardDate= flightSearchResultsSummaryPage.getFlightSearchResultsHeaderComponent().getOnwardDate();
        String actualPassengerCount = flightSearchResultsSummaryPage.getFlightSearchResultsHeaderComponent().getPassengersCount(flightDetails.getTripType());
        Assert.assertTrue(passenger_count_expected==Integer.parseInt(actualPassengerCount),"Expected Passenger count : " + passenger_count_expected +  "Actual Passenger Count : " + actualPassengerCount);
        Assert.assertTrue( SeleniumUtilities.containsDayMonth(flightDetails.getDepartureDate(),actualOnwardDate),"Expected Departure Date : " + flightDetails.getDepartureDate() +  "Actual Departure Date : " + actualOnwardDate);
        if(flightDetails.getTripType().equalsIgnoreCase("return"))
        {
            String actualReturnDate = flightSearchResultsSummaryPage.getFlightSearchResultsHeaderComponent().getReturnDate();
            Assert.assertTrue( SeleniumUtilities.containsDayMonth(flightDetails.getReturnDate(),actualReturnDate),"Expected Return Date : " + flightDetails.getReturnDate() +  "Actual Return Date : " + actualReturnDate);;
        }
        System.out.println("The expected Dates - " + flightDetails.getDepartureDate() + " " + flightDetails.getReturnDate() + " - and Passenger Count - " +passenger_count_expected + " is successfully validated."  );
        test.log(Status.INFO,"The expected Dates - " + flightDetails.getDepartureDate() + " " + flightDetails.getReturnDate() + " - and Passenger Count - " +passenger_count_expected + " is successfully validated."  );
    }
    private void selectOnwardFlight()
    {
        flightSearchResultsSummaryPage.selectFirstOnwardFlight();
        Assert.assertTrue(flightSearchResultsSummaryPage.isGreenTickDisplayedForSelectedFlights(),"Expected Green tick not displayed for onward flight selection");
        System.out.println("Green tick validated successfully for onward flight");
        test.log(Status.INFO,"Green tick validated successfully for onward flight");
    }
    private void selectReturnFlight()
    {
        flightSearchResultsSummaryPage.selectFirstReturnFlight();
        Assert.assertTrue(flightSearchResultsSummaryPage.isGreenTickDisplayedForSelectedFlights(),"Expected Green tick not displayed for return flight selection");
        System.out.println("Green tick validated successfully for return flight");
        test.log(Status.INFO,"Green tick validated successfully for return flight");
    }
    private void selectFareType()
    {
        flightSearchResultsSummaryPage.getFlightFaresComponent().selectFareType(flightDetails.getFareType());
        Assert.assertTrue(flightSearchResultsSummaryPage.getFlightFaresComponent().isGreenTickDisplayedForSelectedFare(),"Expected Green tick not displayed for fare selection");
        System.out.println("Green tick validated successfully for fare type");
        test.log(Status.INFO,"Green tick validated successfully for fare type");
    }
    private void selectLoginLater()
    {
        flightSearchResultsSummaryPage.getPassengerDetailComponent().selectLoginLater();
        System.out.println("Login later is chosen");
        test.log(Status.INFO,"Login later is chosen");
    }
    private void enterPassengerDetails()
    {
        for(int i=0;i<passengerCount.getAdults();i++)
        {
            flightSearchResultsSummaryPage.getPassengerDetailComponent().enterAdultFirstName(i,passengerList.get(passenger_counter).getFirstName());
            flightSearchResultsSummaryPage.getPassengerDetailComponent().enterAdultSurName(i,passengerList.get(passenger_counter).getLastName());
            flightSearchResultsSummaryPage.getPassengerDetailComponent().selectAdultTitle(i,passengerList.get(passenger_counter).getTitle());
            passenger_counter++;
        }
        for(int i=0;i<passengerCount.getTeens();i++)
        {
            flightSearchResultsSummaryPage.getPassengerDetailComponent().enterTeenFirstName(i,passengerList.get(passenger_counter).getFirstName());
            flightSearchResultsSummaryPage.getPassengerDetailComponent().enterTeenSurName(i,passengerList.get(passenger_counter).getLastName());
            flightSearchResultsSummaryPage.getPassengerDetailComponent().selectTeenTitle(i,passengerList.get(passenger_counter).getTitle());
            passenger_counter++;
        }
        System.out.println("Passenger details are entered successfully");
        test.log(Status.INFO,"Passenger details are entered successfully");
        flightSearchResultsSummaryPage.getPassengerDetailComponent().clickContinue();

    }
    private void selectAvailableSeatsAndContinue()
    {
        Assert.assertTrue(passengersSeatPreferencePage.isSeatPreferenceHeaderDisplayed());
        passengersSeatPreferencePage.selectAnyAvailableSeats(passenger_count_expected);
        if(flightDetails.getTripType().equalsIgnoreCase("return"))
        {
            passengersSeatPreferencePage.clickNextFlight();
            passengersSeatPreferencePage.selectAnyAvailableSeats(passenger_count_expected);
        }
        passengersSeatPreferencePage.clickContinue();
        passengersSeatPreferencePage.NoThanksButton();
        System.out.println("Passenger seat selection is success");
        test.log(Status.INFO,"Passenger seat selection is success");
    }
    private void isBagsPageDisplayed()
    {
        Assert.assertTrue(bagsPage.isCabinBagsHeaderDisplayed(),"The cabin bags header is not displayed as expected");
        Assert.assertTrue(bagsPage.isCheckinBagsHeaderDisplayed(),"The checkin bags header is not displayed as expected");
        System.out.println("The bags header is validated successfully");
        test.log(Status.INFO,"The bags header is validated successfully");
    }
}
