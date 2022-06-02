package br.com.zup.caixadesupermercado.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Produto(
    private val nome: String,
    private val quantidade: Int,
    private val valorUnitario: Double
): Parcelable {
    fun getNome() = nome
    fun getQuantidade() = quantidade
    fun getValor() = valorUnitario
}