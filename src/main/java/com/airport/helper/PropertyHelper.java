package com.airport.helper;

import com.airport.logger.ApplicationLogger;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Sanyog Varshney
 * @since 1.0
 * @version 1.0
 * @implNote utility class
 */
public class PropertyHelper {
    private static final Properties properties = new Properties();
    private static final String CLASSNAME = PropertyHelper.class.getName();
    private static final ApplicationLogger logger = new ApplicationLogger();

    private PropertyHelper() {
        logger.debug(CLASSNAME, "propertyhelper()","constructure instansiated");
    }
    static {
        try {
            properties.load(PropertyHelper.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties"));
        }catch(IOException e){
            logger.error("Some System Exception occured", e);
        }
    }
    public static String getProperty(String key){
        if(properties.containsKey(key)) {
            return properties.getProperty(key);
        }else{
            return "";
        }
    }
}
