package com.ryanair.pages.flightresultssummary;

import com.ryanair.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.SeleniumUtilities;

import java.util.List;

public class FlightFaresComponent extends BasePage
{
    @FindBy(xpath="//*[contains(@class,'fare-table')]//descendant::*[contains(text(),'Basic')]//following::button[1]")
    private WebElement basicFare;

    @FindBy(xpath="//*[contains(@class,'fare-table')]//descendant::*[contains(text(),'Regular')]//following::button[1]")
    private WebElement regularFare;

    @FindBy(xpath="//*[contains(@class,'fare-table')]//descendant::*[contains(text(),'Family Plus')]//following::button[1]")
    private WebElement familyPlusFare;

    @FindBy(xpath="//*[contains(@class,'fare-table')]//descendant::*[text()='Plus']//following::button[1]")
    private WebElement plusFare;

    @FindBy(xpath="//button[contains(text(),'Continue with Basic fare')]")
    private WebElement continueWithBasicFare;

    @FindBy(css="fare-summary-container [class*='done-tick__icon']")
    private WebElement fareSummaryGreenTick;

    @FindBy(css="[class*='dialog']")
    private WebElement modalElement;

    public FlightFaresComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void selectFareType(String fare_type)
    {
        switch(fare_type.toUpperCase())
        {
            case "REGULAR":
                SeleniumUtilities.click(driver,regularFare);
                break;
            case "BASIC":
                SeleniumUtilities.click(driver,basicFare);
                handleContinueWithBasicModal();
                driver.switchTo().defaultContent();
                break;
            case "FAMILY PLUS":
                SeleniumUtilities.click(driver,familyPlusFare);
                break;
            case "PLUS":
                SeleniumUtilities.click(driver,plusFare);
                break;
            default:
                System.out.println("Invalid fare type : " + fare_type);
                throw new IllegalArgumentException("Invalid fare type : " + fare_type + ".It can be one among regular,basic,family plus or plus");
        }

    }
    public boolean isGreenTickDisplayedForSelectedFare()
    {
        return SeleniumUtilities.waitForElementDisplayed(driver,fareSummaryGreenTick)?true:false;
    }
    public void handleContinueWithBasicModal()
    {
        SeleniumUtilities.waitForElementDisplayed(driver,modalElement);
        SeleniumUtilities.click(driver,modalElement.findElements(By.cssSelector("[class*='dialog'] button")).get(0));
    }
}

