package com.saucedemo.helpers.interfaces;

public interface TestHelpersWeb {

    void openWebsiteAndAcceptPP(String url);

    default int initIndex(int listSize) {

        return (int) (Math.random() * (listSize));
    }
}
