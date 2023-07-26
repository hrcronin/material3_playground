package com.example.material3sample.userflows.imagepalette

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage

private fun Palette.Swatch.rgbAsColor(): Color = Color(rgb)
private fun Palette.Swatch.titleRGBAsColor(): Color = Color(titleTextColor)

@Composable
fun ImagePaletteView(
    viewModel: ImagePaletteViewModel,
    paddingValues: PaddingValues
) {
    val context = LocalContext.current
    val imageUrl by viewModel.imageUrlFlow.collectAsState(initial = "")
    val palette by viewModel.palette.collectAsState(initial = null)
    LaunchedEffect("init") {
        viewModel.setImageUri(context)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(
                palette?.vibrantSwatch?.rgbAsColor() ?: MaterialTheme.colorScheme.background
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        if (imageUrl.isNotEmpty()) {
            AsyncImage(
                modifier = Modifier.fillMaxWidth(0.75f),
                contentScale = ContentScale.FillWidth,
                model = imageUrl,
                contentDescription = "sunflower",
            )
        }
        Text(
            modifier = Modifier.padding(12.dp),
            text = "Title",
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            color = palette?.vibrantSwatch?.titleRGBAsColor() ?: MaterialTheme.colorScheme.primary
        )
    }
}