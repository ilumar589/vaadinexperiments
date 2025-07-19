package com.example.sk.vaadinexamples.ui

import com.example.sk.vaadinexamples.domain.BusinessPerson
import com.vaadin.flow.component.formlayout.FormLayout
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.data.binder.Binder

class PersonEditor(readOnly: Boolean) : FormLayout() {

    private val binder: Binder<BusinessPerson> = Binder(BusinessPerson::class.java)

    init {
        // name field
        val nameField = TextField()
        addFormItem(nameField, "Name")
        binder.forField(nameField)
            .bind({ it.name }, { person, value -> person.name = value })
        nameField.isReadOnly = readOnly

        // title field
        val titleField = TextField()
        addFormItem(titleField, "Title")
        binder.forField(titleField)
            .bind({ it.title }, { person, value -> person.title = value })
        titleField.isReadOnly = readOnly
    }

    fun getBinder(): Binder<BusinessPerson> {
        return binder
    }
}
