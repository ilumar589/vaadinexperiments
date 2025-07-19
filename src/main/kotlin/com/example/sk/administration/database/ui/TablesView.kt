package com.example.sk.administration.database.ui

import com.example.sk.administration.database.domain.TableInfo
import com.example.sk.administration.database.domain.TableInfoRepository
import com.flowingcode.vaadin.addons.orgchart.OrgChart
import com.flowingcode.vaadin.addons.orgchart.OrgChartItem
import com.flowingcode.vaadin.addons.orgchart.extra.TemplateLiteralRewriter
import com.vaadin.flow.component.orderedlayout.VerticalLayout
import org.slf4j.LoggerFactory

class TablesView(private val tableInfoRepository: TableInfoRepository) : VerticalLayout() {

    init {
        val component = generateChartBasedOnExistingTables()
        val nodeTemplate = """
            <div class='title'>${'$'}{item.title}</div>
            <div class='middle content'>${'$'}{item.name}</div>
            <div class='middle content'>${'$'}{item.data.column_type}</div>
        """.trimIndent()
        
        component.setNodeTemplate("item", TemplateLiteralRewriter.rewriteFunction(nodeTemplate))
        component.addClassNames("chart-container", "image-title-demo")

        component.setChartTitle(
            "My Organization Chart Demo - Example 3 - CUSTOM TEMPLATE WITH IMAGE IN TITLE"
        )
        component.setChartNodeContent("title")
        component.setChartExportButton(true)
        component.setChartExpandCollapse(true)

        setSizeFull()
        add(component)
    }

    private fun generateChartBasedOnExistingTables(): OrgChart {
        val tableInfo = tableInfoRepository.findAllBySchemaName("task_mgmt")

        // group data by table name
        val tableNameToTableInfo = tableInfo.groupBy { it.tableName }

        val rootChartItem = OrgChartItem(1, "", "Root").apply {
            setData("column", "")
            setData("column_type", "")
        }

        tableNameToTableInfo.forEach { (tableName, tableInfos) ->
            val directChildIndex = rootChartItem.children.size + 1
            val tableRootItem = OrgChartItem(directChildIndex, "", tableName).apply {
                setData("column", "")
                setData("column_type", "")
            }

            for (tableDatum in tableInfos) {
                val childIndex = tableRootItem.children.size + 1
                val columnChartItem = createTableItem(
                    tableDatum,
                    childIndex
                )

                tableRootItem.addChildren(columnChartItem)
            }

            rootChartItem.addChildren(tableRootItem)
        }

        return OrgChart(rootChartItem)
    }

    private fun createTableItem(tableInfo: TableInfo, index: Int): OrgChartItem {
        return OrgChartItem(index, "", tableInfo.columnName).apply {
            setData("column_type", tableInfo.dataType)
        }
    }

    private fun getOrgChartData(): OrgChart {
        val item1 = OrgChartItem(1, "John Williams", "Director").apply {
            setData("mail", "jwilliams@example.com")
            setData("imageUrl", "images/users.png")
        }

        val item2 = OrgChartItem(2, "Anna Thompson", "Administration").apply {
            setData("mail", "athomp@example.com")
            setData("imageUrl", "images/users.png")
        }

        val item3 = OrgChartItem(
            3, "Timothy Albert Henry Jones ", "Sub-Director of Administration Department"
        ).apply {
            setData("mail", "timothy.albert.jones@example.com")
            setData("imageUrl", "images/user.png")
        }

        item1.setChildren(listOf(item2, item3))

        val item4 = OrgChartItem(4, "Louise Night", "Department 1").apply {
            setData("mail", "lnight@example.com")
            setData("imageUrl", "images/user.png")
        }

        val item5 = OrgChartItem(5, "John Porter", "Department 2").apply {
            setData("mail", "jporter@example.com")
            setData("imageUrl", "images/user.png")
        }

        val item6 = OrgChartItem(6, "Charles Thomas", "Department 3").apply {
            setData("mail", "ctomas@example.com")
            setData("imageUrl", "images/users.png")
        }

        item2.setChildren(listOf(item4, item5, item6))

        val item7 = OrgChartItem(7, "Michael Wood", "Section 3.1").apply {
            setData("imageUrl", "images/user.png")
        }

        val item8 = OrgChartItem(8, "Martha Brown", "Section 3.2").apply {
            setData("imageUrl", "images/user.png")
        }

        val item9 = OrgChartItem(9, "Mary Parker", "Section 3.3").apply {
            setData("imageUrl", "images/user.png")
        }
        
        val item10 = OrgChartItem(10, "Mary Williamson", "Section 3.4").apply {
            setData("imageUrl", "images/user.png")
        }

        item6.setChildren(listOf(item7, item8, item9, item10))

        return OrgChart(item1)
    }

    companion object {
        private val log = LoggerFactory.getLogger(TablesView::class.java)
    }
}