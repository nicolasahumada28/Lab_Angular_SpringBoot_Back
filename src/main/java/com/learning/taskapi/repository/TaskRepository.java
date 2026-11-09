package com.learning.taskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.taskapi.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
