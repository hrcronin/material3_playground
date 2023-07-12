package com.example.material3sample.userflows

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ComponentListView(
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(paddingValues)
    ) {
        item {
            ClickableRow(
                annotatedString = buildAnnotatedString { this.append("Carousel") },
                navHostController = navHostController,
                route = "component.carousel"
            )
        }
        item {
            ClickableRow(
                annotatedString = buildAnnotatedString { this.append("Chips") },
                navHostController = navHostController,
                route = "component.chips"
            )
        }
    }
}

@Composable
private fun ClickableRow(
    annotatedString: AnnotatedString,
    navHostController: NavHostController,
    route: String
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 10.dp)) {
        ClickableText(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            text = annotatedString,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            ),
            onClick = {
                navHostController.navigate(route)
            }
        )
        Divider(thickness = 2.dp, color = MaterialTheme.colorScheme.onBackground)
    }
}