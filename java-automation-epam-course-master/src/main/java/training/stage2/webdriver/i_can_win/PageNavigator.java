package training.stage2.webdriver.i_can_win;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageNavigator extends AbstractPage {

    private static final String HOMEPAGE_URL = "https://pastebin.com";
    private static final String CODE_FIELD_TEXT = "Hello from WebDriver";
    private static final String EXPIRATION_TIME = "10 Minutes";
    private static final String TITLE_TEXT = "helloweb";

    @FindBy(xpath = "//*[@id='postform-text']")
    private WebElement codeDescriptionField;

    @FindBy(xpath = "//*[@id='select2-postform-expiration-container']")
    private WebElement pasteExpirationField;

    @FindBy(xpath = "//*[@class='select2-results__option' and text()='" + EXPIRATION_TIME + "']")
    private WebElement pasteExpirationFieldSelect;

    @FindBy(xpath = "//*[@id='postform-name']")
    private WebElement titleField;

    @FindBy(xpath = "//*[@class='btn -big']")
    private WebElement createButton;



    public PageNavigator(WebDriver driver) {
        super(driver);
    }

    public PageNavigator openPage() {
        driver.manage().window().maximize();
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, 10).until(CustomConditions.jQueryAJAXCompleted());
        return this;
    }

    public PageNavigator fillSiteForm() {
        codeDescriptionField.sendKeys(CODE_FIELD_TEXT);

        pasteExpirationField.click();
        pasteExpirationFieldSelect.click();

        titleField.sendKeys(TITLE_TEXT);
        return this;
    }

    public PageNavigator createPaste() {

        createButton.click();
        new WebDriverWait(driver, 10).until(CustomConditions.jQueryAJAXCompleted());
        return this;
    }
}
