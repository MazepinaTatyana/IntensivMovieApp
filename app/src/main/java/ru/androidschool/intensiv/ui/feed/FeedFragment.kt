package ru.androidschool.intensiv.ui.feed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.search_toolbar.view.*
import retrofit2.Response
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.extensions.response
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.model.movie_model.ResultApi
import ru.androidschool.intensiv.network.MovieApiClient
import ru.androidschool.intensiv.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    var moviesList: List<ResultApi>? = null

    private val options = navOptions {
        anim {
            enter = R.anim.slide_in_right
            exit = R.anim.slide_out_left
            popEnter = R.anim.slide_in_left
            popExit = R.anim.slide_out_right
        }
    }

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_toolbar.search_edit_text.afterTextChanged {
            Timber.d(it.toString())
            if (it.toString().length > MIN_LENGTH) {
                openSearch(it.toString())
            }
        }

        MovieApiClient.movieApiClient.getPopularMovies().response {
            onFailure = { error ->
                Timber.e("Error popularMovies", error?.message.toString())
            }

            onResponse = { response ->
                createMovieItemAndMainCard(response, R.string.popular)
            }
        }

        MovieApiClient.movieApiClient.getNowPlayingMovies().response {

            onFailure = { error ->
                Timber.e("Error nowPlayingMovies", error?.message.toString())
            }

            onResponse = { response ->
                createMovieItemAndMainCard(response, R.string.recommended)
            }
        }

        MovieApiClient.movieApiClient.getUpcomingMovies().response {
            onFailure = { error ->
                Timber.e("Error upcomingMovies", error?.message.toString())
            }

            onResponse = { response ->
                createMovieItemAndMainCard(response, R.string.upcoming)
            }
        }
    }

    private fun createMovieItemAndMainCard(response: Response<ApiResponse>, mainCardTitle: Int) {
        moviesList = response.body()?.results

        val listMovieItem = moviesList?.map {
            MovieItem(it) { movie ->
                openMovieDetails(
                    movie
                )
            }
        }?.toList()

        val listCardContainer = listOf(listMovieItem?.let {
            MainCardContainer(mainCardTitle, it)
        })

        movies_recycler_view.adapter = adapter.apply {
            addAll(listCardContainer)
        }
    }

    private fun openMovieDetails(resultApi: ResultApi) {
        if (resultApi.id != null) {
            findNavController().navigate(
                FeedFragmentDirections.actionHomeDestToMovieDetailsFragment(
                    resultApi.id
                )
            )
        }
    }

    private fun openSearch(searchText: String) {
        val bundle = Bundle()
        bundle.putString(KEY_SEARCH, searchText)
        findNavController().navigate(R.id.search_dest, bundle, options)
    }

    override fun onStop() {
        super.onStop()
        search_toolbar.clear()
        adapter.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
    }

    companion object {
        const val MIN_LENGTH = 3
        const val KEY_TITLE = "title"
        const val KEY_SEARCH = "search"
    }
}
