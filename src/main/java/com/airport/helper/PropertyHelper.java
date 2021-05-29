package com.airport.helper;

import java.io.IOException;
import java.util.Properties;

public class PropertyHelper {
    private static final Properties properties = new Properties();

    private PropertyHelper(){

    }

    static {
        try {
            properties.load(PropertyHelper.class
                    .getClassLoader()
                    .getResourceAsStream("application.properties"));
        }catch(IOException e){
            e.printStackTrace();
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
