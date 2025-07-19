package com.example.sk.common.ui.component

import com.vaadin.flow.component.Component
import com.vaadin.flow.component.Composite
import com.vaadin.flow.component.applayout.DrawerToggle
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.H1
import com.vaadin.flow.component.html.Header
import com.vaadin.flow.theme.lumo.LumoUtility.*

class ViewToolbar(viewTitle: String, vararg components: Component) : Composite<Header>() {
    init {
        addClassNames(
            Display.FLEX, FlexDirection.COLUMN, JustifyContent.BETWEEN, AlignItems.STRETCH, Gap.MEDIUM,
            FlexDirection.Breakpoint.Medium.ROW, AlignItems.Breakpoint.Medium.CENTER
        )

        val drawerToggle = DrawerToggle()
        drawerToggle.addClassNames(Margin.NONE)

        val title = H1(viewTitle)
        title.addClassNames(FontSize.XLARGE, Margin.NONE, FontWeight.LIGHT)

        val toggleAndTitle = Div(drawerToggle, title)
        toggleAndTitle.addClassNames(Display.FLEX, AlignItems.CENTER)
        content.add(toggleAndTitle)

        if (components.isNotEmpty()) {
            val actions = Div(*components)
            actions.addClassNames(
                Display.FLEX, FlexDirection.COLUMN, JustifyContent.BETWEEN, Flex.GROW, Gap.SMALL,
                FlexDirection.Breakpoint.Medium.ROW
            )
            content.add(actions)
        }
    }

    companion object {
        @JvmStatic
        fun group(vararg components: Component): Component {
            val group = Div(*components)
            group.addClassNames(
                Display.FLEX, FlexDirection.COLUMN, AlignItems.STRETCH, Gap.SMALL,
                FlexDirection.Breakpoint.Medium.ROW, AlignItems.Breakpoint.Medium.CENTER
            )
            return group
        }
    }
}