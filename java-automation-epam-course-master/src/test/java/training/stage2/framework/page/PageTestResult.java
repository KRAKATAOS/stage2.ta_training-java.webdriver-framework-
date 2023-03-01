//package training.stage2.framework.page;
//
//import org.openqa.selenium.WebDriver;
//
//
//public class PageTestResult extends AbstractPage {
//
//    public static Boolean result;
//    private String estimatedCost;
//    private String costValueFromEmail;
//
//    public PageTestResult(WebDriver driver, String estimatedCost, String costValueFromEmail) {
//        super(driver);
//        this.estimatedCost = estimatedCost;
//        this.costValueFromEmail = costValueFromEmail;
//        result = checkEmail();
//    }
//
//    public boolean checkEmail() {
//        return costValueFromEmail.contains(estimatedCost);
//    }
//
//
//}
