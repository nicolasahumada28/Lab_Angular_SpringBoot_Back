package com.learning.taskapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.taskapi.dto.CreateTaskRequest;
import com.learning.taskapi.dto.PutTaskRequest;
import com.learning.taskapi.dto.TaskResponse;
import com.learning.taskapi.dto.UpdateTaskRequest;
import com.learning.taskapi.exception.TaskNotFoundException;
import com.learning.taskapi.model.Task;
import com.learning.taskapi.repository.TaskRepository;

@Service 
public class TaskService {    

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    private TaskResponse toResponse(Task task){
        return new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.getCompleted()
        );
    }

    public List<TaskResponse> getAllTasks(){
        //return tasks;

        return taskRepository.findAll()
                            .stream()
                            .map(this::toResponse)
                            .toList();

    }

    public TaskResponse getTaskById(Long id){

         Task task = taskRepository
            .findById(id)
            .orElseThrow(() ->
                new TaskNotFoundException(id)
        );

        return toResponse(task);

    }

    public TaskResponse createTask(CreateTaskRequest request){
        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setCompleted(Boolean.TRUE.equals(request.getCompleted()));

        Task savedTask = taskRepository.save(task);

        return toResponse(savedTask);
    }

    public TaskResponse updateTask(Long id, PutTaskRequest request){

        Task task = taskRepository
                    .findById(id)
                    .orElseThrow(() ->
                    new TaskNotFoundException(id)
                );
                
        task.setTitle(request.getTitle());
        task.setCompleted(Boolean.TRUE.equals(request.getCompleted()));

        Task savedTask = taskRepository.save(task);

        return toResponse(savedTask);
    }

    public TaskResponse patchTask(Long id, UpdateTaskRequest request){

        Task task = taskRepository
                .findById(id)
                .orElseThrow(() ->
                new TaskNotFoundException(id)
            );

        if(request.getTitle() != null){
            task.setTitle(request.getTitle());
        }
        if(request.getCompleted() != null){
            task.setCompleted(request.getCompleted());
        }

        Task savedTask = taskRepository.save(task);

        return toResponse(savedTask);
    }

    public void deleteTask(Long id){
       
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() ->
                new TaskNotFoundException(id)
            );

        taskRepository.delete(task);
    }
}
