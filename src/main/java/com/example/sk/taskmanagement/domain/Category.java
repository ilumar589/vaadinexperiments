package com.example.sk.taskmanagement.domain;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table(name = "category")
public record Category(
        @Nullable @Id Long id,
        @NullMarked String name,
        @MappedCollection(idColumn = "category_id") Set<Task> tasks) {
}
