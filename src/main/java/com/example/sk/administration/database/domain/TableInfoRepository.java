package com.example.sk.administration.database.domain;

import com.example.sk.SqlUtils;
import org.jspecify.annotations.NonNull;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Repository
public class TableInfoRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TableInfoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public @NonNull List<TableInfo> findAllBySchemaName(@NonNull final String schemaName) {
        try {
            final var sql = SqlUtils.loadSql("db/get_table_info_by_schema.sql");
            final var params = Map.of("schemaName", schemaName);

            return jdbcTemplate.query(sql, params, (row, _) -> {
                final var tableName = row.getString("table_name");
                final var columnName = row.getString("column_name");
                final var dataType = row.getString("data_type");
                final var targetTable = row.getString("target_table");
                final var targetColumn =  row.getString("target_column");

                return new TableInfo(tableName, columnName, dataType, targetTable, targetColumn);
            });
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
