package com.learning.taskapi.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UpdateTaskRequest {

	@Size(min=3,max=100,message = "El título debe tener entre 3 y 100 caracteres")
	@Pattern(regexp = ".*\\S.*", message = "El título no puede contener solo espacios")
    private String title;

    private Boolean completed;

	@AssertTrue(message = "Debe indicar al menos un campo para actualizar")
	public boolean isUpdatePresent(){
		return title != null || completed != null;
	}

    public UpdateTaskRequest(){

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
}
