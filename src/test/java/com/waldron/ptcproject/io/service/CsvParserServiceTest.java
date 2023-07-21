package com.waldron.ptcproject.io.service;

import com.waldron.ptcproject.entity.Task;
import com.waldron.ptcproject.io.CsvConstants;
import com.waldron.ptcproject.io.CsvUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@SpringBootTest
class CsvParserServiceTest {

    @Mock
    private PropertyFileReaderService propertyFileReaderService;

    @InjectMocks
    private CsvParserServiceImpl csvParser;

    private MockedStatic<CsvUtil> csvUtil;

    @BeforeEach
    void initialiseWorker() {
        csvUtil = mockStatic(CsvUtil.class);
    }

    @AfterEach
    public void close() {
        csvUtil.close();
    }

    @Test
    public void readTasksFromCsv_shouldReturnListOfTasks(){
        String fileName = "tasks.csv";
        when(propertyFileReaderService.readPropertyFromFile(
                CsvConstants.PROPERTY_FILE_NAME.toString(),
                CsvConstants.PROPERTY_NAME.toString())).thenReturn(fileName);

        String expectedDescription = "description of task";
        long expectedPriority = 1l;
        Task expectedTask = Task.builder()
                .description(expectedDescription)
                .priority(expectedPriority).build();
        when(CsvUtil.lineToTask(anyString())).thenReturn(expectedTask);

        List<Task> taskList = csvParser.readTasksFromCsv();

        assertInstanceOf(Task.class, taskList.get(0));
    }

    @Test
    public void readTasksFromCsv_shouldCorrectListOfTasks_whenReadingFromFile(){

        String fileName = "tasks.csv";
        when(propertyFileReaderService.readPropertyFromFile(
                CsvConstants.PROPERTY_FILE_NAME.toString(),
                CsvConstants.PROPERTY_NAME.toString())).thenReturn(fileName);

        String expectedDescription = "description of task";
        long expectedPriority = 1l;
        Task expectedTask = Task.builder()
                .description(expectedDescription)
                .priority(expectedPriority).build();
        when(CsvUtil.lineToTask(anyString())).thenReturn(expectedTask);

        List<Task> taskList = csvParser.readTasksFromCsv();

        int taskListSize = taskList.size();
        assertEquals(7, taskListSize);
        assertEquals(expectedDescription, taskList.get(0).getDescription());
        assertEquals(expectedPriority, taskList.get(0).getPriority());
        assertEquals(expectedDescription, taskList.get(taskListSize-1).getDescription());
        assertEquals(expectedPriority, taskList.get(taskListSize-1).getPriority());
    }

    //todo move getBufferedReaderFroFile to public util and mock BufferedReader
    /*@Test
    public void readTasksFromCsv_shouldThrowRuntimeException_ifErrorReadingLines(){
        String fileName = "tasks.csv";
        when(propertyFileReaderService.readPropertyFromFile(
                CsvConstants.PROPERTY_FILE_NAME.toString(),
                CsvConstants.PROPERTY_NAME.toString())).thenReturn(fileName);

        String expectedDescription = "description of task";
        long expectedPriority = 1l;
        Task expectedTask = Task.builder()
                .description(expectedDescription)
                .priority(expectedPriority).build();
        when(CsvUtil.lineToTask(anyString())).thenReturn(expectedTask);


        assertThrows(RuntimeException.class, () -> {
            csvParser.readTasksFromCsv();
        });
    }*/

    @Test
    public void appendToCsvFile(){

    }
}