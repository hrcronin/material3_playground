package com.example.material3sample.model

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class ChipType(val isElevated: Boolean) {
    class Filter(isElevated: Boolean): ChipType(isElevated)
    class Input: ChipType(false)
    class Suggestion(isElevated: Boolean): ChipType(isElevated)
    class Assist(isElevated: Boolean): ChipType(isElevated)
}

fun ChipType.getHeight(): Dp {
    return when(this) {
        is ChipType.Assist -> 300.dp
        is ChipType.Filter -> 250.dp
        is ChipType.Input -> 125.dp
        is ChipType.Suggestion -> 300.dp
    }
}