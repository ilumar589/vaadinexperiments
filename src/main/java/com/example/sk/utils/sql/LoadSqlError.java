package com.example.sk.utils.sql;

import org.jspecify.annotations.NullMarked;

import java.io.IOException;

@NullMarked
public record LoadSqlError(IOException cause) implements LoadSqlResult {
}
