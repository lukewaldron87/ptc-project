package com.waldron.ptcproject.io.service;

import com.waldron.ptcproject.controller.LoggingController;
import com.waldron.ptcproject.entity.Task;
import com.waldron.ptcproject.io.CsvConstants;
import com.waldron.ptcproject.io.CsvUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvParserServiceImpl implements CsvParserService{

    private Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    private PropertyFileReaderService propertyFileReaderService;

    @Override
    public List<Task> readTasksFromCsv(){
        List<Task> tasks = new ArrayList<>();

        // get file name from propertyFileReaderService.readPropertyFromFile
        String fileName = propertyFileReaderService.readPropertyFromFile(
                CsvConstants.PROPERTY_FILE_NAME.toString(),
                CsvConstants.PROPERTY_NAME.toString());

        // create io stream from csv file
        //todo how to mock this so not dependent on file
        try (BufferedReader br = getBufferedReaderFroFile(fileName)) {
            String line;
            while ((line = br.readLine()) != null) {
                // parse lines with lineToTask
                Task task = CsvUtil.lineToTask(line);
                // add lines to list
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // return list
        return tasks;
    }

    private BufferedReader getBufferedReaderFroFile(String fileName) {
        InputStream resourceAsStream = this.getClass().getResourceAsStream(CsvConstants.RESOURCE_PATH + fileName);
        return new BufferedReader(new InputStreamReader(resourceAsStream));
    }
}
