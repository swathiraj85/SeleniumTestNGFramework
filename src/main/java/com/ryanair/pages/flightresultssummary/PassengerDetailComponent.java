package com.ryanair.pages.flightresultssummary;

import com.ryanair.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.SeleniumUtilities;

import java.util.HashMap;
import java.util.List;

public class PassengerDetailComponent extends BasePage
{
    @FindBy(css="[class*='login-later']")
    private WebElement loginLater;

    @FindBy(css="button[class*='login-button']")
    private WebElement loginButton;

    @FindBy(css="button[class*='signup-button']")
    private WebElement signUpButton;
    @FindBy(css="[data-ref*='pax-details__ADT'] [class*='dropdown-item__label']")
    private List<WebElement> titleDropdownItemAdultList;
    @FindBy(css="[data-ref*='pax-details__TEEN'] [class*='dropdown-item__label']")
    private List<WebElement> titleDropdownItemTeenList;

    @FindBy(css="button[class*='continue']")
    private WebElement continueButton;
    public PassengerDetailComponent(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public void selectLoginLater()
    {
        SeleniumUtilities.click(driver,loginLater);
    }
    public void enterAdultFirstName(int index,String firstName)
    {
        By firstNameAdult = By.cssSelector("[data-ref*='pax-details__ADT-"+index+"'] [id*='.name']");
       SeleniumUtilities.sendKeys(driver,driver.findElement(firstNameAdult),firstName);
    }
    public void enterAdultSurName(int index,String surName)
    {
        By surNameAdult = By.cssSelector("[data-ref*='pax-details__ADT-"+index+"'] [id*='.surname']");
        SeleniumUtilities.sendKeys(driver,driver.findElement(surNameAdult),surName);
    }
    public void selectAdultTitle(int index,String title_value)
    {
        By adultTitleToggle = By.cssSelector("[data-ref*='pax-details__ADT-"+index+"'] button[class*='dropdown__toggle']");
        SeleniumUtilities.click(driver,driver.findElement(adultTitleToggle));
        for(WebElement adultTitleDropDownItem : titleDropdownItemAdultList)
        {
            if(SeleniumUtilities.getElementText(driver,adultTitleDropDownItem).equalsIgnoreCase(title_value))
            {
                SeleniumUtilities.click(driver,adultTitleDropDownItem);
                break;
            }
        }
    }
    public void enterTeenFirstName(int index,String firstName)
    {
        By firstNameAdult = By.cssSelector("[data-ref*='pax-details__TEEN-"+index+"'] [id*='.name']");
        SeleniumUtilities.sendKeys(driver,driver.findElement(firstNameAdult),firstName);
    }
    public void enterTeenSurName(int index,String surName)
    {
        By surNameAdult = By.cssSelector("[data-ref*='pax-details__TEEN-"+index+"'] [id*='.surname']");
        SeleniumUtilities.sendKeys(driver,driver.findElement(surNameAdult),surName);
    }
    public void selectTeenTitle(int index,String title_value)
    {
        By teenTitleToggle = By.cssSelector("[data-ref*='pax-details__TEEN-"+index+"'] button[class*='dropdown__toggle']");
        SeleniumUtilities.click(driver,driver.findElement(teenTitleToggle));
        for(WebElement teenTitleDropDownItem : titleDropdownItemTeenList)
        {
            if(SeleniumUtilities.getElementText(driver,teenTitleDropDownItem).equalsIgnoreCase(title_value))
            {
                SeleniumUtilities.click(driver,teenTitleDropDownItem);
                break;
            }
        }
    }
    public void clickContinue()
    {
        SeleniumUtilities.click(driver,continueButton);
    }

}
