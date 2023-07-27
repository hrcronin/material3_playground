package com.example.material3sample.component

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape

@Composable
fun MyButton(
    text: String,
    shape: Shape,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        content = { Text(text) },
        shape = shape
    )
}