package com.raffastudioproducoes.minharota.ui.screens.contas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.raffastudioproducoes.minharota.ui.components.CardConta
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada

data class ContaMock(
    val id: Int,
    val nome: String,
    val data: String,
    val valor: Double,
    val pago: Boolean
)

@Composable
fun ContasScreen() {
    var contas by remember {
        mutableStateOf(
            listOf(
                ContaMock(1, "Aluguel da Moto", "10/06", 450.0, false),
                ContaMock(2, "Plano de Dados", "15/06", 50.0, false),
                ContaMock(3, "Seguro", "05/06", 120.0, true)
            )
        )
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Card de Destaque: Meta Diária
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Meta Diária Automática",
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "R$ 85,00",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Text(
                    text = "Baseado em suas contas fixas",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.6f)
                )
            }
        }

        Text(
            text = "Minhas Contas",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(contas) { conta ->
                CardConta(
                    nome = conta.nome,
                    dataVencimento = conta.data,
                    valor = conta.valor,
                    pago = conta.pago,
                    onTogglePago = {
                        contas = contas.map {
                            if (it.id == conta.id) it.copy(pago = !it.pago) else it
                        }
                    }
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = { /* Ação futura */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Adicionar Conta")
                }
            }
        }
    }
}
