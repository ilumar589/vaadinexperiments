package com.example.sk.utils.sql

import org.springframework.core.io.ClassPathResource
import org.springframework.util.StreamUtils
import java.io.IOException
import java.nio.charset.StandardCharsets

object SqlUtils {
    fun loadSql(classPathLocation: String): LoadSqlResult {
        val resource = ClassPathResource(classPathLocation)
        return try {
            val sqlString = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8)
            LoadSqlSuccess(sqlString)
        } catch (e: IOException) {
            LoadSqlError(e)
        }
    }
}