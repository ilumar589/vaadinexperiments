package com.example.sk.vaadinexamples.ui

import com.example.sk.vaadinexamples.domain.BusinessPerson
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.html.Main
import com.vaadin.flow.component.orderedlayout.FlexComponent
import com.vaadin.flow.component.orderedlayout.HorizontalLayout
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import com.vaadin.flow.router.Menu
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route
import jakarta.annotation.security.PermitAll
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import com.vaadin.flow.data.binder.ValidationException

@Route("person-editor")
@PageTitle("Person Editor")
@Menu(order = 1.0, icon = "vaadin:clipboard-check", title = "Person Editor")
@PermitAll
class PersonEditorView : Main() {

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(PersonEditorView::class.java)
    }

    // data model
    private val businessPerson: BusinessPerson = BusinessPerson()

    // view components
    private val editor1: PersonEditor = PersonEditor(false)
    private val editor2: PersonEditor = PersonEditor(true)

    init {
        // the editor and buttons
        val layout1 = VerticalLayout()
        val layout2 = VerticalLayout()
        val buttonsLayout = HorizontalLayout()
        val editorsSideBySideLayout = HorizontalLayout()

        val saveButton = Button("Save")
        saveButton.addClickListener { 
            try {
                LOGGER.info("Save button clicked")
                editor1.getBinder().writeBean(businessPerson)
                editor2.getBinder().readBean(businessPerson)
            } catch (e: ValidationException) {
                LOGGER.error(e.message)
            }
        }

        val resetButton = Button("Reset")
        resetButton.addClickListener { 
            editor1.getBinder().readBean(businessPerson)
        }

        buttonsLayout.add(saveButton, resetButton)
        layout1.add(editor1, buttonsLayout)
        layout2.add(editor2)

        editorsSideBySideLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.START)
        editorsSideBySideLayout.add(layout1, layout2)

        add(editorsSideBySideLayout)
    }
}
