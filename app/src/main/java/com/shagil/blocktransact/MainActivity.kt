package com.shagil.blocktransact

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Money
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

import com.shagil.blocktransact.presentation.main_screen.BottomNavItem
import com.shagil.blocktransact.presentation.main_screen.BottomNavigationBar
import com.shagil.blocktransact.presentation.main_screen.Navigation


import com.shagil.blocktransact.ui.BlockTransactAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val TAG = "MainAct"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            BlockTransactAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    /*DestinationsNavHost(navGraph = NavGraphs.root)*/
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = listOf(
                                    BottomNavItem(
                                        name = "Transactions",
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

            }
        }
    }
}