package com.ryanair.pages.home;

import com.ryanair.pages.BasePage;
import com.ryanair.pages.home.headersections.HeaderSectionsPage;
import com.ryanair.pages.home.searchwidgettabs.flightswidgettab.CalendarComponent;
import com.ryanair.pages.home.searchwidgettabs.flightswidgettab.FlightsSearchPage;
import com.ryanair.pages.home.searchwidgettabs.flightswidgettab.PassengersComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.SeleniumUtilities;

public class HomePage extends BasePage
{
    private HeaderSectionsPage headerSectionsPage;
    private FlightsSearchPage flightsSearchPage;


    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="button[data-ref='cookie.accept-all']")
    private WebElement acceptAllCookies;

    @FindBy(css="a[title='Ryanair']")
    private WebElement titleHeader;


    public HeaderSectionsPage getHeaderSectionsPage() {
        return new HeaderSectionsPage(driver);
    }

    public FlightsSearchPage getFlightsSearchPage() {
        return new FlightsSearchPage(driver);
    }
    public String getTitleHeader() {
        return titleHeader.getAttribute("title");
    }
    public void acceptAllCookies()
    {
        SeleniumUtilities.click(driver,acceptAllCookies);
    }

}
