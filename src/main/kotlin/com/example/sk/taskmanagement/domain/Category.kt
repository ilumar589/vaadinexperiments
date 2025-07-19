package com.example.sk.taskmanagement.domain

import org.jspecify.annotations.NullMarked
import org.jspecify.annotations.Nullable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.MappedCollection
import org.springframework.data.relational.core.mapping.Table

@Table("category")
data class Category(
    @Id val id: Long?,
    val name: String,
    @MappedCollection(idColumn = "category_id") val tasks: Set<Task> = emptySet()
)
