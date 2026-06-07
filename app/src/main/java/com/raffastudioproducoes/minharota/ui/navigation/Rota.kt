package com.raffastudioproducoes.minharota.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.Today
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Rota(val route: String, val title: String, val icon: ImageVector) {
    object Hoje : Rota("hoje", "Hoje", Icons.Default.Today)
    object Contas : Rota("contas", "Contas", Icons.Default.AccountBalanceWallet)
    object Caixas : Rota("caixas", "Caixas", Icons.Default.Inventory2)
    object Graficos : Rota("graficos", "Gráficos", Icons.Default.BarChart)
    object Garagem : Rota("garagem", "Garagem", Icons.Default.TwoWheeler)
    object Extrato : Rota("extrato", "Extrato", Icons.Default.ReceiptLong)
}

val itensNavegacao = listOf(
    Rota.Hoje,
    Rota.Contas,
    Rota.Caixas,
    Rota.Graficos
)
