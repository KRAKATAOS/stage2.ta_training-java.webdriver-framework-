package training.stage2.framework.service;

import java.util.Locale;
import java.util.ResourceBundle;

public class TestingSetReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"), Locale.ENGLISH);

    public static String getTestingSet(String key) {
        return resourceBundle.getString(key);
    }
}
