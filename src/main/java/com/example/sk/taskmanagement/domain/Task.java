package com.example.sk.taskmanagement.domain;


import jakarta.validation.constraints.Size;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.LocalDate;


@Table(name = "task")
public record Task(
        @Nullable @Id Long id,
        @NullMarked Long categoryId,
        @Nullable @Size(max = DESCRIPTION_MAX_LENGTH) String description,
        @NullMarked Instant creationDate,
        @Nullable LocalDate dueDate) {

    public static final int DESCRIPTION_MAX_LENGTH = 255;

}
