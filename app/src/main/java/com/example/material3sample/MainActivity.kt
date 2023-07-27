package com.example.material3sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.rememberNavController
import com.example.material3sample.bottomNav.NavBarItem
import com.example.material3sample.component.MyNavigationBar
import com.example.material3sample.ui.theme.AppTheme
import com.example.material3sample.userflows.MyNavGraph
import com.example.material3sample.userflows.navigation.Destination
import com.example.material3sample.userflows.navigation.NavDestination
import com.example.material3sample.viewmodel.ActivityViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)
        setContent {
            val useDynamicColor by viewModel.useDynamicColor.observeAsState()
            val useOtherPalette by viewModel.useDiffPalette.observeAsState()
            AppTheme(
                useDynamicColor = useDynamicColor ?: false,
                useOptionalPalette = useOtherPalette ?: false
            ) {
                BottomNav(viewModel)
            }
        }
    }
}

@Composable
fun BottomNav(activityViewModel: ActivityViewModel) {
    val navController = rememberNavController()
    val navItems = listOf(
        NavBarItem("Color Palette", Icons.Filled.Favorite, "colorPalette"),
        NavBarItem("Date Picker", Icons.Filled.DateRange, "myDatePicker"),
        NavBarItem("Components", Icons.Filled.List, "components")
    )
    val navGraphDestinations = listOf(
        NavDestination(Destination.ColorPalette()),
        NavDestination(Destination.ImageColorPalette()),
        NavDestination(Destination.DatePicker()),
        NavDestination(
            mainRoute = Destination.ComponentList(),
            children = listOf(
                Destination.ComponentListChild(),
                Destination.CarouselDestination(),
                Destination.ChipDestination(),
                Destination.CheckBoxDestination(),
                Destination.RadioGroupDestination(),
                Destination.TextInputDest(),
                Destination.ButtonsDest()
            )
        )
    )
    val useVerticalBar by activityViewModel.useVerticalNav.observeAsState(initial = false)
    Scaffold(
        bottomBar = {
            if (!useVerticalBar) {
                MyNavigationBar(
                    isVertical = false,
                    items = navItems,
                    navHostController = navController
                )
            }
        },
        content = {
            if (useVerticalBar) {
                Row( modifier = Modifier.fillMaxWidth()) {
                    MyNavigationBar(
                        isVertical = true,
                        items = navItems,
                        navHostController = navController
                    )
                    MyNavGraph(
                        paddingValues = it,
                        navController = navController,
                        activityViewModel = activityViewModel,
                        startDestination = Destination.ColorPalette(),
                        destinations = navGraphDestinations
                    )
                }
            } else {
                MyNavGraph(
                    paddingValues = it,
                    navController = navController,
                    activityViewModel = activityViewModel,
                    startDestination = Destination.ColorPalette(),
                    destinations = navGraphDestinations
                )
            }

        }
    )
}


