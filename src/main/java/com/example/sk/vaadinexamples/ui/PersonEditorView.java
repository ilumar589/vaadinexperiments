package com.example.sk.vaadinexamples.ui;

import com.example.sk.vaadinexamples.domain.BusinessPerson;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route("person-editor")
@PageTitle("Person Editor")
@Menu(order = 1, icon = "vaadin:clipboard-check", title = "Person Editor")
@PermitAll // When security is enabled, allow all authenticated users
public final class PersonEditorView extends Main {

    // data model
    private final BusinessPerson businessPerson;

    // view components
    private final PersonEditor editor1;
    private final PersonEditor editor2;
    private final VerticalLayout layout1;
    private final VerticalLayout layout2;
    private final HorizontalLayout buttonsLayout;
    private final HorizontalLayout editorsSideBySideLayout;
    private final Button saveButton;
    private final Button resetButton;

    public PersonEditorView() {
        this.businessPerson = new BusinessPerson();
        this.editor1 = new PersonEditor(false);
        this.editor2 = new PersonEditor(true);

        // the editor and buttons
        layout1 = new VerticalLayout();
        layout2 = new VerticalLayout();
        buttonsLayout = new HorizontalLayout();
        editorsSideBySideLayout = new HorizontalLayout();

        saveButton = new Button("Save");
        saveButton.addClickListener(event -> {
            try {
                editor1.getBinder().writeBean(businessPerson);
                editor2.getBinder().readBean(businessPerson);
            } catch (com.vaadin.flow.data.binder.ValidationException e) {

            }
        });

        resetButton = new Button("Reset");
        resetButton.addClickListener(event -> {
            editor1.getBinder().readBean(businessPerson);
        });

        buttonsLayout.add(saveButton, resetButton);
        layout1.add(editor1, buttonsLayout);
        layout2.add(editor2);

        editorsSideBySideLayout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.START);
        editorsSideBySideLayout.add(layout1, layout2);

        add(editorsSideBySideLayout);

    }
}
