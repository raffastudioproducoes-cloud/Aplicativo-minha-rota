package com.raffastudioproducoes.minharota.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch

@Composable
fun ScaffoldPrincipal(
    navController: NavHostController,
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var mostrarModalRapido by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            DrawerConteudo(onClose = {
                scope.launch { drawerState.close() }
            })
        }
    ) {
        Scaffold(
            bottomBar = {
                CustomBottomNavBar(
                    navController = navController,
                    onFabClick = { mostrarModalRapido = true }
                )
            }
        ) { paddingValues ->
            content(paddingValues)
        }

        if (mostrarModalRapido) {
            ModalRegistroRapido(
                onDismiss = { mostrarModalRapido = false },
                onSave = { valor ->
                    // Ação de salvar futura
                    mostrarModalRapido = false
                }
            )
        }
    }
}
