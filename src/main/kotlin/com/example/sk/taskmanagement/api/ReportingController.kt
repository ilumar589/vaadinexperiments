package com.example.sk.taskmanagement.api

import com.example.sk.taskmanagement.domain.Category
import com.example.sk.taskmanagement.domain.CategoryTaskView
import com.example.sk.taskmanagement.service.ReportingService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/report")
class ReportingController(private val reportingService: ReportingService) {

    @GetMapping("/category-tasks")
    fun getCategoryTaskReport(): List<CategoryTaskView> {
        return reportingService.getAllCategoryTasksFlat()
    }

    @PostMapping("/category-tasks")
    fun createCategoryWithTasks(@RequestBody request: CreateCategoryRequest): Category {
        return reportingService.createCategoryWithTasks(request)
    }
}
