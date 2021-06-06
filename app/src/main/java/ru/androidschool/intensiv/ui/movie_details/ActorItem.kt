package ru.androidschool.intensiv.ui.movie_details

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_actor.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.extensions.load

class ActorItem(
    private val content: Actor
) : Item() {

    override fun getLayout() = R.layout.item_actor

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val nameActor = content.name?.split(" ")
        if (nameActor != null && nameActor.isNotEmpty())
            viewHolder.item_actor_name.text = String.format("%s\n %s", nameActor[0], nameActor[1])
        viewHolder.item_actor_photo.load("https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg")
    }
}
