package training.stage2.webdriver.i_can_win;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class WebDriverTest {

    private WebDriver chromeDriver;

    private static WebDriver StartScenarioOnBrowser(WebDriver driver) {
        new PageNavigator(driver)
                .openPage()
                .fillSiteForm()
                .createPaste();
        return driver;
    }

    @Test(description = "test")
    public void scenarioTest() {

        chromeDriver = StartScenarioOnBrowser(new ChromeDriver());

    }
    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        System.setProperty("webdriver.chrome.driver","C:/WebDriver/chromedriver.exe");
         chromeDriver = new ChromeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestCompleted() {
        driverQuit(chromeDriver);
    }

    private void driverQuit(WebDriver driver) {
        driver.quit();
    }

}
