package core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Loads config.properties once and exposes typed getters.
 * Keeps env/browser/base-url decisions out of test code entirely.
 */
public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        // Load from the classpath, not a relative file path — this works
        // identically whether run from IntelliJ, `mvn test`, or a CI runner
        // where the JVM's working directory may not match the project root.
        try (InputStream is = ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (is == null) {
                throw new RuntimeException("config.properties not found on classpath — expected at src/test/resources/config.properties");
            }
            properties.load(is);
        } catch (IOException e) {
            throw new RuntimeException("Could not load config.properties", e);
        }
    }

    private ConfigReader() {
        // static utility, no instances
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Missing config key: " + key);
        }
        return value;
    }

    public static String get(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static String baseUrl() {
        return get("base.url");
    }

    public static String browser() {
        // System property wins (e.g. mvn test -Dbrowser=firefox) — this is what
        // lets testng.xml <parameter> tags and CI matrix builds override per-run
        // without editing the properties file.
        return System.getProperty("browser", get("browser", "chromium")); // chromium | firefox | webkit
    }

    public static boolean headless() {
        return Boolean.parseBoolean(get("headless", "true"));
    }

    public static int slowMo() {
        return Integer.parseInt(get("slowmo.ms", "0"));
    }

    public static String apiBaseUrl() {
        return get("api.base.url", baseUrl());
    }
}