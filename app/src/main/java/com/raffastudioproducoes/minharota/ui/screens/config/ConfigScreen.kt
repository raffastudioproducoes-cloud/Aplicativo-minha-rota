package com.raffastudioproducoes.minharota.ui.screens.config

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Backup
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ConfigScreen() {
    var darkMode by remember { mutableStateOf(true) }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Configurações",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        ListItem(
            headlineContent = { Text("Tema Escuro") },
            supportingContent = { Text("Ativar/Desativar modo dark") },
            leadingContent = { Icon(Icons.Rounded.Palette, contentDescription = null) },
            trailingContent = {
                Switch(checked = darkMode, onCheckedChange = { darkMode = it })
            }
        )

        Divider()

        ListItem(
            headlineContent = { Text("Backup e Restauração") },
            supportingContent = { Text("Sincronizar dados na nuvem") },
            leadingContent = { Icon(Icons.Rounded.Backup, contentDescription = null) },
            modifier = Modifier.padding(vertical = 8.dp)
        )

        Divider()

        ListItem(
            headlineContent = { Text("Sobre o Aplicativo") },
            supportingContent = { Text("Versão 1.0.0-fase9") },
            leadingContent = { Icon(Icons.Rounded.Info, contentDescription = null) }
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = "MinhaRota © 2026",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 24.dp),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
        )
    }
}
