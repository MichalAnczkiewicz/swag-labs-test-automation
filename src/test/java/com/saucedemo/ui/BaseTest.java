package com.saucedemo.ui;

import com.saucedemo.core.serviceConfig.JsonToEnvModelParser;
import com.saucedemo.core.serviceConfig.envJsonModel.EnvModel;

import static com.saucedemo.core.browserConfig.ViewModeResolver.returnViewType;
import static com.saucedemo.core.browserConfig.ViewModeTypes.MOBILE;

public class BaseTest {

    protected final boolean isMobile = MOBILE.equals(returnViewType());
    private static final EnvModel envModel = new JsonToEnvModelParser()
            .getEnvModel();

    public static final String SERVICE_URL = getServiceUrl();
    public static final String SERVICE_USERNAME = getServiceUsername();
    public static final String SERVICE_PASSWORD = getServicePassword();

    private static String getServiceUrl() {

        return envModel.getUrl();
    }

    private static String getServiceUsername() {

        return envModel.getUsername();
    }

    private static String getServicePassword() {

        return envModel.getPassword();
    }
}
