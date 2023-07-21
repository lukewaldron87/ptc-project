package com.waldron.ptcproject.io;

import com.waldron.ptcproject.entity.Task;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CsvUtilTest {


    @Test
    public void lineToTask_shouldReturnATask_whenPassedValidCsvLine(){
        String description = "description of task";
        Long priority = 1l;
        String csvLine = description+CsvConstants.ROW_SEPARATOR+priority+CsvConstants.NEW_LINE.toString();

        Task task = CsvUtil.lineToTask(csvLine);

        assertInstanceOf(Task.class, task);
    }

    @Test
    public void lineToTask_shouldReturnDescription_whenDescriptionInFirstColumn(){
        String description = "description of task";
        Long priority = 1l;
        String csvLine = description+CsvConstants.ROW_SEPARATOR+priority+CsvConstants.NEW_LINE.toString();

        Task task = CsvUtil.lineToTask(csvLine);

        assertEquals(description, task.getDescription());
    }

    @Test
    public void lineToTask_shouldReturnPriority_whenPriorityInSecondColumn(){
        String description = "description of task";
        Long priority = 1l;
        String csvLine = description+CsvConstants.ROW_SEPARATOR+priority+CsvConstants.NEW_LINE.toString();

        Task task = CsvUtil.lineToTask(csvLine);

        assertEquals(priority, task.getPriority());
    }

    @Test
    public void lineToTask_shouldNotReturnRowSeparator_whenLoneContainsMoreThanOneColumn(){
        String rowSeparator = CsvConstants.ROW_SEPARATOR.toString();
        String description = "description of task";
        Long priority = 1l;
        String csvLine = description+rowSeparator+priority+CsvConstants.NEW_LINE.toString();

        Task task = CsvUtil.lineToTask(csvLine);

        assertFalse(task.getDescription().contains(rowSeparator));
    }

    @Test
    public void lineToTask_shouldNotThrowException_whenLineContainsNewline(){
        String description = "description of task";
        Long priority = 1l;
        String csvLine = description+CsvConstants.ROW_SEPARATOR+priority+CsvConstants.NEW_LINE.toString();

        assertDoesNotThrow(() -> {
            CsvUtil.lineToTask(csvLine);
        });
    }

    @Test
    public void taskToLine_shouldReturnString_whenPassedTask(){
        Task task = Task.builder()
                .id(1l)
                .description("description of task")
                .priority(2l).build();

        String csvLine = CsvUtil.taskToLine(task);
        assertInstanceOf(String.class, csvLine);
    }

    @Test
    public void taskToLine_shouldReturnDescriptionInFirstColumn_whenTaskContainsDescription(){
        String expectedDescription = "description of task";
        Task task = Task.builder()
                .id(1l)
                .description(expectedDescription)
                .priority(2l).build();

        String csvLine = CsvUtil.taskToLine(task);

        String[] columns = csvLine.split(CsvConstants.ROW_SEPARATOR.toString());
        assertEquals(expectedDescription, columns[0]);
    }
    @Test
    public void taskToLine_shouldReturnPriorityInSecondColumn_whenTaskContainsPriority(){
        long priority = 2l;
        Task task = Task.builder()
                .id(1l)
                .description("description of task")
                .priority(priority).build();

        String csvLine = CsvUtil.taskToLine(task);

        String[] columns = csvLine.split(CsvConstants.ROW_SEPARATOR.toString());
        assertEquals(String.valueOf(priority), columns[1].trim());
    }

    @Test
    public void taskToLine_shouldCommaSeparatedString_whenMoreThanOneColumnReturned(){

        Task task = Task.builder()
                .id(1l)
                .description("description of task")
                .priority(2l).build();

        String csvLine = CsvUtil.taskToLine(task);

        assertTrue(csvLine.contains(CsvConstants.ROW_SEPARATOR.toString()));
    }

    @Test
    public void taskToLine_shouldReturnStringEndingInNewLine(){

        Task task = Task.builder()
                .id(1l)
                .description("description of task")
                .priority(2l).build();

        String csvLine = CsvUtil.taskToLine(task);

        assertTrue(csvLine.contains(CsvConstants.NEW_LINE.toString()));
    }

}