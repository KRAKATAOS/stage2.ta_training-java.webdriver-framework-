package training.stage2.webdriver.hardcore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class WebDriverTest {

    WebDriver chromeDriver;

    private static WebDriver StartScenarioOnBrowser(WebDriver driver) {
        new HomePageNavigator(driver)
                .openPage()
                .searchForElementAndClick()
                .fillSiteForm()
                .createRequest()
                .openEmailSiteAndTakeEmailName()
                .sendEmail()
                .verifyCostInEmail();
        return driver;
    }

    @Test(description = "test")
    public void scenarioTest() {
        chromeDriver = StartScenarioOnBrowser(new ChromeDriver());
        testProcedure(chromeDriver);
    }

    private void testProcedure(WebDriver driver) {
        System.out.println("result(assert method): " + PageTestResult.result);
        Assert.assertTrue(PageTestResult.result, driver.toString() + ": FAIL: email address is not equal");
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestCompleted() {
        driverQuit(chromeDriver);
    }

    private void driverQuit(WebDriver driver) {
        driver.quit();
    }
}
