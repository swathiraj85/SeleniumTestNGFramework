package com.ryanair.pages.seatpreference;

import com.ryanair.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.SeleniumUtilities;

import java.time.Duration;
import java.util.List;

public class PassengersSeatPreferencePage extends BasePage {

    @FindBy(xpath="//*[contains(text(),'Choose your seat preference')]")
    private WebElement pageTitle;

    @FindBy(css="seat-map")
    private WebElement seatMapContainer;

    @FindBy(css="button[class*='standard']")
    private List<WebElement> availableSeats;

    @FindBy(css="[data-ref*='-selected'")
    private List<WebElement> selectedSeat;

    @FindBy(xpath="//button[contains(text(),'Next flight')]")
    private WebElement nextFlightButton;

    @FindBy(css="[data-ref*='continue']")
    private WebElement continueButton;

    @FindBy(css="[class='enhanced-takeover-beta__modal']")
    private WebElement modalElement;

    @FindBy(xpath="//button[contains(text(),'No, thanks')]")
    private WebElement noThanksButton;

    public PassengersSeatPreferencePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
    public boolean isSeatPreferenceHeaderDisplayed()
    {
        return SeleniumUtilities.waitForElementDisplayed(driver,pageTitle);
    }
    public void selectAnyAvailableSeats(int passengerCount)
    {
        SeleniumUtilities.waitForElementDisplayed(driver,seatMapContainer);
        // This wait is required as the seats component dynamically reload after the elements are verified.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(int i=0;i<passengerCount;i++)
        {
            SeleniumUtilities.click(driver,availableSeats.get(i));
        }
        System.out.println("The Selected seats are " + selectedSeat.get(0));
    }
    public void clickNextFlight()
    {
        SeleniumUtilities.click(driver,nextFlightButton);
    }
    public void clickContinue()
    {
       SeleniumUtilities.click(driver,continueButton);
    }
    public void NoThanksButton()
    {
        SeleniumUtilities.waitForElementDisplayed(driver,modalElement);
       // modalElement.findElement(By.xpath("//button[contains(text(),'No, thanks')]")).click();
        SeleniumUtilities.click(driver,modalElement.findElement(By.xpath("//button[contains(text(),'No, thanks')]")));
    }
}
