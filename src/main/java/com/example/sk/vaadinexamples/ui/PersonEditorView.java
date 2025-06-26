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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route("person-editor")
@PageTitle("Person Editor")
@Menu(order = 1, icon = "vaadin:clipboard-check", title = "Person Editor")
@PermitAll // When security is enabled, allow all authenticated users
public final class PersonEditorView extends Main {

    private final static Logger LOGGER = LoggerFactory.getLogger(PersonEditorView.class);

    // data model
    private final BusinessPerson businessPerson;

    // view components
    private final PersonEditor editor1;
    private final PersonEditor editor2;

    public PersonEditorView() {
        this.businessPerson = new BusinessPerson();
        this.editor1 = new PersonEditor(false);
        this.editor2 = new PersonEditor(true);

        // the editor and buttons
        final var layout1 = new VerticalLayout();
        final var layout2 = new VerticalLayout();
        final var buttonsLayout = new HorizontalLayout();
        final var editorsSideBySideLayout = new HorizontalLayout();

        Button saveButton = new Button("Save");
        saveButton.addClickListener(event -> {
            try {
                LOGGER.info("Save button clicked");
                editor1.getBinder().writeBean(businessPerson);
                editor2.getBinder().readBean(businessPerson);
            } catch (com.vaadin.flow.data.binder.ValidationException e) {
                LOGGER.error(e.getMessage());
            }
        });

        Button resetButton = new Button("Reset");
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
