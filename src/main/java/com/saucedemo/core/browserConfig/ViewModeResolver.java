package com.saucedemo.core.browserConfig;

public class ViewModeResolver {

    private static final String VIEW_MODE_PROPERTY = "VIEW_MODE";

    public static String returnViewType() {

        return returnTrueIfMobileAndFalseIfDesktop() ? "mobile" : "desktop";
    }

    private static boolean returnTrueIfMobileAndFalseIfDesktop() {

        return System.getProperty(VIEW_MODE_PROPERTY) != null &&
                System.getProperty(VIEW_MODE_PROPERTY).contains("MOBILE");
    }
}
