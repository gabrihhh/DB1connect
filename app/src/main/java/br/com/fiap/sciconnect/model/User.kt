package br.com.fiap.sciconnect.model

data class User (
    val nome: String = "",
    val avatar: String = "",
    val formado: Boolean = false,
    val formadoTitulo: String = "",
    val senha: String = "",
    val professor: Boolean = false,
    val interesses: List<String> = emptyList() ,
    val login: String = "",
    val id: String = "",
    val texto: String = ""
)