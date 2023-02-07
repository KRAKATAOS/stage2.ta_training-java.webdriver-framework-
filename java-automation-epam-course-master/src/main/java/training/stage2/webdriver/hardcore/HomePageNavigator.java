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

    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private static final String SEARCH_REQUEST = "Google Cloud Platform Pricing Calculator";

    private static final String EMAIL_GENERATOR_URL = "https://yopmail.com/ru/";

    private static final String FORM_NUMBER_OF_INSTANCE = "4";
    private static final String FORM_OS_TYPE = "Free: Debian, CentOS, CoreOS, Ubuntu or BYOL (Bring Your Own License)";
    private static final String FORM_CLASS_TYPE = "Regular";
    private static final String FORM_INSTANCE_SERIES = "N1";
    private static final String FORM_INSTANCE_TYPE = "n1-standard-8 (vCPUs: 8, RAM: 30GB)";
    private static final String FORM_GPU_NUMBER = "1";
    private static final String FORM_GPU_TYPE = "NVIDIA Tesla P100";
    private static final String FORM_SSD_CAPACITY = "2x375 GB";
    private static final String FORM_LOCATION = "Frankfurt (europe-west3)";
    private static final String FORM_USAGE = "1 Year";

    String emailName;
    ArrayList<String> tabs;
    String estimatedCost;

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

    public HomePageNavigator openPage() {
        driver.manage().window().maximize();
        driver.get(HOMEPAGE_URL);

        new WebDriverWait(driver, 10).until(CustomConditions.pageLoadCompleted());

        return this;
    }

    public HomePageNavigator searchForElementAndClick() {

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();

        searchButton.sendKeys(SEARCH_REQUEST);
        searchButton.sendKeys(Keys.RETURN);

        new WebDriverWait(driver, 10).until(CustomConditions.pageLoadCompleted());

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchResultField));
        searchResultField.findElement(By.linkText(SEARCH_REQUEST)).click();

        return this;
    }

    public HomePageNavigator fillSiteForm() {

        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='cloud-site']//iframe")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='myFrame']")));

        inputFieldHandling(inputFieldNumberOfInstances, FORM_NUMBER_OF_INSTANCE);

        spanOptionHandling("Operating System / Software", FORM_OS_TYPE);
        spanOptionHandling("Provisioning model", FORM_CLASS_TYPE);
        spanOptionHandling("Series", FORM_INSTANCE_SERIES);
        spanOptionHandling("Machine type", FORM_INSTANCE_TYPE);

        checkBoxHandling(checkBoxAddGPUs);

        spanOptionHandling("GPU type", FORM_GPU_TYPE);
        spanOptionHandling("Number of GPUs", FORM_GPU_NUMBER);

        scrollDownTillElementAppeared(localSSD);

        spanOptionHandling("Local SSD", FORM_SSD_CAPACITY);
        fieldOfDataCenter(fieldOfDataCenter);
        checkBoxOfDataCenter(checkBoxOfDataCenter);
       // spanOptionHandling("Datacenter location", FORM_LOCATION);
        spanOptionHandling("Committed usage", FORM_USAGE);

        return this;
    }

    public EmailPageNavigator createRequest() {
        buttonAddToEstimate.click();
        return new EmailPageNavigator(driver, EMAIL_GENERATOR_URL);
    }

    public EmailPageNavigator sendEmail() {

        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='cloud-site']//iframe")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='myFrame']")));

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(buttonEmailEstimate));

        estimatedCost = getCost(costText.getText());

        buttonEmailEstimate.click();

        scrollDownTillElementAppeared(inputFieldEmail);
        inputFieldEmail.sendKeys(emailName);

        buttonSendEmail.click();


        driver.switchTo().window(tabs.get(1));

        return new EmailPageNavigator(driver, estimatedCost, emailName);
    }

    private String getCost(String text) {
        String[] fulltext = text.split(" ");
        return fulltext[4];
    }

    private void checkBoxHandling(WebElement checkBoxField) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(checkBoxField));
        checkBoxField.click();

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
    }

    private void spanOptionHandling(String optionDescription, String textOption) {

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")));
        driver.findElement(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//*[@class='md-select-menu-container md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")));
        driver.findElement(By.xpath("*//*[@class='md-select-menu-container md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")).click();

        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
    }

    private void inputFieldHandling(WebElement fieldXpath, String value) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(fieldXpath));
        fieldXpath.sendKeys(value);

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
    }
    private void fieldOfDataCenter(WebElement fieldXpath) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(fieldXpath));
        fieldXpath.click();

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
    }
    private void checkBoxOfDataCenter(WebElement fieldXpath) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(fieldXpath));
        fieldXpath.click();

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
    }

    private void scrollDownTillElementAppeared(WebElement optionDescription) {
        while (!optionDescription.isDisplayed()) {
            new Actions(driver).sendKeys(Keys.DOWN);
        }
    }
}
