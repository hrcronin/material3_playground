package com.example.material3sample.userflows.components

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.material3sample.component.MyButton
import com.example.material3sample.component.MyIconFAB
import com.example.material3sample.component.MyTextFAB
import com.example.material3sample.component.MyTopAppBar

@Composable
fun ButtonView(
    navHostController: NavHostController,
    paddingValues: PaddingValues
) {
    Scaffold(
        topBar = {
            MyTopAppBar(
                title = "Buttons",
                navIcon = Icons.Filled.ArrowBack,
                onNavIconClick = {
                    navHostController.popBackStack()
                }
            )
        }

    ) {
        val context = LocalContext.current
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(paddingValues)
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            item {
                Text(
                    text = "Different shapes of buttons",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
            item {
                MyButton(text = "Small", shape = MaterialTheme.shapes.small) {
                    Toast.makeText(context, "Small Shape button click", Toast.LENGTH_SHORT).show()
                }
            }
            item {
                MyButton(text = "Medium", shape = MaterialTheme.shapes.medium) {
                    Toast.makeText(context, "Medium Shape button click", Toast.LENGTH_SHORT).show()
                }
            }
            item {
                MyButton(text = "Large", shape = MaterialTheme.shapes.large) {
                    Toast.makeText(context, "Large Shape button click", Toast.LENGTH_SHORT).show()
                }
            }
            item {
                MyButton(text = "X-Large", shape = MaterialTheme.shapes.extraLarge) {
                    Toast.makeText(context, "X-Large Shape button click", Toast.LENGTH_SHORT).show()
                }
            }
            item {
                Text(
                    text = "Floating Action Button",
                    style = MaterialTheme.typography.headlineSmall
                )
            }
            item {
                Text(
                    text = "The FAB represents the most important action on a screen. It puts key actions within reach.",
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            item {
                Divider(
                    thickness = 2.dp,
                    modifier = Modifier.padding(vertical = 12.dp)
                )
            }
            item {
                MyTextFAB(
                    modifier = Modifier.padding(vertical = 16.dp),
                    onClick = {
                        Toast.makeText(context, "Icon text FAB click", Toast.LENGTH_SHORT).show()
                    },
                    text = "Text Action",
                    shape = MaterialTheme.shapes.medium
                )

                MyIconFAB(
                    onClick = {
                        Toast.makeText(context, "Icon FAB (default shape) click", Toast.LENGTH_SHORT).show()
                    },
                    vector = Icons.Filled.Star
                )

                MyIconFAB(
                    modifier = Modifier.padding(vertical = 16.dp),
                    onClick = {
                        Toast.makeText(context, "Icon FAB click", Toast.LENGTH_SHORT).show()
                    },
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 10.dp),
                    vector = Icons.Filled.Add,
                    shape = MaterialTheme.shapes.extraLarge
                )
            }
        }
    }
}