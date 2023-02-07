package training.stage2.webdriver.hurt_me_plenty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class WebDriverTest {

    WebDriver chromeDriver;

    private static WebDriver StartScenarioOnBrowser(WebDriver driver) {
        new PageNavigator(driver)
                .openPage()
                .searchForElementAndClick()
                .fillSiteForm()
                .createRequest();
        return driver;
    }

    @Test(description = "test")
    public void scenarioTest() {

        chromeDriver = StartScenarioOnBrowser(new ChromeDriver());

        testProcedure(chromeDriver);
    }

    private void testProcedure(WebDriver driver) {
        PageTestResult testPage = new PageTestResult(driver);
        Assert.assertTrue(testPage.checkVMClass(), driver.toString() + ": FAIL: for VM Class check");
        Assert.assertFalse(testPage.checkInstanceType(), driver.toString() + ": FAIL: for Instance Type check");
        Assert.assertTrue(testPage.checkRegion(), driver.toString() + ": FAIL: for Region check");
        Assert.assertFalse(testPage.checkSSD(), driver.toString() + ": FAIL: for SSD capacity check");
        Assert.assertTrue(testPage.checkUsage(), driver.toString() + ": FAIL: for Usage term check");
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestCompleted() {
        driverQuit(chromeDriver);
    }

    private void driverQuit(WebDriver driver) {
        driver.quit();
    }

}
