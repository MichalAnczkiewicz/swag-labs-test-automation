package com.saucedemo.core.browserConfig;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.saucedemo.core.browserConfig.ViewModeResolver.returnViewType;
import static com.saucedemo.core.browserConfig.ViewModeTypes.DESKTOP;
import static com.saucedemo.core.browserConfig.ViewModeTypes.MOBILE;

public class ViewModeConfig {

    private static final Logger LOGGER
            = LogManager.getLogger(ViewModeConfig.class);

    public static int[] setBrowserViewport() {

        if (MOBILE.equals(returnViewType())) {

            LOGGER.info("Mobile view mode choosen");
            return new int[]{375, 812};
        } else if (DESKTOP.equals(returnViewType())) {

            LOGGER.info("Desktop view mode choosen");
            return new int[]{1920, 1080};
        } else {

            LOGGER.info("Default (desktop) view mode choosen");
            return new int[]{1920, 1080};
        }
    }
}
