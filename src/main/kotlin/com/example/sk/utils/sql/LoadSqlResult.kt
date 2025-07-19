package com.example.sk.utils.sql

import java.io.IOException
import org.jspecify.annotations.NullMarked

sealed interface LoadSqlResult

@NullMarked
data class LoadSqlSuccess(val sqlString: String) : LoadSqlResult

@NullMarked
data class LoadSqlError(val cause: IOException) : LoadSqlResult