package com.example.sk.taskmanagement.service

import com.example.sk.taskmanagement.api.CreateCategoryRequest
import com.example.sk.taskmanagement.api.CreateTaskRequest
import com.example.sk.taskmanagement.domain.Category
import com.example.sk.taskmanagement.domain.CategoryRepository
import com.example.sk.taskmanagement.domain.CategoryTaskView
import com.example.sk.taskmanagement.domain.CategoryTaskViewRepository
import com.example.sk.taskmanagement.domain.Task
import com.example.sk.taskmanagement.domain.TaskRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Instant

@Service
@Transactional
class ReportingService(
    private val categoryTaskViewRepository: CategoryTaskViewRepository,
    private val taskRepository: TaskRepository,
    private val categoryRepository: CategoryRepository
) {

    fun getAllCategoryTasksFlat(): List<CategoryTaskView> {
        return categoryTaskViewRepository.findAllCategoryTasks()
    }

    fun createCategoryWithTasks(createCategoryRequest: CreateCategoryRequest): Category {
        val createdCategory = categoryRepository.save(Category(
            id = null,
            name = createCategoryRequest.name
        ))

        // create and persist each task
        val savedTasks = createCategoryRequest.tasks.map { taskRequest ->
            Task(
                id = null,
                categoryId = createdCategory.id!!,
                description = taskRequest.description,
                creationDate = Instant.now(),
                dueDate = taskRequest.dueDate
            )
        }.map { taskRepository.save(it) }.toSet()

        // Step 3: Save category again with the tasks included
        val categoryWithTasks = Category(
            id = createdCategory.id,
            name = createdCategory.name,
            tasks = savedTasks
        )
        return categoryRepository.save(categoryWithTasks)
    }
}
