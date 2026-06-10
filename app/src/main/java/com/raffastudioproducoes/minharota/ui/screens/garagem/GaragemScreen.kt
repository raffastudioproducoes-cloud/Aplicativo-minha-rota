package com.raffastudioproducoes.minharota.ui.screens.garagem

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raffastudioproducoes.minharota.domain.model.Veiculo

@Composable
fun GaragemScreen(viewModel: GaragemViewModel = viewModel()) {
    val context = LocalContext.current
    val veiculo by viewModel.veiculo.collectAsState()

    var kmAtualInput by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.carregarVeiculo(context)
    }

    LaunchedEffect(veiculo) {
        veiculo?.quilometragemAtual?.let { kmAtualInput = it.toString() }
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
                        value = kmAtualInput,
                        onValueChange = { kmAtualInput = it },
                        modifier = Modifier.weight(1f),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        suffix = { Text("KM") }
                    )
                    Button(onClick = {
                        kmAtualInput.toDoubleOrNull()?.let { newKm ->
                            viewModel.atualizarQuilometragem(context, newKm)
                        }
                    }) {
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
            veiculo?.manutencoes?.let { manutencoes ->
                items(manutencoes) { manutencao ->
                    val kmRestante = (manutencao.proximoServicoKm - (veiculo?.quilometragemAtual ?: 0.0)).toInt()
                    val cardColor = when {
                        kmRestante <= 0 -> MaterialTheme.colorScheme.error.copy(alpha = 0.3f) // Crítico
                        kmRestante < 100 -> MaterialTheme.colorScheme.tertiary.copy(alpha = 0.3f) // Alerta (Amarelo)
                        else -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f)
                    }
                    val textColor = when {
                        kmRestante <= 0 -> MaterialTheme.colorScheme.error
                        kmRestante < 100 -> MaterialTheme.colorScheme.tertiary
                        else -> MaterialTheme.colorScheme.onSurface
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp, horizontal = 16.dp),
                        colors = CardDefaults.cardColors(containerColor = cardColor)
                    ) {
                        Row(
                            modifier = Modifier.padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "🔧", style = MaterialTheme.typography.headlineSmall) // TODO: Adicionar emoji dinâmico
                            Spacer(modifier = Modifier.width(16.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                Text(text = manutencao.tipo, fontWeight = FontWeight.Bold)
                                Text(
                                    text = if (kmRestante <= 0) "Vencido!" else "Faltam ${kmRestante} km",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = textColor
                                )
                            }
                            Button(onClick = { /* TODO: Implementar registro de manutenção */ }) {
                                Text("Registrar")
                            }
                        }
                    }
                }
            }
        }
    }
}
