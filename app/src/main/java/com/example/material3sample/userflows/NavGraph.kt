package com.example.material3sample.userflows

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.material3sample.userflows.navigation.Destination
import com.example.material3sample.userflows.navigation.NavDestination
import com.example.material3sample.viewmodel.ActivityViewModel

@Composable
fun MyNavGraph(
    paddingValues: PaddingValues,
    navController: NavHostController,
    activityViewModel: ActivityViewModel,
    destinations: List<NavDestination>,
    startDestination: Destination
) {
    val destinationsWithNoChildren = destinations.filter {
        it.children.isEmpty()
    }
    val destinationsWithChildren = destinations.filter {
        it.children.isNotEmpty()
    }
    NavHost(navController = navController, startDestination = startDestination.route) {
        destinationsWithNoChildren.forEach { dest ->
            composable(dest.mainRoute.route) {
                dest.mainRoute.composable(it, paddingValues, activityViewModel, navController)
            }
        }
        destinationsWithChildren.forEach { nestedDestination ->
            val childStartDestination = nestedDestination.children.firstOrNull() ?: return@forEach
            navigation(
                startDestination = childStartDestination.route,
                route = nestedDestination.mainRoute.route
            ) {
                nestedDestination.children.forEach { child ->
                    composable(route = child.route) {
                        child.composable(it, paddingValues, activityViewModel, navController)
                    }
                }
            }
        }
    }
}
