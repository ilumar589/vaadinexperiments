package com.example.sk.taskmanagement.ui.view

import com.example.sk.common.ui.component.ViewToolbar
import com.example.sk.taskmanagement.domain.Task
import com.example.sk.taskmanagement.service.TaskService
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.button.ButtonVariant
import com.vaadin.flow.component.datepicker.DatePicker
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.html.Main
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.notification.NotificationVariant
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.provider.Query
import com.vaadin.flow.router.Menu
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import com.vaadin.flow.theme.lumo.LumoUtility
import jakarta.annotation.security.PermitAll
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import java.time.Clock
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.Optional

@Route("task-list")
@PageTitle("Task List")
@Menu(order = 0.0, icon = "vaadin:clipboard-check", title = "Task List")
@PermitAll
class TaskListView(
    private val taskService: TaskService,
    clock: Clock
) : Main() {

    private val description: TextField
    private val dueDate: DatePicker
    private val createBtn: Button
    private val taskGrid: Grid<Task>

    init {
        description = TextField()
        description.setPlaceholder("What do you want to do?")
        description.setAriaLabel("Task description")
        description.setMaxLength(500) // Using the value from @Size annotation in Task class
        description.setMinWidth("20em")

        dueDate = DatePicker()
        dueDate.setPlaceholder("Due date")
        dueDate.setAriaLabel("Due date")

        createBtn = Button("Create") { createTask() }
        createBtn.addThemeVariants(ButtonVariant.LUMO_PRIMARY)

        val dateTimeFormatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)
            .withZone(clock.zone)
            .withLocale(locale)
        val dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
            .withLocale(locale)

        taskGrid = Grid()
        taskGrid.setItems { query -> taskService.list(toSpringPageRequest(query)).stream() }
        taskGrid.addColumn(Task::description).setHeader("Description")
        taskGrid.addColumn { task -> 
            Optional.ofNullable(task.dueDate).map(dateFormatter::format).orElse("Never")
        }.setHeader("Due Date")
        taskGrid.addColumn { task -> dateTimeFormatter.format(task.creationDate) }.setHeader("Creation Date")
        taskGrid.setSizeFull()

        setSizeFull()
        addClassNames(
            LumoUtility.BoxSizing.BORDER, 
            LumoUtility.Display.FLEX, 
            LumoUtility.FlexDirection.COLUMN,
            LumoUtility.Padding.MEDIUM, 
            LumoUtility.Gap.SMALL
        )

        add(ViewToolbar("Task List", ViewToolbar.group(description, dueDate, createBtn)))
        add(taskGrid)
    }

    private fun createTask() {
        taskService.createTask(description.value, dueDate.value)
        taskGrid.dataProvider.refreshAll()
        description.clear()
        dueDate.clear()
        Notification.show("Task added", 3000, Notification.Position.BOTTOM_END)
            .addThemeVariants(NotificationVariant.LUMO_SUCCESS)
    }

    private fun toSpringPageRequest(query: Query<Task, Void?>): Pageable {
        val pageSize = query.limit
        val page = if (pageSize > 0) query.offset / pageSize else 0
        return PageRequest.of(page.toInt(), pageSize)
    }
}
