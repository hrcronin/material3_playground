package com.example.material3sample.model

import androidx.compose.ui.graphics.vector.ImageVector

class ChipViewModel(
    val type: ChipType,
    val icon: ImageVector?,
    val text: String,
    val description: String,
    val onClick: (ChipType) -> Unit
)
