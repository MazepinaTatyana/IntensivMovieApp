package ru.androidschool.intensiv.ui.tvshows

import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_tv_show.*
import kotlinx.android.synthetic.main.item_with_text.content
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.TvShow

class TvShowItem (
    private val content: TvShow,
    private val onClick: (tvShow: TvShow) -> Unit
) : Item() {

    override fun getLayout() = R.layout.item_tv_show

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.tv_show_title.text = content.title
        viewHolder.tv_show_rating.rating = content.rating
        viewHolder.content.setOnClickListener {
            onClick.invoke(content)
        }

        Picasso.get()
            .load("https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg")
            .into(viewHolder.tv_show_img)
    }
}