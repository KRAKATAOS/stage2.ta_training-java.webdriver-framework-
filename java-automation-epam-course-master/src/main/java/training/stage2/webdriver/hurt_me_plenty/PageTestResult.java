package training.stage2.webdriver.hurt_me_plenty;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;


public class PageTestResult extends AbstractPage {

    private Boolean result;

    public PageTestResult(WebDriver driver) {
        super(driver);
    }

//    public Boolean checkVMClass() {
//        String classType = PageNavigator.getFormClassType().toLowerCase();
//        checkResult(classType);
//        return result;
//    }
    public Boolean checkVMClass() {
        String[] instance = PageNavigator.getFormClassType().split(" ");
        System.out.println("Provisioning model: " + instance[0]);
        checkResult(instance[0]);
        return result;
    }


    public Boolean checkInstanceType() {
        String[] instance = PageNavigator.getFormInstanceType().split(" ");
        System.out.println("Instance type: " + instance[0]);
        checkResult(instance[0]);
        return result;
    }

    public Boolean checkRegion() {
        String[] region = PageNavigator.getFormLocation().split(" ");
        System.out.println("Region: " + region[0]);
        checkResult(region[0]);
        return result;
    }

    public Boolean checkSSD() {
        String[] capacity = PageNavigator.getFormSsdCapacity().split(" ");
        System.out.println("capacity: " + capacity[0]);
        checkResult(capacity[0]);
        return result;
    }

    public Boolean checkUsage() {
        checkResult(PageNavigator.getFormUsage());
        return result;
    }

    private void checkResult(String textToCompare) {

        List<WebElement> listOfWebElement = driver.findElements(By.xpath("//*[@class='md-list-item-text ng-binding']"));

        for (WebElement list : listOfWebElement) {
            if (list.getText().contains(textToCompare)) {
                System.out.println("PASSED: [" + list.getText() + "] contains: " + textToCompare);
                result = true;
                return;
            }
        }
        result = false;
        return;
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
