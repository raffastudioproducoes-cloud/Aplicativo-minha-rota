package com.raffastudioproducoes.minharota.ui.screens.caixas

import android.content.Context
import androidx.lifecycle.ViewModel
import com.raffastudioproducoes.minharota.data.local.SharedPreferencesManager
import com.raffastudioproducoes.minharota.domain.model.Caixinha
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.UUID

class CaixasViewModel : ViewModel() {
    private val _caixinhas = MutableStateFlow<List<Caixinha>>(emptyList())
    val caixinhas: StateFlow<List<Caixinha>> = _caixinhas.asStateFlow()

    private val _isPro = MutableStateFlow(false)
    val isPro: StateFlow<Boolean> = _isPro.asStateFlow()

    private val _showPaywallModal = MutableStateFlow(false)
    val showPaywallModal: StateFlow<Boolean> = _showPaywallModal.asStateFlow()

    fun carregarStatusPro(context: Context) {
        val prefs = SharedPreferencesManager(context)
        _isPro.value = prefs.obterIsPro()
    }

    fun carregarCaixinhas(context: Context) {
        val prefs = SharedPreferencesManager(context)
        _caixinhas.value = prefs.obterCaixinhas()
    }

    fun confirmarDeposito(context: Context, caixinhaId: String, valor: Double) {
        val prefs = SharedPreferencesManager(context)
        val listaAtual = _caixinhas.value.toMutableList()
        val index = listaAtual.indexOfFirst { it.id == caixinhaId }
        
        if (index != -1) {
            val caixinha = listaAtual[index]
            listaAtual[index] = caixinha.copy(saldoAtual = caixinha.saldoAtual + valor)
            _caixinhas.value = listaAtual
            prefs.salvarCaixinhas(listaAtual)
        }
    }

    fun validarPercentuais(novaCaixinha: Caixinha): Boolean {
        val somaAtual = _caixinhas.value.sumOf { it.percentual }
        return (somaAtual + novaCaixinha.percentual) <= 100.0
    }

    fun adicionarCaixinha(context: Context, caixinha: Caixinha, onSuccess: () -> Unit) {
        if (!_isPro.value && _caixinhas.value.size >= 3) {
            _showPaywallModal.value = true
            return
        }
        val prefs = SharedPreferencesManager(context)
        val listaAtual = _caixinhas.value.toMutableList()
        listaAtual.add(caixinha.copy(id = UUID.randomUUID().toString()))
        prefs.salvarCaixinhas(listaAtual)
        _caixinhas.value = listaAtual
        onSuccess()
    }

    fun dismissPaywallModal() {
        _showPaywallModal.value = false
    }

    fun upgradeToPro(context: Context) {
        val prefs = SharedPreferencesManager(context)
        prefs.salvarIsPro(true)
        _isPro.value = true
        _showPaywallModal.value = false
    }
}
