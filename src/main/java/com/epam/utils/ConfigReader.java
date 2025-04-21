package com.epam.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties properties;

    static{
        try(FileInputStream fis = new FileInputStream("src/main/resources/config.properties")){
            properties = new Properties();
            properties.load(fis);
            System.out.println("Config loaded successfully");
        }catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key){
        return System.getProperty(key, properties.getProperty(key));
    }
}
