package com.projeto.searchmovies.network

import com.projeto.searchmovies.BuildConfig.baseUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private fun getOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(RequestInterceptor())
        .build()
}

fun getMovieRetrofit(): Retrofit {
    return Retrofit.Builder()
        .client(getOkHttpClient())
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}