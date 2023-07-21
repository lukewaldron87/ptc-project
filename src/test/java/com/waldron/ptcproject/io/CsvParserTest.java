package com.waldron.ptcproject.io;

import com.waldron.ptcproject.entity.Task;
import com.waldron.ptcproject.io.service.CsvParserServiceImpl;
import com.waldron.ptcproject.io.service.PropertyFileReaderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class CsvParserTest {

    @Mock
    private PropertyFileReaderService propertyFileReaderService;

    @InjectMocks
    private CsvParserServiceImpl csvParser;

    @Test
    public void readTasksFromCsv_shouldReturnListOfTasks(){
        String fileName = "tasks.csv";
        when(propertyFileReaderService.readPropertyFromFile(
                CsvConstants.PROPERTY_FILE_NAME.toString(),
                CsvConstants.PROPERTY_NAME.toString())).thenReturn(fileName);

        List<Task> taskList = csvParser.readTasksFromCsv();

        assertInstanceOf(Task.class, taskList.get(0));
    }

    @Test
    public void readTasksFromCsv_shouldCorrectListOfTasks_whenReadingFromFile(){

        String fileName = "tasks.csv";
        when(propertyFileReaderService.readPropertyFromFile(
                CsvConstants.PROPERTY_FILE_NAME.toString(),
                CsvConstants.PROPERTY_NAME.toString())).thenReturn(fileName);

        //todo MOCK lineToTask

        List<Task> taskList = csvParser.readTasksFromCsv();

        int taskListSize = taskList.size();
        assertEquals(7, taskListSize);
        assertEquals("description of task 1", taskList.get(0).getDescription());
        assertEquals(1l, taskList.get(0).getPriority());
        assertEquals("description of task 7", taskList.get(taskListSize-1).getDescription());
        assertEquals(7l, taskList.get(taskListSize-1).getPriority());
    }
}