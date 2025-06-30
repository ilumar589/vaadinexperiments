package com.example.sk.administration.database.domain;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

public record TableInfo(@NullMarked String tableName,
                        @NullMarked String columnName,
                        @NullMarked String dataType,
                        @Nullable   String targetTable,
                        @Nullable   String targetColumn) {
}
