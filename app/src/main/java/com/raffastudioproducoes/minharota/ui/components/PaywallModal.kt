package com.raffastudioproducoes.minharota.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun PaywallModal(onDismiss: () -> Unit, onUpgrade: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.4f))
        ) {
            // Efeito de Glassmorphism (Blur) no fundo
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .blur(16.dp)
            )

            Column(
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(24.dp))
                    .background(Color(0xFF121214))
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "MinhaRota PRO",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "R$ 9,99/mês",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF10B981),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Benefícios
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                ) {
                    BenefitItem("Caixinhas Ilimitadas")
                    BenefitItem("OCR Nativo de Ganhos")
                    BenefitItem("Tracker de Eficiência (Km/L)")
                    BenefitItem("Sem Anúncios")
                }

                // Seletor de Pagamento
                PaymentMethodSelector()

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onUpgrade,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF10B981))
                ) {
                    Text("Assinar Agora", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                
                TextButton(onClick = onDismiss) {
                    Text("Agora não", color = Color.White.copy(alpha = 0.6f))
                }
            }
        }
    }
}

@Composable
fun BenefitItem(text: String) {
    Row(
        modifier = Modifier.padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(Icons.Rounded.CheckCircle, contentDescription = null, tint = Color(0xFF10B981), modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, color = Color.White, fontSize = 14.sp)
    }
}

@Composable
fun PaymentMethodSelector() {
    val methods = listOf(
        Pair("PIX", Icons.Rounded.QrCode),
        Pair("Cartão", Icons.Rounded.CreditCard),
        Pair("G Pay", Icons.Rounded.AccountBalanceWallet),
        Pair("Boleto", Icons.Rounded.ReceiptLong)
    )
    var selected by remember { mutableStateOf("PIX") }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(methods) { (name, icon) ->
            val isSelected = selected == name
            val borderColor by animateColorAsState(
                if (isSelected) Color(0xFF10B981) else Color.White.copy(alpha = 0.1f)
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isSelected) Color(0xFF10B981).copy(alpha = 0.1f) else Color.Transparent)
                    .border(
                        width = if (isSelected) 2.dp else 1.dp,
                        brush = if (isSelected) {
                            Brush.sweepGradient(listOf(Color(0xFF3B82F6), Color(0xFF10B981), Color(0xFF3B82F6)))
                        } else {
                            Brush.linearGradient(listOf(borderColor, borderColor))
                        },
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { selected = name }
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Icon(icon, contentDescription = null, tint = if (isSelected) Color(0xFF10B981) else Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = name, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
