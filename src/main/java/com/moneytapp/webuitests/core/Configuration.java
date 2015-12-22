package com.moneytapp.webuitests.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by u.yahyoev on 21.12.2015.
 */
public class Configuration {

    private static final Logger logger = LoggerFactory.getLogger(Configuration.class);
    private static Configuration instance;
    private Properties properties;

    public Configuration() {
        logger.info("Initializing configuration....");
        properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/test.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException("test.properties file is not found");
        }
    }

    public static Configuration getInstance() {
        if (instance == null) {
            synchronized (Configuration.class) {
                if (instance == null) {
                    instance = new Configuration();
                }
            }
        }
        return instance;
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
