package training.stage2.webdriver.bring_it_on;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestPastebinFieldFillerAndChecker {

    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private static final String CODE_FIELD_TEXT = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String EXPIRATION_TIME = "10 Minutes";
    private static final String SYNTAX = "Bash";
    private static final String TITLE_TEXT = "how to gain dominance among developers";
    protected WebDriver driver;


    @Test
    public void fillingInTheSiteFields() {
        new PageNavigator(driver)
                .openPage(HOMEPAGE_URL)
                .fillCodeForThePasteForm(CODE_FIELD_TEXT)
                .fillTitleForm(TITLE_TEXT)
                .fillingInTheExpirationDateField(EXPIRATION_TIME)
                .fillingOfSyntaxHighlightComboBox(SYNTAX)
                .createPaste();
        PageTestResult pageAfterCreatePaste = new PageTestResult(driver);
        Assert.assertEquals(CODE_FIELD_TEXT, pageAfterCreatePaste.checkCodeText());
        Assert.assertEquals(SYNTAX,pageAfterCreatePaste.checkSyntaxHighlight());
        Assert.assertEquals(TITLE_TEXT,pageAfterCreatePaste.checkTitle());

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
