package com.example.sk.taskmanagement.api

import java.time.LocalDate

data class CreateTaskRequest(
    val description: String,
    val dueDate: LocalDate
)
