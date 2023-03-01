package training.stage2.framework.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import training.stage2.framework.util.CustomConditions;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class GooglePageNavigation extends AbstractPage {

    private final Logger logger = LogManager.getRootLogger();

    String emailName;
    ArrayList<String> tabs;
    public static String estimatedCost;

    @FindBy(xpath = "//*[@class='devsite-search-field devsite-search-query']")
    private WebElement searchButton;
    @FindBy(xpath = "//*[@class='gsc-results gsc-webResult']")
    private WebElement searchResultField;
    @FindBy(xpath = "//*[@class='compute-engine-block']")
    private WebElement elementOnCalculatorPage;
    @FindBy(xpath = "//md-input-container//label[contains(text(),'Number of instances')]/../input")
    private WebElement inputFieldNumberOfInstances;
    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.addGPUs']//*[@class='md-container md-ink-ripple']")
    private WebElement checkBoxAddGPUs;
    @FindBy(xpath = "//label[text()='Local SSD']/../md-select")
    private WebElement localSSD;
    @FindBy(xpath = "//*[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement buttonAddToEstimate;
    @FindBy(xpath = "//*[@id='Email Estimate']")
    private WebElement buttonEmailEstimate;
    @FindBy(xpath = "//input[@type='email']")
    private WebElement inputFieldEmail;
    @FindBy(xpath = "//*[@id='dialogContent_551']/form/md-dialog-actions/button[2]")
    private WebElement buttonSendEmail;
    @FindBy(xpath = "//*[@id='resultBlock']//h2/b")
    private WebElement costText;


    public GooglePageNavigation(WebDriver driver) {
        super(driver);
    }

    public GooglePageNavigation(WebDriver driver, String emailName, ArrayList<String> tabs) {
        super(driver);
        this.emailName = emailName;
        this.tabs = tabs;
    }

    public GooglePageNavigation openPage(String cloudGoogleUrl) {
        driver.manage().window().maximize();
        driver.get(cloudGoogleUrl);

        new WebDriverWait(driver, 10).until(CustomConditions.pageLoadCompleted());
        logger.info("Home page opened");

        return this;
    }

    public GooglePageNavigation searchElementAndClick(String sendRequestInCloudGoogle) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
        searchButton.sendKeys(sendRequestInCloudGoogle);
        searchButton.sendKeys(Keys.RETURN);

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchResultField));
        searchResultField.findElement(By.linkText(sendRequestInCloudGoogle)).click();

        return this;
    }

    public GooglePageNavigation changeTheFrameBeforeFillingInTheFields() {

        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='cloud-site']//iframe")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='myFrame']")));
        return this;
    }


    public EmailPageNavigation createRequest(String emailGeneratorURL) {
        buttonAddToEstimate.click();
        return new EmailPageNavigation(driver, emailGeneratorURL);
    }

    public EmailPageNavigation sendEmail() {

        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='cloud-site']//iframe")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='myFrame']")));

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(buttonEmailEstimate));

        String[] getCostFromCloudGoogle = costText.getText().split(" ");
        estimatedCost = getCostFromCloudGoogle[4];

        buttonEmailEstimate.click();

        scrollDownTillElementAppeared(inputFieldEmail);
        inputFieldEmail.sendKeys(emailName);

        buttonSendEmail.click();

        driver.switchTo().window(tabs.get(1));

        return new EmailPageNavigation(driver, estimatedCost, emailName);
    }

    public GooglePageNavigation clickTheAddGPUButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(checkBoxAddGPUs));
        checkBoxAddGPUs.click();

        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        return this;
    }

    public GooglePageNavigation spanOptionHandling (String optionDescription, String textOption) {

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")));
        driver.findElement(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//*[@class='md-select-menu-container md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")));
        driver.findElement(By.xpath("*//*[@class='md-select-menu-container md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")).click();

        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        return this;
    }

    public GooglePageNavigation spanOptionHandlingForDatacenter(String optionDescription, String textOption) {

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")));
        driver.findElement(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//*[@class='md-select-menu-container cpc-region-select md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")));
        driver.findElement(By.xpath("*//*[@class='md-select-menu-container cpc-region-select md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")).click();

        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        return this;
    }

    private void scrollDownTillElementAppeared(WebElement optionDescription) {
        while (!optionDescription.isDisplayed()) {
            new Actions(driver).sendKeys(Keys.DOWN);
        }
    }

    public GooglePageNavigation inputFieldHandling(String handlingField) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(inputFieldNumberOfInstances));
        inputFieldNumberOfInstances.sendKeys(handlingField);

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        return this;
    }

}
