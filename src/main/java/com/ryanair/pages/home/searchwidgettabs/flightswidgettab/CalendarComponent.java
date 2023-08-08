package com.ryanair.pages.home.searchwidgettabs.flightswidgettab;

import com.ryanair.pages.BasePage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.SeleniumUtilities;

import java.util.List;

public class CalendarComponent extends BasePage
{
    @FindBy(css="calendar[class*='calendar--left']>[class*='month-name']")
    private WebElement calendarMonthYearName;

    @FindBy(css="*[class*='datepicker__arrow--right']")
    private WebElement datePickerArrowRight;

    @FindBy(xpath="//*[contains(@class,'calendar--left')]//descendant::*[@class='calendar-body__cell' or @class='calendar-body__cell calendar-body__cell--weekend']")
    private List<WebElement> calendarDayList;

    public CalendarComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getMonthYearName()
    {
        SeleniumUtilities.waitForElementDisplayed(driver,calendarMonthYearName);
       return  SeleniumUtilities.getElementText(driver,calendarMonthYearName).trim();
    }
    public void navigateToTargetMonthYear(String targetDate) {
        String targetMonthYear = SeleniumUtilities.convertToMonthYear(targetDate);
        while (!targetMonthYear.equalsIgnoreCase(getMonthYearName())) {
            SeleniumUtilities.click(driver, datePickerArrowRight);
        }
    }

    public boolean selectTargetDate(String date)
    {
        boolean b_day_found_flag = false;

        // Verify the input date is not less than the current date and throw exception otherwise
        if (!SeleniumUtilities.isDateNotLessThanCurrent(date))
        {
            throw new IllegalArgumentException("The input date is either less than current date or Invalid date format provided. Expected 'dd MMM yyyy'.");
        }
        // call the function to navigate to target month/year
        navigateToTargetMonthYear(date);

        // get the day from date
       int input_day = Integer.parseInt(splitDayFromDate(date));

        // loop through the available days in the target month and select if it matches the input day
            for (WebElement calendarDayItem  : calendarDayList)
            {
                int available_day = Integer.parseInt(SeleniumUtilities.getElementText(driver,calendarDayItem));

                if(available_day == input_day)
                {
                    SeleniumUtilities.click(driver,calendarDayItem);
                    b_day_found_flag = true;
                    break;
                }
            }
        return  b_day_found_flag;
    }
    public String splitDayFromDate(String date)
    {
        String arr_date[] = date.split(" ");

        if (arr_date.length != 3)
        {
            throw new IllegalArgumentException("Invalid date format. Expected 'dd MMM yyyy'.");
        }
       return arr_date[0];
    }

}
