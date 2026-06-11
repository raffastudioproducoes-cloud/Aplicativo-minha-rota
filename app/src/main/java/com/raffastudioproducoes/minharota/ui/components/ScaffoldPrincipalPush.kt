package com.raffastudioproducoes.minharota.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.raffastudioproducoes.minharota.ui.screens.hoje.HojeViewModel
import com.raffastudioproducoes.minharota.ui.theme.FundoDark
import kotlinx.coroutines.launch

@Composable
fun ScaffoldPrincipalPush(
    navController: NavHostController,
    hojeViewModel: HojeViewModel,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var mostrarModalRapido by remember { mutableStateOf(false) }
    val isRidingMode by hojeViewModel.isRidingMode.collectAsState()

    // Animar o offset do conteúdo quando o drawer abre/fecha
    val drawerOffsetPx by animateDpAsState(
        targetValue = if (drawerState.isOpen) 280.dp else 0.dp,
        label = "DrawerPushOffset"
    )

    Box(modifier = Modifier.background(FundoDark)) {
        // Drawer (Push Navigation)
        Box(
            modifier = Modifier
                .offset(x = -(280.dp - drawerOffsetPx))
                .background(FundoDark)
        ) {
            DrawerConteudoGradientRainbow(
                navController = navController,
                onClose = {
                    scope.launch { drawerState.close() }
                }
            )
        }

        // Conteúdo Principal (Deslocado) com Canto Arredondado
        Box(
            modifier = Modifier
                .offset(x = drawerOffsetPx)
                .clip(RoundedCornerShape(topStart = 24.dp, bottomStart = 24.dp))
        ) {
            Scaffold(
                topBar = {
                    if (!isRidingMode) {
                        HeaderSuperior(onDrawerClick = { scope.launch { drawerState.open() } })
                    }
                },
                bottomBar = {
                    if (!isRidingMode) {
                        BottomNavBarNotch(
                            navController = navController,
                            onFabClick = { mostrarModalRapido = true }
                        )
                    }
                }
            ) { paddingValues ->
                content(paddingValues)
            }
        }

        // Overlay Scrim (quando drawer está aberto)
        if (drawerState.isOpen) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .background(Color.Black.copy(alpha = 0.32f))
                    .offset(x = drawerOffsetPx)
            )
        }

        if (mostrarModalRapido) {
            ModalRegistroRapido(
                onDismiss = { mostrarModalRapido = false },
                onSave = { valor ->
                    hojeViewModel.adicionarGanhoRapido(valor)
                    mostrarModalRapido = false
                }
            )
        }
    }
}
