package com.example.sk.utils.sql;

import org.jspecify.annotations.NonNull;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class SqlUtils {

    private SqlUtils() {}

    public static @NonNull LoadSqlResult loadSql(@NonNull String classPathLocation) {
        final var resource = new ClassPathResource(classPathLocation);
        try {
            final var sqlString = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
            return new LoadSqlSuccess(sqlString);
        } catch (IOException e) {
            return new LoadSqlError(e);
        }
    }
}
