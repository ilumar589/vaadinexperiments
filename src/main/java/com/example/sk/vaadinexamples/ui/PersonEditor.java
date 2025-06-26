package com.example.sk.vaadinexamples.ui;

import com.example.sk.vaadinexamples.domain.BusinessPerson;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public final class PersonEditor extends FormLayout {

    private final Binder<BusinessPerson> binder;

    public PersonEditor(boolean readOnly) {
        binder = new Binder<>(BusinessPerson.class);

        // name field
        final var nameField = new TextField();
        addFormItem(nameField, "Name");
        binder.bind(nameField, BusinessPerson::getName, BusinessPerson::setName);
        nameField.setReadOnly(readOnly);

        // title field
        final var titleField = new TextField();
        addFormItem(titleField, "Title");
        binder.bind(titleField, BusinessPerson::getTitle, BusinessPerson::setTitle);
        titleField.setReadOnly(readOnly);
    }

    public Binder<BusinessPerson> getBinder() {
        return binder;
    }
}
