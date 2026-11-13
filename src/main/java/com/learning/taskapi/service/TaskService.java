package com.learning.taskapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.learning.taskapi.dto.CreateTaskRequest;
import com.learning.taskapi.dto.PutTaskRequest;
import com.learning.taskapi.dto.TaskResponse;
import com.learning.taskapi.dto.PatchTaskRequest;
import com.learning.taskapi.exception.TaskNotFoundException;
import com.learning.taskapi.model.Task;
import com.learning.taskapi.repository.TaskRepository;

@Service 
public class TaskService {    

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    //GENERACION DE RESPONSE
    private TaskResponse toResponse(Task task){
        return new TaskResponse(
            task.getId(),
            task.getTitle(),
            task.getDescription(),
            task.getPriority(),
            task.getCompleted(),
            task.getCreatedAt(),
            task.getUpdatedAt(),
            task.getDueDate()
        );
    }

    //Normalizacion de descripcion
    private String normalizeDecription(String description){
        if(description == null || description.isBlank()){
            return null;
        }
        return description.trim();
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

    //CREATE
    public TaskResponse createTask(CreateTaskRequest request){
        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(normalizeDecription(request.getDescription()));
        task.setPriority(request.getPriority());
        task.setCompleted(Boolean.TRUE.equals(request.getCompleted()));
        task.setDueDate(request.getDueDate());

        Task savedTask = taskRepository.save(task);

        return toResponse(savedTask);
    }

    //UPDATE (PUT)
    public TaskResponse updateTask(Long id, PutTaskRequest request){

        Task task = taskRepository
                    .findById(id)
                    .orElseThrow(() ->
                    new TaskNotFoundException(id)
                );
                
        task.setTitle(request.getTitle());
        task.setDescription(normalizeDecription(request.getDescription()));
        task.setPriority(request.getPriority());
        task.setCompleted(Boolean.TRUE.equals(request.getCompleted()));
        task.setDueDate(request.getDuedate());

        Task savedTask = taskRepository.save(task);

        return toResponse(savedTask);
    }

    //UPDATE (PATCH)
    public TaskResponse patchTask(Long id, PatchTaskRequest request){

        Task task = taskRepository
                .findById(id)
                .orElseThrow(() ->
                new TaskNotFoundException(id)
            );

        if(request.getTitle() != null){
            task.setTitle(request.getTitle().trim());
        }
        if(request.getDescription() != null){
            this.normalizeDecription(request.getDescription());
        }
        if(request.getPriority() != null)
        {
            task.setPriority(request.getPriority());
        }
        if(request.getCompleted() != null){
            task.setCompleted(request.getCompleted());
        }

        Task savedTask = taskRepository.save(task);

        return toResponse(savedTask);
    }

    // DELETE
    public void deleteTask(Long id){
       
        Task task = taskRepository
                .findById(id)
                .orElseThrow(() ->
                new TaskNotFoundException(id)
            );

        taskRepository.delete(task);
    }
}
