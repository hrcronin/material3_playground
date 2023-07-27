package com.example.material3sample.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.material3sample.bottomNav.NavBarItem

@Composable
fun MyNavigationBar(
    isVertical: Boolean,
    items: List<NavBarItem>,
    navHostController: NavHostController
) {
    var selectedItem by remember { mutableStateOf(0) }
    if (isVertical) {
        NavigationRail{
           items.forEachIndexed { index, item ->
               MyNavigationItem(
                   item = item,
                   index = index,
                   navHostController = navHostController,
                   selectedItem = selectedItem,
                   onItemSelected = { selectedItem = it }
               )
           }
        }
    } else {
        NavigationBar{
            items.forEachIndexed { index, item ->
                MyNavigationItem(
                    item = item,
                    index = index,
                    navHostController = navHostController,
                    selectedItem = selectedItem,
                    onItemSelected = { selectedItem = it }
                )
            }
        }
    }
}

@Composable
fun RowScope.MyNavigationItem(
    item: NavBarItem,
    index: Int,
    navHostController: NavHostController,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    var badgeCount by remember { mutableStateOf(5) }
    NavigationBarItem(
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label
            )
        },
        label = {
            Row {
                Text(item.label)
                if (badgeCount > 0) {
                    MyBadge(
                        modifier = Modifier.padding(start = 3.dp),
                        count = badgeCount
                    )
                }
            }
        },
        selected = selectedItem == index,
        onClick = {
            onItemSelected(index)
            badgeCount = 0
            navHostController.navigate(item.navRoute)
        }
    )
}

@Composable
fun ColumnScope.MyNavigationItem(
    item: NavBarItem,
    index: Int,
    navHostController: NavHostController,
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationRailItem(
        selected = selectedItem == index,
        onClick = {
            onItemSelected(index)
            navHostController.navigate(item.navRoute)
        },
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = item.label
            )
        }
    )
}