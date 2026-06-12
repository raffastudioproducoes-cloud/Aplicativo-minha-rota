package com.raffastudioproducoes.minharota.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Inventory2
import androidx.compose.material.icons.rounded.MoneyOff
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Today
import androidx.compose.material.icons.rounded.TwoWheeler
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.raffastudioproducoes.minharota.data.local.SharedPreferencesManager
import com.raffastudioproducoes.minharota.ui.navigation.Rota
import com.raffastudioproducoes.minharota.ui.theme.CyanBright
import com.raffastudioproducoes.minharota.ui.theme.ElectricBlue
import com.raffastudioproducoes.minharota.ui.theme.OrangeWarm
import com.raffastudioproducoes.minharota.ui.theme.PinkVibrant
import com.raffastudioproducoes.minharota.ui.theme.TealAccent
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada

@Composable
fun DrawerConteudoGradientRainbowV2(
    navController: NavController,
    onClose: () -> Unit,
    prefsManager: SharedPreferencesManager
) {
    ModalDrawerSheet(
        modifier = Modifier.fillMaxHeight(),
        drawerContainerColor = MaterialTheme.colorScheme.surface
    ) {
        // Cabeçalho do Perfil
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Foto de Perfil (Placeholder com Avatar)
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = MaterialTheme.colorScheme.primaryContainer,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Rounded.Person,
                    contentDescription = "Foto de Perfil",
                    modifier = Modifier.size(48.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nome do Usuário
            val nomeUsuario = prefsManager.obterNomeUsuario()
            Text(
                text = nomeUsuario,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Tag de Assinatura (PRO/FREE)
            val isPro = prefsManager.obterIsPro()
            Box(
                modifier = Modifier
                    .background(
                        color = if (isPro) VerdeEntrada.copy(alpha = 0.2f) else Color.Gray.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isPro) "PRO" else "FREE",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = if (isPro) VerdeEntrada else Color.Gray,
                    fontSize = 10.sp
                )
            }
        }

        Divider(modifier = Modifier.padding(horizontal = 16.dp))

        Spacer(modifier = Modifier.height(16.dp))

        // Botão de Fechar (X) no Topo Direito
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onClose) {
                Icon(Icons.Rounded.Close, contentDescription = "Fechar Menu")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Navegação Principal
        Text(
            text = "Navegação Principal",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // Itens Principais com Gradientes Arco-Íris Translúcidos (Pílula Esticada)
        val mainItems = listOf(
            Triple(Icons.Rounded.Person, "Perfil", Brush.linearGradient(
                colors = listOf(VerdeEntrada, VerdeEntrada.copy(alpha = 0.5f), Color.Transparent)
            )),
            Triple(Icons.Rounded.Today, "Hoje", Brush.linearGradient(
                colors = listOf(TealAccent, TealAccent.copy(alpha = 0.5f), Color.Transparent)
            )),
            Triple(Icons.Rounded.AccountBalanceWallet, "Contas", Brush.linearGradient(
                colors = listOf(ElectricBlue, ElectricBlue.copy(alpha = 0.5f), Color.Transparent)
            )),
            Triple(Icons.Rounded.Inventory2, "Caixas", Brush.linearGradient(
                colors = listOf(PinkVibrant, PinkVibrant.copy(alpha = 0.5f), Color.Transparent)
            )),
            Triple(Icons.Rounded.BarChart, "Gráficos", Brush.linearGradient(
                colors = listOf(VerdeEntrada, VerdeEntrada.copy(alpha = 0.5f), Color.Transparent)
            ))
        )

        mainItems.forEach { (icon, title, gradient) ->
            DrawerItemGradientRainbowV2(icon, title, gradient) {
                val route = when (title) {
                    "Perfil" -> Rota.Perfil.route
                    "Hoje" -> Rota.Hoje.route
                    "Contas" -> Rota.Contas.route
                    "Caixas" -> Rota.Caixas.route
                    "Gráficos" -> Rota.Graficos.route
                    else -> Rota.Hoje.route
                }
                navController.navigate(route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
                onClose()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Outros",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        // Itens Secundários com Gradientes Arco-Íris Translúcidos (Pílula Esticada)
        val secondaryItems = listOf(
            Triple(Icons.Rounded.ReceiptLong, "Extrato", Brush.linearGradient(
                colors = listOf(CyanBright, CyanBright.copy(alpha = 0.5f), Color.Transparent)
            )),
            Triple(Icons.Rounded.MoneyOff, "Dívidas", Brush.linearGradient(
                colors = listOf(OrangeWarm, OrangeWarm.copy(alpha = 0.5f), Color.Transparent)
            )),
            Triple(Icons.Rounded.TwoWheeler, "Garagem", Brush.linearGradient(
                colors = listOf(VerdeEntrada, VerdeEntrada.copy(alpha = 0.5f), Color.Transparent)
            )),
            Triple(Icons.Rounded.Settings, "Configurações", Brush.linearGradient(
                colors = listOf(ElectricBlue, ElectricBlue.copy(alpha = 0.5f), Color.Transparent)
            ))
        )

        secondaryItems.forEach { (icon, title, gradient) ->
            DrawerItemGradientRainbowV2(icon, title, gradient) {
                val route = when (title) {
                    "Extrato" -> Rota.Extrato.route
                    "Dívidas" -> Rota.Dividas.route
                    "Garagem" -> Rota.Garagem.route
                    "Configurações" -> Rota.Configuracoes.route
                    else -> Rota.Hoje.route
                }
                navController.navigate(route) {
                    popUpTo(navController.graph.startDestinationId) { saveState = true }
                    launchSingleTop = true
                    restoreState = true
                }
                onClose()
            }
        }
    }
}

@Composable
fun DrawerItemGradientRainbowV2(
    icon: ImageVector,
    title: String,
    gradient: Brush,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .background(brush = gradient, shape = RoundedCornerShape(50.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(24.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}
