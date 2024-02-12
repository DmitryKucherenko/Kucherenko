package ru.tinkoff.lab.presentation.filmList.previewFilmList.adapter

import android.graphics.drawable.Drawable
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
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
    private val previewFilmProgress = binding.previewProgressBar

    fun bind(previewFilm: PreviewFilm) {
        filmName.text = previewFilm.nameRu
        val genre = (previewFilm.genres.getOrNull(0)?.genre ?: "")
            .replaceFirstChar { it.uppercaseChar() }
        val year = previewFilm.year
        filmGenre.text = "$genre($year)"
        Glide.with(itemView)
            .load(previewFilm.posterUrlPreview)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    previewFilmProgress.visibility = GONE
                    return false
                }
                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    previewFilmProgress.visibility = GONE
                    return false
                }
            })
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