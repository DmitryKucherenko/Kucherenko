package ru.tinkoff.lab.presentation.FilmList.adapter

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import ru.tinkoff.lab.R
import ru.tinkoff.lab.databinding.PreviewFilmItemBinding
import ru.tinkoff.lab.domain.model.PreviewFilm

class PFilmHolder(
    private val itemClickListener: ItemClickListener?,
    binding: PreviewFilmItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val filmName = binding.filmName
    private val filmGanre = binding.filmGanre
    private val previewIcon = binding.previewIcon
    private val favouriteIcon = binding.favouriteIcon
    private val circularProgressDrawable = CircularProgressDrawable(itemView.context)


    fun bind(previewFilm: PreviewFilm) {
        filmName.text = previewFilm.nameRu
        filmGanre.text = previewFilm.genres[0].genre ?: ""
        Glide.with(itemView)
            .load(previewFilm?.posterUrlPreview)
            .centerCrop()
            .placeholder(circularProgressDrawable)
            .into(previewIcon)

        itemView.setOnClickListener {
            itemClickListener?.onItemClick(previewFilm)
        }
        itemView.setOnLongClickListener {
            favouriteIcon.visibility = VISIBLE
            true
        }
    }


}