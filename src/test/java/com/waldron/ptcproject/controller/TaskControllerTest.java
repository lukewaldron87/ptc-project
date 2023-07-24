package com.waldron.ptcproject.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.waldron.ptcproject.entity.Task;
import com.waldron.ptcproject.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(TaskController.class)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskService taskService;

    /*@InjectMocks
    private TaskController taskController;*/

    @Test
    public void getAll_ShouldReturnListFromService()throws Exception{

        Task task1 = Task.builder().id(1l).description("description 1").priority(1l).build();
        Task taks2 = Task.builder().id(2l).description("description 2").priority(2l).build();
        List<Task> taskList = Arrays.asList(task1, taks2);

        when(taskService.getAllTask()).thenReturn(taskList);

        String expectedJson = new ObjectMapper().writeValueAsString(taskList);

        MvcResult mvcResult = mockMvc.perform(get("/tasks"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson))
                .andReturn();
    }

    //todo update should pass task


    //todo update

}