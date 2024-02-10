package ru.tinkoff.lab.presentation.favouriteFilms.filmList.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.tinkoff.lab.domain.model.Film

object DiffCallBack : DiffUtil.ItemCallback<Film>() {
    override fun areItemsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem.filmId == newItem.filmId
    }

    override fun areContentsTheSame(oldItem: Film, newItem: Film): Boolean {
        return oldItem == newItem
    }
}