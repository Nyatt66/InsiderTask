package com.insider.tests;

import com.insider.pages.CareersPage;
import com.insider.pages.MainMenuPage;
import com.insider.utilities.BrowserUtils;
import com.insider.utilities.ConfigurationReader;
import com.insider.utilities.Driver;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class InsiderTest{
    CareersPage careersPage = new CareersPage();
    MainMenuPage mainMenuPage = new MainMenuPage();
    JavascriptExecutor jse;

    @RegisterExtension
    ScreenShotOnFailure watcher = new ScreenShotOnFailure("screenshots");

    @BeforeAll()
    public void setUp() {
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }


    @AfterAll
    public void tearDown() {
        Driver.closeDriver();
    }

    //1. Visit https://useinsider.com/ and check Insider home page is opened or not
    @Test
    @Order(1)
    public void get_mainPage_title() {

        assertEquals(Driver.getDriver().getTitle(), "#1 Leader in Individualized, Cross-Channel CX — Insider");

    }

    //2. Select “More” menu in navigation bar, select “Careers” and check Career page,
    //its Locations, Teams and Life at Insider blocks are opened or not
    @Test
    @Order(2)
    public void select_careers() {

        //Accept cookies
        mainMenuPage.acceptCookiesButton.click();

        // Check Careers Page
        mainMenuPage.moreMenuButton.click();
        mainMenuPage.careersBlock.click();
        assertTrue(Driver.getDriver().getTitle().contains("Careers"));

        // Check Locations Block
        BrowserUtils.verifyElementDisplayed(careersPage.locationBlock);

        // Check Teams Block
        BrowserUtils.verifyElementDisplayed(careersPage.teamsBlock);

        // Check Life at Insider Block
        BrowserUtils.verifyElementDisplayed(careersPage.lifeAtInsiderBlock);

    }

    //3. Click “See All Teams”, select Quality Assurance, click “See all QA jobs”, filter jobs by
    //Location - Istanbul, Turkey and department - Quality Assurance, check presence of
    //jobs list
    @Test
    @Order(3)
    public void see_all_qa_jobs() {

    // Click “See All Teams”
        jse = (JavascriptExecutor) Driver.getDriver();
        jse.executeScript("arguments[0].scrollIntoView()", careersPage.seeAllTeamsButton);
        jse.executeScript("arguments[0].click()", careersPage.seeAllTeamsButton);

    // select Quality Assurance
        jse.executeScript("arguments[0].click()", careersPage.selectQaJobs);

    //click “See all QA jobs”
        careersPage.seeAllQaJobs.click();

    // filter jobs Location - Istanbul, Turkey
        BrowserUtils.waitFor(4);
        careersPage.filterByLocation.click();
        careersPage.istanbulTurkeyLocation.click();

    //filter department by Quality Assurance

        careersPage.filterByDepartment.click();
        careersPage.qualityAssuranceDepartment.click();

    // check presence of job list
        BrowserUtils.waitFor(1);
        for (WebElement eachJobs : careersPage.jobList) {
            assertTrue(eachJobs.isDisplayed());
        }

    }

    //4. Check that all jobs’ Position contains “Quality Assurance”, Department contains
    //“Quality Assurance”, Location contains “Istanbul, Turkey” and “Apply Now” button
    @Test
    @Order(4)
    public void check_all_jobs() {
        //Check that all jobs’ Position contains “Quality Assurance”
        for (WebElement eachJobs : careersPage.positionList) {
            assertTrue(eachJobs.getText().contains("Quality Assurance"));
        }
        //Department contains "Quality Assurance"
        for (WebElement eachJobs : careersPage.departmentList) {
            assertTrue(eachJobs.getText().contains("Quality Assurance"));
        }
        //Location contains “Istanbul, Turkey”
        for (WebElement eachJobs : careersPage.locationList) {
            assertTrue(eachJobs.getText().contains("Istanbul, Turkey"));
        }
        //Contains Apply Now Button
        for (WebElement eachApplyButton : careersPage.applyNowButtonList) {
            assertTrue(eachApplyButton.getAttribute("innerHTML").contains("Apply Now"));
        }
    }

    //5. Click “Apply Now” button and check that this action redirects us to Lever Application form page
    @Test
    @Order(5)
    public void click_applyNow_Button() {

        // click "Apply Now" button
        jse.executeScript("arguments[0].click()", careersPage.applyNowButton1);

        //switch window
        Set<String> windowIds = Driver.getDriver().getWindowHandles();
        Iterator<String> itr = windowIds.iterator();
        String originWindowID = itr.next();
        String childWindowID = itr.next();

        Driver.getDriver().switchTo().window(childWindowID);
        BrowserUtils.waitFor(5);

        // Checks lever application form page
        assertTrue(Driver.getDriver().getTitle().contains("Quality Assurance"));

    }

}
