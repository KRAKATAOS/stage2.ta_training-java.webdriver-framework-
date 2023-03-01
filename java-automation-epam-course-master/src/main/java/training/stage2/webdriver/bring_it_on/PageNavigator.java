package training.stage2.webdriver.bring_it_on;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageNavigator extends AbstractPage {


    @FindBy(xpath = "//*[@id='postform-text']")
    private WebElement codeDescriptionField;

    @FindBy(xpath = "//*[@id='select2-postform-expiration-container']")
    private WebElement pasteExpirationField;

    @FindBy(xpath = "//*[@class='select2-selection__rendered' and @id='select2-postform-format-container']")
    private WebElement syntaxHighlightComboBox;

    @FindBy(xpath = "//*[@id='postform-name']")
    private WebElement titleField;

    @FindBy(xpath = "//*[@class='btn -big']")
    private WebElement createButton;


    public PageNavigator(WebDriver driver) {
        super(driver);
    }

    public PageNavigator openPage(String pasteUrl) {
        driver.manage().window().maximize();
        driver.get(pasteUrl);
        new WebDriverWait(driver, 5).until(CustomConditions.jQueryAJAXCompleted());

        return this;
    }

    public PageNavigator fillCodeForThePasteForm(String codeForThePaste) {
        codeDescriptionField.sendKeys(codeForThePaste);

        return this;
    }
    public PageNavigator fillTitleForm(String titleForThePaste) {
        titleField.sendKeys(titleForThePaste);

        return this;
    }

    public PageNavigator fillingInTheExpirationDateField(String timeForExpiration){

        pasteExpirationField.click();
        driver.findElement(By.xpath("//*[@class='select2-results__option' and text()='" + timeForExpiration + "']"))
                .click();

        new WebDriverWait(driver, 10).until(CustomConditions.jQueryAJAXCompleted());
        return this;
    }

    public PageNavigator fillingOfSyntaxHighlightComboBox (String syntaxHighLight){

        syntaxHighlightComboBox.click();
        driver.findElement(By.xpath("//*[@class='select2-results__option' and text()='" + syntaxHighLight + "']"))
                .click();

        new WebDriverWait(driver, 10).until(CustomConditions.jQueryAJAXCompleted());
        return this;
    }

    public void createPaste() {

        createButton.click();
        new WebDriverWait(driver, 20).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='bash']")));
    }
}
