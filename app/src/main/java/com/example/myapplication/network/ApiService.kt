package com.example.myapplication.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/{apiKey}/{id}")
    fun getSuperHero(
        @Path("apiKey") apiKey: String = "357a75952d768b06202ab822a0e63141",
        @Path("id") id: String
    ): Call<SuperHero>
}
