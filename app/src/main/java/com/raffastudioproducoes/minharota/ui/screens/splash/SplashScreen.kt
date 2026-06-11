package com.raffastudioproducoes.minharota.ui.screens.splash

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raffastudioproducoes.minharota.ui.theme.FundoDark
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashComplete: () -> Unit) {
    var showDots by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(3000)
        onSplashComplete()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoDark),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo (texto em estilo vazado/outline)
            Text(
                text = "◯",
                fontSize = 64.sp,
                fontWeight = FontWeight.Thin,
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Nome do App
            Text(
                text = "MinhaRota",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            // Animação dos 3 Pontinhos Gemini
            if (showDots) {
                GeminiDots()
            }
        }
    }
}

@Composable
fun GeminiDots() {
    val infiniteTransition = rememberInfiniteTransition(label = "gemini_dots")

    // Animação para o primeiro ponto
    val dot1Scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "dot1_scale"
    )

    // Animação para o segundo ponto (delay de 200ms)
    val dot2Scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, delayMillis = 200, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "dot2_scale"
    )

    // Animação para o terceiro ponto (delay de 400ms)
    val dot3Scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.5f,
        animationSpec = infiniteRepeatable(
            animation = tween(600, delayMillis = 400, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Reverse
        ),
        label = "dot3_scale"
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Ponto 1
        Box(
            modifier = Modifier
                .size(12.dp * dot1Scale)
                .background(Color.White, shape = androidx.compose.foundation.shape.CircleShape)
        )

        // Ponto 2
        Box(
            modifier = Modifier
                .size(12.dp * dot2Scale)
                .background(Color.White, shape = androidx.compose.foundation.shape.CircleShape)
        )

        // Ponto 3
        Box(
            modifier = Modifier
                .size(12.dp * dot3Scale)
                .background(Color.White, shape = androidx.compose.foundation.shape.CircleShape)
        )
    }
}
