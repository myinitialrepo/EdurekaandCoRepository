package com.selenium.util;

import com.selenium.exceptions.IllegalValueException;
import com.selenium.exceptions.PropertyLoadException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {

    private static final Properties PROPERTIES;

    private PropertyUtil(){}

    static {
        PROPERTIES = loadProperties();
    }

    private static Properties loadProperties() {
        String file = "data.properties";
        try(InputStream inputStream = PropertyUtil.class.getClassLoader().getResourceAsStream(file)){
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch(IOException e){
            throw new PropertyLoadException("error while loading properties from file: " + file);
        }
    }

    public static String getProperty(String key){
        return (key == null || key.isEmpty()) ? key : (String) PROPERTIES.get(key);
    }

    public static int getInt(String key) {
        String value = getProperty(key);
        if(value == null || value.isEmpty())
            throw new IllegalValueException("no property/value found with the key:"+key);
        return Integer.parseInt(value);
    }

}
