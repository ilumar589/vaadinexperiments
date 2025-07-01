package com.example.sk.administration.database.ui;

import com.example.sk.administration.database.domain.TableInfoRepository;
import com.example.sk.base.ui.component.ViewToolbar;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route("db-tables-admin")
@PageTitle("Db tables admin")
public final class DbTablesAdminView extends Main {

    private final TableInfoRepository tableInfoRepository;

    public DbTablesAdminView(TableInfoRepository tableInfoRepository) {
        this.tableInfoRepository = tableInfoRepository;

        add(new ViewToolbar("Db tables admin", ViewToolbar.group()));
        add(new TablesView(this.tableInfoRepository));
    }
}
