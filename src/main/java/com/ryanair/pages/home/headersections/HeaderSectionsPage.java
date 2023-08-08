package com.ryanair.pages.home.headersections;

import com.ryanair.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeaderSectionsPage extends BasePage {
    @FindBy(xpath="//button//*[contains(text(),'Plan')]")
    private WebElement plan;

    @FindBy(xpath="//button//*[contains(text(),'My Bookings')]")
    private WebElement myBookings;

    @FindBy(xpath="//button//*[contains(text(),'Sign Up')]")
    private WebElement signUp;

    @FindBy(xpath="//button//*[contains(text(),'Log In')]")
    private WebElement logIn;

    @FindBy(xpath="//button//*[contains(text(),'Help')]")
    private WebElement help;

    public HeaderSectionsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }
}
