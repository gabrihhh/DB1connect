package br.com.fiap.DB1Connect.service

import br.com.fiap.DB1Connect.model.Usuario
import retrofit2.Call
import retrofit2.http.GET

interface UsuarioService {
    @GET("/usuarios")
    public fun getUsuarios(): Call<List<Usuario>>

}