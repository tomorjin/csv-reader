package crisp.assignment.csvimporter.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static Properties properties;
    protected static final Object LOCK = new Object();
    protected static boolean isLoadingConfig;

    public static void loadConfig(String filePath) throws IOException {
        synchronized (LOCK) {
            properties = new Properties();
            isLoadingConfig = true;
            properties.load(new FileInputStream(filePath));
            isLoadingConfig = false;
        }
    }

    public static String getString(String key) {
        return properties.getProperty(key);
    }

    public static Integer getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }

    public static Double getDouble(String key) {
        return Double.parseDouble(properties.getProperty(key));
    }

    public static Float getFloat(String key) {
        return Float.parseFloat(properties.getProperty(key));
    }

    public static Long getLong(String key) {
        return Long.parseLong(properties.getProperty(key));
    }

    public static Properties getProperties(String... names) {
        Properties props = new Properties();
        Object value;
        for (String name : names) {
            if ((value = properties.get(name)) != null) {
                props.put(name, value);
            }
        }
        return props;
    }
}
