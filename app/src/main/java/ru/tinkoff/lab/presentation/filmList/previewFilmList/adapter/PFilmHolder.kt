package ru.tinkoff.lab.presentation.filmList.previewFilmList.adapter

import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import ru.tinkoff.lab.databinding.PreviewFilmItemBinding
import ru.tinkoff.lab.domain.model.PreviewFilm

class PFilmHolder(
    private val itemClickListener: (PreviewFilm) -> Unit,
    private val itemLongClickListener: (PreviewFilm) -> Unit,
    binding: PreviewFilmItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    private val filmName = binding.filmName
    private val filmGenre = binding.filmGanre
    private val previewIcon = binding.previewIcon
    private val favouriteIcon = binding.favouriteIcon

    fun bind(previewFilm: PreviewFilm) {
        filmName.text = previewFilm.nameRu
        val genre = (previewFilm.genres.getOrNull(0)?.genre ?: "")
            .replaceFirstChar { it.uppercaseChar() }
        val year = previewFilm.year
        filmGenre.text = "$genre($year)"
        Glide.with(itemView)
            .load(previewFilm.posterUrlPreview)
            .centerCrop()
            .into(previewIcon)

        itemView.setOnClickListener {
            itemClickListener(previewFilm)
        }
        itemView.setOnLongClickListener {
            favouriteIcon.visibility =  if(favouriteIcon.isVisible) GONE else VISIBLE
            itemLongClickListener(previewFilm)
            true
        }
    }


}