package com.waldron.ptcproject.service;

import com.waldron.ptcproject.entity.Task;
import com.waldron.ptcproject.exception.TaskNotFoundException;
import com.waldron.ptcproject.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task updateTask(Long id, Task task) {

        Optional<Task> existingTask = taskRepository.findById(id);

        if(!existingTask.isPresent()){
            throw new TaskNotFoundException("Task not found for ID");
        }

        task.setId(existingTask.get().getId());

        //todo check and map other fields

        taskRepository.save(task);

        return task;
    }
}
