package com.example.sk.administration.database.domain;

import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public final class TableInfoRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TableInfoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public @NonNull List<TableInfo> findAllBySchemaName(@NonNull final String schemaName) {

    }
}
