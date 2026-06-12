package com.raffastudioproducoes.minharota.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raffastudioproducoes.minharota.R
import com.raffastudioproducoes.minharota.data.local.SharedPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * IMPLEMENTAÇÃO DO MENU DRAWER COM ALINHAMENTO CORRIGIDO,
 * BOTÕES EM FORMATO DE PÍLULA INTEGRAL E CABEÇALHO FINTECH.
 */
@Composable
fun DrawerConteudoGradientRainbowV2(
    drawerState: DrawerState,
    scope: CoroutineScope,
    onNavigate: (String) -> Unit,
    currentRoute: String,
    sharedPreferencesManager: SharedPreferencesManager
) {
    val isPro = sharedPreferencesManager.obterIsPro()
    val nomeUsuario = sharedPreferencesManager.obterNomeUsuario()
    val fotoUrl = sharedPreferencesManager.getString("foto_perfil_url", "")

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(290.dp)
            .background(Color(0xFF121214)) // Grafite Clean Premium
            .statusBarsPadding()
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        // =======================================================
        // 1. CABEÇALHO DO PERFIL (ALINHAMENTO EM GRADE CORRIGIDO)
        // =======================================================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp), // Alinhado perfeitamente na mesma grade esquerda dos botões
            horizontalAlignment = Alignment.CenterHorizontally // Centralizado de forma elegante
        ) {
            // Container do Avatar com borda fina premium
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(Color(0x0DFFFFFF))
                    .clickable {
                        scope.launch { drawerState.close() }
                        onNavigate("perfil")
                    }
            ) {
                if (!fotoUrl.isNullOrEmpty()) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_placeholder_avatar),
                        contentDescription = "Foto de Perfil",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    // Avatar padrão do Material Symbols
                    Icon(
                        imageVector = Icons.Rounded.Person,
                        contentDescription = "Avatar",
                        tint = Color(0xFF3B82F6),
                        modifier = Modifier
                            .size(48.dp)
                            .align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Nome de Usuário de fácil legibilidade
            Text(
                text = nomeUsuario,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Badge Retangular Arredondada (PRO ou FREE)
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50.dp)) // Estilo pílula miniatura
                    .background(if (isPro) Color(0x1F10B981) else Color(0x1AFFFFFF))
                    .padding(horizontal = 14.dp, vertical = 4.dp)
            ) {
                Text(
                    text = if (isPro) "PRO" else "FREE",
                    color = if (isPro) Color(0xFF10B981) else Color.LightGray,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Divider(color = Color(0x0DFFFFFF), modifier = Modifier.padding(horizontal = 24.dp))
        Spacer(modifier = Modifier.height(24.dp))

        // =======================================================
        // 2. BOTÕES EM PÍLULA DO DRAWER (PONTA A PONTA COM MARGEM)
        // =======================================================
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Navegação Principal",
                color = Color.Gray,
                fontSize = 11.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 24.dp, bottom = 4.dp)
            )

            // Perfil em Primeiro Lugar
            DrawerItemPill(
                label = "Perfil",
                icon = Icons.Rounded.Person,
                isSelected = currentRoute == "perfil",
                gradientColors = listOf(Color(0xFF3B82F6), Color.Transparent),
                onClick = {
                    scope.launch { drawerState.close() }
                    onNavigate("perfil")
                }
            )

            // Hoje
            DrawerItemPill(
                label = "Hoje",
                icon = Icons.Rounded.Today,
                isSelected = currentRoute == "hoje",
                gradientColors = listOf(Color(0xFF10B981), Color.Transparent),
                onClick = {
                    scope.launch { drawerState.close() }
                    onNavigate("hoje")
                }
            )

            // Contas
            DrawerItemPill(
                label = "Contas",
                icon = Icons.Rounded.AccountBalanceWallet,
                isSelected = currentRoute == "contas",
                gradientColors = listOf(Color(0xFF3B82F6), Color.Transparent),
                onClick = {
                    scope.launch { drawerState.close() }
                    onNavigate("contas")
                }
            )

            // Caixas
            DrawerItemPill(
                label = "Caixas",
                icon = Icons.Rounded.Inventory2,
                isSelected = currentRoute == "caixas",
                gradientColors = listOf(Color(0xFFEC4899), Color.Transparent),
                onClick = {
                    scope.launch { drawerState.close() }
                    onNavigate("caixas")
                }
            )

            // Gráficos
            DrawerItemPill(
                label = "Gráficos",
                icon = Icons.Rounded.BarChart,
                isSelected = currentRoute == "graficos",
                gradientColors = listOf(Color(0xFF10B981), Color.Transparent),
                onClick = {
                    scope.launch { drawerState.close() }
                    onNavigate("graficos")
                }
            )
        }
    }
}

/**
 * COMPONENTE CORE: ITEM DO DRAWER EM FORMATO PÍLULA (ROUNDED 50.DP)
 * Vai de ponta a ponta com espaçamento lateral elegante.
 */
@Composable
fun DrawerItemPill(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    isSelected: Boolean,
    gradientColors: List<Color>,
    onClick: () -> Unit
) {
    val backgroundBrush = if (isSelected) {
        Brush.horizontalGradient(colors = gradientColors)
    } else {
        Brush.horizontalGradient(colors = listOf(Color.Transparent, Color.Transparent))
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp) // Espaço lateral que mantém a pílula flutuando no menu
            .clip(RoundedCornerShape(50.dp)) // Pílula perfeita!
            .background(backgroundBrush)
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isSelected) Color.White else Color.Gray,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = label,
                color = if (isSelected) Color.White else Color.Gray,
                fontSize = 14.sp,
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal
            )
        }
    }
}
