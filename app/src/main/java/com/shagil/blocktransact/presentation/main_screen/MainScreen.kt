package com.shagil.blocktransact.presentation.main_screen

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Money
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun MainScreen(
    navigator: DestinationsNavigator
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(items = listOf(
                BottomNavItem(
                    name = "All Transactions",
                    route = "transaction_listings",
                    icon = Icons.Default.List
                ),
                BottomNavItem(
                    name = "Transact",
                    route = "transaction_set",
                    icon = Icons.Default.Money
                )
            ),
                navController = navController,
                onItemClick = {
                    navController.navigate(it.route)
                }
            )
        }
    ) {
        Navigation(navController = navController)
    }
}