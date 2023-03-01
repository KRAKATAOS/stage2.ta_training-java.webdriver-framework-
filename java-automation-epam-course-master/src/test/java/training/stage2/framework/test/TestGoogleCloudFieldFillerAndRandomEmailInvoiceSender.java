package training.stage2.framework.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import training.stage2.framework.page.EmailPageNavigation;
import training.stage2.framework.page.GooglePageNavigation;
import training.stage2.framework.service.TestingSetCreator;


public class TestGoogleCloudFieldFillerAndRandomEmailInvoiceSender extends CommonConditions {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    private static final String SEARCH_REQUEST = "Google Cloud Platform Pricing Calculator";
    private static final String EMAIL_GENERATOR_URL = "https://yopmail.com/ru/";

    @Test
    public void fillingInTheSiteFields() {
        new GooglePageNavigation(driver)
                .openPage(HOMEPAGE_URL)
                .searchElementAndClick(SEARCH_REQUEST)
                .changeTheFrameBeforeFillingInTheFields()
                .inputFieldHandling(TestingSetCreator.withTestingSetFromProperty().getFormNumberOfInstance())
                .spanOptionHandling("Operating System / Software",TestingSetCreator.withTestingSetFromProperty().getFormOsType())
                .spanOptionHandling("Provisioning model", TestingSetCreator.withTestingSetFromProperty().getFormClassType())
                .spanOptionHandling("Series",TestingSetCreator.withTestingSetFromProperty().getFormInstanceSeries())
                .spanOptionHandling("Machine type", TestingSetCreator.withTestingSetFromProperty().getFormInstanceType())
                .clickTheAddGPUButton()
                .spanOptionHandling("GPU type",TestingSetCreator.withTestingSetFromProperty().getFormGpuType())
                .spanOptionHandling("Number of GPUs",TestingSetCreator.withTestingSetFromProperty().getFormGpuNumber())
                .spanOptionHandling("Local SSD",TestingSetCreator.withTestingSetFromProperty().getFormSsdCapacity() )
                .spanOptionHandlingForDatacenter("Datacenter location",TestingSetCreator.withTestingSetFromProperty().getFormLocation())
                .spanOptionHandling("Committed usage",TestingSetCreator.withTestingSetFromProperty().getFormUsage())
                .createRequest(EMAIL_GENERATOR_URL)
                .openEmailSiteAndTakeEmailName()
                .sendEmail();
        Assert.assertEquals(GooglePageNavigation.estimatedCost, EmailPageNavigation.costValueFromEmail);

    }
}