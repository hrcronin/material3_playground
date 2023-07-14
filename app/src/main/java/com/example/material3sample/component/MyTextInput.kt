package com.example.material3sample.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

data class TextInputModel(
    val initialValue: String,
    val hint: String,
    val label: String,
    val leadingIcon: ImageVector?,
    val trailingIcon: ImageVector?,
    val clearOnTrailingIconClick: Boolean = false,
    val hideKeyboard: Boolean = false,
    val imeAction: ImeAction = ImeAction.Done,
    val validateInput: (String) -> Unit,
    val errorString: String = ""
)

@Composable
fun MyTextInput(model: TextInputModel, modifier: Modifier = Modifier) {
    var textInputValue by remember {
        mutableStateOf(model.initialValue)
    }
    val focus = LocalFocusManager.current
    if (model.hideKeyboard) {
        focus.clearFocus(force = true)
    }
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 12.dp)) {
        OutlinedTextField(
            modifier = modifier
                .fillMaxWidth()
                .onFocusChanged {
                    if (!it.hasFocus) {
                        model.validateInput(textInputValue)
                    }
                },
            value = textInputValue,
            label = { Text(model.label) },
            placeholder = { Text(model.hint) },
            leadingIcon = model.leadingIcon?.let {
                {
                    Icon(imageVector = it, contentDescription = model.hint)
                }
            },
            trailingIcon = model.trailingIcon?.let {
                {
                    Icon(
                        imageVector = it,
                        contentDescription = model.hint,
                        modifier = Modifier.clickable {
                            if (model.clearOnTrailingIconClick) {
                                textInputValue = ""
                            }
                        }
                    )
                }
            },
            onValueChange = {
                textInputValue = it
            },
            isError = model.errorString.isNotEmpty(),
            keyboardOptions = KeyboardOptions(
                imeAction = model.imeAction
            ),
            keyboardActions = when (model.imeAction) {
                ImeAction.Done ->
                    KeyboardActions(
                        onDone = {
                            focus.clearFocus(force = true)
                            model.validateInput(textInputValue)
                        }
                    )
                ImeAction.Next ->
                    KeyboardActions(
                        onNext = {
                            focus.moveFocus(focusDirection = FocusDirection.Next)
                            model.validateInput(textInputValue)
                        }
                    )
                else -> KeyboardActions()
            }
        )
        if (model.errorString.isNotEmpty()) {
            Text(
                modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                text = model.errorString,
                style = MaterialTheme.typography.labelMedium.copy(
                    fontStyle = FontStyle.Italic,
                    color = MaterialTheme.colorScheme.error
                )
            )
        }
    }
}
