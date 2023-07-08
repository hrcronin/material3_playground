package com.example.material3sample.userflows

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun MyNavGraph(paddingValues: PaddingValues, navController: NavHostController) {
    NavHost(navController = navController, startDestination = "colorPalette") {
        composable("colorPalette") {
            ColorPaletteView(paddingValues)
        }
        composable("myDatePicker") {
            MyDatePickerView(paddingValues)
        }
    }

}