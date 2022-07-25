package com.shagil.blocktransact.presentation.main_screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.shagil.blocktransact.presentation.transaction_info.TransactionInfoScreen
import com.shagil.blocktransact.presentation.transaction_info_set.TransactionInfoSetScreen
import com.shagil.blocktransact.presentation.transactions_listing.TransactionListingsScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = "transaction_listings") {
        composable("transaction_listings") {
            TransactionListingsScreen(navController = navController)
        }
        composable("transaction_set") {
            TransactionInfoSetScreen()
        }
        composable(
            route = "transaction_info/{amount}/{index}",
            arguments = listOf(navArgument("amount"){
                type = NavType.StringType
            },
            navArgument("index") {
                type = NavType.IntType
            })
        ) {
            val amount = it.arguments?.getString("amount").toString()
            Log.e("amount: ", amount)
            TransactionInfoScreen(amount = amount)
        }
    }
}