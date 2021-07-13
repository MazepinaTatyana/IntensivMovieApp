package ru.androidschool.intensiv.presentation.movie_details

import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.item_actor.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.dto.details_movie.ActorDto
import ru.androidschool.intensiv.presentation.extension.load

class ActorItem(
    private val content: ActorDto
) : Item() {

    override fun getLayout() = R.layout.item_actor

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val nameActor = content.name?.split(" ")
        if (!nameActor.isNullOrEmpty() && nameActor.size > 1) {
            viewHolder.item_actor_name.text = String.format("%s\n %s", nameActor[0], nameActor[1])
        } else viewHolder.item_actor_name.text = content.name
        if (!content.profilePath.isNullOrEmpty()) viewHolder.item_actor_photo.load(url = content.profilePath)
    }
}
