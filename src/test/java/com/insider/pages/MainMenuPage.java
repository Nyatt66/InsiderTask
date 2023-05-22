package com.insider.pages;

import com.insider.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainMenuPage {
    public MainMenuPage(){
        PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy (xpath = "//a[@id = 'mega-menu-1']//span[.='More']")
    public WebElement moreMenuButton;

    @FindBy (xpath = "//a[@href=\"https://useinsider.com/careers/\"]")
    public WebElement careersBlock;

    @FindBy (id = "cookie-law-info-bar")
    public WebElement cookiesBlock;

@FindBy (id = "wt-cli-accept-all-btn")
    public WebElement acceptCookiesButton;



}
