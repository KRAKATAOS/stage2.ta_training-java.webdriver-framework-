package training.stage2.framework.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import training.stage2.webdriver.hardcore.CustomConditions;


import java.util.ArrayList;

public class EmailPageNavigation extends AbstractPage {

    public static String costValueFromEmail;
    private final Logger logger = LogManager.getRootLogger();
    String emailName;
    String estimatedCost;
    String[] bodyText;
    private String urlName;

    @FindBy(xpath = "//*[@id='listeliens']/a[1]")
    private WebElement xpathForARandomAddress;

    @FindBy(xpath = "//*[@id='geny']")
    private WebElement xpathEmailNameField;

    @FindBy(xpath = "/html/body/div/div[2]/main/div/div[2]/div/div[1]/div[2]/button[2]")
    private WebElement xpathCheckMailButton ;

    @FindBy(xpath = "//*[@id='refresh']")
    private WebElement xpathCheckRefreshButton;

    @FindBy(xpath = "//*[@id='webmail']/div[1]/div/main/div[2]/div[3]")
    private WebElement fieldNewMail;

    @FindBy(xpath = "//*//h2")
    private WebElement emailBody;

    protected EmailPageNavigation(WebDriver driver, String urlName) {
        super(driver);
        this.urlName = urlName;
    }

    protected EmailPageNavigation(WebDriver driver, String estimatedCost, String emailName) {
        super(driver);
        this.emailName = emailName;
        this.estimatedCost = estimatedCost;
        costValueFromEmail = getCostValueFromEmail();
    }

    public String getCostValueFromEmail() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(xpathCheckMailButton));
        xpathCheckMailButton.click();
        xpathCheckRefreshButton.click();

        WebElement iframe = driver.findElement(By.xpath("//*[@id='ifmail']"));
        driver.switchTo().frame(iframe);

        bodyText = emailBody.getText().split(":");
        bodyText = bodyText[1].split(" ");
        costValueFromEmail = bodyText[2];

        return costValueFromEmail;
    }

    public GooglePageNavigation openEmailSiteAndTakeEmailName() {
        ((JavascriptExecutor) driver).executeScript("window.open();");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

        driver.switchTo().window(tabs.get(1));
        driver.get(urlName);

        new WebDriverWait(driver, 10).until(CustomConditions.pageLoadCompleted());

        xpathForARandomAddress.click();
        logger.info("Email generator site opened");

        emailName = xpathEmailNameField.getText();
        logger.info("Email has been generated");

        driver.switchTo().window(tabs.get(0));
        return new GooglePageNavigation(driver, emailName, tabs);
    }
}
