package com.raffastudioproducoes.minharota.data.local

import android.content.Context
import android.content.SharedPreferences
import com.raffastudioproducoes.minharota.domain.model.Caixinha
import com.raffastudioproducoes.minharota.domain.model.ContaFixa
import com.raffastudioproducoes.minharota.domain.model.Movimentacao
import com.raffastudioproducoes.minharota.domain.model.Turno
import com.raffastudioproducoes.minharota.domain.model.Divida
import com.raffastudioproducoes.minharota.domain.model.Veiculo
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.UUID

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
            val padrao = listOf(
                Caixinha(UUID.randomUUID().toString(), "Base de Tudo", "Custos essenciais", "🏠", "#820AD1", 40.0),
                Caixinha(UUID.randomUUID().toString(), "Manutenção", "Reserva para a moto", "🏍️", "#2ECC71", 30.0),
                Caixinha(UUID.randomUUID().toString(), "Lazer", "Diversão e descanso", "🎉", "#FFD700", 30.0)
            )
            salvarCaixinhas(padrao)
            padrao
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

    // --- Contas Fixas ---
    fun salvarContas(lista: List<ContaFixa>) {
        val jsonString = json.encodeToString(lista)
        sharedPreferences.edit().putString(KEY_CONTAS, jsonString).apply()
    }

    fun obterContas(): List<ContaFixa> {
        val jsonString = sharedPreferences.getString(KEY_CONTAS, null)
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

    // --- Dívidas ---
    fun salvarDividas(lista: List<Divida>) {
        val jsonString = json.encodeToString(lista)
        sharedPreferences.edit().putString(KEY_DIVIDAS, jsonString).apply()
    }

    fun obterDividas(): List<Divida> {
        val jsonString = sharedPreferences.getString(KEY_DIVIDAS, null)
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

    // --- Veículo ---
    fun salvarVeiculo(veiculo: Veiculo) {
        val jsonString = json.encodeToString(veiculo)
        sharedPreferences.edit().putString(KEY_VEICULO, jsonString).apply()
    }

    fun obterVeiculo(): Veiculo? {
        val jsonString = sharedPreferences.getString(KEY_VEICULO, null)
        return if (jsonString != null) {
            try {
                json.decodeFromString(jsonString)
            } catch (e: Exception) {
                null
            }
        } else {
            null
        }
    }

    companion object {
        private const val KEY_CAIXINHAS = "caixinhas"
        private const val KEY_TURNOS = "turnos"
        private const val KEY_MOVIMENTACOES = "movimentacoes"
        private const val KEY_CONTAS = "contas_fixas"
        private const val KEY_DIVIDAS = "dividas"
        private const val KEY_VEICULO = "veiculo"
    }
}
