package com.insider.pages;

import com.insider.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CareersPage {
    public CareersPage(){PageFactory.initElements(Driver.getDriver(),this);}

    @FindBy (id = "career-our-location")
    public WebElement locationBlock;

    @FindBy (id = "career-find-our-calling")
    public WebElement teamsBlock;

    @FindBy (xpath = "//h2[.='Life at Insider']")
    public WebElement lifeAtInsiderBlock;

    @FindBy (linkText = "See all teams")
    public WebElement seeAllTeamsButton;

    @FindBy (xpath = "//h3[.='Quality Assurance']")
    public WebElement selectQaJobs;

    @FindBy(xpath = "//a[.='See all QA jobs']")
    public WebElement seeAllQaJobs;

    @FindBy (xpath = "//li[.='Istanbul, Turkey']")
    public WebElement istanbulTurkeyLocation;

    @FindBy(xpath = "//span[@data-select2-id ='1']")
    public WebElement filterByLocation;

    @FindBy(xpath = "//span[@id='select2-filter-by-department-container']")
    public WebElement filterByDepartment;

    @FindBy (xpath = "//li[.='Quality Assurance']")
    public WebElement qualityAssuranceDepartment;

   @FindBys(@FindBy (xpath = "//*[@id='jobs-list']"))
    public List<WebElement> jobList;

   @FindBys(@FindBy (xpath = "//*[@class='position-list-item-wrapper bg-light']/p"))
    public List<WebElement> positionList;

    @FindBys(@FindBy(xpath = "//*[@class='position-department text-large font-weight-600 text-primary']"))
    public List<WebElement> departmentList;

    @FindBys(@FindBy(xpath = "//*[@class='position-location text-large']"))
    public List<WebElement> locationList;

    @FindBys(@FindBy(xpath = "//*[@class='position-list-item-wrapper bg-light']/a"))
    public List<WebElement> applyNowButtonList;


    @FindBy(xpath = "(//*[.='Apply Now'])[1]")
    public WebElement applyNowButton1;

}
