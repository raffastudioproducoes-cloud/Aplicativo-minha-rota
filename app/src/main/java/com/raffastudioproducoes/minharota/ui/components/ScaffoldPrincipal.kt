package com.raffastudioproducoes.minharota.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun ScaffoldPrincipal(
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        bottomBar = {
            CustomBottomNavBar(navController = navController)
        }
    ) { paddingValues ->
        content(paddingValues)
    }
}
