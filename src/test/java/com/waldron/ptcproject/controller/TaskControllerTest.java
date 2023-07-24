package com.waldron.ptcproject.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.waldron.ptcproject.entity.Task;
import com.waldron.ptcproject.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    private static String TASK_REQUEST_MAPPING = "/tasks";

    @Test
    public void getAllTask_shouldReturnListFromService()throws Exception{

        Task task1 = Task.builder().id(1l).description("description 1").priority(1l).build();
        Task task2 = Task.builder().id(2l).description("description 2").priority(2l).build();
        List<Task> taskList = Arrays.asList(task1, task2);

        when(taskService.getAllTask()).thenReturn(taskList);

        String expectedJson = new ObjectMapper().writeValueAsString(taskList);

        mockMvc.perform(get(TASK_REQUEST_MAPPING))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson));
    }

    @Test
    public void updateTask_shouldPassIdAndTaskToService() throws Exception {

        long updateId = 1l;
        String expectedDescription = "update task";
        long expectedPriority = 1l;
        Task updateTask = Task.builder().description(expectedDescription).priority(expectedPriority).build();
        String json = new GsonBuilder().create().toJson(updateTask);

        when(taskService.updateTask(updateId, updateTask)).thenReturn(updateTask);

        mockMvc.perform(put(TASK_REQUEST_MAPPING + "/" +updateId)
                        .contentType(MediaType.APPLICATION_JSON).content(json))
                .andDo(print())
                .andExpect(status().isOk());

        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);
        ArgumentCaptor<Task> taskCaptor = ArgumentCaptor.forClass(Task.class);
        verify(taskService, times(1)).updateTask(longCaptor.capture(), taskCaptor.capture());
        assertEquals(updateId, longCaptor.getValue());
        assertEquals(expectedDescription, taskCaptor.getValue().getDescription());
        assertEquals(expectedPriority, taskCaptor.getValue().getPriority());
    }


    //todo update

}