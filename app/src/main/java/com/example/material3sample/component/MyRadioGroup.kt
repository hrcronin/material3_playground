package com.example.material3sample.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.material3sample.model.RadioOptionsViewModel

@Composable
fun MyRadioGroup(viewModel: RadioOptionsViewModel) {
    val selectedStates = viewModel.options.map {
        remember { mutableStateOf(false) }
    }
    var deselectOptionState by remember { mutableStateOf(false) }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(12.dp)) {
        viewModel.options.forEachIndexed { index, label ->
            var selectedState by selectedStates[index]
            MyRadioItem(
                label = label,
                isSelected = selectedState,
            ) { isSelected, selectedLabel ->
                deselectOptionState = false
                selectedState = isSelected
                selectedStates.forEachIndexed{ listIndex, item ->
                    if (index != listIndex) {
                        item.value = false
                    }
                }
                viewModel.onItemSelected(selectedLabel)
            }
        }
        viewModel.deselectOption?.let { deselectOption ->
            MyRadioItem(
                label = deselectOption,
                isSelected = deselectOptionState,
            ) { isSelected, label ->
                selectedStates.forEach {
                    it.value = false
                }
                deselectOptionState = true
                viewModel.onItemSelected("")
            }
        }
    }
}

@Composable
private fun MyRadioItem(
    label: String,
    isSelected: Boolean,
    onSelectedChanged: (Boolean, String) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold))
        RadioButton(
            selected = isSelected,
            onClick = {
                onSelectedChanged(true, label)
            }
        )
    }
}