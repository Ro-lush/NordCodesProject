package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {

    private static final Properties properties;

    static {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/test/resources/config.properties"));
        } catch (IOException e) {
            // Используем значения по умолчанию
            properties.setProperty("base.url", "http://localhost:8080");
            properties.setProperty("api.key", "qazWSXedc");
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getApiKey() {
        return properties.getProperty("api.key");
    }

}

