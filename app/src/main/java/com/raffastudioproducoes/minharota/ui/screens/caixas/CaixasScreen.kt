package com.raffastudioproducoes.minharota.ui.screens.caixas

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raffastudioproducoes.minharota.domain.model.Caixinha
import com.raffastudioproducoes.minharota.ui.components.CardCaixinha
import com.raffastudioproducoes.minharota.ui.components.PaywallModal
import com.raffastudioproducoes.minharota.ui.screens.hoje.HojeViewModel
import com.raffastudioproducoes.minharota.ui.theme.FundoDark

@Composable
fun CaixasScreen(
    viewModel: CaixasViewModel = viewModel(),
    hojeViewModel: HojeViewModel = viewModel()
) {
    val context = LocalContext.current
    val caixinhas by viewModel.caixinhas.collectAsState()
    val ganhoLiquidoHoje by hojeViewModel.ganhoLiquido.collectAsState()
    val isPro by viewModel.isPro.collectAsState()
    val showPaywallModal by viewModel.showPaywallModal.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.carregarStatusPro(context)
        viewModel.carregarCaixinhas(context)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Distribuição de Ganhos",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp)
        )

        if (ganhoLiquidoHoje > 0) {
            Text(
                text = "Disponível hoje: R$ ${String.format("%.2f", ganhoLiquidoHoje)}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
                color = MaterialTheme.colorScheme.primary
            )
        }

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            items(caixinhas) { caixinha ->
                val valorParaDepositar = (ganhoLiquidoHoje * (caixinha.percentual / 100.0))
                
                CardCaixinha(
                    emoji = caixinha.emoji,
                    titulo = caixinha.nome,
                    valorGuardado = caixinha.saldoAtual,
                    metaValor = caixinha.metaValor,
                    corDestaque = Color(android.graphics.Color.parseColor(caixinha.cor)),
                    percentual = caixinha.percentual.toFloat() / 100f,
                    onDepositoClick = {
                        if (valorParaDepositar > 0) {
                            viewModel.confirmarDeposito(context, caixinha.id, valorParaDepositar)
                        }
                    }
                )
                
                if (valorParaDepositar > 0) {
                    Button(
                        onClick = { viewModel.confirmarDeposito(context, caixinha.id, valorParaDepositar) },
                        modifier = Modifier
                            .padding(horizontal = 16.dp, vertical = 4.dp)
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(android.graphics.Color.parseColor(caixinha.cor))
                        )
                    ) {
                        Text("Depositar R$ ${String.format("%.2f", valorParaDepositar)}")
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedButton(
                    onClick = {
                        viewModel.adicionarCaixinha(context, Caixinha(id = "", nome = "Nova Caixinha", percentual = 0.0)) {
                            // Sucesso ao adicionar, se necessário
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(56.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Icon(Icons.Rounded.Add, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Nova Caixinha")
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = { /* Implementar simulação de rendimento */ },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White.copy(alpha = 0.05f))
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("Simular Rendimento", color = Color.White)
                        if (!isPro) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(imageVector = Icons.Rounded.Lock, contentDescription = "Recurso Pro", tint = Color.White.copy(alpha = 0.7f))
                        }
                    }
                }
            }
        }
    }

    if (showPaywallModal) {
        PaywallModal(
            onDismiss = { viewModel.dismissPaywallModal() },
            onUpgrade = { viewModel.upgradeToPro(context) }
        )
    }
}
