package com.raffastudioproducoes.minharota.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.raffastudioproducoes.minharota.ui.navigation.itensNavegacao
import com.raffastudioproducoes.minharota.ui.theme.CyanBright
import com.raffastudioproducoes.minharota.ui.theme.ElectricBlue
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada

@Composable
fun BottomNavBarSquircle(
    navController: NavController,
    onFabClick: () -> Unit
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val surfaceColor = MaterialTheme.colorScheme.surface

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        // NavBar com Berço Côncavo para o FAB
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(70.dp)
                .drawBehind {
                    val path = Path().apply {
                        val width = size.width
                        val height = size.height
                        val notchWidth = 80.dp.toPx()
                        val notchHeight = 30.dp.toPx()
                        val notchRadius = 20.dp.toPx()

                        // Começar do canto inferior esquerdo
                        moveTo(0f, height)
                        lineTo(0f, notchRadius)
                        quadraticBezierTo(0f, 0f, notchRadius, 0f)

                        // Linha até o início do berço
                        lineTo((width / 2) - (notchWidth / 2), 0f)

                        // Berço côncavo (curva para dentro)
                        quadraticBezierTo(
                            (width / 2) - (notchWidth / 2),
                            notchHeight,
                            width / 2,
                            notchHeight
                        )
                        quadraticBezierTo(
                            (width / 2) + (notchWidth / 2),
                            notchHeight,
                            (width / 2) + (notchWidth / 2),
                            0f
                        )

                        // Linha até o canto superior direito
                        lineTo(width - notchRadius, 0f)
                        quadraticBezierTo(width, 0f, width, notchRadius)
                        lineTo(width, height)
                        close()
                    }
                    drawPath(
                        path = path,
                        color = surfaceColor
                    )
                }
        ) {
            NavigationBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                containerColor = Color.Transparent,
                tonalElevation = 0.dp
            ) {
                itensNavegacao.forEachIndexed { index, item ->
                    // Espaço vazio no meio para o FAB
                    if (index == 2) {
                        NavigationBarItem(
                            selected = false,
                            onClick = { },
                            icon = { },
                            enabled = false,
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent
                            )
                        )
                    }

                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            if (currentRoute != item.route) {
                                navController.navigate(item.route) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        },
                        icon = {
                            Box(modifier = Modifier.size(48.dp), contentAlignment = Alignment.Center) {
                                // Efeito Retangular Arredondado Permanente na Seleção
                                if (currentRoute == item.route) {
                                    Box(
                                        modifier = Modifier
                                            .size(width = 56.dp, height = 40.dp)
                                            .shadow(elevation = 8.dp, shape = RoundedCornerShape(12.dp))
                                            .background(
                                                brush = Brush.linearGradient(
                                                    colors = listOf(
                                                        ElectricBlue.copy(alpha = 0.4f),
                                                        CyanBright.copy(alpha = 0.2f)
                                                    )
                                                ),
                                                shape = RoundedCornerShape(12.dp)
                                            )
                                    )
                                }
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        },
                        label = {
                            Text(text = item.title, style = MaterialTheme.typography.labelSmall)
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            unselectedIconColor = Color.Gray,
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }

        // FAB Squircle (Rounded Square) com Degradê - Posicionado no Berço
        val fabGradient = Brush.linearGradient(
            colors = listOf(VerdeEntrada, CyanBright)
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = (-15).dp)
                .size(60.dp)
                .shadow(elevation = 16.dp, shape = RoundedCornerShape(16.dp))
                .background(brush = fabGradient, shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            androidx.compose.material3.IconButton(
                onClick = onFabClick,
                modifier = Modifier.size(60.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = "Adicionar",
                    modifier = Modifier.size(30.dp),
                    tint = Color.Black
                )
            }
        }
    }
}
