package com.example.sk.utils.sql;

import java.io.IOException;

public record LoadSqlError(IOException cause) implements LoadSqlResult {
}
