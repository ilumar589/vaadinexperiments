package com.example.sk.base.ui.view

import com.example.sk.common.ui.component.ViewToolbar
import com.vaadin.flow.component.UI
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.Main
import com.vaadin.flow.router.Route
import com.vaadin.flow.theme.lumo.LumoUtility
import jakarta.annotation.security.PermitAll

/**
 * This view shows up when a user navigates to the root ('/') of the application.
 */
@Route
@PermitAll // When security is enabled, allow all authenticated users
class MainView : Main() {

    // TODO Replace with your own main view.

    init {
        addClassName(LumoUtility.Padding.MEDIUM)
        add(ViewToolbar("Main"))
        add(Div("Please select a view from the menu on the left."))
    }

    companion object {
        /**
         * Navigates to the main view.
         */
        @JvmStatic
        fun showMainView() {
            UI.getCurrent().navigate(MainView::class.java)
        }
    }
}