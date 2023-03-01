package training.stage2.webdriver.hardcore;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class HomePageNavigator extends AbstractPage {
    String emailName;
    ArrayList<String> tabs;
    static String estimatedCost;

    Actions actions = new Actions(driver);
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
    // Fields for filling in data about the data center
    @FindBy(xpath = "//*[@id='select_value_label_90']/span[1]")
    private WebElement fieldOfDataCenter;
    @FindBy(xpath = "//*[@id='select_option_230']")
    private WebElement checkBoxOfDataCenter;

    public HomePageNavigator(WebDriver driver) {
        super(driver);
    }

    public HomePageNavigator(WebDriver driver, String emailName, ArrayList<String> tabs) {
        super(driver);
        this.emailName = emailName;
        this.tabs = tabs;
    }

    public HomePageNavigator openPage(String cloudGoogleUrl) {
        driver.manage().window().maximize();
        driver.get(cloudGoogleUrl);
        new WebDriverWait(driver, 10).until(CustomConditions.pageLoadCompleted());

        return this;
    }

    public HomePageNavigator searchElementAndClick(String sendRequestInCloudGoogle) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
        searchButton.sendKeys(sendRequestInCloudGoogle);
        searchButton.sendKeys(Keys.RETURN);

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchResultField));
        searchResultField.findElement(By.linkText(sendRequestInCloudGoogle)).click();

        return this;
    }

    public HomePageNavigator changeTheFrameBeforeFillingInTheFields() {

        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='cloud-site']//iframe")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='myFrame']")));
        return this;
    }

    public EmailPageNavigator createRequest(String emailGeneratorURL) {
        buttonAddToEstimate.click();
        return new EmailPageNavigator(driver, emailGeneratorURL);
    }

    public EmailPageNavigator sendEmail() {

        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='cloud-site']//iframe")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='myFrame']")));

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(buttonEmailEstimate));

        String[] getCostFromCloudGoogle = costText.getText().split(" ");
        estimatedCost = getCostFromCloudGoogle[4];

        buttonEmailEstimate.click();

        scrollDownTillElementAppearedInEmailGenerator();
        inputFieldEmail.sendKeys(emailName);

        buttonSendEmail.click();


        driver.switchTo().window(tabs.get(1));

        return new EmailPageNavigator(driver, estimatedCost, emailName);
    }


    public HomePageNavigator clickTheAddGPUButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(checkBoxAddGPUs));
        checkBoxAddGPUs.click();

        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        return this;
    }

    public HomePageNavigator spanOptionHandling (String optionDescription, String textOption) {

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")));
        driver.findElement(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//*[@class='md-select-menu-container md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")));
        driver.findElement(By.xpath("*//*[@class='md-select-menu-container md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")).click();

        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        return this;
    }
    public HomePageNavigator spanOptionHandlingForDatacenter(String optionDescription, String textOption) {

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")));
        driver.findElement(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//*[@class='md-select-menu-container cpc-region-select md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")));
        driver.findElement(By.xpath("*//*[@class='md-select-menu-container cpc-region-select md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")).click();

        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        return this;
    }

    public HomePageNavigator inputFieldHandling(String handlingField) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(inputFieldNumberOfInstances));
        inputFieldNumberOfInstances.sendKeys(handlingField);

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        return this;
    }

    public HomePageNavigator scrollDownTillElementAppeared() {
        while (!localSSD.isDisplayed()) {
            new Actions(driver).sendKeys(Keys.DOWN);
        }
        return this;
    }
    public void scrollDownTillElementAppearedInEmailGenerator () {
        while (!inputFieldEmail.isDisplayed()) {
            new Actions(driver).sendKeys(Keys.DOWN);
        }
    }
}
