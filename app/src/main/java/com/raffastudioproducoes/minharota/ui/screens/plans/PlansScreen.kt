package com.raffastudioproducoes.minharota.ui.screens.plans

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raffastudioproducoes.minharota.ui.theme.FundoDark
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada

@Composable
fun PlansScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoDark)
            .verticalScroll(rememberScrollState())
            .padding(24.dp)
    ) {
        Text(
            text = "Escolha seu Plano",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        // Plano Free
        PlanCard(
            titulo = "Free",
            preco = "Grátis",
            descricao = "Perfeito para começar",
            recursos = listOf(
                "Gestão de Turnos",
                "Caixinhas Básicas",
                "Histórico de 30 dias",
                "Suporte por Email"
            ),
            ehAtual = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Plano Premium
        PlanCard(
            titulo = "Premium",
            preco = "R$ 9,99/mês",
            descricao = "Para profissionais",
            recursos = listOf(
                "Tudo do Free +",
                "Heatmap de Horários de Ouro",
                "Análise Avançada",
                "Histórico Ilimitado",
                "Suporte Prioritário",
                "OCR de Documentos"
            ),
            ehAtual = false,
            destaque = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Plano Pro
        PlanCard(
            titulo = "Pro",
            preco = "R$ 19,99/mês",
            descricao = "Para empresas",
            recursos = listOf(
                "Tudo do Premium +",
                "Gestão de Múltiplos Motoristas",
                "Relatórios Personalizados",
                "API de Integração",
                "Suporte 24/7",
                "Backup Automático"
            ),
            ehAtual = false
        )

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun PlanCard(
    titulo: String,
    preco: String,
    descricao: String,
    recursos: List<String>,
    ehAtual: Boolean = false,
    destaque: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .then(
                if (destaque) {
                    Modifier
                        .border(
                            width = 2.dp,
                            color = VerdeEntrada,
                            shape = RoundedCornerShape(16.dp)
                        )
                } else {
                    Modifier
                }
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (destaque) Color(0xFF1C1C1E).copy(alpha = 0.8f) else Color(0xFF1C1C1E)
        )
    ) {
        Column(
            modifier = Modifier.padding(24.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = titulo,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = descricao,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
                if (ehAtual) {
                    Surface(
                        color = VerdeEntrada,
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text(
                            text = "Atual",
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }
            }

            Text(
                text = preco,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = VerdeEntrada,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Lista de Recursos
            recursos.forEach { recurso ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Check,
                        contentDescription = null,
                        modifier = Modifier
                            .size(20.dp)
                            .padding(end = 12.dp),
                        tint = VerdeEntrada
                    )
                    Text(
                        text = recurso,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Botão de Ação
            Button(
                onClick = { /* Implementar upgrade */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (ehAtual) Color.Gray else VerdeEntrada
                ),
                enabled = !ehAtual
            ) {
                Text(
                    text = if (ehAtual) "Plano Atual" else "Escolher Plano",
                    fontWeight = FontWeight.Bold,
                    color = if (ehAtual) Color.White else Color.Black
                )
            }
        }
    }
}
