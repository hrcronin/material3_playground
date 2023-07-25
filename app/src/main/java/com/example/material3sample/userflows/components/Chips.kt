package com.example.material3sample.userflows.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.material3sample.component.MyCheckbox
import com.example.material3sample.component.MyChip
import com.example.material3sample.component.MyRadioGroup
import com.example.material3sample.component.MyTopAppBar
import com.example.material3sample.model.ChipSheetModel
import com.example.material3sample.model.ChipType
import com.example.material3sample.model.ChipViewModel
import com.example.material3sample.model.RadioOptionsViewModel
import com.example.material3sample.model.getHeight
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Chips(paddingValues: PaddingValues, navHostController: NavHostController) {
    val state = rememberBottomSheetScaffoldState(
        bottomSheetState = SheetState(
            initialValue = SheetValue.Hidden,
            skipPartiallyExpanded = false,
            confirmValueChange = {
                true
            }
        )
    )
    var sheetModel by remember {
        mutableStateOf(ChipSheetModel(ChipType.Assist(false), 200.dp))
    }

    BottomSheetScaffold(
        topBar = {
            MyTopAppBar(title = "Chips", navIcon = Icons.Filled.ArrowBack) {
                navHostController.popBackStack()
            }
        },
        sheetPeekHeight = 20.dp,
        scaffoldState = state,
        sheetContent = { SheetContent(sheetModel) },
        sheetSwipeEnabled = true,
        content = {
            ChipViewContent(
                state = state,
                paddingValues = paddingValues,
                updateSheetType = {
                    sheetModel = ChipSheetModel(it, it.getHeight())
                }
            )
        }
    )
}

@Composable
fun SheetContent(sheetModel: ChipSheetModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(sheetModel.height)
            .padding(12.dp)
    ) {
        when (sheetModel.type) {
            is ChipType.Assist -> {
                Text(
                    text = "Trigger Google Assistant...",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displayMedium
                )
            }
            is ChipType.Filter -> {
                MyCheckbox(label = "First Filter Value: ", modifier = Modifier.padding(bottom = 12.dp))
                MyCheckbox(label = "Second Filter Value: ", modifier = Modifier.padding(bottom = 12.dp))
            }
            is ChipType.Input -> {
                Text(text = "Input", style = MaterialTheme.typography.bodyLarge)
            }
            is ChipType.Suggestion -> {
                MyRadioGroup(
                    viewModel = RadioOptionsViewModel(
                        options = listOf(
                            "First Option",
                            "Second Option",
                            "Third Option"
                        ),
                        deselectOption = "None",
                        onItemSelected = { }
                    )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChipViewContent(
    updateSheetType: (ChipType) -> Unit,
    state: BottomSheetScaffoldState,
    paddingValues: PaddingValues
) {
    val coroutineScope = rememberCoroutineScope()
    val chipElevationTypes = listOf(
        ChipViewModel(
            type = ChipType.Assist(false),
            icon = Icons.Filled.Info,
            text = "Assist (flat)",
            description = "",
            onClick = { }
        ),
        ChipViewModel(
            type = ChipType.Assist(true),
            icon = Icons.Filled.Info,
            text = "Assist (elevated)",
            description = "",
            onClick = { }
        )
    )
    val chips = listOf(
        ChipViewModel(
            type = ChipType.Assist(false),
            icon = Icons.Filled.Info,
            text = "Assist",
            description = ChipConstants.ASSIST_CHIP_DESC,
            onClick = {
                coroutineScope.launch {
                    updateSheetType(ChipType.Assist(false))
                    state.bottomSheetState.expand()
                }
            }
        ),
        ChipViewModel(
            type = ChipType.Filter(false),
            icon = Icons.Filled.Add,
            text = "Filter",
            description = ChipConstants.FILTER_CHIP_DEC,
            onClick = {
                coroutineScope.launch {
                    updateSheetType(ChipType.Filter(false))
                    state.bottomSheetState.expand()
                }
            }
        ),
        ChipViewModel(
            type = ChipType.Suggestion(false),
            icon = Icons.Filled.Edit,
            text = "Suggestion",
            description = ChipConstants.SUGGESTION_CHIP_DEC,
            onClick = {
                coroutineScope.launch {
                    updateSheetType(ChipType.Suggestion(false))
                    state.bottomSheetState.expand()
                }
            }
        ),
        ChipViewModel(
            type = ChipType.Input(),
            icon = Icons.Filled.Edit,
            text = "Input",
            description = ChipConstants.INPUT_CHIP_DEC,
            onClick = {
                coroutineScope.launch {
                    updateSheetType(ChipType.Input())
                    state.bottomSheetState.expand()
                }
            }
        )
    )
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        item {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "Chip Styles",
                style = MaterialTheme.typography.titleLarge
            )
        }
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                chipElevationTypes.forEach { item ->
                    MyChip(model = item, modifier = Modifier.padding(bottom = 12.dp))
                }
            }
        }
        item {
            Divider(
                thickness = 2.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            )
        }
        item {
            Text(
                modifier = Modifier.padding(vertical = 12.dp),
                text = "Chip Types",
                style = MaterialTheme.typography.titleLarge
            )
        }
        items(chips) { item ->
            ChipWithDescription(model = item)
        }
    }
}

@Composable
fun ChipWithDescription(model: ChipViewModel) {
    Column(modifier = Modifier.padding(vertical = 12.dp)) {
        Text(
            modifier = Modifier.padding(bottom = 12.dp),
            text = model.description,
            style = MaterialTheme.typography.bodyLarge
        )
        MyChip(
            modifier = Modifier.padding(bottom = 12.dp),
            model = model
        )
        Divider(
            thickness = 2.dp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp)
        )
    }
}

private object ChipConstants {
    const val ASSIST_CHIP_DESC = "Assist chips represent smart or automated actions that can span multiple apps, such as opening a calendar event from the home screen. Assist chips function as though the user asked an assistant to complete the action. They should appear dynamically and contextually in a UI."
    const val FILTER_CHIP_DEC = "Filter chips use tags or descriptive words to filter content. They can be a good alternative to toggle buttons or checkboxes."
    const val SUGGESTION_CHIP_DEC = "Suggestion chips help narrow a user's intent by presenting dynamically generated suggestions, such as possible responses or search filters."
    const val INPUT_CHIP_DEC = "Input chips represent discrete pieces of information entered by a user."
}
