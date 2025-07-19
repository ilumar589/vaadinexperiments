package com.example.sk.taskmanagement.service

import com.example.sk.taskmanagement.domain.Task
import com.example.sk.taskmanagement.domain.TaskRepository
import org.jspecify.annotations.Nullable
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import java.time.Clock
import java.time.Instant
import java.time.LocalDate

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW)
class TaskService(
    private val taskRepository: TaskRepository,
    private val clock: Clock
) {

    fun createTask(description: String, dueDate: LocalDate?) {
        if (description == "fail") {
            throw RuntimeException("This is for testing the error handler")
        }
        val task = Task(null, 1L, description, Instant.now(clock), dueDate)
        taskRepository.save(task)
    }

    fun list(pageable: Pageable): List<Task> {
        return taskRepository.findAllBy(pageable).toList()
    }
}
