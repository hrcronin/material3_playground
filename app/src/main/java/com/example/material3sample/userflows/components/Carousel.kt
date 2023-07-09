package com.example.material3sample.userflows.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel() {
    val pages = listOf(
        "Title" to "This is a subtitle",
        "Title Two" to "This is a subtitle on the second page.",
        "Title Three" to "This is a subtitle on the third page."
    )
    HorizontalPager(
        pageCount = pages.size,
        state = rememberPagerState(),
        pageSpacing = 10.dp
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(70f).padding(10.dp),
            border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary)
        ) {
            Text(
                modifier = Modifier.padding(top = 6.dp).padding(horizontal = 12.dp),
                text = pages[it].first,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                modifier = Modifier.padding(vertical = 6.dp).padding(horizontal = 12.dp),
                text = pages[it].second,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontStyle = FontStyle.Italic
                )
            )
        }
    }
}