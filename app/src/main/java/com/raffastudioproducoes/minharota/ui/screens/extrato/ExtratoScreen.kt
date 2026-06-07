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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada

data class MovimentacaoMock(
    val tipo: String, // "ENTRADA" ou "SAIDA"
    val descricao: String,
    val data: String,
    val valor: Double
)

@Composable
fun ExtratoScreen() {
    val movimentacoes = remember {
        listOf(
            MovimentacaoMock("ENTRADA", "Ganho do Dia", "Hoje", 150.0),
            MovimentacaoMock("SAIDA", "Gasolina", "Hoje", 45.0),
            MovimentacaoMock("ENTRADA", "Ganho do Dia", "Ontem", 185.0),
            MovimentacaoMock("SAIDA", "Lanche", "Ontem", 22.0)
        )
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
            FilterChip(selected = true, onClick = {}, label = { Text("7 dias") })
            FilterChip(selected = false, onClick = {}, label = { Text("15 dias") })
            FilterChip(selected = false, onClick = {}, label = { Text("Mês") })
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
fun CardMovimentacao(mov: MovimentacaoMock) {
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
