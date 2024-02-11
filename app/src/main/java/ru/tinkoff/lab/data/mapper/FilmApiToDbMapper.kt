package ru.tinkoff.lab.data.mapper

import ru.tinkoff.lab.data.network.model.DetailsFilmApi
import ru.tinkoff.lab.data.network.model.PreviewFilmApi
import ru.tinkoff.lab.domain.model.Country
import ru.tinkoff.lab.domain.model.DetailsFilm
import ru.tinkoff.lab.domain.model.Genre
import ru.tinkoff.lab.domain.model.PreviewFilm

class FilmApiToDbMapper {

    companion object {
        fun previewFilmApiToDb(previewFilmApi: PreviewFilmApi) = PreviewFilm(
            filmId = previewFilmApi.filmId,
            nameRu = previewFilmApi.nameRu,
            year = previewFilmApi.year,
            countries = previewFilmApi.countries.map { countryApi ->
                Country(countryApi.country)
            },
            genres = previewFilmApi.genres.map { genreFilmApi ->
                Genre(genreFilmApi.genre)
            },
            posterUrl = previewFilmApi.posterUrl,
            posterUrlPreview = previewFilmApi.posterUrlPreview,
        )


        fun detailsFilmApiToDb(detailsFilmApi: DetailsFilmApi) = DetailsFilm(
            kinopoiskId = detailsFilmApi.kinopoiskId,
            nameRu = detailsFilmApi.nameRu,
            posterUrl = detailsFilmApi.posterUrl,
            posterUrlPreview = detailsFilmApi.posterUrlPreview,
            description = detailsFilmApi.description ?: "",
            shortDescription = detailsFilmApi.shortDescription ?: "",
            countries = detailsFilmApi.countries.map { countryApi ->
                Country(countryApi.country)
            },
            genres = detailsFilmApi.genres.map { genreFilmApi ->
                Genre(genreFilmApi.genre)
            }
        )

        fun filmListApiToDb(filmsListApi: List<PreviewFilmApi>) =
            filmsListApi.map { previewFilmApi ->
                previewFilmApiToDb(previewFilmApi)
            }
    }
}