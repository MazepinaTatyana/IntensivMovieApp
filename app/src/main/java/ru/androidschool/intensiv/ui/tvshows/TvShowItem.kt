package ru.androidschool.intensiv.ui.tvshows

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_tv_show.*
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.extensions.load
import ru.androidschool.intensiv.model.tv_show_model.TvShowModel

class TvShowItem(
    private val tvShowContent: TvShowModel,
    private val onClick: (tvShow: TvShowModel) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_tv_show

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.tv_show_title.text = tvShowContent.name
        viewHolder.tv_show_rating.rating = tvShowContent.rating
        viewHolder.tv_show_content.setOnClickListener {
            onClick.invoke(tvShowContent)
        }
        viewHolder.tv_show_img.load(sizePoster = BuildConfig.BIG_POSTER_SIZE, url = tvShowContent.posterPath)
    }
}
