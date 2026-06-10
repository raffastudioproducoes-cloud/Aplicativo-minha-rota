package com.raffastudioproducoes.minharota.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Inventory2
import androidx.compose.material.icons.filled.MoneyOff
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Today
import com.raffastudioproducoes.minharota.ui.navigation.itensNavegacao
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.raffastudioproducoes.minharota.ui.navigation.Rota

@Composable
fun DrawerConteudo(navController: NavController, onClose: () -> Unit) {
    ModalDrawerSheet(
        modifier = Modifier.fillMaxHeight(),
        drawerContainerColor = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Fechar Menu")
            }
        }
        
        Text(
            text = "MinhaRota",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Divider(modifier = Modifier.padding(horizontal = 16.dp))

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Navegação Principal",
            style = MaterialTheme.typography.labelLarge,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        itensNavegacao.forEach { item ->
            DrawerItem(item.icon, item.title) {
                navController.navigate(item.route) {
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
        DrawerItem(Icons.Default.ReceiptLong, "Extrato") {
            navController.navigate(Rota.Extrato.route)
            onClose()
        }
        DrawerItem(Icons.Default.MoneyOff, "Dívidas") {
            navController.navigate(Rota.Dividas.route)
            onClose()
        }
        DrawerItem(Icons.Default.TwoWheeler, "Garagem") {
            navController.navigate(Rota.Garagem.route)
            onClose()
        }
        DrawerItem(Icons.Default.Settings, "Configurações") {
            navController.navigate(Rota.Configuracoes.route)
            onClose()
        }
        DrawerItem(Icons.Default.Person, "Perfil") {
            navController.navigate(Rota.Perfil.route)
            onClose()
        }

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun DrawerItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    NavigationDrawerItem(
        icon = { Icon(icon, contentDescription = null) },
        label = { Text(label) },
        selected = false,
        onClick = onClick,
        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
    )
}
