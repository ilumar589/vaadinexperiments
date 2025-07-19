package com.example.sk.taskmanagement.api

data class CreateCategoryRequest(
    val name: String,
    val tasks: List<CreateTaskRequest>
)