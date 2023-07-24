package com.waldron.ptcproject.service;

import com.waldron.ptcproject.entity.Task;
import com.waldron.ptcproject.exception.TaskNotFoundException;
import com.waldron.ptcproject.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void updateTask_shouldReturnTask(){

        when(taskRepository.findById(anyLong())).thenReturn(Optional.of(Task.builder().build()));

        Task task = taskService.updateTask(1l, Task.builder().build());

        assertInstanceOf(Task.class, task);
    }

    @Test
    public void updateTask_shouldThrowException_whenTaskDoesNotExist(){


        long taskId = 1l;
        TaskNotFoundException expectedException = new TaskNotFoundException("message");
        when(taskRepository.findById(taskId)).thenThrow(expectedException);

        Task task = Task.builder().id(taskId).build();

        TaskNotFoundException exception = assertThrows(TaskNotFoundException.class, () -> {
            taskService.updateTask(taskId, task);
        });

        assertEquals(expectedException, exception);
        assertEquals(expectedException.getMessage(), exception.getMessage());
    }

    @Test
    public void updateTask_shouldSaveTask_whenTaskExists(){

        long taskId = 1l;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(Task.builder().id(taskId).build()));

        Task taskFromController = Task.builder().id(taskId).build();

        taskService.updateTask(taskId, taskFromController);

        verify(taskRepository).save(taskFromController);
    }

    @Test
    public void updateTask_shouldAddIdToTask_whenTaskFoundInRepository(){

        long taskId = 1l;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(Task.builder().id(taskId).build()));

        Task taskFromController = Task.builder().build();

        taskService.updateTask(taskId, taskFromController);

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).save(taskCaptor.capture());
        assertEquals(taskId, taskCaptor.getValue().getId());

    }

}