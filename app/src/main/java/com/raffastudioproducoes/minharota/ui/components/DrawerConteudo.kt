package com.raffastudioproducoes.minharota.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.AccountBalanceWallet
import androidx.compose.material.icons.rounded.BarChart
import androidx.compose.material.icons.rounded.Inventory2
import androidx.compose.material.icons.rounded.MoneyOff
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Today
import androidx.compose.material.icons.rounded.TwoWheeler
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
                Icon(Icons.Rounded.Close, contentDescription = "Fechar Menu")
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
        DrawerItem(Icons.Rounded.ReceiptLong, "Extrato") {
            navController.navigate(Rota.Extrato.route)
            onClose()
        }
        DrawerItem(Icons.Rounded.MoneyOff, "Dívidas") {
            navController.navigate(Rota.Dividas.route)
            onClose()
        }
        DrawerItem(Icons.Rounded.TwoWheeler, "Garagem") {
            navController.navigate(Rota.Garagem.route)
            onClose()
        }
        DrawerItem(Icons.Rounded.Settings, "Configurações") {
            navController.navigate(Rota.Configuracoes.route)
            onClose()
        }
        DrawerItem(Icons.Rounded.Person, "Perfil") {
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
