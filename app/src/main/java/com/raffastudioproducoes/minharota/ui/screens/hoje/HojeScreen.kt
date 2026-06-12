package com.raffastudioproducoes.minharota.ui.screens.hoje

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada

@Composable
fun HojeScreen(viewModel: HojeViewModel = viewModel()) {
    val context = LocalContext.current
    
    val ganhoBruto by viewModel.ganhoBruto.collectAsState()
    val custoRua by viewModel.custoRua.collectAsState()
    val ganhoLiquido by viewModel.ganhoLiquido.collectAsState()
    val valorPorHora by viewModel.valorPorHora.collectAsState()
    val horasTrabalhadas by viewModel.horasTrabalhadas.collectAsState()
    val isRidingMode by viewModel.isRidingMode.collectAsState()
    val ganhosRapidos by viewModel.ganhosRapidos.collectAsState()

    var mostrarModalRapido by remember { mutableStateOf(false) }

    if (isRidingMode) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0F172A)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "R$ ${String.format("%.2f", ganhoLiquido)}",
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold,
                    color = VerdeEntrada
                )
                Spacer(modifier = Modifier.height(32.dp))
                Button(
                    onClick = { mostrarModalRapido = true },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .height(64.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = VerdeEntrada)
                ) {
                    Text("Ganho Rápido", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                }
                Spacer(modifier = Modifier.height(16.dp))
                TextButton(onClick = { viewModel.toggleRidingMode() }) {
                    Text("Sair do Modo Riding", color = Color.White.copy(alpha = 0.7f))
                }
            }

            if (mostrarModalRapido) {
                com.raffastudioproducoes.minharota.ui.components.ModalRegistroRapido(
                    onDismiss = { mostrarModalRapido = false },
                    onSave = { valor ->
                        viewModel.registrarGanhoRapido(valor)
                        mostrarModalRapido = false
                    }
                )
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(top = 16.dp, bottom = 100.dp)
        ) {
            item {
                // Card Principal Glassmorphism
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.12f),
                                    Color.White.copy(alpha = 0.04f)
                                )
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Ganho Acumulado do Dia",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color.White.copy(alpha = 0.6f)
                        )
                        Text(
                            text = "R$ ${String.format("%.2f", ganhoLiquido)}",
                            fontSize = 42.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = VerdeEntrada
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            MiniInfo(label = "Horas", value = horasTrabalhadas)
                            Divider(modifier = Modifier.height(24.dp).width(1.dp), color = Color.White.copy(alpha = 0.1f))
                            MiniInfo(label = "R$/Hora", value = "R$ ${String.format("%.2f", valorPorHora)}")
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Histórico de Ganhos",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "${ganhosRapidos.size} entradas",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.White.copy(alpha = 0.5f)
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
            }

            if (ganhosRapidos.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Nenhum ganho registrado ainda.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.White.copy(alpha = 0.3f)
                        )
                    }
                }
            } else {
                items(ganhosRapidos.reversed()) { ganho ->
                    GanhoItem(horario = ganho.horario, valor = ganho.valor)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = { viewModel.toggleRidingMode() },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.05f))
                ) {
                    Text("Entrar no Modo Riding", color = Color.White)
                }
            }
        }
    }
}

@Composable
fun MiniInfo(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = label, style = MaterialTheme.typography.labelSmall, color = Color.White.copy(alpha = 0.4f))
        Text(text = value, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold, color = Color.White)
    }
}

@Composable
fun GanhoItem(horario: String, valor: Double) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.03f)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(VerdeEntrada)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                    text = horario,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
            Text(
                text = "R$ ${String.format("%.2f", valor)}",
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = VerdeEntrada
            )
        }
    }
}
