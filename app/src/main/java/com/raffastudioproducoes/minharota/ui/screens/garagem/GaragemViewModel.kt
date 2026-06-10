package com.raffastudioproducoes.minharota.ui.screens.garagem

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raffastudioproducoes.minharota.data.local.SharedPreferencesManager
import com.raffastudioproducoes.minharota.domain.model.Veiculo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GaragemViewModel : ViewModel() {

    private val _veiculo = MutableStateFlow<Veiculo?>(null)
    val veiculo: StateFlow<Veiculo?> = _veiculo.asStateFlow()

    fun carregarVeiculo(context: Context) {
        viewModelScope.launch {
            val prefs = SharedPreferencesManager(context)
            _veiculo.value = prefs.obterVeiculo()
        }
    }

    fun atualizarQuilometragem(context: Context, novaKm: Double) {
        viewModelScope.launch {
            val prefs = SharedPreferencesManager(context)
            val veiculoAtual = _veiculo.value ?: Veiculo(quilometragemAtual = 0.0)
            val veiculoAtualizado = veiculoAtual.copy(quilometragemAtual = novaKm)
            prefs.salvarVeiculo(veiculoAtualizado)
            _veiculo.value = veiculoAtualizado
        }
    }

    fun registrarManutencao(context: Context, tipoManutencao: String, kmAtual: Double, proximoServicoKm: Double) {
        viewModelScope.launch {
            val prefs = SharedPreferencesManager(context)
            val veiculoAtual = _veiculo.value ?: Veiculo(quilometragemAtual = 0.0)
            val manutencoesAtualizadas = veiculoAtual.manutencoes.toMutableList().apply {
                add(Veiculo.Manutencao(tipo = tipoManutencao, kmUltimoServico = kmAtual, proximoServicoKm = proximoServicoKm))
            }
            val veiculoAtualizado = veiculoAtual.copy(manutencoes = manutencoesAtualizadas)
            prefs.salvarVeiculo(veiculoAtualizado)
            _veiculo.value = veiculoAtualizado
        }
    }
}
