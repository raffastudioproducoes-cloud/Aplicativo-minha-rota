package com.raffastudioproducoes.minharota.ui.screens.perfil

import android.content.Context
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import com.raffastudioproducoes.minharota.data.local.SharedPreferencesManager

class PerfilViewModel : ViewModel() {
    private val _nomeUsuario = MutableStateFlow("")
    val nomeUsuario: StateFlow<String> = _nomeUsuario

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _dataAniversario = MutableStateFlow("")
    val dataAniversario: StateFlow<String> = _dataAniversario

    private val _fotoPerfilUrl = MutableStateFlow("")
    val fotoPerfilUrl: StateFlow<String> = _fotoPerfilUrl

    fun carregarDadosPerfil(context: Context) {
        val prefs = SharedPreferencesManager(context)
        _nomeUsuario.value = prefs.obterNomeUsuario()
        _email.value = prefs.obterEmail()
        _dataAniversario.value = prefs.obterDataAniversario()
        _fotoPerfilUrl.value = prefs.obterFotoPerfilUrl()
    }

    fun atualizarNomeUsuario(nome: String, context: Context) {
        _nomeUsuario.value = nome
        SharedPreferencesManager(context).salvarNomeUsuario(nome)
    }

    fun atualizarEmail(novoEmail: String, context: Context) {
        _email.value = novoEmail
        SharedPreferencesManager(context).salvarEmail(novoEmail)
    }

    fun atualizarDataAniversario(data: String, context: Context) {
        _dataAniversario.value = data
        SharedPreferencesManager(context).salvarDataAniversario(data)
    }

    fun atualizarFotoPerfilUrl(url: String, context: Context) {
        _fotoPerfilUrl.value = url
        SharedPreferencesManager(context).salvarFotoPerfilUrl(url)
    }
}
