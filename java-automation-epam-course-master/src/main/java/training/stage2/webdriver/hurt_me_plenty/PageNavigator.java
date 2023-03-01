package training.stage2.webdriver.hurt_me_plenty;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PageNavigator extends AbstractPage {
    Actions actions = new Actions(driver);

    @FindBy(xpath = "//*[@class='devsite-search-field devsite-search-query']")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@class='gsc-results gsc-webResult']")
    private WebElement searchResultField;

    @FindBy(xpath = "//*[@class='compute-engine-block']")
    private WebElement elementOnCalculatorPage;

    @FindBy(xpath = "//md-input-container//label[contains(text(),'Number of instances')]/../input")
    private WebElement inputFieldNumberOfInstances;

    @FindBy(xpath = "//*[@id='select_value_label_91']/span[1]")
    private WebElement fieldOfDataCenter;

    @FindBy(xpath = "//*[@id='select_option_231']")
    private WebElement checkBoxOfDataCenter;

    @FindBy(xpath = "//*[@ng-model='listingCtrl.computeServer.addGPUs']//*[@class='md-container md-ink-ripple']")
    private WebElement checkBoxAddGPUs;

    @FindBy(xpath = "//label[text()='Local SSD']/../md-select")
    private WebElement localSSD;

    @FindBy(xpath = "//*[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']")
    private WebElement buttonAddToEstimate;

    public PageNavigator(WebDriver driver) {
        super(driver);
    }

    public PageNavigator openPage(String cloudGoogleUrl) {
        driver.manage().window().maximize();
        driver.get(cloudGoogleUrl);
        return this;

    }

    public PageNavigator searchElementAndClick(String sendRequestInCloudGoogle) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchButton));
        searchButton.click();
        searchButton.sendKeys(sendRequestInCloudGoogle);
        searchButton.sendKeys(Keys.RETURN);

        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(searchResultField));
        searchResultField.findElement(By.linkText(sendRequestInCloudGoogle)).click();

        return this;
    }

    public PageNavigator changeTheFrameBeforeFillingInTheFields() {

        driver.switchTo().frame(driver.findElement(By.xpath("//*[@id='cloud-site']//iframe")));
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='myFrame']")));
        return this;
    }

    public void createRequest() {
        buttonAddToEstimate.click();
    }

    public PageNavigator clickTheAddGPUButton() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(checkBoxAddGPUs));
        checkBoxAddGPUs.click();

        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        return this;
    }


    public PageNavigator spanOptionHandling(String optionDescription, String textOption) {

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")));
        driver.findElement(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//*[@class='md-select-menu-container md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")));
        driver.findElement(By.xpath("*//*[@class='md-select-menu-container md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")).click();

        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        return this;
    }
    public PageNavigator spanOptionHandlingForDatacenter(String optionDescription, String textOption) {

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")));
        driver.findElement(By.xpath("*//label[text()='" + optionDescription + "']/../md-select")).click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("*//*[@class='md-select-menu-container cpc-region-select md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")));
        driver.findElement(By.xpath("*//*[@class='md-select-menu-container cpc-region-select md-active md-clickable']//.//*[contains(text(),'" + textOption + "')]")).click();

        driver.manage().timeouts().implicitlyWait(200, TimeUnit.MILLISECONDS);
        return this;
    }

    public PageNavigator inputFieldHandling(String handlingField ) {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(inputFieldNumberOfInstances));
        inputFieldNumberOfInstances.sendKeys(handlingField);

        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
        return this;
    }

//    public PageNavigator fillFieldOfDataCenter() {
//        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(fieldOfDataCenter));
//        fieldOfDataCenter.click();
//        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(checkBoxOfDataCenter));
//        checkBoxOfDataCenter.click();
//
//        driver.manage().timeouts().implicitlyWait(100, TimeUnit.MILLISECONDS);
//        return this;
//    }

    public PageNavigator scrollDownTillElementAppeared() {
        while (!localSSD.isDisplayed()) {
            new Actions(driver).sendKeys(Keys.DOWN);
        }
        return this;
    }
}
