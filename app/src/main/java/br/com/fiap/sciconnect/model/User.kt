package br.com.fiap.sciconnect.model

data class User (
    val nome: String = "",
    val avatar: String = "",
    val formado: Boolean = false,
    val formadoTitulo: String = "",
    val senha: String = "",
    val professor: Boolean = false,
    val interesses: Array<String> =  emptyArray(),
    val login: String = "",
    val id: String = ""
)