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

    private val _todasMovimentacoes = MutableStateFlow<List<Movimentacao>>(emptyList())
    private val _movimentacoes = MutableStateFlow<List<Movimentacao>>(emptyList())
    val movimentacoes: StateFlow<List<Movimentacao>> = _movimentacoes.asStateFlow()

    private val _filtroSelecionado = MutableStateFlow("Todos")
    val filtroSelecionado: StateFlow<String> = _filtroSelecionado.asStateFlow()

    private val _totalEntradas = MutableStateFlow(0.0)
    val totalEntradas: StateFlow<Double> = _totalEntradas.asStateFlow()

    private val _totalSaidas = MutableStateFlow(0.0)
    val totalSaidas: StateFlow<Double> = _totalSaidas.asStateFlow()

    private val _saldoTotal = MutableStateFlow(0.0)
    val saldoTotal: StateFlow<Double> = _saldoTotal.asStateFlow()

    fun carregarMovimentacoes(context: Context) {
        viewModelScope.launch {
            val prefs = SharedPreferencesManager(context)
            val todasMovimentacoes = prefs.obterMovimentacoes()
            _todasMovimentacoes.value = todasMovimentacoes.sortedByDescending { SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).parse(it.data) }
            atualizarMovimentacoesFiltradas()
            calcularTotais()
        }
    }

    fun aplicarFiltro(filtro: String) {
        _filtroSelecionado.value = filtro
        atualizarMovimentacoesFiltradas()
    }

    private fun atualizarMovimentacoesFiltradas() {
        val filtro = _filtroSelecionado.value
        val movimentacoesFiltradas = when (filtro) {
            "Entrada" -> _todasMovimentacoes.value.filter { it.tipo == "Entrada" }
            "Saída" -> _todasMovimentacoes.value.filter { it.tipo == "Saída" }
            else -> _todasMovimentacoes.value
        }
        _movimentacoes.value = movimentacoesFiltradas
    }

    private fun calcularTotais() {
        var entradas = 0.0
        var saidas = 0.0

        for (mov in _todasMovimentacoes.value) {
            when (mov.tipo) {
                "Entrada" -> entradas += mov.valor
                "Saída" -> saidas += mov.valor
            }
        }

        _totalEntradas.value = entradas
        _totalSaidas.value = saidas
        _saldoTotal.value = entradas - saidas
    }
}
