package ru.tinkoff.lab.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.tinkoff.lab.data.local.model.FilmDb

@Dao
interface FilmDao {
    @Query("SELECT * FROM favouritefilms")
    fun getFavouriteFilms(): Flow<List<FilmDb>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFilm(film: FilmDb)

    @Query("DELETE FROM favouritefilms WHERE filmId = :filmId")
    suspend fun deleteFilm(filmId: Int)

    @Query("SELECT * FROM favouritefilms WHERE filmId = :filmId")
    suspend fun getFilm(filmId: Int): FilmDb

    @Query("SELECT filmId FROM favouritefilms")
    fun getFavouritesId(): Flow<List<Int>>

    @Query("SELECT * FROM favouritefilms WHERE nameRu LIKE :searchQuery")
    fun search(searchQuery: String): Flow<List<FilmDb>>
}