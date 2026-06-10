package com.raffastudioproducoes.minharota.ui.screens.hoje

import android.content.Context
import androidx.lifecycle.ViewModel
import com.raffastudioproducoes.minharota.data.local.SharedPreferencesManager
import com.raffastudioproducoes.minharota.domain.model.Turno
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.*

class HojeViewModel : ViewModel() {
    private val _ganhoBruto = MutableStateFlow(0.0)
    val ganhoBruto: StateFlow<Double> = _ganhoBruto.asStateFlow()

    private val _custoRua = MutableStateFlow(0.0)
    val custoRua: StateFlow<Double> = _custoRua.asStateFlow()

    private val _ganhoLiquido = MutableStateFlow(0.0)
    val ganhoLiquido: StateFlow<Double> = _ganhoLiquido.asStateFlow()

    private val _horaInicio = MutableStateFlow("--:--")
    val horaInicio: StateFlow<String> = _horaInicio.asStateFlow()

    private val _horaFim = MutableStateFlow("--:--")
    val horaFim: StateFlow<String> = _horaFim.asStateFlow()

    fun updateGanhoBruto(valor: Double) {
        _ganhoBruto.value = valor
        calcularLiquido()
    }

    fun updateCustoRua(valor: Double) {
        _custoRua.value = valor
        calcularLiquido()
    }

    private fun calcularLiquido() {
        _ganhoLiquido.value = _ganhoBruto.value - _custoRua.value
    }

    fun adicionarGanhoRapido(valor: Double) {
        _ganhoBruto.value += valor
        calcularLiquido()
    }

    fun salvarTurno(context: Context, onSuccess: () -> Unit) {
        val prefs = SharedPreferencesManager(context)
        val hoje = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        
        val novoTurno = Turno(
            id = UUID.randomUUID().toString(),
            data = hoje,
            horaInicio = _horaInicio.value,
            horaFim = _horaFim.value,
            ganhoBruto = _ganhoBruto.value,
            custoRua = _custoRua.value,
            ganhoLiquido = _ganhoLiquido.value
        )

        val turnosAtuais = prefs.obterTurnos().toMutableList()
        turnosAtuais.add(novoTurno)
        prefs.salvarTurnos(turnosAtuais)
        
        limparCampos()
        onSuccess()
    }

    private fun limparCampos() {
        _ganhoBruto.value = 0.0
        _custoRua.value = 0.0
        _ganhoLiquido.value = 0.0
        _horaInicio.value = "--:--"
        _horaFim.value = "--:--"
    }
}
