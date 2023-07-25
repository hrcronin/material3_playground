package com.example.material3sample.userflows.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.material3sample.component.MyTextInput
import com.example.material3sample.component.MyTopAppBar
import com.example.material3sample.component.TextInputModel

@Composable
fun TextInputView(
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    var hideKeyboard by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            MyTopAppBar(title = "Text Inputs", navIcon = Icons.Filled.ArrowBack, onNavIconClick = { navHostController.popBackStack() })
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .clickable { hideKeyboard = true }
                .padding(paddingValues)
                .padding(it)
                .padding(12.dp)
        ) {
            item {
                Text(
                    text = "Using Leading and trailing icons",
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            item {
                MyTextInput(
                    model = TextInputModel(
                        initialValue = "",
                        hint = "This is a hint",
                        label = "Enter your text",
                        leadingIcon = Icons.Filled.Search,
                        trailingIcon = Icons.Filled.Close,
                        clearOnTrailingIconClick = true,
                        hideKeyboard = hideKeyboard,
                        imeAction = ImeAction.Next,
                        validateInput = {}
                    ),
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .onFocusChanged {
                            if (it.hasFocus) {
                                hideKeyboard = false
                            }
                        }
                )
            }

            item {
                Text(
                    text = "Using Error validation",
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
            item {
                // this would normally go in a viewmodel; for simplicity using a remember
                var hasError by remember { mutableStateOf(false) }
                MyTextInput(
                    model = TextInputModel(
                        initialValue = "",
                        hint = "Enter text < 5 characters",
                        label = "Enter your text [2]",
                        leadingIcon = Icons.Filled.Search,
                        trailingIcon = Icons.Filled.Close,
                        clearOnTrailingIconClick = true,
                        hideKeyboard = hideKeyboard,
                        errorString = if (hasError) "Please enter only 5 characters or less." else "",
                        validateInput = {
                            val isValid = it.length < 5
                            hasError = !isValid
                        }
                    ),
                    modifier = Modifier
                        .padding(bottom = 10.dp)
                        .onFocusChanged {
                            if (it.hasFocus) {
                                hideKeyboard = false
                            }
                        }
                )
            }
            // TODO add others for input validation
        }
    }
}
