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
        var totalMensal = 0.0

        for (conta in _contasDiarias.value) {
            totalMensal += when (conta.frequencia) {
                "Diario" -> conta.valor * 30
                "Semanal" -> (conta.valor * 4.3)
                "Quinzenal" -> (conta.valor * 2)
                "Mensal" -> conta.valor
                else -> 0.0
            }
        }

        _metaDiaria.value = if (totalMensal > 0) totalMensal / 30 else 0.0
    }
}
