package com.example.material3sample.userflows.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
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
        composable = { _, paddingValues, activityViewModel, _ ->
            ColorPaletteView(paddingValues, activityViewModel)
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
            ComponentListView(paddingValues = paddingValues, navHostController = navHostController)
        } // nothing, because this is nested graph
    )
    class CarouselDestination : Destination(
        route = "component.carousel",
        composable = { _, paddingValues, _, _ ->
            Carousel(paddingValues)
        }
    )
    class ChipDestination : Destination(
        route = "component.chips",
        composable = { _, paddingValues, _, _ ->
            Chips(paddingValues)
        }
    )
    class CheckBoxDestination : Destination(
        route = "component.checkbox",
        composable = { _, paddingValues, _, _ ->
            CheckboxView(paddingValues)
        }
    )
    class RadioGroupDestination : Destination(
        route = "component.radioGroup",
        composable = { _, paddingValues, _, _ ->
            RadioGroupView(paddingValues = paddingValues)
        }
    )
    class TextInputDest : Destination(
        route = "component.textInput",
        composable = { _, paddingValues, _, _ ->
            TextInputView(paddingValues = paddingValues)
        }
    )
}
