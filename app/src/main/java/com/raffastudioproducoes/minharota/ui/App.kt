package com.raffastudioproducoes.minharota.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.raffastudioproducoes.minharota.ui.components.ScaffoldPrincipal
import com.raffastudioproducoes.minharota.ui.navigation.Rota
import com.raffastudioproducoes.minharota.ui.screens.hoje.HojeScreen
import com.raffastudioproducoes.minharota.ui.screens.caixas.CaixasScreen
import com.raffastudioproducoes.minharota.ui.screens.contas.ContasScreen
import com.raffastudioproducoes.minharota.ui.screens.graficos.GraficosScreen

@Composable
fun MainAppContent() {
    val navController = rememberNavController()

    ScaffoldPrincipal(navController = navController) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Rota.Hoje.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Rota.Hoje.route) { HojeScreen() }
            composable(Rota.Contas.route) { ContasScreen() }
            composable(Rota.Caixas.route) { CaixasScreen() }
            composable(Rota.Graficos.route) { GraficosScreen() }
        }
    }
}

@Composable
fun ScreenPlaceholder(name: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
