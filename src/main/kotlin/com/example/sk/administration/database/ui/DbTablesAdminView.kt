package com.example.sk.administration.database.ui

import com.example.sk.administration.database.domain.TableInfoRepository
import com.example.sk.common.ui.component.ViewToolbar
import com.vaadin.flow.component.html.Main
import com.vaadin.flow.router.PageTitle
import com.vaadin.flow.router.Route

@Route("db-tables-admin")
@PageTitle("Db tables admin")
class DbTablesAdminView(tableInfoRepository: TableInfoRepository) : Main() {
    init {
        add(ViewToolbar("Db tables admin", ViewToolbar.group()))
        add(TablesView(tableInfoRepository))
    }
}