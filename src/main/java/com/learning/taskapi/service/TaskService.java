package com.learning.taskapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.learning.taskapi.model.Task;

@Service 
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();

    public TaskService(){
        tasks.add(new Task(1L,"Estudiar Angular",true));
        tasks.add(new Task(2L,"Estudiar SpringBoot",false));
        tasks.add(new Task(3L,"Construir Aplicación Full Stack",false));
    }

    public List<Task> getAllTasks(){
        return tasks;
    }

    public Optional<Task> getTaskById(Long id){
        return tasks.stream()
            .filter(task -> task.getId().equals(id))
            .findFirst();
    }

    public Task createTask(Task task){
        Long nextId = getNextId();

        Task newTask = new Task(
            nextId,
            task.getTitle(),
            task.getCompleted()
        );

        tasks.add(newTask);
        return newTask;
    }

    public Optional<Task> updateTask(Long id, Task request){
        Optional<Task> optionalTask = getTaskById(id);

        if(optionalTask.isEmpty()){
            return Optional.empty();
        }

        Task existingTask = optionalTask.get();

        existingTask.setTitle(request.getTitle());
        existingTask.setCompleted(request.getCompleted());

        return Optional.of(existingTask);
    }

    public Optional<Task> patchTask(Long id, Task request){
        Optional<Task> optionalTask = getTaskById(id);

        if(optionalTask.isEmpty()){
            return Optional.empty();
        }

        Task existingTask = optionalTask.get();

        if(request.getTitle() != null){
            existingTask.setTitle(request.getTitle());
        }

        if(request.getCompleted() != null){
            existingTask.setCompleted(request.getCompleted());
        }

        return Optional.of(existingTask);
    }

    public boolean deleteTask(Long id){
        return tasks.removeIf(
            task -> task.getId().equals(id)
        );
    }

    private Long getNextId(){
        return tasks.stream()
        .map(Task::getId)
        .max(Long::compareTo)
        .orElse(0L) +1;
    }
}
