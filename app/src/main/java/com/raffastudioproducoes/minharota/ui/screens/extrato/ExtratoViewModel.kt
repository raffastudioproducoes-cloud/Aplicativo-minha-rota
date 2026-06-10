package com.raffastudioproducoes.minharota.ui.screens.extrato

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raffastudioproducoes.minharota.data.local.SharedPreferencesManager
import com.raffastudioproducoes.minharota.domain.model.Movimentacao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ExtratoViewModel : ViewModel() {

    private val _movimentacoes = MutableStateFlow<List<Movimentacao>>(emptyList())
    val movimentacoes: StateFlow<List<Movimentacao>> = _movimentacoes.asStateFlow()

    private val _filtroSelecionado = MutableStateFlow("Todos")
    val filtroSelecionado: StateFlow<String> = _filtroSelecionado.asStateFlow()

    fun carregarMovimentacoes(context: Context) {
        viewModelScope.launch {
            val prefs = SharedPreferencesManager(context)
            val todasMovimentacoes = prefs.obterMovimentacoes()
            _movimentacoes.value = todasMovimentacoes.sortedByDescending { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(it.data) }
        }
    }

    fun aplicarFiltro(filtro: String) {
        _filtroSelecionado.value = filtro
        // TODO: Implementar lógica de filtragem por data
    }

    // TODO: Adicionar cálculo de saldo, entradas e saídas
}
