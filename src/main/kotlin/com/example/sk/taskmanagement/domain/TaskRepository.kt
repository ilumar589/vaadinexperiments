package com.example.sk.taskmanagement.domain

import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Slice
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.Instant
import java.time.LocalDate

@Repository
interface TaskRepository : CrudRepository<Task, Long> {
    // If you don't need a total row count, Slice is better than Page.
    fun findAllBy(pageable: Pageable): Slice<Task>

    // Find all tasks due on a specific date
    fun findByDueDate(dueDate: LocalDate): List<Task>

    // Find all tasks with a non-null due date
    @Query("SELECT * FROM task_mgmt.task WHERE due_date IS NOT NULL")
    fun findAllWithDueDate(): List<Task>

    // Find tasks where description contains a keyword (case-insensitive)
    @Query("SELECT * FROM task_mgmt.task WHERE LOWER(description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    fun searchByDescription(keyword: String): List<Task>

    // Find all tasks created after a given date
    fun findByCreationDateAfter(after: Instant): List<Task>
}
