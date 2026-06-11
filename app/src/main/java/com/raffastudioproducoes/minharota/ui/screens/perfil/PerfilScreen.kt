package com.raffastudioproducoes.minharota.ui.screens.perfil

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raffastudioproducoes.minharota.ui.theme.FundoDark
import com.raffastudioproducoes.minharota.ui.theme.VerdeEntrada

@Composable
fun PerfilScreen(viewModel: PerfilViewModel = viewModel()) {
    val context = LocalContext.current
    val nomeUsuario by viewModel.nomeUsuario.collectAsState()
    val email by viewModel.email.collectAsState()
    val dataAniversario by viewModel.dataAniversario.collectAsState()

    var nomeEditavel by remember { mutableStateOf(false) }
    var emailEditavel by remember { mutableStateOf(false) }
    var dataEditavel by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.carregarDadosPerfil(context)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(FundoDark)
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

        // Foto de Perfil
        Box(
            modifier = Modifier
                .size(120.dp)
                .background(color = VerdeEntrada.copy(alpha = 0.1f), shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "Foto de Perfil",
                modifier = Modifier.size(100.dp),
                tint = VerdeEntrada
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Campo Nome
        PerfilInputField(
            label = "Nome",
            value = nomeUsuario,
            isEditing = nomeEditavel,
            onEditClick = { nomeEditavel = !nomeEditavel },
            onValueChange = { viewModel.atualizarNomeUsuario(it, context) }
        )

        // Campo E-mail
        PerfilInputField(
            label = "E-mail",
            value = email,
            isEditing = emailEditavel,
            onEditClick = { emailEditavel = !emailEditavel },
            onValueChange = { viewModel.atualizarEmail(it, context) }
        )

        // Campo Data de Aniversário
        PerfilInputField(
            label = "Data de Aniversário",
            value = if (dataAniversario.isEmpty()) "Não informado" else dataAniversario,
            isEditing = dataEditavel,
            onEditClick = { dataEditavel = !dataEditavel },
            onValueChange = { viewModel.atualizarDataAniversario(it, context) },
            placeholder = "DD/MM/YYYY"
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Botão Planos Premium
        Button(
            onClick = { /* Navegar para tela de Planos */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text("Ver Planos Premium", fontWeight = FontWeight.Bold, color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botão Sair
        TextButton(
            onClick = { /* Logout futuro */ },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Sair da Conta", color = Color(0xFFE57373), fontWeight = FontWeight.Medium)
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun PerfilInputField(
    label: String,
    value: String,
    isEditing: Boolean,
    onEditClick: () -> Unit,
    onValueChange: (String) -> Unit,
    placeholder: String = ""
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = Color.Gray,
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = onEditClick, modifier = Modifier.size(24.dp)) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "Editar $label",
                    modifier = Modifier.size(18.dp),
                    tint = VerdeEntrada
                )
            }
        }
        
        if (isEditing) {
            OutlinedTextField(
                value = if (value == "Não informado") "" else value,
                onValueChange = onValueChange,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                shape = RoundedCornerShape(12.dp),
                placeholder = { Text(placeholder, color = Color.Gray) },
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = VerdeEntrada,
                    unfocusedBorderColor = Color.White.copy(alpha = 0.1f),
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
        } else {
            Text(
                text = value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp),
                style = MaterialTheme.typography.bodyLarge,
                color = if (value == "Não informado") Color.Gray else Color.White,
                fontWeight = FontWeight.Medium
            )
            Divider(color = Color.White.copy(alpha = 0.05f))
        }
    }
}
