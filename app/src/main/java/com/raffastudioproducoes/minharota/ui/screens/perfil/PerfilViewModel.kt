package com.raffastudioproducoes.minharota.ui.screens.perfil

import android.content.Context
import androidx.lifecycle.ViewModel
import com.raffastudioproducoes.minharota.data.local.SharedPreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PerfilViewModel : ViewModel() {
    private val _nome = MutableStateFlow("")
    val nome: StateFlow<String> = _nome.asStateFlow()

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email.asStateFlow()

    private val _dataNascimento = MutableStateFlow("")
    val dataNascimento: StateFlow<String> = _dataNascimento.asStateFlow()

    private val _plano = MutableStateFlow("FREE")
    val plano: StateFlow<String> = _plano.asStateFlow()

    fun carregarPerfil(context: Context) {
        val prefs = SharedPreferencesManager(context)
        _nome.value = prefs.obterString("nome_usuario", "Motorista Parceiro")
        _email.value = prefs.obterString("email_usuario", "motorista@exemplo.com")
        _dataNascimento.value = prefs.obterString("data_nascimento", "")
        _plano.value = prefs.obterString("plano_usuario", "FREE")
    }

    fun atualizarNome(novoNome: String, context: Context) {
        _nome.value = novoNome
        val prefs = SharedPreferencesManager(context)
        prefs.salvarString("nome_usuario", novoNome)
    }

    fun atualizarEmail(novoEmail: String, context: Context) {
        _email.value = novoEmail
        val prefs = SharedPreferencesManager(context)
        prefs.salvarString("email_usuario", novoEmail)
    }

    fun atualizarDataNascimento(novaData: String, context: Context) {
        _dataNascimento.value = novaData
        val prefs = SharedPreferencesManager(context)
        prefs.salvarString("data_nascimento", novaData)
    }
}
