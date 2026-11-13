package com.learning.taskapi.dto;

import java.time.LocalDate;

import com.learning.taskapi.model.TaskPriority;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PatchTaskRequest {

	@Size(min=3,max=100,message = "El título debe tener entre 3 y 100 caracteres")
	@Pattern(regexp = ".*\\S.*", message = "El título no puede contener solo espacios")
    private String title;

	@Size(max = 500, message = "La descripción no debe superar los 500 caracteres")
	private String description;

	private TaskPriority priority;

    private Boolean completed;

	private LocalDate dueDate;

	@AssertTrue(message = "Debe indicar al menos un campo para actualizar")
	public boolean isUpdatePresent(){
		return title != null || completed != null;
	}

    public PatchTaskRequest(){

    }

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TaskPriority getPriority() {
		return priority;
	}

	public void setPriority(TaskPriority priority) {
		this.priority = priority;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
}
