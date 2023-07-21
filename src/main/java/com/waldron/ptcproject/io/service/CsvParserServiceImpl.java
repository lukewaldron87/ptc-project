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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CsvParserServiceImpl implements CsvParserService{

    private Logger logger = LoggerFactory.getLogger(LoggingController.class);

    @Autowired
    private PropertyFileReaderService propertyFileReaderService;

    @Override
    public List<Task> readTasksFromCsv(){

        // get file name from propertyFileReaderService.readPropertyFromFile
        String fileName = propertyFileReaderService.readPropertyFromFile(
                CsvConstants.PROPERTY_FILE_NAME.toString(),
                CsvConstants.PROPERTY_NAME.toString());

        return readTasksFromFile(fileName);
    }

    private List<Task> readTasksFromFile(String fileName) {

        // try-with-resources declare BufferedReader to be used in try black
        try (BufferedReader br = getBufferedReaderFroFile(fileName)) {

            return br.lines()
                    .map(line -> CsvUtil.lineToTask(line))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //todo how to mock this so not dependent on file
    private BufferedReader getBufferedReaderFroFile(String fileName) {
        // create io stream from csv file
        InputStream resourceAsStream = this.getClass().getResourceAsStream(CsvConstants.RESOURCE_PATH + fileName);
        InputStreamReader streamReader = new InputStreamReader(resourceAsStream);
        return new BufferedReader(streamReader);
    }

    @Override
    public void appendToCsvFile(List<Task> tasks, String pathToFile) {

        //todo validate pathToFile

        String fileName = propertyFileReaderService.readPropertyFromFile(
                CsvConstants.PROPERTY_FILE_NAME.toString(),
                CsvConstants.PROPERTY_NAME.toString());

        try {
            //todo move to static util so I can mock it
            BufferedWriter writer = new BufferedWriter(new FileWriter(pathToFile+fileName, true));
            for(Task task: tasks){
                String line = CsvUtil.taskToLine(task);
                writer.append(line);
            }

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
