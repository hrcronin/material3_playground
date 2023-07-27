package com.example.material3sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.material3sample.bottomNav.BottomNavItem
import com.example.material3sample.component.BottomNavButtonWithBadge
import com.example.material3sample.component.MyBadge
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
        BottomNavItem("Color Palette", Icons.Filled.Favorite, "colorPalette"),
        BottomNavItem("Date Picker", Icons.Filled.DateRange, "myDatePicker"),
        BottomNavItem("Components", Icons.Filled.List, "components")
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
    Scaffold(
        bottomBar = {
            BottomAppBar(
                contentColor = MaterialTheme.colorScheme.primary,
                actions = {
                    navItems.forEach { navItem ->
                        BottomNavButtonWithBadge(
                            navItem = navItem,
                            navHostController =  navController
                        )
                    }
                }
            )
        },
        content = {
            MyNavGraph(
                paddingValues = it,
                navController = navController,
                activityViewModel = activityViewModel,
                startDestination = Destination.ColorPalette(),
                destinations = navGraphDestinations
            )
        }
    )
}


