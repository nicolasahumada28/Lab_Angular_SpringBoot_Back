package com.learning.taskapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PutTaskRequest {

    @NotBlank(message = "El título es obligatorio")
    @Size(min=3,max=100,message = "El título debe tener entre 3 y 100 caracteres")
    private String title;

    @NotNull(message = "El estado completado es obligatorio")
    private Boolean completed;

    public PutTaskRequest(){

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
