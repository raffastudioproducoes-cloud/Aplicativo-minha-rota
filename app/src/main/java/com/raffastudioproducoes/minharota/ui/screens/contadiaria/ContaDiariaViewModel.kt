package com.raffastudioproducoes.minharota.ui.screens.contadiaria

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.raffastudioproducoes.minharota.data.local.SharedPreferencesManager
import com.raffastudioproducoes.minharota.domain.model.ContaDiaria
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID

class ContaDiariaViewModel(application: Application) : AndroidViewModel(application) {
    private val preferencesManager = SharedPreferencesManager(application)

    private val _contasDiarias = MutableStateFlow<List<ContaDiaria>>(emptyList())
    val contasDiarias: StateFlow<List<ContaDiaria>> = _contasDiarias

    private val _metaDiaria = MutableStateFlow(0.0)
    val metaDiaria: StateFlow<Double> = _metaDiaria

    init {
        carregarContas()
    }

    private fun carregarContas() {
        viewModelScope.launch {
            val contas = preferencesManager.obterContasDiarias()
            _contasDiarias.value = contas
            calcularMetaDiaria()
        }
    }

    fun adicionarConta(descricao: String, valor: Double, frequencia: String) {
        if (descricao.isBlank() || valor <= 0) return
        
        val novaConta = ContaDiaria(
            id = UUID.randomUUID().toString(),
            descricao = descricao,
            valor = valor,
            frequencia = frequencia
        )

        val listaAtualizada = _contasDiarias.value + novaConta
        _contasDiarias.value = listaAtualizada
        preferencesManager.salvarContasDiarias(listaAtualizada)
        calcularMetaDiaria()
    }

    fun removerConta(id: String) {
        val listaAtualizada = _contasDiarias.value.filter { it.id != id }
        _contasDiarias.value = listaAtualizada
        preferencesManager.salvarContasDiarias(listaAtualizada)
        calcularMetaDiaria()
    }

    private fun calcularMetaDiaria() {
        var totalDiario = 0.0

        for (conta in _contasDiarias.value) {
            totalDiario += when (conta.frequencia) {
                "Diário" -> conta.valor
                "Semanal" -> conta.valor / 7.0
                "Quinzenal" -> conta.valor / 15.0
                "Mensal" -> conta.valor / 30.0
                else -> 0.0
            }
        }

        _metaDiaria.value = totalDiario
    }
}
