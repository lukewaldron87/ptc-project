package com.waldron.ptcproject.service;

import com.waldron.ptcproject.entity.Task;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface TaskService {

    Task updateTask(Long id, Task task);

    Task saveTask(Task taskFromController);

    List<Task> getAllTask();
}
