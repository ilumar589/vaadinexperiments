package com.example.sk.administration.database.domain;

import com.example.sk.utils.sql.LoadSqlError;
import com.example.sk.utils.sql.LoadSqlSuccess;
import com.example.sk.utils.sql.SqlUtils;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class TableInfoRepository {

    private static final Logger log = LoggerFactory.getLogger(TableInfoRepository.class);


    private final NamedParameterJdbcTemplate jdbcTemplate;

    public TableInfoRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public @NonNull List<TableInfo> findAllBySchemaName(@NonNull final String schemaName) {
        final var sqlResult = SqlUtils.loadSql("db/administration/get_table_info_by_schema.sql");

        switch (sqlResult) {
            case null -> {
                log.error("Invalid state, no sql result in get_table_info_by_schema.sql usage");
                return List.of();
            }
            case LoadSqlError(IOException err) -> {
                log.error("Failed to load sql result", err);
                return List.of();
            }
            case LoadSqlSuccess(String sqlString) -> {
                final var params = Map.of("schemaName", schemaName);
                return jdbcTemplate.query(sqlString, params, (row, _) -> getTableInfo(row));
            }
        }
    }

    private static @NotNull TableInfo getTableInfo(ResultSet row) throws SQLException {
        final var tableName = row.getString("table_name");
        final var columnName = row.getString("column_name");
        final var dataType = row.getString("data_type");
        final var targetTable = row.getString("target_table");
        final var targetColumn =  row.getString("target_column");

        return new TableInfo(tableName, columnName, dataType, targetTable, targetColumn);
    }
}
