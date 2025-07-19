package com.example.sk.administration.database.domain

import com.example.sk.utils.sql.LoadSqlError
import com.example.sk.utils.sql.LoadSqlSuccess
import com.example.sk.utils.sql.SqlUtils
import org.slf4j.LoggerFactory
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository
import java.io.IOException
import java.sql.ResultSet
import java.sql.SQLException

@Repository
class TableInfoRepository(private val jdbcTemplate: NamedParameterJdbcTemplate) {

    fun findAllBySchemaName(schemaName: String): List<TableInfo> {
        val sqlResult = SqlUtils.loadSql("db/administration/get_table_info_by_schema.sql")

        return when (sqlResult) {
            is LoadSqlError -> {
                log.error("Failed to load sql result", sqlResult.cause)
                emptyList()
            }
            is LoadSqlSuccess -> {
                val params = mapOf("schemaName" to schemaName)
                jdbcTemplate.query(sqlResult.sqlString, params) { row, _ -> getTableInfo(row) }
            }
        }
    }

    private fun getTableInfo(row: ResultSet): TableInfo {
        val tableName = row.getString("table_name")
        val columnName = row.getString("column_name")
        val dataType = row.getString("data_type")
        val targetTable = row.getString("target_table")
        val targetColumn = row.getString("target_column")

        return TableInfo(tableName, columnName, dataType, targetTable, targetColumn)
    }

    companion object {
        private val log = LoggerFactory.getLogger(TableInfoRepository::class.java)
    }
}