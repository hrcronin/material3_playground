package com.example.material3sample.userflows.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.material3sample.component.MyRadioGroup
import com.example.material3sample.component.MyTopAppBar
import com.example.material3sample.model.RadioOptionsViewModel

@Composable
fun RadioGroupView(
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            MyTopAppBar(title = "Radio Buttons", navIcon = Icons.Filled.ArrowBack, onNavIconClick = { navHostController.popBackStack() })
        }
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(paddingValues).padding(it)) {
            MyRadioGroup(
                viewModel = RadioOptionsViewModel(
                    options = listOf(
                        "First Option",
                        "Second Option",
                        "Third Option"
                    ),
                    deselectOption = "None",
                    onItemSelected = { }
                )
            )
        }
    }
}
