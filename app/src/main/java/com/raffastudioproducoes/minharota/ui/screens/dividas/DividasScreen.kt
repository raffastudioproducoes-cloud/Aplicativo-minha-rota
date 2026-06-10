package com.raffastudioproducoes.minharota.ui.screens.dividas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raffastudioproducoes.minharota.domain.model.Divida
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada

@Composable
fun DividasScreen(viewModel: DividasViewModel = viewModel()) {
    val context = LocalContext.current
    val dividas by viewModel.dividas.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.carregarDividas(context)
    }

    val totalEmAberto = dividas.sumOf { it.valorTotal - it.valorPago }
    val totalPago = dividas.sumOf { it.valorPago }

    Column(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Minhas Dívidas",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )
            // TODO: Adicionar botão para adicionar nova dívida aqui, se necessário

        // Card de Totalizadores
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Em aberto", style = MaterialTheme.typography.labelSmall)
                Text(
                    text = "R$ ${String.format("%.2f", dividas.sumOf { it.valorTotal - it.valorPago })}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFE57373)
                )
                }
                VerticalDivider(modifier = Modifier.height(40.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Total Pago", style = MaterialTheme.typography.labelSmall)
                Text(
                    text = "R$ ${String.format("%.2f", dividas.sumOf { it.valorPago })}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = VerdeEntrada
                )
                }
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(dividas) { divida ->
                CardDivida(
                    divida = divida,
                    onPagarParcela = { id, valor -> viewModel.pagarParcela(context, id, valor) },
                    onQuitarDivida = { id -> viewModel.quitarDivida(context, id) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = { /* TODO: Implementar adicionar dívida */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Adicionar Dívida")
                }
            }
        }
    }
}

@Composable
fun CardDivida(divida: Divida, onPagarParcela: (String, Double) -> Unit, onQuitarDivida: (String) -> Unit) {
    val progresso = (divida.valorPago / divida.valorTotal).toFloat()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp, horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = divida.credor, fontWeight = FontWeight.Bold)
                Text(
                    text = "R$ ${String.format("%.2f", divida.valorTotal)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            LinearProgressIndicator(
                progress = progresso,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp)
                    .clip(RoundedCornerShape(4.dp)),
                color = VerdeEntrada,
                trackColor = VerdeEntrada.copy(alpha = 0.2f)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Pago: R$ ${String.format("%.2f", divida.valorPago)}",
                    style = MaterialTheme.typography.labelSmall,
                    color = VerdeEntrada
                )
                Text(
                    text = "${(progresso * 100).toInt()}%",
                    style = MaterialTheme.typography.labelSmall
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* TODO: Implementar modal para pagar parcela */ onPagarParcela(divida.id, 0.0) },
                    enabled = divida.valorPago < divida.valorTotal,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Pagar Parcela")
                }
                Button(
                    onClick = { onQuitarDivida(divida.id) },
                    enabled = divida.valorPago < divida.valorTotal,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.tertiary)
                ) {
                    Text("Quitar")
                }
            }
        }
    }
}
