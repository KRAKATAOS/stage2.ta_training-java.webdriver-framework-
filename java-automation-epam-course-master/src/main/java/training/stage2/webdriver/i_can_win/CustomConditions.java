package training.stage2.webdriver.i_can_win;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

public class CustomConditions {
    public static ExpectedCondition<Boolean> jQueryAJAXCompleted() {
        return driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
    }
}
