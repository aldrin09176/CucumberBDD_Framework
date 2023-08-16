package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	private static final String MAIN_PROPERTY_PATH = "./src/test/resources/config/config.properties";
	
	private static String getProperty(String key, String path) {
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream(path));
		} catch(IOException e) {
			System.out.println("Could not read property " + key + " from " + path + ". Error: " + e.getMessage());
			return null;
		}
		return prop.getProperty(key);
	}
	
	public static String getBrowser() {
		return getProperty("browser",MAIN_PROPERTY_PATH);
	}
	
	public static String getUrl() {
		return getProperty("url",MAIN_PROPERTY_PATH);
	}
	
	
	
	
	
	
	
}
