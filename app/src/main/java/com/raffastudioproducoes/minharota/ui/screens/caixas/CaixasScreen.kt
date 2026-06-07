package com.raffastudioproducoes.minharota.ui.screens.caixas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.raffastudioproducoes.minharota.ui.components.CardCaixinha

data class CaixinhaMock(
    val emoji: String,
    val titulo: String,
    val valor: Double,
    val meta: Double,
    val cor: Color
)

@Composable
fun CaixasScreen() {
    val caixinhasSimuladas = remember {
        listOf(
            CaixinhaMock("🏠", "Base de Tudo", 1250.0, 2000.0, Color(0xFF820AD1)),
            CaixinhaMock("🏍️", "Manutenção", 450.0, 800.0, Color(0xFF2ECC71)),
            CaixinhaMock("🎉", "Lazer", 150.0, 500.0, Color(0xFFFFD700))
        )
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Distribuição de Ganhos",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(caixinhasSimuladas) { caixinha ->
                CardCaixinha(
                    emoji = caixinha.emoji,
                    titulo = caixinha.titulo,
                    valorGuardado = caixinha.valor,
                    metaValor = caixinha.meta,
                    corDestaque = caixinha.cor
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
                    Text("Nova Caixinha")
                }
            }
        }
    }
}
