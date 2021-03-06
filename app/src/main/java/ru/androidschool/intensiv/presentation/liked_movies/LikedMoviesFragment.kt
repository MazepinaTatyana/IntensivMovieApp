package ru.androidschool.intensiv.presentation.liked_movies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.databinding.FragmentLikedMoviesBinding
import ru.androidschool.intensiv.presentation.watchlist.MoviePreviewItem
import timber.log.Timber

class LikedMoviesFragment : Fragment(R.layout.fragment_liked_movies) {

    private lateinit var likedMoviesBinding: FragmentLikedMoviesBinding
    private var moviesList = listOf<MoviePreviewItem>()
    private lateinit var likedMovieViewModel: LikedMovieViewModel
    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        likedMoviesBinding = FragmentLikedMoviesBinding.bind(view)
        likedMovieViewModel = ViewModelProvider(this).get(LikedMovieViewModel::class.java)

        val likedMoviesRecycler = likedMoviesBinding.likedMovies.root
        likedMoviesRecycler.layoutManager = GridLayoutManager(context, 4)

        likedMovieViewModel.moviesList.observe(viewLifecycleOwner, Observer { list ->
            moviesList = list.data?.map {
                    MoviePreviewItem(
                        it.movie
                    ) { movie -> }
                }?.toList() ?: listOf()

            likedMoviesRecycler.adapter = adapter.apply {
                addAll(moviesList)
            }
        })

        likedMovieViewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Timber.e("Error liked movies", error.message)
        })
    }
}
