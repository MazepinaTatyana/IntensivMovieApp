package ru.androidschool.intensiv.ui.watchlist

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text.*
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.extensions.load
import ru.androidschool.intensiv.model.db_movie_model.FavouriteMovies
import ru.androidschool.intensiv.model.db_movie_model.FavouriteMoviesEntity
import ru.androidschool.intensiv.model.db_movie_model.Movie

class MoviePreviewItem(
    private val content: Movie,
    private val onClick: (movieDtoModel: Movie) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_small

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.image_preview.setOnClickListener {
            onClick.invoke(content)
        }
        viewHolder.image_preview.load(sizePoster = BuildConfig.SMALL_POSTER_SIZE, url = content.posterPath)
    }
}