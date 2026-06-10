package com.raffastudioproducoes.minharota.ui.screens.graficos

import android.content.Context
import androidx.lifecycle.ViewModel
import com.raffastudioproducoes.minharota.data.local.SharedPreferencesManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.*

class GraficosViewModel : ViewModel() {
    // Matriz 7x24: Dias (0-6) por Horas (0-23)
    private val _heatmapData = MutableStateFlow(Array(7) { DoubleArray(24) { 0.0 } })
    val heatmapData: StateFlow<Array<DoubleArray>> = _heatmapData.asStateFlow()

    private val _melhorDia = MutableStateFlow("---")
    val melhorDia: StateFlow<String> = _melhorDia.asStateFlow()

    private val _melhorHora = MutableStateFlow("--h")
    val melhorHora: StateFlow<String> = _melhorHora.asStateFlow()

    private val diasSemana = listOf("Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb")

    fun carregarDados(context: Context) {
        val prefs = SharedPreferencesManager(context)
        val corridas = prefs.obterTodasCorridas()
        val novaMatriz = Array(7) { DoubleArray(24) { 0.0 } }
        
        var maxGanhoDia = 0.0
        var maxGanhoHora = 0.0
        var indexMelhorDia = -1
        var melhorHoraValor = -1

        val ganhosPorDia = DoubleArray(7) { 0.0 }

        corridas.forEach { corrida ->
                val cal = Calendar.getInstance()
                cal.timeInMillis = corrida.timestamp
                val dia = cal.get(Calendar.DAY_OF_WEEK) - 1 // 0-indexed (Dom=0)
                val hora = cal.get(Calendar.HOUR_OF_DAY)

                novaMatriz[dia][hora] += corrida.valor
                ganhosPorDia[dia] += corrida.valor

                if (novaMatriz[dia][hora] > maxGanhoHora) {
                    maxGanhoHora = novaMatriz[dia][hora]
                    melhorHoraValor = hora
                }
            }
        }

        for (i in 0..6) {
            if (ganhosPorDia[i] > maxGanhoDia) {
                maxGanhoDia = ganhosPorDia[i]
                indexMelhorDia = i
            }
        }

        _heatmapData.value = novaMatriz
        if (indexMelhorDia != -1) _melhorDia.value = diasSemana[indexMelhorDia]
        if (melhorHoraValor != -1) _melhorHora.value = "${melhorHoraValor}h"
    }
}
