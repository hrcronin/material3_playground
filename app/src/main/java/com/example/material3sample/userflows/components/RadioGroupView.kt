package com.example.material3sample.userflows.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.material3sample.component.MyCheckbox
import com.example.material3sample.component.MyRadioGroup
import com.example.material3sample.model.RadioOptionsViewModel

@Composable
fun RadioGroupView(paddingValues: PaddingValues) {
    Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
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