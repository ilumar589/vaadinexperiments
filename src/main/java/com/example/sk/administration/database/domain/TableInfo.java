package com.example.sk.administration.database.domain;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public record TableInfo(String tableName,
                        String columnName,
                        String dataType,
                        @Nullable String targetTable,
                        @Nullable String targetColumn) {
}
