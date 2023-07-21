package com.waldron.ptcproject.io;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PropertyFileReaderTest {
    private PropertyFileReader propertyFileReader = new PropertyFileReader();

    @Test
    public void readFile_shouldReturnString_whenApplicationFileExists(){
        String fileName = "application.properties";
        String propertyName = "file.name";
        String expectedFileName = "events.csv";

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