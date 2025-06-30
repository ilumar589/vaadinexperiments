package com.example.sk.administration.database.ui;

import com.flowingcode.vaadin.addons.orgchart.OrgChart;
import com.flowingcode.vaadin.addons.orgchart.OrgChartItem;
import com.flowingcode.vaadin.addons.orgchart.extra.TemplateLiteralRewriter;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.util.List;

public final class TablesView extends VerticalLayout {

    public TablesView() {
        OrgChart component = getOrgChartData();
        String nodeTemplate =
                "<div class='title'>"
                        + "${item.data.imageUrl?`<img src=${item.data.imageUrl}></img>`:''}"
                        + "${item.title}</div>"
                        + "<div class='middle content'>${item.name}</div>"
                        + "${item.data.mail?`<div class='custom content'>${item.data.mail}</div>`:''}";
        component.setNodeTemplate("item", TemplateLiteralRewriter.rewriteFunction(nodeTemplate));
        component.addClassNames("chart-container", "image-title-demo");

        component.setChartTitle(
                "My Organization Chart Demo - Example 3 - CUSTOM TEMPLATE WITH IMAGE IN TITLE");
        component.setChartNodeContent("title");
        component.setChartExportButton(true);
        component.setChartExpandCollapse(true);

        setSizeFull();
        add(component);
    }


    private OrgChart getOrgChartData() {
        OrgChartItem item1 = new OrgChartItem(1, "John Williams", "Director");
        item1.setData("mail", "jwilliams@example.com");
        item1.setData("imageUrl", "images/users.png");

        OrgChartItem item2 = new OrgChartItem(2, "Anna Thompson", "Administration");
        item2.setData("mail", "athomp@example.com");
        item2.setData("imageUrl", "images/users.png");

        OrgChartItem item3 =
                new OrgChartItem(
                        3, "Timothy Albert Henry Jones ", "Sub-Director of Administration Department");
        item3.setData("mail", "timothy.albert.jones@example.com");
        item3.setData("imageUrl", "images/user.png");

        item1.setChildren(List.of(item2, item3));

        OrgChartItem item4 = new OrgChartItem(4, "Louise Night", "Department 1");
        item4.setData("mail", "lnight@example.com");
        item4.setData("imageUrl", "images/user.png");

        OrgChartItem item5 = new OrgChartItem(5, "John Porter", "Department 2");
        item5.setData("mail", "jporter@example.com");
        item5.setData("imageUrl", "images/user.png");

        OrgChartItem item6 = new OrgChartItem(6, "Charles Thomas", "Department 3");
        item6.setData("mail", "ctomas@example.com");
        item6.setData("imageUrl", "images/users.png");

        item2.setChildren(List.of(item4, item5, item6));

        OrgChartItem item7 = new OrgChartItem(7, "Michael Wood", "Section 3.1");
        item7.setData("imageUrl", "images/user.png");

        OrgChartItem item8 = new OrgChartItem(8, "Martha Brown", "Section 3.2");
        item8.setData("imageUrl", "images/user.png");

        OrgChartItem item9 = new OrgChartItem(9, "Mary Parker", "Section 3.3");
        item9.setData("imageUrl", "images/user.png");
        OrgChartItem item10 = new OrgChartItem(10, "Mary Williamson", "Section 3.4");
        item10.setData("imageUrl", "images/user.png");

        item6.setChildren(List.of(item7, item8, item9, item10));

        return new OrgChart(item1);
    }
}
