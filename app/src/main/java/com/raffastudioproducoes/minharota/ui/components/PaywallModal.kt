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
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.CreditCard
import androidx.compose.material.icons.rounded.QrCode
import androidx.compose.material.icons.rounded.ReceiptLong
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.raffastudioproducoes.minharota.ui.theme.CyanBright
import com.raffastudioproducoes.minharota.ui.theme.ElectricBlue
import com.raffastudioproducoes.minharota.ui.theme.FundoDark
import com.raffastudioproducoes.minharota.ui.theme.GlassDark
import com.raffastudioproducoes.minharota.ui.theme.HaloBlue
import com.raffastudioproducoes.minharota.ui.theme.OrangeWarm
import com.raffastudioproducoes.minharota.ui.theme.PinkVibrant
import com.raffastudioproducoes.minharota.ui.theme.RoxoPrimary
import com.raffastudioproducoes.minharota.ui.theme.TealAccent
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada

@Composable
fun PaywallModal(onDismiss: () -> Unit, onUpgrade: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
                .blur(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.9f)
                    .clip(RoundedCornerShape(24.dp))
                    .background(FundoDark)
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
                    text = "R$ 14,90/mês",
                    fontSize = 42.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = VerdeEntrada,
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Tabela de Benefícios
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 24.dp)
                ) {
                    BenefitItem(text = "Caixinhas Ilimitadas", icon = Icons.Rounded.CheckCircle)
                    BenefitItem(text = "Rendimento Simulado", icon = Icons.Rounded.CheckCircle)
                    BenefitItem(text = "Analytics Avançados", icon = Icons.Rounded.CheckCircle)
                    BenefitItem(text = "Suporte Prioritário", icon = Icons.Rounded.CheckCircle)
                }

                // Cards de Métodos de Pagamento
                PaymentMethodSelector()

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onUpgrade,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = VerdeEntrada)
                ) {
                    Text("Assinar Agora", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(12.dp))
                TextButton(onClick = onDismiss) {
                    Text("Agora não", color = Color.White.copy(alpha = 0.7f))
                }
            }
        }
    }
}

@Composable
fun BenefitItem(text: String, icon: ImageVector) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = VerdeEntrada,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = text,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
fun PaymentMethodSelector() {
    val paymentMethods = listOf(
        PaymentMethod("PIX", Icons.Rounded.QrCode, listOf(TealAccent, CyanBright)),
        PaymentMethod("Cartão", Icons.Rounded.CreditCard, listOf(PinkVibrant, RoxoPrimary)),
        PaymentMethod("Google Pay", Icons.Rounded.ShoppingCart, listOf(VerdeEntrada, ElectricBlue)),
        PaymentMethod("Boleto", Icons.Rounded.ReceiptLong, listOf(OrangeWarm, Color.Yellow))
    )
    var selectedMethod by remember { mutableStateOf<PaymentMethod?>(null) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(paymentMethods) { method ->
            val isSelected = selectedMethod == method
            val borderColor by animateColorAsState(
                targetValue = if (isSelected) method.gradientColors.last() else Color.Transparent,
                animationSpec = tween(300),
                label = "borderColorAnimation"
            )
            val backgroundColor by animateColorAsState(
                targetValue = if (isSelected) method.gradientColors.first().copy(alpha = 0.2f) else FundoDark.copy(alpha = 0.5f),
                animationSpec = tween(300),
                label = "backgroundColorAnimation"
            )
            val borderWidth by animateDpAsState(
                targetValue = if (isSelected) 2.dp else 0.dp,
                animationSpec = tween(300),
                label = "borderWidthAnimation"
            )

            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(backgroundColor)
                    .border(borderWidth, borderColor, RoundedCornerShape(16.dp))
                    .clickable { selectedMethod = method }
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = method.icon,
                    contentDescription = method.name,
                    tint = if (isSelected) method.gradientColors.last() else Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.size(36.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = method.name,
                    color = if (isSelected) method.gradientColors.last() else Color.White,
                    fontWeight = FontWeight.Medium,
                    fontSize = 14.sp
                )
            }
        }
    }
}

data class PaymentMethod(
    val name: String,
    val icon: ImageVector,
    val gradientColors: List<Color>
)
