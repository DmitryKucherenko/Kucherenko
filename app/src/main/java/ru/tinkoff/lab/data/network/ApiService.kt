package ru.tinkoff.lab.data.network

import retrofit2.http.*
import ru.tinkoff.lab.data.network.model.DetailsFilmApi
import ru.tinkoff.lab.data.network.model.FilmsListApi

interface ApiService {

    @Headers("Content-type: application/json")
    @GET("/api/v2.2/films/top")
    suspend fun getFilms(
        @Header(API_KEY) apiKey: String,
        @Query(TYPE) type: String
    ): FilmsListApi

    @Headers("Content-type: application/json")
    @GET("/api/v2.2/films/{film_id}")
    suspend fun getDetailsFilm(
        @Header(API_KEY) apiKey: String,
        @Path(FILM_ID) filmId: Int
    ): DetailsFilmApi

}

const val API_KEY = "X-API-KEY"
const val TYPE = "type"
const val FILM_ID = "film_id"
