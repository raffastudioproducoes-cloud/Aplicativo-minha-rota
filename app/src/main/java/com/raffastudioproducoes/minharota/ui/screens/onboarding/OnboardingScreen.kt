package com.raffastudioproducoes.minharota.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raffastudioproducoes.minharota.ui.theme.FundoDark
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada
import kotlinx.coroutines.launch
import androidx.compose.foundation.ExperimentalFoundationApi

data class OnboardingPage(
    val titulo: String,
    val descricao: String,
    val icone: ImageVector
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onOnboardingComplete: () -> Unit) {
    val pages = listOf(
        OnboardingPage(
            titulo = "Bem-vindo ao MinhaRota",
            descricao = "Controle financeiro completo para motoboys e entregadores",
            icone = Icons.Rounded.TwoWheeler
        ),
        OnboardingPage(
            titulo = "Gestão de Turnos",
            descricao = "Registre suas horas trabalhadas e ganhos em tempo real",
            icone = Icons.Rounded.Today
        ),
        OnboardingPage(
            titulo = "Caixinhas Inteligentes",
            descricao = "Distribua seus ganhos em envelopes virtuais automáticos",
            icone = Icons.Rounded.Inventory2
        ),
        OnboardingPage(
            titulo = "Horários de Ouro",
            descricao = "Visualize os melhores horários para trabalhar com heatmap",
            icone = Icons.Rounded.BarChart
        ),
        OnboardingPage(
            titulo = "Controle de Garagem",
            descricao = "Monitore quilometragem e alertas de manutenção",
            icone = Icons.Rounded.TwoWheeler
        ),
        OnboardingPage(
            titulo = "Gestão de Dívidas",
            descricao = "Planeje suas contas fixas e metas financeiras",
            icone = Icons.Rounded.MoneyOff
        ),
        OnboardingPage(
            titulo = "Comece Agora",
            descricao = "Você está pronto para maximizar seus ganhos!",
            icone = Icons.Rounded.CheckCircle
        )
    )

    val pagerState = rememberPagerState(pageCount = { pages.size })
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoDark)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { page ->
            OnboardingPageContent(pages[page])
        }

        // Indicadores de página
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            repeat(pages.size) { index ->
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .background(
                            color = if (index == pagerState.currentPage) VerdeEntrada else Color.Gray,
                            shape = CircleShape
                        )
                )
            }
        }

        // Botões de Navegação
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (pagerState.currentPage > 0) {
                Button(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.outlinedButtonColors()
                ) {
                    Text("Anterior")
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.width(16.dp))

            if (pagerState.currentPage < pages.size - 1) {
                Button(
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeEntrada
                    )
                ) {
                    Text("Próximo")
                }
            } else {
                Button(
                    onClick = {
                        onOnboardingComplete()
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = VerdeEntrada
                    )
                ) {
                    Text("Começar")
                }
            }
        }
    }
}

@Composable
fun OnboardingPageContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = page.icone,
            contentDescription = page.titulo,
            modifier = Modifier
                .size(96.dp)
                .padding(bottom = 32.dp),
            tint = VerdeEntrada
        )

        Text(
            text = page.titulo,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = page.descricao,
            fontSize = 16.sp,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 32.dp)
        )
    }
}
