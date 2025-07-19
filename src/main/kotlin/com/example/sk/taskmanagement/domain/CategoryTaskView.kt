package com.example.sk.taskmanagement.domain

import java.time.Instant
import java.time.LocalDate

data class CategoryTaskView(
    val categoryId: Long,
    val categoryName: String,
    val taskId: Long,
    val taskDescription: String,
    val taskCreationDate: Instant,
    val taskDueDate: LocalDate,
)
