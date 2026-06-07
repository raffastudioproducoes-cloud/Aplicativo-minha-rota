package com.raffastudioproducoes.minharota.data.local

import android.content.Context
import android.content.SharedPreferences
import com.raffastudioproducoes.minharota.domain.model.Caixinha
import com.raffastudioproducoes.minharota.domain.model.Movimentacao
import com.raffastudioproducoes.minharota.domain.model.Turno
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SharedPreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("minha_rota_prefs", Context.MODE_PRIVATE)

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
    }

    // --- Caixinhas ---
    fun salvarCaixinhas(lista: List<Caixinha>) {
        val jsonString = json.encodeToString(lista)
        sharedPreferences.edit().putString(KEY_CAIXINHAS, jsonString).apply()
    }

    fun obterCaixinhas(): List<Caixinha> {
        val jsonString = sharedPreferences.getString(KEY_CAIXINHAS, null)
        return if (jsonString != null) {
            try {
                json.decodeFromString(jsonString)
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    // --- Turnos ---
    fun salvarTurnos(lista: List<Turno>) {
        val jsonString = json.encodeToString(lista)
        sharedPreferences.edit().putString(KEY_TURNOS, jsonString).apply()
    }

    fun obterTurnos(): List<Turno> {
        val jsonString = sharedPreferences.getString(KEY_TURNOS, null)
        return if (jsonString != null) {
            try {
                json.decodeFromString(jsonString)
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    // --- Movimentações ---
    fun salvarMovimentacoes(lista: List<Movimentacao>) {
        val jsonString = json.encodeToString(lista)
        sharedPreferences.edit().putString(KEY_MOVIMENTACOES, jsonString).apply()
    }

    fun obterMovimentacoes(): List<Movimentacao> {
        val jsonString = sharedPreferences.getString(KEY_MOVIMENTACOES, null)
        return if (jsonString != null) {
            try {
                json.decodeFromString(jsonString)
            } catch (e: Exception) {
                emptyList()
            }
        } else {
            emptyList()
        }
    }

    companion object {
        private const val KEY_CAIXINHAS = "caixinhas"
        private const val KEY_TURNOS = "turnos"
        private const val KEY_MOVIMENTACOES = "movimentacoes"
    }
}
