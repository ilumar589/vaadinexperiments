package com.example.sk.administration.ui;

import com.example.sk.base.ui.component.ViewToolbar;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("categories-admin")
@PageTitle("Categories admin")
public final class AdminCategoriesView extends Main {

    public AdminCategoriesView() {
        add(new ViewToolbar("Categories admin", ViewToolbar.group()));

    }
}
