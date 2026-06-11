package com.raffastudioproducoes.minharota.ui.screens.perfil

import androidx.compose.foundation.background
<<<<<<< HEAD
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
=======
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
>>>>>>> bd6dd227a6a40499a32245072bd8cdc8fb97b699
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
<<<<<<< HEAD
import com.raffastudioproducoes.minharota.ui.theme.FundoDark
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada

@Composable
fun PerfilScreen(viewModel: PerfilViewModel = PerfilViewModel()) {
    val context = LocalContext.current
    val nomeUsuario by viewModel.nomeUsuario.collectAsState()
    val email by viewModel.email.collectAsState()
    val dataAniversario by viewModel.dataAniversario.collectAsState()

    var nomeEditavel by remember { mutableStateOf(nomeUsuario) }
    var emailEditavel by remember { mutableStateOf(email) }
    var dataEditavel by remember { mutableStateOf(dataAniversario) }

    LaunchedEffect(Unit) {
        viewModel.carregarDadosPerfil(context)
    }

    LaunchedEffect(nomeUsuario, email, dataAniversario) {
        nomeEditavel = nomeUsuario
        emailEditavel = email
        dataEditavel = dataAniversario
=======
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
>>>>>>> bd6dd227a6a40499a32245072bd8cdc8fb97b699
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
<<<<<<< HEAD
            .background(FundoDark)
=======
>>>>>>> bd6dd227a6a40499a32245072bd8cdc8fb97b699
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Meu Perfil",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(16.dp))

<<<<<<< HEAD
        // Foto de Perfil
=======
        // Foto de Perfil (Placeholder)
>>>>>>> bd6dd227a6a40499a32245072bd8cdc8fb97b699
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(color = VerdeEntrada.copy(alpha = 0.2f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Rounded.AccountCircle,
                contentDescription = "Foto de Perfil",
                modifier = Modifier.size(100.dp),
                tint = VerdeEntrada
            )
        }

<<<<<<< HEAD
        Spacer(modifier = Modifier.height(24.dp))

        // Campo de Nome
        OutlinedTextField(
            value = nomeEditavel,
            onValueChange = { nomeEditavel = it },
            label = { Text("Nome") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        // Campo de Email
        OutlinedTextField(
            value = emailEditavel,
            onValueChange = { emailEditavel = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        // Campo de Data de Aniversário
        OutlinedTextField(
            value = dataEditavel,
            onValueChange = { dataEditavel = it },
            label = { Text("Data de Aniversário (DD/MM/YYYY)") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botão Salvar
        Button(
            onClick = {
                viewModel.atualizarNomeUsuario(nomeEditavel, context)
                viewModel.atualizarEmail(emailEditavel, context)
                viewModel.atualizarDataAniversario(dataEditavel, context)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = VerdeEntrada
            )
        ) {
            Text(
                text = "Salvar Alterações",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botão Planos (Premium)
        Button(
            onClick = { /* TODO: Navegar para tela de Planos */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text = "Ver Planos Premium",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
=======
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
>>>>>>> bd6dd227a6a40499a32245072bd8cdc8fb97b699
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
