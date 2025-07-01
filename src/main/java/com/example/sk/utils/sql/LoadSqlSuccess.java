package com.example.sk.utils.sql;

import org.jspecify.annotations.NullMarked;

@NullMarked
public record LoadSqlSuccess(String sqlString) implements LoadSqlResult {
}
