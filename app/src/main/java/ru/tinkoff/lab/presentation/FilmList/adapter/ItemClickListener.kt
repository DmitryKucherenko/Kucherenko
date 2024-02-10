package ru.tinkoff.lab.presentation.FilmList.adapter

import ru.tinkoff.lab.domain.model.PreviewFilm

interface ItemClickListener {
    fun onItemClick(previewFilm: PreviewFilm?)
}