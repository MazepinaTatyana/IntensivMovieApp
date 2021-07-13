package ru.androidschool.intensiv.presentation.watchlist

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text.*
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.presentation.extension.load
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieEntity

class MoviePreviewItem(
    private val content: MovieEntity,
    private val onClick: (movieFromEntityDtoModel: MovieEntity) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_small

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.image_preview.setOnClickListener {
            onClick.invoke(content)
        }
        viewHolder.image_preview.load(sizePoster = BuildConfig.SMALL_POSTER_SIZE, url = content.posterPath)
    }
}
