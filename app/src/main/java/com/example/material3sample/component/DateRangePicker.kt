package com.example.material3sample.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDateRangePicker(paddingValues: PaddingValues) {
    val MILLIS_IN_DAY = 86_400_000
    val today = Calendar.getInstance()
    val endDate = Calendar.getInstance()
    endDate.set(Calendar.MONTH, today.get(Calendar.MONTH) + 3)
    val state = rememberDateRangePickerState(
        yearRange = today.get(Calendar.YEAR)..(today.get(Calendar.YEAR) + 1),
        initialDisplayMode = DisplayMode.Picker
    )
    var daysSelected by remember { mutableStateOf(0) }
    val showDaysSelected by remember {
        derivedStateOf {
            daysSelected > 0
        }
    }
    val selectedStartDate = state.selectedStartDateMillis ?: 0
    val selectedEndDate = state.selectedEndDateMillis ?: 0
    val differenceInDays = (selectedEndDate - selectedStartDate) / MILLIS_IN_DAY
    daysSelected = kotlin.math.max(0, differenceInDays.toInt())
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(12.dp)
    ) {
        Box(modifier = Modifier) {
            DateRangePicker(
                modifier = Modifier.fillMaxHeight(),
                state = state,
                showModeToggle = false,
                headline = {
                    Text("Select your dates:")
                },
                title = {
                    Text("Heather Date Picker")
                }
            )
            val context = LocalContext.current
            if (showDaysSelected) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth(0.75f)
                        .align(Alignment.BottomCenter)
                        .clickable {
                            Toast.makeText(context, "$daysSelected clicked", Toast.LENGTH_SHORT).show()
                        },
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                ) {
                    Text(
                        modifier = Modifier.padding(20.dp),
                        text = "Days selected: $daysSelected",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
            }
        }
    }
}
