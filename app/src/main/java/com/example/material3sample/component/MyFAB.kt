package com.example.material3sample.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun MyTextFAB(
    modifier:Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    shape: Shape = FloatingActionButtonDefaults.shape,
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        content = { Text(text = text, modifier = Modifier.padding(10.dp)) },
        contentColor = contentColor,
        containerColor = containerColor,
        shape = shape,
        elevation = elevation
    )
}

@Composable
fun MyIconFAB(
    modifier:Modifier = Modifier,
    onClick: () -> Unit,
    shape: Shape = FloatingActionButtonDefaults.shape,
    vector: ImageVector,
    elevation: FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(),
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        shape = shape,
        content = { Image(imageVector = vector, contentDescription = "image", colorFilter = ColorFilter.tint(contentColor)) },
        contentColor = contentColor,
        containerColor = containerColor,
        elevation = elevation
    )
}