package com.example.sk.taskmanagement.api;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;

public record CreateTaskRequest(@NullMarked String description,
                                @Nullable LocalDate dueDate) {
}
