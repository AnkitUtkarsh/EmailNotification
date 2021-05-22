package com.email;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ResourceConfig {
    private static final Logger log= LogManager.getLogger(ResourceConfig.class);

    private static Properties configproperties = null;

    private static FileReader reader;

    public static String getString(String key) {
        try {
            return configproperties.getProperty(key);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return "!" + key +"!";
    }

    public static void setResourceConfig(String configFile) throws FileNotFoundException, IOException{
        configproperties = new Properties();
        reader = new FileReader(configFile);
        configproperties.load(reader);

    }
    
}
