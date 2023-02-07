package training.stage2.framework.service;

import training.stage2.framework.model.TestingSet;

public class TestingSetCreator {
    private static final String FORM_NUMBER_OF_INSTANCE = "form.number.of.instance";
    private static final String FORM_OS_TYPE = "form.os.type";
    private static final String FORM_CLASS_TYPE = "form.class.type";
    private static final String FORM_INSTANCE_SERIES = "form.instance.series";
    private static final String FORM_INSTANCE_TYPE = "form.instance.type";
    private static final String FORM_GPU_NUMBER = "form.gpu.number";
    private static final String FORM_GPU_TYPE = "form.gpu.type";
    private static final String FORM_SSD_CAPACITY = "form.ssd.capacity";
    private static final String FORM_LOCATION = "form.location";
    private static final String FORM_USAGE = "form.usage";

    public static TestingSet withTestingSetFromProperty() {
        return new TestingSet(TestingSetReader.getTestingSet(FORM_NUMBER_OF_INSTANCE),
                TestingSetReader.getTestingSet(FORM_OS_TYPE),
                TestingSetReader.getTestingSet(FORM_CLASS_TYPE),
                TestingSetReader.getTestingSet(FORM_INSTANCE_SERIES),
                TestingSetReader.getTestingSet(FORM_INSTANCE_TYPE),
                TestingSetReader.getTestingSet(FORM_GPU_NUMBER),
                TestingSetReader.getTestingSet(FORM_GPU_TYPE),
                TestingSetReader.getTestingSet(FORM_SSD_CAPACITY),
                TestingSetReader.getTestingSet(FORM_LOCATION),
                TestingSetReader.getTestingSet(FORM_USAGE));
    }

}


