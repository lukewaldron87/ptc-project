package com.waldron.ptcproject.controller;

import com.waldron.ptcproject.entity.Task;
import com.waldron.ptcproject.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Task> getTasks(){
        return taskService.getAllTask();
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody Task task){
        return taskService.updateTask(id, task);
    }
}
