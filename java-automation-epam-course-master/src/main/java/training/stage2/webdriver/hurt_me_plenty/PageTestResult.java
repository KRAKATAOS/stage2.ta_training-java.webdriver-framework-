package training.stage2.webdriver.hurt_me_plenty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Arrays;



public class PageTestResult extends AbstractPage {

    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[1]")
    private WebElement regionDataCenter ;

    @FindBy(xpath = "//*[@id='compute']/md-list/md-list-item[4]")
    private WebElement formClassType ;

    @FindBy(xpath = " //*[@id='compute']/md-list/md-list-item[5]/div[1]")
    private WebElement formInstanceType ;

    @FindBy(xpath = " //*[@id='compute']/md-list/md-list-item[3]")
    private WebElement formUsage ;

    @FindBy(xpath = " //*[@id='compute']/md-list/md-list-item[8]/div[1]")
    private WebElement formSSD ;

    public PageTestResult(WebDriver driver) {
        super(driver);
    }

    public String  checkVMClass() {
        String[] VMClass= formClassType.getText().split(" ");
        return VMClass[2];
    }

    public String checkInstanceType() {
        String[] instance = formInstanceType.getText().split(" ");
        return instance[2].substring(0,13).trim();
    }

    public String  checkRegion() {
        String[] region = regionDataCenter.getText().split(" ");
        return region[1];
    }

    public String checkSSD() {
        String[] capacity = formSSD.getText().split(":");
        capacity = capacity[1].split(" ");
        return capacity[1].trim();
    }

    public String checkUsage() {
        String[] usage = formUsage.getText().split(":");
        return usage[1].trim();
    }

}
