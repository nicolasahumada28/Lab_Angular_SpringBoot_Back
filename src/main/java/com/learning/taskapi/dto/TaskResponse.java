package com.learning.taskapi.dto;

public class TaskResponse {

    private Long id;
    private String title;
    private Boolean completed;

    public TaskResponse(){

    }

	public TaskResponse(Long id, String title, Boolean completed) {
		this.id = id;
		this.title = title;
		this.completed = completed;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Boolean getCompleted() {
		return completed;
	}
}
