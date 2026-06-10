package com.raffastudioproducoes.minharota.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Inventory2
import androidx.compose.material.icons.rounded.Today
import androidx.compose.material.icons.rounded.TwoWheeler
import androidx.compose.material.icons.rounded.MoneyOff
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Rota(val route: String, val title: String, val icon: ImageVector) {
    object Hoje : Rota("hoje", "Hoje", Icons.Rounded.Today)
    object Contas : Rota("contas", "Contas", Icons.Rounded.AccountBalanceWallet)
    object Caixas : Rota("caixas", "Caixas", Icons.Rounded.Inventory2)
    object Graficos : Rota("graficos", "Gráficos", Icons.Rounded.BarChart)
    object Garagem : Rota("garagem", "Garagem", Icons.Rounded.TwoWheeler)
    object Extrato : Rota("extrato", "Extrato", Icons.Rounded.ReceiptLong)
    object Dividas : Rota("dividas", "Dívidas", Icons.Rounded.MoneyOff)
    object Perfil : Rota("perfil", "Perfil", Icons.Rounded.Person)
    object Configuracoes : Rota("configuracoes", "Configurações", Icons.Rounded.Settings)
}

val itensNavegacao = listOf(
    Rota.Hoje,
    Rota.Contas,
    Rota.Caixas,
    Rota.Graficos
)
