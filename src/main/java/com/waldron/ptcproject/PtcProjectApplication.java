package com.waldron.ptcproject;

import com.waldron.ptcproject.controller.LoggingController;
import com.waldron.ptcproject.entity.Task;
import com.waldron.ptcproject.io.service.CsvParserService;
import com.waldron.ptcproject.io.service.CsvParserServiceImpl;
import com.waldron.ptcproject.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@SpringBootApplication
public class PtcProjectApplication {

	private static Logger logger = LoggerFactory.getLogger(LoggingController.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(PtcProjectApplication.class, args);

		readAndWriteFile(applicationContext);
	}

	private static void readAndWriteFile(ConfigurableApplicationContext applicationContext) {
		CsvParserService csvParserService = applicationContext.getBean(CsvParserService.class);

		List<Task> taskList = csvParserService.readTasksFromCsv();

		saveTasks(applicationContext, taskList);


		csvParserService.appendToCsvFile(taskList, "C:\\Users\\Luke\\Documents\\PTC\\");
	}

	private static void saveTasks(ConfigurableApplicationContext applicationContext, List<Task> taskList) {
		TaskService taskService = applicationContext.getBean(TaskService.class);
		//save all
		taskList.stream()
				.forEach(task -> taskService.saveTask(task));

		//get all and check they saved
		taskService.getAllTask().stream()
				.forEach(task -> logger.info(task.toString()));
	}

}
