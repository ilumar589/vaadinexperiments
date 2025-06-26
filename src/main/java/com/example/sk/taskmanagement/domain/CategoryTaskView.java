package com.example.sk.taskmanagement.domain;

import java.time.Instant;
import java.time.LocalDate;

public record CategoryTaskView(Long categoryId,
                               String categoryName,
                               Long taskId,
                               String taskDescription,
                               Instant taskCreationDate,
                               LocalDate taskDueDate) {
}
