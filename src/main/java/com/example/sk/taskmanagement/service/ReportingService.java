package com.example.sk.taskmanagement.service;

import com.example.sk.taskmanagement.api.CreateCategoryRequest;
import com.example.sk.taskmanagement.domain.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public final class ReportingService {

    private final CategoryTaskViewRepository categoryTaskViewRepository;
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    public ReportingService(CategoryTaskViewRepository categoryTaskViewRepository,
                            TaskRepository taskRepository,
                            CategoryRepository categoryRepository) {
        this.categoryTaskViewRepository = categoryTaskViewRepository;
        this.taskRepository = taskRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryTaskView> getAllCategoryTasksFlat() {
        return categoryTaskViewRepository.findAllCategoryTasks();
    }

    public Category createCategoryWithTasks(CreateCategoryRequest createCategoryRequest) {
        final var createdCategory = categoryRepository.save(new Category(null, createCategoryRequest.name(), Set.of()));

        // create and persist each task
        final var savedTasks = createCategoryRequest.tasks()
                .stream()
                .map(t -> new Task(
                        null,
                        createdCategory.id(),
                        t.description(),
                        Instant.now(),
                        t.dueDate()
                ))
                .map(taskRepository::save)
                .collect(Collectors.toSet());

        // Step 3: Save category again with the tasks included
        final var categoryWithTasks = new Category(createdCategory.id(), createdCategory.name(), savedTasks);
        return categoryRepository.save(categoryWithTasks);
    }
}
