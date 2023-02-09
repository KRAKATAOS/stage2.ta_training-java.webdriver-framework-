package training.stage2.webdriver.bring_it_on;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageNavigator extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private static final String CODE_FIELD_TEXT = "git config --global user.name  \"New Sheriff in Town\"\n" +
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")\n" +
            "git push origin master --force";
    private static final String EXPIRATION_TIME = "10 Minutes";
    private static final String SYNTAX = "Bash";
    private static final String TITLE_TEXT = "how to gain dominance among developers";

    @FindBy(xpath = "//*[@id='postform-text']")
    private WebElement codeDescriptionField;

    @FindBy(xpath = "//*[@id='select2-postform-expiration-container']")
    private WebElement pasteExpirationField;

    @FindBy(xpath = "//*[@class='select2-results__option' and text()='" + EXPIRATION_TIME + "']")
    private WebElement pasteExpirationFieldSelect;

    @FindBy(xpath = "//*[@class='select2-selection__rendered' and @id='select2-postform-format-container']")
    private WebElement syntaxHighlightComboBox;

    @FindBy(xpath = "//*[@class='select2-results__option' and text()='" + SYNTAX + "']")
    private WebElement syntaxHighlightComboBoxSelect;

    @FindBy(xpath = "//*[@id='postform-name']")
    private WebElement titleField;

    @FindBy(xpath = "//*[@class='btn -big']")
    private WebElement createButton;

    public PageNavigator(WebDriver driver) {
        super(driver);
    }

    public static String getCodeFieldText() {
        return CODE_FIELD_TEXT;
    }

    public static String getSYNTAX() {
        return SYNTAX;
    }

    public static String getTitleText() {
        return TITLE_TEXT;
    }

    public PageNavigator openPage() {
        driver.manage().window().maximize();

        driver.get(HOMEPAGE_URL);

        new WebDriverWait(driver, 5).until(CustomConditions.jQueryAJAXCompleted());

        return this;
    }

    public PageNavigator fillSiteForm() {
        codeDescriptionField.sendKeys(CODE_FIELD_TEXT);

        pasteExpirationField.click();
        pasteExpirationFieldSelect.click();

        syntaxHighlightComboBox.click();
        syntaxHighlightComboBoxSelect.click();

        titleField.sendKeys(TITLE_TEXT);

        return this;
    }

    public PageNavigator createRequest() {

        createButton.click();

        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='bash']")));

        return this;
    }
}
