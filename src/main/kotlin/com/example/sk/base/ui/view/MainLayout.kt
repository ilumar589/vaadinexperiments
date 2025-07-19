package com.example.sk.base.ui.view

import com.example.sk.administration.database.ui.DbTablesAdminView
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.applayout.AppLayout
import com.vaadin.flow.component.avatar.Avatar
import com.vaadin.flow.component.avatar.AvatarVariant
import com.vaadin.flow.component.html.Div
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.component.icon.Icon
import com.vaadin.flow.component.icon.VaadinIcon
import com.vaadin.flow.component.menubar.MenuBar
import com.vaadin.flow.component.menubar.MenuBarVariant
import com.vaadin.flow.component.orderedlayout.Scroller
import com.vaadin.flow.component.sidenav.SideNav
import com.vaadin.flow.component.sidenav.SideNavItem
import com.vaadin.flow.router.Layout
import com.vaadin.flow.server.menu.MenuConfiguration
import com.vaadin.flow.server.menu.MenuEntry
import jakarta.annotation.security.PermitAll

import com.vaadin.flow.theme.lumo.LumoUtility.*

@Layout
@PermitAll // When security is enabled, allow all authenticated users
class MainLayout : AppLayout() {

    init {
        primarySection = Section.DRAWER
        addToDrawer(
            createHeader(),
            Scroller(createSideNav()),
            createAdminSideNav(),
            createUserMenu()
        )
    }

    private fun createHeader(): Div {
        // TODO Replace with real application logo and name
        val appLogo = VaadinIcon.CUBES.create()
        appLogo.addClassNames(TextColor.PRIMARY, IconSize.LARGE)

        val appName = Span("Walking Skeleton")
        appName.addClassNames(FontWeight.SEMIBOLD, FontSize.LARGE)

        val header = Div(appLogo, appName)
        header.addClassNames(Display.FLEX, Padding.MEDIUM, Gap.MEDIUM, AlignItems.CENTER)
        return header
    }

    private fun createSideNav(): SideNav {
        val nav = SideNav()
        nav.addClassNames(Margin.Horizontal.MEDIUM)
        MenuConfiguration.getMenuEntries().forEach { entry -> nav.addItem(createSideNavItem(entry)) }
        return nav
    }

    private fun createSideNavItem(menuEntry: MenuEntry): SideNavItem {
        return if (menuEntry.icon() != null) {
            SideNavItem(menuEntry.title(), menuEntry.path(), Icon(menuEntry.icon()))
        } else {
            SideNavItem(menuEntry.title(), menuEntry.path())
        }
    }

    private fun createAdminSideNav(): SideNav {
        val adminNav = SideNav()
        adminNav.setLabel("Admin")
        adminNav.setCollapsible(true)
        adminNav.addItem(
            SideNavItem(
                "Db tables admin", 
                DbTablesAdminView::class.java,
                VaadinIcon.GROUP.create()
            )
        )
//        adminNav.addItem(SideNavItem("Permissions", PermissionsView::class.java,
//                VaadinIcon.KEY.create()))

        return adminNav
    }

    private fun createUserMenu(): Component {
        // TODO Replace with real user information and actions
        val avatar = Avatar("John Smith")
        avatar.addThemeVariants(AvatarVariant.LUMO_XSMALL)
        avatar.addClassNames(Margin.Right.SMALL)
        avatar.setColorIndex(5)

        val userMenu = MenuBar()
        userMenu.addThemeVariants(MenuBarVariant.LUMO_TERTIARY_INLINE)
        userMenu.addClassNames(Margin.MEDIUM)

        val userMenuItem = userMenu.addItem(avatar)
        userMenuItem.add("John Smith")
        userMenuItem.subMenu.addItem("View Profile").isEnabled = false
        userMenuItem.subMenu.addItem("Manage Settings").isEnabled = false
        userMenuItem.subMenu.addItem("Logout").isEnabled = false

        return userMenu
    }
}