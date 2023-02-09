package training.stage2.framework.test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import training.stage2.framework.page.HomePageNavigator;
import training.stage2.framework.page.PageTestResult;
import training.stage2.framework.service.TestingSetCreator;

public class WebDriverTest extends CommonConditions {

    private static WebDriver StartScenarioOnBrowser(WebDriver driver) {
        new HomePageNavigator(driver)
                .openPage()
                .searchForElementAndClick()
                .fillSiteForm(TestingSetCreator.withTestingSetFromProperty())
                .createRequest()
                .openEmailSiteAndTakeEmailName()
                .sendEmail()
                .verifyCostInEmail();
        return driver;
    }

    @Test(description = "test")
    public void scenarioTest() {
        testProcedure(StartScenarioOnBrowser(driver));
    }

    private void testProcedure(WebDriver driver) {
        Assert.assertTrue(PageTestResult.result, driver.toString() + ": FAIL: total estimated cost is not equal");
    }

}