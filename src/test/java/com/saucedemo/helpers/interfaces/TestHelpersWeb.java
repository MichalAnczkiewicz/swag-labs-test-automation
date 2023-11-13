package com.saucedemo.helpers.interfaces;

public interface TestHelpersWeb {

    void openWebsite(String url);

    default int initIndex(int listSize) {

        return (int) (Math.random() * (listSize));
    }
}
