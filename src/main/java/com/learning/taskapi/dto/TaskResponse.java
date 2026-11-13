package com.learning.taskapi.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.learning.taskapi.model.TaskPriority;

public class TaskResponse {

    private Long id;
    private String title;
	private String description;
	private TaskPriority priority;
    private Boolean completed;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDate dueDate;

    public TaskResponse(){

    }

	public TaskResponse(Long id, String title, String description, TaskPriority priority, Boolean completed, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDate dueDate) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.priority = priority;
		this.completed = completed;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.dueDate = dueDate;
	}

	//SOLO GETTERS
	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public String getDescription() {
		return description;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}
}
