package com.saucedemo.core.serviceConfig;

import com.saucedemo.core.serviceConfig.envJsonModel.EnvModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonToEnvModelParser {

    private static final Logger LOGGER
            = LogManager.getLogger(JsonToEnvModelParser.class);
    private static final String RESOURCES_PATH = "src/main/resources/envs/";

    public String ENVIRONMENT_TYPE = System.getProperty("ENV");

    private String getEnvironment() {

        if (ENVIRONMENT_TYPE == null || ENVIRONMENT_TYPE.isEmpty()) {
            ENVIRONMENT_TYPE = "stage";
        }
        return ENVIRONMENT_TYPE;
    }

    public EnvModel getEnvModel() {

        EnvModel envModel = new EnvImporter(RESOURCES_PATH.concat(
                getEnvironment()).toLowerCase().concat(".json")).getEnvFromFile();

        if (envModel == null) {

            LOGGER.error("The config file is empty. \n Tests won't run.");
            return null;
        }

        return envModel;
    }
}
