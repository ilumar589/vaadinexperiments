package com.example.sk.taskmanagement.service;

import com.example.sk.taskmanagement.domain.Task;
import com.example.sk.taskmanagement.domain.TaskRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TaskService {

    private final TaskRepository taskRepository;

    private final Clock clock;

    TaskService(TaskRepository taskRepository, Clock clock) {
        this.taskRepository = taskRepository;
        this.clock = clock;
    }

    public void createTask(String description, @Nullable LocalDate dueDate) {
        if ("fail".equals(description)) {
            throw new RuntimeException("This is for testing the error handler");
        }
        final var task = new Task(null, 1L, description, Instant.now(), dueDate);
        taskRepository.save(task);
    }

    public List<Task> list(Pageable pageable) {
        return taskRepository.findAllBy(pageable).toList();
    }

}
