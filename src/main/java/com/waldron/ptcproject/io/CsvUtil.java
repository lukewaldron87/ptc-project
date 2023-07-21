package com.waldron.ptcproject.io;

import com.waldron.ptcproject.entity.Task;

public class CsvUtil {

    private static final String ROW_SEPARATOR = ",";

    public static Task  lineToTask(String line){
        line = line.trim();
        String[] columns = line.split(ROW_SEPARATOR);
        return Task.builder()
                .description(columns[0])
                .priority(Long.parseLong(columns[1])).build();
    }
}
