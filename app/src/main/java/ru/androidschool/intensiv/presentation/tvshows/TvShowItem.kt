package ru.androidschool.intensiv.presentation.tvshows

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_tv_show.*
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.vo.Movie
import ru.androidschool.intensiv.presentation.extension.load

class TvShowItem(
    private val tvShowContent: Movie,
    private val onClick: (result: Movie) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_tv_show

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.tv_show_title.text = tvShowContent.name
        viewHolder.tv_show_rating.rating = tvShowContent.calculatedRating.toFloat()
        viewHolder.tv_show_content.setOnClickListener {
            onClick.invoke(tvShowContent)
        }
        tvShowContent.posterPath.let {
            viewHolder.tv_show_img.load(sizePoster = BuildConfig.BIG_POSTER_SIZE, url = it)
        }
    }
}
