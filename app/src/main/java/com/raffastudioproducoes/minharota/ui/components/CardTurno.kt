package com.raffastudioproducoes.minharota.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada

@Composable
fun CardTurno(
    horasTrabalhadas: String,
    ganhoBruto: Double,
    custoRua: Double,
    ganhoLiquido: Double
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Resumo do Turno",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
            )
            
            Spacer(modifier = Modifier.height(8.dp))
            
            Text(
                text = "R$ ${String.format("%.2f", ganhoLiquido)}",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color = VerdeEntrada
            )
            
            Text(
                text = "$horasTrabalhadas trabalhadas",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ResumoItem(
                    label = "Bruto",
                    valor = "R$ ${String.format("%.2f", ganhoBruto)}",
                    cor = MaterialTheme.colorScheme.onSurface
                )
                
                VerticalDivider(modifier = Modifier.height(40.dp))
                
                ResumoItem(
                    label = "Custos",
                    valor = "R$ ${String.format("%.2f", custoRua)}",
                    cor = Color(0xFFE57373) // Vermelho para custos
                )
            }
        }
    }
}

@Composable
fun ResumoItem(label: String, valor: String, cor: Color) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
        )
        Text(
            text = valor,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold,
            color = cor
        )
    }
}
