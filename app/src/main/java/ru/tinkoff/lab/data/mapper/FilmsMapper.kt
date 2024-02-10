package ru.tinkoff.lab.data.mapper

import ru.tinkoff.lab.data.local.model.FilmDb
import ru.tinkoff.lab.data.network.dto.CountryDto
import ru.tinkoff.lab.data.network.dto.FilmDetailsDto
import ru.tinkoff.lab.data.network.dto.FilmDto
import ru.tinkoff.lab.domain.model.FilmDetails
import ru.tinkoff.lab.domain.model.Film

class FilmsMapper {
    companion object {
        private fun filmDtoToFilm(filmDto: FilmDto) = Film(
            filmId = filmDto.filmId,
            nameRu = filmDto.nameRu,
            year = filmDto.year,
            countries = filmDto.countries.map { country ->
                country.country
            },
            genres = filmDto.genres.map { genre ->
                genre.genre
            },
            posterUrl = filmDto.posterUrl,
            posterUrlPreview = filmDto.posterUrlPreview,
        )


        fun filmDetailsDtoToFilmDetails(filmDetailsDto: FilmDetailsDto) = FilmDetails(
            kinopoiskId = filmDetailsDto.kinopoiskId,
            nameRu = filmDetailsDto.nameRu,
            posterUrl = filmDetailsDto.posterUrl,
            posterUrlPreview = filmDetailsDto.posterUrlPreview,
            description = filmDetailsDto.description ?: "",
            shortDescription = filmDetailsDto.shortDescription ?: "",
            countries = filmDetailsDto.countries.map { country ->
                country.country
            },
            genres = filmDetailsDto.genres.map { genre ->
                genre.genre
            }
        )

        fun filmListDtoToFilmList(filmsListApi: List<FilmDto>) =
            filmsListApi.map { previewFilmApi ->
                filmDtoToFilm(previewFilmApi)
            }

        fun filmDbToFilm(filmDb: FilmDb): Film =
            Film(
                filmId = filmDb.filmId,
                nameRu = filmDb.nameRu,
                year = filmDb.year,
                countries = filmDb.countries,
                genres = filmDb.genres,
                posterUrl = filmDb.posterUrl,
                posterUrlPreview = filmDb.posterUrlPreview
            )

        fun filmDbListToFilmList(filmDbList: List<FilmDb>): List<Film> =
            filmDbList.map { filmDb ->
                filmDbToFilm(filmDb)
            }

        fun filmToFilmDb(film: Film): FilmDb =
            FilmDb(
                filmId = film.filmId,
                nameRu = film.nameRu,
                year = film.year,
                countries = film.countries,
                genres = film.genres,
                posterUrl = film.posterUrl,
                posterUrlPreview = film.posterUrlPreview
            )

    }
}