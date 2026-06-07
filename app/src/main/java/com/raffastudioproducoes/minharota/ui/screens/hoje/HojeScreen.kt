package com.raffastudioproducoes.minharota.ui.screens.hoje

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.raffastudioproducoes.minharota.ui.components.CardTurno

@Composable
fun HojeScreen() {
    var ganhoBrutoInput by remember { mutableStateOf("") }
    var custoRuaInput by remember { mutableStateOf("") }
    
    val ganhoBruto = ganhoBrutoInput.toDoubleOrNull() ?: 0.0
    val custoRua = custoRuaInput.toDoubleOrNull() ?: 0.0
    val ganhoLiquido = ganhoBruto - custoRua

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Card de Resumo no Topo
        CardTurno(
            horasTrabalhadas = "00:00",
            ganhoBruto = ganhoBruto,
            custoRua = custoRua,
            ganhoLiquido = ganhoLiquido
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Seção de Inputs
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Registro de Ganhos",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            OutlinedTextField(
                value = ganhoBrutoInput,
                onValueChange = { ganhoBrutoInput = it },
                label = { Text("Ganho Bruto (R$)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            OutlinedTextField(
                value = custoRuaInput,
                onValueChange = { custoRuaInput = it },
                label = { Text("Custo de Rua (R$)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { /* Ação futura de salvar */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = MaterialTheme.shapes.medium,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(
                    text = "Salvar Dia",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
