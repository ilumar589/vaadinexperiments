package com.example.sk;

import org.jspecify.annotations.NonNull;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public final class SqlUtils {

    private SqlUtils() {}

    public static String loadSql(@NonNull String classPathLocation) throws IOException {
        final var resource = new ClassPathResource(classPathLocation);
        return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
    }
}
