package com.example.jokerjokes.service

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JokeApiService {

    @GET("joke/Any")
    suspend fun getRandomJoke(): Response<ResponseJokes>

    @GET("joke/Programming")
    suspend fun getProgrammingJoke(): Response<ResponseJokes>

    @GET("joke/Miscellaneous")
    suspend fun getMiscellaneousJoke(): Response<ResponseJokes>

    @GET("joke/Dark")
    suspend fun getDarkJoke(): Response<ResponseJokes>

    @GET("joke/Pun")
    suspend fun getPunJoke(): Response<ResponseJokes>

    @GET("joke/Spooky")
    suspend fun getSpookyJoke(): Response<ResponseJokes>

    @GET("joke/Christmas")
    suspend fun getChristmasJoke(): Response<ResponseJokes>

}
object JokesClient{
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://v2.jokeapi.dev/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val jokeApiService = retrofit.create(JokeApiService::class.java)

}