package com.waldron.ptcproject.io.service;

import com.waldron.ptcproject.entity.Task;

import java.util.ArrayList;
import java.util.List;

public interface CsvParserService {

    // read all lines
    List<Task> readTasksFromCsv();

    //write list to csv

    // read 1 line
}
