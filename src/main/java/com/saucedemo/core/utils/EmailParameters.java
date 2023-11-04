package com.saucedemo.core.utils;

public class EmailParameters {

    public static final String SENDER = "testyautomatycznekbh@gmail.com";
    public static final String SENDER_NAME = "Test automation result";
    public static final String EMAIL_RECEIVER_ENV = "EMAIL_RECEIVER";
    public static final String RECEIVER = getReceiver();
    public static final String EMAIL_PASSWORD_ENV = "EMAIL_PASSWORD";
    public static final String EMAIL_PASSWORD = getEmailPassword();

    private static String getReceiver() {

        return System.getProperty(EMAIL_RECEIVER_ENV) != null ? System.getProperty(EMAIL_RECEIVER_ENV) : "testyautomatycznekbh@gmail.com";
    }

    private static String getEmailPassword() {

        return System.getProperty(EMAIL_PASSWORD_ENV) != null ? System.getProperty(EMAIL_PASSWORD_ENV) : null;
    }
}
