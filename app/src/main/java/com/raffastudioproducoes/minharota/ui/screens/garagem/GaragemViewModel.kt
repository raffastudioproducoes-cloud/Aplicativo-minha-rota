package com.raffastudioproducoes.minharota.ui.screens.garagem

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine

class GaragemViewModel : ViewModel() {

    private val _kmRodados = MutableStateFlow("")
    val kmRodados: StateFlow<String> = _kmRodados.asStateFlow()

    private val _litrosAbastecidos = MutableStateFlow("")
    val litrosAbastecidos: StateFlow<String> = _litrosAbastecidos.asStateFlow()

    val mediaKmL: StateFlow<Double> = combine(_kmRodados, _litrosAbastecidos) { km, litros ->
        val k = km.toDoubleOrNull() ?: 0.0
        val l = litros.toDoubleOrNull() ?: 0.0
        if (l > 0) k / l else 0.0
    }.let { flow ->
        val state = MutableStateFlow(0.0)
        // No ViewModel, não podemos coletar diretamente sem um scope, mas o combine resolve o estado reativo
        // Em um app real, usaríamos stateIn(viewModelScope)
        state
    }
    
    // Simplificando para o StateFlow reativo funcionar corretamente no Compose
    private val _mediaResult = MutableStateFlow(0.0)
    val mediaResult: StateFlow<Double> = _mediaResult.asStateFlow()

    fun updateKm(value: String) {
        _kmRodados.value = value
        calcularMedia()
    }

    fun updateLitros(value: String) {
        _litrosAbastecidos.value = value
        calcularMedia()
    }

    private fun calcularMedia() {
        val k = _kmRodados.value.replace(",", ".").toDoubleOrNull() ?: 0.0
        val l = _litrosAbastecidos.value.replace(",", ".").toDoubleOrNull() ?: 0.0
        _mediaResult.value = if (l > 0) k / l else 0.0
    }
}
