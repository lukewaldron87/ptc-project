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

import java.util.Arrays;
import java.util.List;
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

    @Test
    public void saveTask_shouldPassTaskToRepository(){

        Task taskFromController = Task.builder().build();

        taskService.saveTask(taskFromController);

        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskRepository).save(taskCaptor.capture());
        assertEquals(taskFromController, taskCaptor.getValue());
    }

    @Test
    public void saveTask_shouldReturnTaskFromRepository(){

        Task taskFromController = Task.builder().build();

        Task expectedTask = Task.builder().build();
        when(taskRepository.save(taskFromController)).thenReturn(expectedTask);

        Task returnedTask = taskService.saveTask(taskFromController);

        assertEquals(expectedTask, returnedTask);
    }

    @Test
    public void getAllTask_shouldReturnListOfTasks_whenListReturnedFromRepository(){

        List<Task> expectedTaskList = Arrays.asList(Task.builder().build(), Task.builder().build());
        when(taskRepository.findAll()).thenReturn(expectedTaskList);

        List<Task> returnedTaskList = taskService.getAllTask();

        assertEquals(expectedTaskList, returnedTaskList);
    }

}