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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.material3sample.component.MyCheckbox
import com.example.material3sample.component.MyTopAppBar

@Composable
fun CheckboxView(paddingValues: PaddingValues, navHostController: NavHostController) {
    Scaffold(
        topBar = {
            MyTopAppBar(title = "Checkboxes", navIcon = Icons.Filled.ArrowBack, onNavIconClick = { navHostController.popBackStack() })
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(paddingValues)
                .padding(12.dp)
        ) {
            MyCheckbox(label = "This is a checkbox label")
        }
    }
}
