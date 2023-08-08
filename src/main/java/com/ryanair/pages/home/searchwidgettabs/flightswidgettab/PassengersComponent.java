package com.ryanair.pages.home.searchwidgettabs.flightswidgettab;

import com.ryanair.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.SeleniumUtilities;

public class PassengersComponent extends BasePage
{
    @FindBy(css="ry-counter[data-ref='passengers-picker__adults'] div[data-ref='counter.counter__increment'] div[class='counter__button']")
    private WebElement adultIncrementCounter;

    @FindBy(css="ry-counter[data-ref='passengers-picker__adults'] div[data-ref='counter.counter__decrement'] div[class='counter__button']")
    private WebElement adultDecrementCounter;

    @FindBy(css="ry-counter[data-ref='passengers-picker__teens'] div[data-ref='counter.counter__increment'] div[class='counter__button']")
    private WebElement teensIncrementCounter;

    @FindBy(css="ry-counter[data-ref='passengers-picker__teens'] div[data-ref='counter.counter__decrement'] div[class='counter__button']")
    private WebElement teensDecrementCounter;

    @FindBy(css="ry-counter[data-ref='passengers-picker__infant'] div[data-ref='counter.counter__increment'] div[class='counter__button']")
    private WebElement infantIncrementCounter;

    @FindBy(css="ry-counter[data-ref='passengers-picker__infant'] div[data-ref='counter.counter__decrement'] div[class='counter__button']")
    private WebElement infantDecrementCounter;

    @FindBy(css="ry-counter[data-ref='passengers-picker__children'] div[data-ref='counter.counter__increment'] div[class='counter__button']")
    private WebElement childIncrementCounter;

    @FindBy(css="ry-counter[data-ref='passengers-picker__children'] div[data-ref='counter.counter__decrement'] div[class='counter__button']")
    private WebElement childDecrementCounter;

    @FindBy(css="button[aria-label='Done']")
    private WebElement passengersConfirmButton;

    public PassengersComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void incrementOrDecrementCounter(int count,WebElement element)
    {
        // increment/decrement the counter until the condition is met
        for(int i=0;i<count;i++)
        {
            SeleniumUtilities.click(driver,element);
        }
    }
    public void selectNoOfAdults(int adults_count)
    {
        // check for minimum adult condition
        if(adults_count< 1)
        {
            throw new IllegalArgumentException("The minimum number of adult is 1");
        }
        // call the function to increment counter.By default ,1 adult is chosen always in the application.
        incrementOrDecrementCounter(adults_count-1,adultIncrementCounter);
    }
    public void checkTotalCountOfPassengers(int passenger_count)
    {
        // check for boundary conditions for max passengers
        if(passenger_count> 25)
        {
            throw new IllegalArgumentException("The maximum number of passengers are 25. If there are more than 25 passengers please use booking form");
        }
    }
    public void selectNoOfTeens(int teens_count)
    {
        // call the function to increment counter.
        incrementOrDecrementCounter(teens_count,teensIncrementCounter);
    }
    public void clickDone()
    {
        SeleniumUtilities.click(driver,passengersConfirmButton);
    }
}
