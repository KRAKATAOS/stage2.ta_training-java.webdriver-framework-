package training.stage2.webdriver.i_can_win;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestPastebinFieldFiller {
    private static final String CODE_FIELD_TEXT = "Hello from WebDriver";
    private static final String TITLE_TEXT = "helloweb";
    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private static final String EXPIRATION_TIME = "10 Minutes";
    protected WebDriver driver;

    @Test
    public void fillingInTheSiteFields() {
        new PageNavigator(driver)
                  .openPage(HOMEPAGE_URL)
                  .fillSiteForm(CODE_FIELD_TEXT,TITLE_TEXT)
                  .fillingInTheExpirationDateField(EXPIRATION_TIME)
                  .createPaste();
    }

    @BeforeMethod
    public void startBrowserDriver(){
        driver = new ChromeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestCompleted() {
        driver.quit();
    }

}
