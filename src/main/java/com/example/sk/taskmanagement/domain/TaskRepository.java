package com.example.sk.taskmanagement.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    // If you don't need a total row count, Slice is better than Page.
    Slice<Task> findAllBy(Pageable pageable);

    // Find all tasks due on a specific date
    List<Task> findByDueDate(LocalDate dueDate);

    // Find all tasks with a non-null due date
    @Query("SELECT * FROM task WHERE due_date IS NOT NULL")
    List<Task> findAllWithDueDate();

    // Find tasks where description contains a keyword (case-insensitive)
    @Query("SELECT * FROM task WHERE LOWER(description) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Task> searchByDescription(String keyword);

    // Find all tasks created after a given date
    List<Task> findByCreationDateAfter(Instant after);
}
