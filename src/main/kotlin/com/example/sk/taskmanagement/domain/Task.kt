package com.example.sk.taskmanagement.domain

import jakarta.validation.constraints.Size
import org.jspecify.annotations.NullMarked
import org.jspecify.annotations.Nullable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.time.LocalDate

@Table("task", schema = "task_mgmt")
data class Task(
    @Id val id: Long?,
    val categoryId: Long,
    @Size(min = 1, max = 500) val description: String,
    val creationDate: Instant,
    val dueDate: LocalDate?
)
