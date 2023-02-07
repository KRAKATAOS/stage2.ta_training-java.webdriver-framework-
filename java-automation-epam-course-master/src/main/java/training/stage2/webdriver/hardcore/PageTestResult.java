package training.stage2.webdriver.hardcore;

import org.openqa.selenium.WebDriver;


public class PageTestResult extends AbstractPage {

    public static Boolean result;
    private String estimatedCost;
    private String costValueFromEmail;

    public PageTestResult(WebDriver driver, String estimatedCost, String costValueFromEmail) {
        super(driver);
        this.estimatedCost = estimatedCost;
        this.costValueFromEmail = costValueFromEmail;
        result = checkEmail();
        System.out.println("final result: " + result);
    }

    public boolean checkEmail() {
        System.out.println("estimatedCost: " + estimatedCost);
        System.out.println("costValueFromEmail: " + costValueFromEmail);
        System.out.println("result of comparision: " + costValueFromEmail.contains(estimatedCost));
        return costValueFromEmail.contains(estimatedCost);
    }

    @Override
    protected AbstractPage openPage() {
        return null;
    }
}
