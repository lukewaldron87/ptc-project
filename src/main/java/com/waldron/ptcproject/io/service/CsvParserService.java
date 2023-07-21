package com.waldron.ptcproject.io.service;

import com.waldron.ptcproject.entity.Task;

import java.util.ArrayList;
import java.util.List;

public interface CsvParserService {

    List<Task> readTasksFromCsv();

    //todo write list to csv
    void appendToCsvFile(List<Task> tasks, String pathToFile);

    //todo read 1 line
}
