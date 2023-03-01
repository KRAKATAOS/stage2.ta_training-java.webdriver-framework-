package training.stage2.webdriver.bring_it_on;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class PageTestResult extends AbstractPage {

    @FindBy(xpath = "//*[@class='bash']")
    private WebElement codeText;

    @FindBy(xpath = "//a[@href='/archive/bash']")
    private WebElement syntaxHighlight;

    @FindBy(xpath = "//div[@class='info-top']/h1")
    private WebElement titleText;

    public PageTestResult(WebDriver driver) {
        super(driver);
    }

    public String checkCodeText() {
        return codeText.getText();
    }

    public String checkSyntaxHighlight() {
        return syntaxHighlight.getText();
    }

    public String checkTitle() {
        return driver.findElement(By.tagName("h1")).getText();
    }


}
