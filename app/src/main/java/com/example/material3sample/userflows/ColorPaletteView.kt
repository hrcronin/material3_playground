package com.example.material3sample.userflows

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.material3sample.ui.theme.AppTheme
import com.example.material3sample.viewmodel.ActivityViewModel

@Composable
fun ColorPaletteView(paddingValues: PaddingValues, activityViewModel: ActivityViewModel) {
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(paddingValues)
        .padding(20.dp)
    ) {
        item {
            DynamicColorSwitch(activityViewModel = activityViewModel)
        }
        item {
            ColorPaletteSwitch(activityViewModel = activityViewModel)
        }
        item {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )
        }
        item {
            PrimaryGreeting(name = "Heather")
        }
        item {
            SecondaryGreeting(name = "Heather")
        }
        item {
            TertiaryGreeting(name = "Heather")
        }
        item {
            PrimaryContainer(name = "Heather")
        }
        item {
            SecondaryContainer(name = "Heather")
        }
        item {
            TertiaryContainer(name = "Heather")
        }
    }
}

@Composable
private fun DynamicColorSwitch(activityViewModel: ActivityViewModel) {
    val isChecked:Boolean by activityViewModel.useDynamicColor.observeAsState(false)
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(end = 10.dp),
            text = "Dynamic Color",
            style = MaterialTheme.typography.titleLarge
        )
        Switch(
            checked = isChecked,
            onCheckedChange = { isCheckedValue ->
                if (isCheckedValue) {
                    activityViewModel.updateOtherPalette(false)
                }
                activityViewModel.updateDynamicColor(isCheckedValue)
            }
        )
    }

}

@Composable
private fun ColorPaletteSwitch(activityViewModel: ActivityViewModel) {
    val isChecked:Boolean by activityViewModel.useDiffPalette.observeAsState(false)
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            modifier = Modifier.padding(end = 10.dp),
            text = "Color Palette Switch",
            style = MaterialTheme.typography.titleLarge
        )
        Switch(
            checked = isChecked,
            onCheckedChange = { isCheckedValue ->
                if (isCheckedValue) {
                    activityViewModel.updateDynamicColor(false)
                }
                activityViewModel.updateOtherPalette(isCheckedValue)
            }
        )
    }

}

@Composable
private fun PrimaryGreeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        color = MaterialTheme.colorScheme.primary,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier.padding(vertical = 12.dp)
    )
}

@Composable
private fun SecondaryGreeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        color = MaterialTheme.colorScheme.secondary,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier.padding(vertical = 12.dp)
    )
}

@Composable
private fun TertiaryGreeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        color = MaterialTheme.colorScheme.tertiary,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.headlineLarge,
        modifier = modifier.padding(vertical = 12.dp)
    )
}

@Composable
private fun PrimaryContainer(name: String, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.padding(vertical = 12.dp),
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(
            text = "Hello $name!",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displayMedium,
            modifier = modifier.padding(12.dp)
        )
    }
}


@Composable
private fun SecondaryContainer(name: String, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.padding(vertical = 12.dp),
        shape = MaterialTheme.shapes.large,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
    ) {
        Text(
            text = "Hello $name!",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displayMedium,
            modifier = modifier.padding(12.dp)
        )
    }
}

@Composable
private fun TertiaryContainer(name: String, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier.padding(vertical = 12.dp),
        shape = MaterialTheme.shapes.extraLarge,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.tertiaryContainer,
            contentColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
    ) {
        Text(
            text = "Hello $name!",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.displayMedium,
            modifier = modifier.padding(12.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    AppTheme {
        PrimaryGreeting("Android")
    }
}