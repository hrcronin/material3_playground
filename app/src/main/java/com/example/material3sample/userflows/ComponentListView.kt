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
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.material3sample.component.MyTopAppBar
import com.example.material3sample.userflows.navigation.Destination

@Composable
fun ComponentListView(
    paddingValuesParent: PaddingValues,
    navHostController: NavHostController
) {
    Scaffold(
        topBar = {
            MyTopAppBar(title = "Component List", navIcon = null, onNavIconClick = null)
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValuesParent)
                .padding(it)
        ) {
            item {
                ClickableRow(
                    annotatedString = buildAnnotatedString { this.append("Carousel") },
                    navHostController = navHostController,
                    route = Destination.CarouselDestination().route
                )
            }
            item {
                ClickableRow(
                    annotatedString = buildAnnotatedString { this.append("Chips") },
                    navHostController = navHostController,
                    route = Destination.ChipDestination().route
                )
            }
            item {
                ClickableRow(
                    annotatedString = buildAnnotatedString { this.append("Checkboxes") },
                    navHostController = navHostController,
                    route = Destination.CheckBoxDestination().route
                )
            }
            item {
                ClickableRow(
                    annotatedString = buildAnnotatedString { this.append("Radio Buttons") },
                    navHostController = navHostController,
                    route = Destination.RadioGroupDestination().route
                )
            }
            item {
                ClickableRow(
                    annotatedString = buildAnnotatedString { this.append("Text Inputs") },
                    navHostController = navHostController,
                    route = "component.textInput"
                )
            }
        }
    }
}

@Composable
private fun ClickableRow(
    annotatedString: AnnotatedString,
    navHostController: NavHostController,
    route: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
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
