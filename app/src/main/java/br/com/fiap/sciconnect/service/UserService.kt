package br.com.fiap.sciconnect.service

import br.com.fiap.sciconnect.model.User
import retrofit2.Call
import retrofit2.http.GET

interface UserService {
        @GET("usuarios")
        fun getUsuarios(): Call<List<User>>

}