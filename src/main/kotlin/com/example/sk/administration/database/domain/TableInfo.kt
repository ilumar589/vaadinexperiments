package com.example.sk.administration.database.domain

import org.jspecify.annotations.NullMarked

@NullMarked
data class TableInfo(
    val tableName: String,
    val columnName: String,
    val dataType: String,
    val targetTable: String?,
    val targetColumn: String?
)