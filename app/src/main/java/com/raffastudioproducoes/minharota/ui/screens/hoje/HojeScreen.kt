package com.raffastudioproducoes.minharota.ui.screens.hoje

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raffastudioproducoes.minharota.ui.components.CardTurno

@Composable
fun HojeScreen(viewModel: HojeViewModel = viewModel()) {
    val context = LocalContext.current
    
    val ganhoBruto by viewModel.ganhoBruto.collectAsState()
    val custoRua by viewModel.custoRua.collectAsState()
    val ganhoLiquido by viewModel.ganhoLiquido.collectAsState()
    
    // Estados locais para o texto do input (evita bugs de digitação com double)
    var ganhoBrutoText by remember { mutableStateOf(if (ganhoBruto == 0.0) "" else ganhoBruto.toString()) }
    var custoRuaText by remember { mutableStateOf(if (custoRua == 0.0) "" else custoRua.toString()) }

    // Sincroniza texto se o valor do ViewModel mudar externamente (ex: Ganho Rápido)
    LaunchedEffect(ganhoBruto) {
        if (ganhoBruto.toString() != ganhoBrutoText) {
            ganhoBrutoText = if (ganhoBruto == 0.0) "" else ganhoBruto.toString()
        }
    }
    
    LaunchedEffect(custoRua) {
        if (custoRua.toString() != custoRuaText) {
            custoRuaText = if (custoRua == 0.0) "" else custoRua.toString()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CardTurno(
            horasTrabalhadas = "00:00",
            ganhoBruto = ganhoBruto,
            custoRua = custoRua,
            ganhoLiquido = ganhoLiquido
        )

        Spacer(modifier = Modifier.height(24.dp))

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
                value = ganhoBrutoText,
                onValueChange = { 
                    ganhoBrutoText = it
                    viewModel.updateGanhoBruto(it.toDoubleOrNull() ?: 0.0)
                },
                label = { Text("Ganho Bruto (R$)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            OutlinedTextField(
                value = custoRuaText,
                onValueChange = { 
                    custoRuaText = it
                    viewModel.updateCustoRua(it.toDoubleOrNull() ?: 0.0)
                },
                label = { Text("Custo de Rua (R$)") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { 
                    viewModel.salvarTurno(context) {
                        Toast.makeText(context, "Turno salvo com sucesso! ✅", Toast.LENGTH_SHORT).show()
                        ganhoBrutoText = ""
                        custoRuaText = ""
                    }
                },
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
