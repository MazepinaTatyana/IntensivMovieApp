package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.details_movie.MockDetailsMovieRepository
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.extensions.load
import ru.androidschool.intensiv.ui.feed.FeedFragment

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }
    private lateinit var movieDetailsFragmentBinding: MovieDetailsFragmentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsFragmentBinding = MovieDetailsFragmentBinding.bind(view)

        val navArgs: MovieDetailsFragmentArgs by navArgs()
        val title = navArgs.movieId

        movieDetailsFragmentBinding.detailsMovieTitle.text = title
        movieDetailsFragmentBinding.detailsMovieRating.rating = 5f
        movieDetailsFragmentBinding.detailsMovieImg.load("https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg")

        val actorList =
            MockDetailsMovieRepository.getDetailsMovie().actors
                .map {
                ActorItem(
                    it
                ) }.toList()

        movieDetailsFragmentBinding.detailsMovieRecyclerView.adapter = adapter.apply { addAll(actorList) }

        movieDetailsFragmentBinding.detailsMovieBackIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
