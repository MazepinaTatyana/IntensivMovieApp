package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.item_with_text.*
import kotlinx.android.synthetic.main.movie_details_fragment.*
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.details_movie.MockDetailsMovieRepository
import ru.androidschool.intensiv.data.tv_shows.MockTVShowsRepository
import ru.androidschool.intensiv.extensions.load
import ru.androidschool.intensiv.ui.feed.FeedFragment
import ru.androidschool.intensiv.ui.tvshows.TvShowItem

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailsTitle = requireArguments().getString(FeedFragment.KEY_TITLE)
        details_movie_title.text = detailsTitle
        details_movie_img.load("https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg")

        details_movie_recycler_view.adapter = adapter.apply { addAll(listOf()) }

        val actorList =
            MockDetailsMovieRepository.getDetailsMovie().actors
                .map {
                ActorItem(
                    it
                ) }.toList()

        details_movie_recycler_view.adapter = adapter.apply { addAll(actorList) }

//        Picasso.get()
//            .load("https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg")
//            .into(details_movie_img)
    }
}
