package com.moneytapp.webuitests.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by u.yahyoev on 21.12.2015.
 */
public class Config {

    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static Config instance;
    private Properties properties;

    public Config() {
        logger.info("Initializing configuration....");
        properties = new Properties();
        try {
            properties.load(getClass().getResourceAsStream("/test.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException("test.properties file is not found");
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            synchronized (Config.class) {
                if (instance == null) {
                    instance = new Config();
                }
            }
        }
        return instance;
    }

    public String getProperty(String name) {
        return properties.getProperty(name);
    }
}
