package com.learning.taskapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.taskapi.dto.CreateTaskRequest;
import com.learning.taskapi.dto.PutTaskRequest;
import com.learning.taskapi.dto.TaskResponse;
import com.learning.taskapi.dto.PatchTaskRequest;
import com.learning.taskapi.service.TaskService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController 
@RequestMapping("/api/tasks")
//@CrossOrigin(origins = "http://localhost:4200")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    // GET
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(){
        return ResponseEntity.ok(
            taskService.getAllTasks()
        );
    }

    // GET{id}
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok(
            taskService.getTaskById(id)
        );
    }

    //POST
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request){
        
        TaskResponse created = taskService.createTask(request);

        return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(created);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody PutTaskRequest request){

        return ResponseEntity.ok(
            taskService.updateTask(id, request)
        );
    }

    // PATCH
    @PatchMapping("/{id}")
    public ResponseEntity<TaskResponse> patchTask(@PathVariable Long id, @Valid @RequestBody PatchTaskRequest request){

        return ResponseEntity.ok(
            taskService.patchTask(id, request)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();

    }
    
}
