package ru.tinkoff.lab.data.network.api

import retrofit2.http.*
import ru.tinkoff.lab.data.network.dto.FilmDetailsDto
import ru.tinkoff.lab.data.network.dto.FilmsListDto

interface ApiService {

    @Headers("Content-type: application/json")
    @GET("/api/v2.2/films/top")
    suspend fun getFilms(
        @Header(API_KEY) apiKey: String,
        @Query(TYPE) type: String
    ): FilmsListDto

    @Headers("Content-type: application/json")
    @GET("/api/v2.2/films/{film_id}")
    suspend fun getFilmDetails(
        @Header(API_KEY) apiKey: String,
        @Path(FILM_ID) filmId: Int
    ): FilmDetailsDto

}

const val API_KEY = "X-API-KEY"
const val TYPE = "type"
const val FILM_ID = "film_id"
