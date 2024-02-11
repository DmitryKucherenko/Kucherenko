package ru.tinkoff.lab.data.database.model

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface FilmDao {
    @Query("SELECT * FROM FilmDbModel")
    fun getFilms(): Flow<List<FilmDbModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertJoke(film: FilmDbModel)

    @Delete
    suspend fun deleteFilm(film: FilmDbModel)

    @Query("SELECT * FROM FilmDbModel WHERE filmId = :id")
    suspend fun getFilm(id:Int):FilmDbModel
}