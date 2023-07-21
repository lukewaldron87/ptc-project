package com.waldron.ptcproject.io.service;

import com.waldron.ptcproject.io.service.PropertyFileReaderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PropertyFileReaderServiceImplTest {

    @Autowired
    private PropertyFileReaderServiceImpl propertyFileReader;

    @Test
    public void readFile_shouldReturnString_whenApplicationFileExists(){
        String fileName = "application.properties";
        String propertyName = "file.name";
        String expectedFileName = "tasks.csv";

        String returnedFileName = propertyFileReader.readPropertyFromFile(fileName, propertyName);

        assertEquals(expectedFileName, returnedFileName);
    }

    @Test
    public void readFile_shouldReturnDefaultFileName_whenFileNotFound(){
        String fileName = "does not exist";
        String propertyName = "file.name";
        String expectedFileName = "default.csv";

        String returnedFileName = propertyFileReader.readPropertyFromFile(fileName, propertyName);

        assertEquals(expectedFileName, returnedFileName);
    }

    @Test
    public void readFile_shouldReturnDefaultFileName_whenPropertyNotFound(){
        String fileName = "application.properties";
        String propertyName = "does not exist";
        String expectedFileName = "default.csv";

        String returnedFileName = propertyFileReader.readPropertyFromFile(fileName, propertyName);

        assertEquals(expectedFileName, returnedFileName);
    }
}