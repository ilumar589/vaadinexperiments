package com.example.sk.taskmanagement.api;

import org.jspecify.annotations.NullMarked;

import java.util.List;

public record CreateCategoryRequest(@NullMarked String name,
                                    @NullMarked List<CreateTaskRequest> tasks) {
}
