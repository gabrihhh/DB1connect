package br.com.fiap.DB1Connect.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {
    private val URL = "https://66469b8351e227f23aaf5dc3.mockapi.io/dbc/v1"

    private val retrofitFactory = Retrofit
        .Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    public fun getUsersService(): UsuarioService{
        return retrofitFactory.create(UsuarioService::class.java)
    }

}