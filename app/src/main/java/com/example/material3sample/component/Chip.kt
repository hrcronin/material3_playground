package com.example.material3sample.component

import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SelectableChipElevation
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.material3sample.model.ChipType
import com.example.material3sample.model.ChipViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyChip(
    modifier: Modifier = Modifier,
    model: ChipViewModel
) {
    var isSelected by remember { mutableStateOf(false) }
    when (model.type) {
        is ChipType.Assist -> {
            if (model.type.isElevated) {
                ElevatedAssistChip(
                    modifier = modifier,
                    label = {
                        Text(
                            text = model.text,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    leadingIcon = model.icon?.let { icon ->
                        {
                            Icon(imageVector = icon, contentDescription = model.text)
                        }
                    },
                    elevation = AssistChipDefaults.elevatedAssistChipElevation(
                        elevation = 3.dp,
                        pressedElevation = 1.dp
                    ),
                    onClick = { model.onClick(model.type) }
                )
            } else {
                AssistChip(
                    modifier = modifier,
                    label = {
                        Text(
                            text = model.text,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    leadingIcon = model.icon?.let { icon ->
                        {
                            Icon(imageVector = icon, contentDescription = model.text)
                        }
                    },
                    onClick = { model.onClick(model.type) }
                )
            }
        }
        is ChipType.Filter ->
            if (model.type.isElevated) {
                ElevatedFilterChip(
                    modifier = modifier,
                    label = {
                        Text(
                            text = model.text,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    leadingIcon = model.icon?.let { icon ->
                        {
                            Icon(imageVector = icon, contentDescription = model.text)
                        }
                    },
                    selected = isSelected,
                    elevation = FilterChipDefaults.elevatedFilterChipElevation(
                        elevation = 3.dp,
                        pressedElevation = 1.dp
                    ),
                    onClick = { model.onClick(model.type) }
                )
            } else {
                FilterChip(
                    modifier = modifier,
                    label = {
                        Text(
                            text = model.text,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    leadingIcon = model.icon?.let { icon ->
                        {
                            Icon(imageVector = icon, contentDescription = model.text)
                        }
                    },
                    selected = isSelected,
                    onClick = { model.onClick(model.type) }
                )
            }
        is ChipType.Input ->
            InputChip(
                modifier = modifier,
                label = {
                    Text(
                        text = model.text,
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                leadingIcon = model.icon?.let { icon ->
                    {
                        Icon(imageVector = icon, contentDescription = model.text)
                    }
                },
                selected = isSelected,
                onClick = { model.onClick(model.type) }
            )
        is ChipType.Suggestion ->
            if (model.type.isElevated) {
                ElevatedSuggestionChip(
                    modifier = modifier,
                    label = {
                        Text(
                            text = model.text,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    icon = model.icon?.let { icon ->
                        {
                            Icon(imageVector = icon, contentDescription = model.text)
                        }
                    },
                    elevation = SuggestionChipDefaults.elevatedSuggestionChipElevation(
                        elevation = 3.dp,
                        pressedElevation = 1.dp
                    ),
                    onClick = { model.onClick(model.type) }
                )
            } else {
                SuggestionChip(
                    modifier = modifier,
                    label = {
                        Text(
                            text = model.text,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    icon = model.icon?.let { icon ->
                        {
                            Icon(imageVector = icon, contentDescription = model.text)
                        }
                    },
                    onClick = { model.onClick(model.type) }
                )
            }
    }
}