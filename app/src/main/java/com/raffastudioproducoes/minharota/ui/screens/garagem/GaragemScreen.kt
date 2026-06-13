package com.raffastudioproducoes.minharota.ui.screens.garagem

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocalGasStation
import androidx.compose.material.icons.outlined.Motorcycle
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GaragemScreen(viewModel: GaragemViewModel = viewModel()) {
    val kmRodados by viewModel.kmRodados.collectAsState()
    val litrosAbastecidos by viewModel.litrosAbastecidos.collectAsState()
    val mediaKmL by viewModel.mediaResult.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121214))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Eficiência da Moto",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Card Mestre de Desempenho (Glassmorphism)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(Color.White.copy(alpha = 0.05f))
                .border(1.dp, Color.White.copy(alpha = 0.1f), RoundedCornerShape(24.dp))
        ) {
            // Efeito de Blur no fundo do card
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(16.dp)
            )

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Média Atual",
                    color = Color.White.copy(alpha = 0.6f),
                    fontSize = 14.sp
                )
                Text(
                    text = "${String.format("%.1f", mediaKmL)} Km/L",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = if (mediaKmL > 0) Color(0xFF10B981) else Color(0xFF3B82F6)
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Inputs de Abastecimento
        EfficiencyInput(
            label = "Quilômetros Rodados",
            value = kmRodados,
            onValueChange = { viewModel.updateKm(it) },
            icon = Icons.Outlined.Motorcycle,
            suffix = "KM"
        )

        Spacer(modifier = Modifier.height(16.dp))

        EfficiencyInput(
            label = "Litros Abastecidos",
            value = litrosAbastecidos,
            onValueChange = { viewModel.updateLitros(it) },
            icon = Icons.Outlined.LocalGasStation,
            suffix = "L"
        )
    }
}

@Composable
fun EfficiencyInput(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    suffix: String
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50.dp)),
        label = { Text(label, color = Color.Gray) },
        leadingIcon = { Icon(icon, contentDescription = null, tint = Color.Gray) },
        suffix = { Text(suffix, color = Color.Gray) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
        singleLine = true,
        shape = RoundedCornerShape(50.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF3B82F6),
            unfocusedBorderColor = Color.White.copy(alpha = 0.1f),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            cursorColor = Color(0xFF3B82F6)
        )
    )
}
