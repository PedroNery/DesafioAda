package com.projeto.searchmovies.data.service

import com.projeto.searchmovies.data.model.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("/")
    suspend fun getMoviesByName(@Query("s") name : String) : SearchResponse

}