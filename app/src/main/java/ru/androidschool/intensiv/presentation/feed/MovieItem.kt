package ru.androidschool.intensiv.presentation.feed

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_with_text.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.presentation.extension.load

class MovieItem(
    private val content: Movie,
    private val onClick: (resultApi: Movie) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_with_text

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.description.text = content.title
        viewHolder.movie_rating.rating = content.calculatedRating.toFloat()
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }
        content.posterPath.let {
            viewHolder.image_preview.load(url = it)
        }
    }
}
