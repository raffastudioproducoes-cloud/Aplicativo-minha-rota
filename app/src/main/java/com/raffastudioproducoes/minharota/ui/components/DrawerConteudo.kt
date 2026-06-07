package com.raffastudioproducoes.minharota.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DrawerConteudo(onClose: () -> Unit) {
    ModalDrawerSheet(
        modifier = Modifier.fillMaxHeight(),
        drawerContainerColor = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "MinhaRota",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            IconButton(onClick = onClose) {
                Icon(Icons.Default.Close, contentDescription = "Fechar Menu")
            }
        }

        Divider(modifier = Modifier.padding(horizontal = 16.dp))

        Spacer(modifier = Modifier.height(16.dp))

        DrawerItem(Icons.Default.Person, "Perfil", onClose)
        DrawerItem(Icons.Default.ReceiptLong, "Extrato", onClose)
        DrawerItem(Icons.Default.MoneyOff, "Dívidas", onClose)
        DrawerItem(Icons.Default.TwoWheeler, "Garagem", onClose)
        
        Spacer(modifier = Modifier.weight(1f))
        
        DrawerItem(Icons.Default.Settings, "Configurações", onClose)
        
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
