package com.example.material3sample.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.material3sample.bottomNav.BottomNavItem

@Composable
fun BottomNavButtonWithBadge(
    navItem: BottomNavItem,
    navHostController: NavHostController
) {
    var badgeCount by remember {
        mutableStateOf(5)
    }
    Box {
        if (badgeCount > 0) {
            MyBadge(
                count = badgeCount,
                modifier = Modifier.align(Alignment.TopEnd)
            )
        }
        Column(
            modifier = Modifier.padding(horizontal = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(
                onClick = {
                    badgeCount = 0
                    navHostController.navigate(navItem.navRoute)
                }
            ) {
                Icon(navItem.icon, contentDescription = navItem.label)
            }
            Text(text = navItem.label)
        }
    }
}