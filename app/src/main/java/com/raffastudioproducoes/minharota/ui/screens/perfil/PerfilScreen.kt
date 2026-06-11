package com.raffastudioproducoes.minharota.ui.screens.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PerfilScreen(viewModel: PerfilViewModel = viewModel()) {
    val context = LocalContext.current
    val nome by viewModel.nome.collectAsState()
    val email by viewModel.email.collectAsState()
    val dataNascimento by viewModel.dataNascimento.collectAsState()
    val plano by viewModel.plano.collectAsState()

    var nomeEditavel by remember { mutableStateOf(false) }
    var emailEditavel by remember { mutableStateOf(false) }
    var dataNascimentoEditavel by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.carregarPerfil(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Meu Perfil",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Foto de Perfil (Placeholder)
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.Person,
                contentDescription = null,
                modifier = Modifier.size(64.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Badge do Plano
        Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
            shape = MaterialTheme.shapes.small
        ) {
            Text(
                text = "Plano Atual: $plano",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.onSecondaryContainer,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Campo Nome
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Nome", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                IconButton(
                    onClick = { nomeEditavel = !nomeEditavel },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = "Editar Nome",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            if (nomeEditavel) {
                OutlinedTextField(
                    value = nome,
                    onValueChange = { viewModel.atualizarNome(it, context) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true
                )
            } else {
                Text(
                    text = nome,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Campo E-mail
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("E-mail", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                IconButton(
                    onClick = { emailEditavel = !emailEditavel },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = "Editar E-mail",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            if (emailEditavel) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { viewModel.atualizarEmail(it, context) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    singleLine = true
                )
            } else {
                Text(
                    text = email,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Campo Data de Aniversário
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Data de Aniversário", style = MaterialTheme.typography.labelMedium, fontWeight = FontWeight.Bold)
                IconButton(
                    onClick = { dataNascimentoEditavel = !dataNascimentoEditavel },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Edit,
                        contentDescription = "Editar Data de Aniversário",
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
            if (dataNascimentoEditavel) {
                OutlinedTextField(
                    value = dataNascimento,
                    onValueChange = { viewModel.atualizarDataNascimento(it, context) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium,
                    placeholder = { Text("DD/MM/YYYY") },
                    singleLine = true
                )
            } else {
                Text(
                    text = if (dataNascimento.isEmpty()) "Não informado" else dataNascimento,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // Botão Planos
        Button(
            onClick = { /* Navegar para tela de Planos */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Ver Planos Premium", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botão Sair
        Button(
            onClick = { /* Logout futuro */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFE57373)
            )
        ) {
            Text("Sair da Conta", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
