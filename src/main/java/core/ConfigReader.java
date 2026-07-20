package core;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties = new Properties();
    private static final String CONFIG_PATH = "D:\\aakash\\playwright\\PriceComparision\\src\\main\\resources\\config";

    static{
        try(FileInputStream fis = new FileInputStream(CONFIG_PATH)) {
            properties.load(fis);
        } catch (IOException e){
            throw new RuntimeException("File not found at" + CONFIG_PATH, e);
        }
    }

    private ConfigReader(){
        //static utility, no instances
    }

    public static String get(String key) {
        String value = properties.getProperty(key);
        if(value == null) {
            throw new RuntimeException("Missing config key: " + key);
        }
        return value;
    }

    public static String get(String key, String defaultValue){
        return properties.getProperty(key,defaultValue);
    }
    public static String baseUrl() {
        return get("base.url");
    }

    public static String browser() {
        return System.getProperty("browser", get("browser", "chromium")); //chromium|firefox|webkit
    }

    public static boolean headless(){
        return Boolean.parseBoolean(get("headless","true"));
    }

    public static int slowMo(){
        return Integer.parseInt(get("slowmo.ms", "0"));
    }

    public static String apiBaseUrl() {
        return get("api.base.url", baseUrl());
    }
}
