package com.example.material3sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.material3sample.bottomNav.BottomNavItem
import com.example.material3sample.component.MyDateRangePicker
import com.example.material3sample.ui.theme.AppTheme
import com.example.material3sample.userflows.MyNavGraph


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                BottomNav()
            }
        }
    }
}

@Composable
fun BottomNav() {
    val navController = rememberNavController()
    val navItems = listOf(
        BottomNavItem("Color Palette", Icons.Filled.Favorite, "colorPalette"),
        BottomNavItem("Date Picker", Icons.Filled.DateRange, "myDatePicker")
    )
    Scaffold(
        bottomBar = {
            BottomAppBar(
                contentColor = MaterialTheme.colorScheme.primary,
                actions = {
                    navItems.forEach { navItem ->
                        Column(
                            modifier = Modifier.padding(horizontal = 12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            IconButton(onClick = { navController.navigate(navItem.navRoute) }) {
                                Icon(navItem.icon, contentDescription = navItem.label)
                            }
                            Text(text = navItem.label)
                        }
                    }
                }
            )
        },
        content = {
            MyNavGraph(it, navController)
        }
    )
}