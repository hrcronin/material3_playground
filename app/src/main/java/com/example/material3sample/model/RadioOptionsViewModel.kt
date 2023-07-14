package com.example.material3sample.model

class RadioOptionsViewModel(
    val options: List<String>,
    val deselectOption: String?,
    val onItemSelected: (String) -> Unit
)
