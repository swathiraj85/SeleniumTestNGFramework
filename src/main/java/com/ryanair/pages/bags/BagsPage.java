package com.ryanair.pages.bags;

import com.ryanair.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.SeleniumUtilities;

public class BagsPage extends BasePage
{
    @FindBy(css="[id*=bag-header-1] *")
    private WebElement cabinHeader;

    @FindBy(css="[id*=bag-header-0] *")
    private WebElement checkinHeader;


    public BagsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public boolean isCabinBagsHeaderDisplayed()
    {
        return SeleniumUtilities.waitForElementDisplayed(driver,cabinHeader);
    }
    public boolean isCheckinBagsHeaderDisplayed()
    {
        return SeleniumUtilities.waitForElementDisplayed(driver,checkinHeader);
    }
}
