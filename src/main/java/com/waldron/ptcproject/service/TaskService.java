package com.waldron.ptcproject.service;

import com.waldron.ptcproject.entity.Task;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;

public interface TaskService {

    public Task updateTask(Long id, Task task);
}
