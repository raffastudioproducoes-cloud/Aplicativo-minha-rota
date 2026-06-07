package com.raffastudioproducoes.minharota.ui.screens.garagem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

data class AlertaManutencao(val emoji: String, val nome: String, val kmRestante: Int)

@Composable
fun GaragemScreen() {
    var kmAtual by remember { mutableStateOf("12500") }
    val alertas = remember {
        listOf(
            AlertaManutencao("🛢️", "Troca de Óleo", 500),
            AlertaManutencao("🔧", "Relação", 1200),
            AlertaManutencao("🛑", "Pastilha de Freio", 2500)
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Minha Garagem",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        // Card de Quilometragem
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Quilometragem Atual",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = kmAtual,
                        onValueChange = { kmAtual = it },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        suffix = { Text("KM") }
                    )
                    Button(onClick = { /* Atualizar */ }) {
                        Text("Atualizar")
                    }
                }
            }
        }

        Text(
            text = "Alertas de Manutenção",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(alertas) { alerta ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp, horizontal = 16.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = alerta.emoji, style = MaterialTheme.typography.headlineSmall)
                        Spacer(modifier = Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = alerta.nome, fontWeight = FontWeight.Bold)
                            Text(
                                text = "Faltam ${alerta.kmRestante} km",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (alerta.kmRestante < 600) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}
