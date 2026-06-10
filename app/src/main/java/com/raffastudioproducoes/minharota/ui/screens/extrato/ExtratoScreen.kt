package com.raffastudioproducoes.minharota.ui.screens.extrato

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raffastudioproducoes.minharota.domain.model.Movimentacao
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada

@Composable
fun ExtratoScreen(viewModel: ExtratoViewModel = viewModel()) {
    val context = LocalContext.current
    val movimentacoes by viewModel.movimentacoes.collectAsState()
    val filtroSelecionado by viewModel.filtroSelecionado.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.carregarMovimentacoes(context)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Meu Extrato",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        // Filtros em pílula
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(selected = filtroSelecionado == "7 dias", onClick = { viewModel.aplicarFiltro("7 dias") }, label = { Text("7 dias") })
            FilterChip(selected = filtroSelecionado == "15 dias", onClick = { viewModel.aplicarFiltro("15 dias") }, label = { Text("15 dias") })
            FilterChip(selected = filtroSelecionado == "Este mês", onClick = { viewModel.aplicarFiltro("Este mês") }, label = { Text("Este mês") })
            FilterChip(selected = filtroSelecionado == "Todos", onClick = { viewModel.aplicarFiltro("Todos") }, label = { Text("Todos") })
        }

        // Card de Resumo
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ResumoColuna("Entradas", "R$ 335,00", VerdeEntrada)
                ResumoColuna("Saídas", "R$ 67,00", Color(0xFFE57373))
                ResumoColuna("Saldo", "R$ 268,00", MaterialTheme.colorScheme.primary)
            }
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(movimentacoes) { mov ->
                CardMovimentacao(mov)
            }
        }
    }
}

@Composable
fun ResumoColuna(label: String, valor: String, cor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.labelSmall)
        Text(text = valor, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, color = cor)
    }
}

@Composable
fun CardMovimentacao(mov: Movimentacao) {
    ListItem(
        headlineContent = { Text(mov.descricao, fontWeight = FontWeight.Bold) },
        supportingContent = { Text(mov.data) },
        leadingContent = {
            Surface(
                shape = CircleShape,
                color = if (mov.tipo == "ENTRADA") VerdeEntrada.copy(alpha = 0.1f) else Color.Red.copy(alpha = 0.1f),
                modifier = Modifier.size(40.dp)
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = if (mov.tipo == "ENTRADA") Icons.Default.ArrowUpward else Icons.Default.ArrowDownward,
                        contentDescription = null,
                        tint = if (mov.tipo == "ENTRADA") VerdeEntrada else Color.Red,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        },
        trailingContent = {
            Text(
                text = "${if (mov.tipo == "ENTRADA") "+" else "-"} R$ ${String.format("%.2f", mov.valor)}",
                fontWeight = FontWeight.Bold,
                color = if (mov.tipo == "ENTRADA") VerdeEntrada else Color.Red
            )
        }
    )
}
