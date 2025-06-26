package com.example.sk.taskmanagement.domain;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface CategoryTaskViewRepository extends Repository<CategoryTaskView, Long> {

    @Query("""
        SELECT 
            c.id AS category_id,
            c.name AS category_name,
            t.id AS task_id,
            t.description AS task_description,
            t.creation_date AS task_creation_date,
            t.due_date AS task_due_date
        FROM category c
        LEFT JOIN task t ON t.category_id = c.id
        ORDER BY c.id, t.id
        """)
    List<CategoryTaskView> findAllCategoryTasks();
}
