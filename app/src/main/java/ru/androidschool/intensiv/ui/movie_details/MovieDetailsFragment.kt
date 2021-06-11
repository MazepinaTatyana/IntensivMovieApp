package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.extensions.load
import ru.androidschool.intensiv.extensions.response
import ru.androidschool.intensiv.network.details_movie.DetailsMovieApiClient

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }
    private lateinit var movieDetailsFragmentBinding: MovieDetailsFragmentBinding

    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsFragmentBinding = MovieDetailsFragmentBinding.bind(view)

        val navArgs: MovieDetailsFragmentArgs by navArgs()
        val id = navArgs.movieId
        DetailsMovieApiClient.detailsMovieApiClient.getDetailsMovieById(id).response {
            onFailure = { error ->
                Log.d("error details movie", error.toString())
            }
            onResponse = { respounse ->
                val detailsMovie = respounse.body()
                if (detailsMovie != null) {
                    val genresName = arrayListOf<String>()
                    detailsMovie.genres.forEach {
                        genresName.add(it.name)
                    }
                    movieDetailsFragmentBinding.detailsMovieGenre.text =
                        genresName.joinToString(", ")

                    val productionsCompany = arrayListOf<String>()
                    detailsMovie.productionCompanies.forEach {
                        productionsCompany.add(it.name)
                    }
                    movieDetailsFragmentBinding.detailsMovieStudioName.text =
                        productionsCompany.joinToString(", ")

                    movieDetailsFragmentBinding.detailsMovieRating.rating = detailsMovie.rating
                    movieDetailsFragmentBinding.detailsMovieTextDescription.text =
                        detailsMovie.overview
                    movieDetailsFragmentBinding.detailsMovieYear.text =
                        detailsMovie.releaseDate.substring(0..3)
                    movieDetailsFragmentBinding.detailsMovieImg.load(
                        sizePoster = BuildConfig.BIG_POSTER_SIZE,
                        url = detailsMovie.posterPath
                    )
                }
            }
        }

        DetailsMovieApiClient.detailsMovieApiClient.getMovieActors(id).response {
            onFailure = { error ->
                Log.d("error actors", error.toString())
            }
            onResponse = { response ->
                val actors = response.body()?.actors?.map {
                    ActorItem(it)
                }?.toList()

                movieDetailsFragmentBinding.detailsMovieRecyclerView.adapter =
                    adapter.apply {
                        if (actors != null) {
                            addAll(actors)
                        }
                    }
            }
        }

        movieDetailsFragmentBinding.detailsMovieBackIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}
