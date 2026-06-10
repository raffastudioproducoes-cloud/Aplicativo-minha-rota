package com.raffastudioproducoes.minharota.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Corrida(val id: String, val valor: Double, val timestamp: Long)

@Serializable
data class Turno(
    val id: String,
    val data: String,
    val horaInicio: String,
    val horaFim: String,
    val ganhoBruto: Double,
    val custoRua: Double,
    val ganhoLiquido: Double,
    val corridas: List<Corrida> = emptyList()
)

@Serializable
data class Caixinha(
    val id: String,
    val nome: String,
    val subtitulo: String = "",
    val emoji: String = "💰",
    val cor: String = "#2ECC71",
    val percentual: Double,
    val metaValor: Double = 0.0,
    val saldoAtual: Double = 0.0,
    val pausada: Boolean = false
)

@Serializable
data class Movimentacao(
    val id: String,
    val tipo: String,
    val descricao: String,
    val valor: Double,
    val data: String
)
