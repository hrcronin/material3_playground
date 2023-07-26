package com.example.material3sample.userflows.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.material3sample.userflows.ColorPaletteView
import com.example.material3sample.userflows.ComponentListView
import com.example.material3sample.userflows.MyDatePickerView
import com.example.material3sample.userflows.components.Carousel
import com.example.material3sample.userflows.components.CheckboxView
import com.example.material3sample.userflows.components.Chips
import com.example.material3sample.userflows.components.RadioGroupView
import com.example.material3sample.userflows.components.TextInputView
import com.example.material3sample.userflows.imagepalette.ImagePaletteView
import com.example.material3sample.viewmodel.ActivityViewModel

data class NavDestination(
    val mainRoute: Destination,
    val children: List<Destination> = listOf()
)
sealed class Destination(
    val route: String,
    val composable: @Composable (NavBackStackEntry, PaddingValues, ActivityViewModel, NavHostController) -> Unit
) {
    class ColorPalette : Destination(
        route = "colorPalette",
        composable = { _, paddingValues, activityViewModel, controller ->
            ColorPaletteView(paddingValues, activityViewModel, controller)
        }
    )
    class ImageColorPalette : Destination(
        route = "imageColorPalette",
        composable = { _, paddingValues, activityViewModel, _ ->
            ImagePaletteView(paddingValues = paddingValues, viewModel = viewModel())
        }
    )
    class DatePicker : Destination(
        route = "myDatePicker",
        composable = { _, paddingValues, _, _ ->
            MyDatePickerView(paddingValues)
        }
    )
    class ComponentList : Destination(
        route = "components",
        composable = { _, _, _, _ -> } // nothing, because this is nested graph
    )
    class ComponentListChild : Destination(
        route = "componentList",
        composable = { _, paddingValues, _, navHostController ->
            ComponentListView(paddingValuesParent = paddingValues, navHostController = navHostController)
        } // nothing, because this is nested graph
    )
    class CarouselDestination : Destination(
        route = "component.carousel",
        composable = { _, paddingValues, _, navController ->
            Carousel(paddingValues, navController)
        }
    )
    class ChipDestination : Destination(
        route = "component.chips",
        composable = { _, paddingValues, _, navController ->
            Chips(paddingValues, navController)
        }
    )
    class CheckBoxDestination : Destination(
        route = "component.checkbox",
        composable = { _, paddingValues, _, navController ->
            CheckboxView(paddingValues, navController)
        }
    )
    class RadioGroupDestination : Destination(
        route = "component.radioGroup",
        composable = { _, paddingValues, _, navController ->
            RadioGroupView(paddingValues, navController)
        }
    )
    class TextInputDest : Destination(
        route = "component.textInput",
        composable = { _, paddingValues, _, navController ->
            TextInputView(paddingValues, navController)
        }
    )
}
