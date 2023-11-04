package com.saucedemo.core.serviceConfig;

import com.google.gson.Gson;
import com.saucedemo.core.serviceConfig.envJsonModel.EnvModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class EnvImporter {

    private static final Logger LOGGER
            = LogManager.getLogger(EnvImporter.class);
    private final String fileLocation;

    public EnvImporter(String fileLocation) {

        this.fileLocation = fileLocation;
    }

    public EnvModel getEnvFromFile() {

        EnvModel envModel = new EnvModel();
        try {
            envModel = new Gson().fromJson(new FileReader(fileLocation), EnvModel.class);

        } catch (FileNotFoundException ex) {

            LOGGER.error("Couldn't find file with choosen testing env. \n" +
                    "Please make sure that env is present in /resources/envs/.");
        }

        return envModel;
    }
}
