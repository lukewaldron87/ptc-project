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
        String csvLine = description+","+priority+"\n";

        Task task = CsvUtil.lineToTask(csvLine);

        assertInstanceOf(Task.class, task);
    }

    @Test
    public void lineToTask_shouldReturnDescription_whenDescriptionInFirstColumn(){
        String description = "description of task";
        Long priority = 1l;
        String csvLine = description+","+priority+"\n";

        Task task = CsvUtil.lineToTask(csvLine);

        assertEquals(description, task.getDescription());
    }

    @Test
    public void lineToTask_shouldReturnPriority_whenPriorityInSecondColumn(){
        String description = "description of task";
        Long priority = 1l;
        String csvLine = description+","+priority+"\n";

        Task task = CsvUtil.lineToTask(csvLine);

        assertEquals(priority, task.getPriority());
    }

    @Test
    public void lineToTask_shouldNotReturnRowSeparator_whenLoneContainsMoreThanOneColumn(){
        String rowSeparator = ",";
        String description = "description of task";
        Long priority = 1l;
        String csvLine = description+rowSeparator+priority+"\n";

        Task task = CsvUtil.lineToTask(csvLine);

        assertFalse(task.getDescription().contains(rowSeparator));
    }

    @Test
    public void lineToTask_shouldNotThrowException_whenLineContainsNewline(){
        String description = "description of task";
        Long priority = 1l;
        String csvLine = description+","+priority+"\n";

        assertDoesNotThrow(() -> {
            CsvUtil.lineToTask(csvLine);
        });
    }

}