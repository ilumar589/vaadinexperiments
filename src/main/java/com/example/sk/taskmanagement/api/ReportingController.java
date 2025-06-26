package com.example.sk.taskmanagement.api;

import com.example.sk.taskmanagement.domain.Category;
import com.example.sk.taskmanagement.domain.CategoryTaskView;
import com.example.sk.taskmanagement.service.ReportingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public final class ReportingController {

    private final ReportingService reportingService;

    public ReportingController(ReportingService reportingService) {
        this.reportingService = reportingService;
    }

    @GetMapping("/category-tasks")
    public List<CategoryTaskView> getCategoryTaskReport() {
        return reportingService.getAllCategoryTasksFlat();
    }

    @PostMapping("/category-tasks")
    public Category createCategoryWithTasks(@RequestBody CreateCategoryRequest request) {
        return reportingService.createCategoryWithTasks(request);
    }
}