package com.waldron.ptcproject.io;

import com.waldron.ptcproject.entity.Task;

public class CsvUtil {

    public static Task lineToTask(String line){
        line = line.trim();
        String[] columns = line.split(CsvConstants.ROW_SEPARATOR.toString());
        return Task.builder()
                .description(columns[0])
                .priority(Long.parseLong(columns[1])).build();
    }

    public static String taskToLine(Task task){

        StringBuilder csvLineBuilder = new StringBuilder();
        csvLineBuilder.append(task.getDescription());
        csvLineBuilder.append(CsvConstants.ROW_SEPARATOR);
        csvLineBuilder.append(task.getPriority());
        csvLineBuilder.append(CsvConstants.NEW_LINE);

        return csvLineBuilder.toString();
    }
}
