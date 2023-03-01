package training.stage2.webdriver.hurt_me_plenty;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestGoogleCloudFieldFiller {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private static final String SEARCH_REQUEST = "Google Cloud Platform Pricing Calculator";

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

    protected WebDriver driver;

    @Test
    public void fillingInTheSiteFields() {
        new PageNavigator(driver)
                .openPage(HOMEPAGE_URL)
                .searchElementAndClick(SEARCH_REQUEST)
                .changeTheFrameBeforeFillingInTheFields()
                .inputFieldHandling(FORM_NUMBER_OF_INSTANCE)
                .spanOptionHandling("Operating System / Software",FORM_OS_TYPE)
                .spanOptionHandling("Provisioning model", FORM_CLASS_TYPE)
                .spanOptionHandling("Series", FORM_INSTANCE_SERIES)
                .spanOptionHandling("Machine type", FORM_INSTANCE_TYPE)
                .clickTheAddGPUButton()
                .spanOptionHandling("GPU type", FORM_GPU_TYPE)
                .spanOptionHandling("Number of GPUs", FORM_GPU_NUMBER)
                .scrollDownTillElementAppeared()
                .spanOptionHandling("Local SSD", FORM_SSD_CAPACITY)
                .spanOptionHandling("Committed usage", FORM_USAGE)
                .spanOptionHandlingForDatacenter("Datacenter location",FORM_LOCATION)
                .createRequest();
        PageTestResult testPage = new PageTestResult(driver);
        Assert.assertEquals(testPage.checkInstanceType(),FORM_INSTANCE_TYPE.substring(0,13));
        Assert.assertEquals(testPage.checkRegion(), FORM_LOCATION.substring(0,9));
        Assert.assertEquals(testPage.checkVMClass(), FORM_CLASS_TYPE );
        Assert.assertEquals(testPage.checkUsage(), FORM_USAGE );
        Assert.assertEquals(testPage.checkSSD(),FORM_SSD_CAPACITY.substring(0,5));
    }

    @BeforeMethod
    public void startBrowserDriver(){
        driver = new ChromeDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void afterTestCompleted() {
        driver.quit();
    }
}
