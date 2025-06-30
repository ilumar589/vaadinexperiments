package com.example.sk.administration.database.ui;

import com.example.sk.base.ui.component.ViewToolbar;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("db-tables-admin")
@PageTitle("Db tables admin")
public final class DbTablesAdminView extends Main {

    public DbTablesAdminView() {
        add(new ViewToolbar("Db tables admin", ViewToolbar.group()));
        add(new TablesView());
    }
}
