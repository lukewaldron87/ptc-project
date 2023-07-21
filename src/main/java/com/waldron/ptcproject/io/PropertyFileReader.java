package com.waldron.ptcproject.io;

import com.waldron.ptcproject.controller.LoggingController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.util.Properties;

public class PropertyFileReader {

    private Logger logger = LoggerFactory.getLogger(LoggingController.class);
    private static String DEFAULT_FILE_NAME = "default.csv";

    //cvs util (string to event) and (event to string) maybe change to task
    //csvio readLint(id) readAll write
    public String readPropertyFromFile(String fileName, String propertyName)  {

        // In the assessment I could not find the file in the test.
        // Here I know it's in source/resources

        ClassPathResource classPathResource = new ClassPathResource(fileName);
        Properties appProps = new Properties();

        try {
            appProps.load(classPathResource.getInputStream());

            String appVersion = appProps.getProperty(propertyName);
            if(appVersion != null){
                return appVersion;
            }else {
                return DEFAULT_FILE_NAME;
            }

        } catch (Exception e) {
            logger.warn("Exception when loading file "+fileName);
            logger.warn(e.getMessage());
            return DEFAULT_FILE_NAME;
        }

    }
}