package br.com.fiap.sciconnect.service

import br.com.fiap.sciconnect.model.User
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserService {
        @GET("usuarios")
        fun getUsuarios(): Call<List<User>>

        @Headers("Content-Type: application/json")
        @POST("usuarios")
        fun postUser(@Body body: User): Call<User>
}