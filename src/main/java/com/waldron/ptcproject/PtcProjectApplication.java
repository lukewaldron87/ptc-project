package com.waldron.ptcproject;

import com.waldron.ptcproject.controller.LoggingController;
import com.waldron.ptcproject.entity.Task;
import com.waldron.ptcproject.io.service.CsvParserService;
import com.waldron.ptcproject.io.service.CsvParserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

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
		taskList.stream().forEach(task -> logger.info(task.toString()));

		csvParserService.appendToCsvFile(taskList, "C:\\Users\\Luke\\Documents\\PTC\\");
	}

}
