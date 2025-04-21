package com.epam.utils;

public class TestConfig {
    public static void main(String[] args) {
        System.out.println("API URL: " + ConfigReader.getProperty("api.url"));
        System.out.println("Username: " + ConfigReader.getProperty("api.username"));
        System.out.println("Password: " + ConfigReader.getProperty("api.password"));
    }
}
