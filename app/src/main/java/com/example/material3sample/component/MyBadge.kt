package com.example.material3sample.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBadge(
    modifier: Modifier = Modifier,
    count: Int
) {
    Badge(
        modifier = modifier,
        content = {
            Text(
                text = "$count",
                modifier = Modifier.padding(3.dp)
            )
        }
    )
}