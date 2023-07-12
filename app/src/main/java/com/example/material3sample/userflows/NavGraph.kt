package com.example.material3sample.userflows

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.material3sample.userflows.components.Carousel
import com.example.material3sample.userflows.components.CheckboxView
import com.example.material3sample.userflows.components.Chips
import com.example.material3sample.userflows.components.RadioGroupView
import com.example.material3sample.viewmodel.ActivityViewModel

@Composable
fun MyNavGraph(
    paddingValues: PaddingValues,
    navController: NavHostController,
    activityViewModel: ActivityViewModel
) {
    NavHost(navController = navController, startDestination = "colorPalette") {
        composable("colorPalette") {
            ColorPaletteView(paddingValues, activityViewModel)
        }
        composable("myDatePicker") {
            MyDatePickerView(paddingValues)
        }
        navigation(startDestination = "componentList", route = "components") {
            composable("componentList"){
                ComponentListView(paddingValues, navController)
            }
            composable("component.carousel") {
                Carousel()
            }
            composable("component.chips") {
                Chips(paddingValues)
            }
            composable("component.checkbox") {
                CheckboxView(paddingValues)
            }
            composable("component.radioGroup") {
                RadioGroupView(paddingValues)
            }
        }
    }

}