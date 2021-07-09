package ru.androidschool.intensiv.ui.feed

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.movies.MovieVo
import ru.androidschool.intensiv.extensions.load

class MovieItem(
    private val content: MovieVo,
    private val onClick: (resultApi: MovieVo) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_with_text

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.description.text = content.title
        viewHolder.movie_rating.rating = content.rating
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }
        content.posterPath.let {
            viewHolder.image_preview.load(url = it)
        }
    }
}
