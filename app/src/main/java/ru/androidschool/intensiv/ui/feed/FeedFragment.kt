package ru.androidschool.intensiv.ui.feed

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.feed_fragment.*
import kotlinx.android.synthetic.main.feed_header.*
import kotlinx.android.synthetic.main.search_toolbar.view.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.movies.MovieRepository
import ru.androidschool.intensiv.model.movie_model.ApiResponse
import ru.androidschool.intensiv.model.movie_model.ResultApi
import ru.androidschool.intensiv.ui.afterTextChanged
import timber.log.Timber

class FeedFragment : Fragment(R.layout.feed_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }
    private val movieRepository = MovieRepository
    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()

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

        createMovieItemAndMainCard(movieRepository.getPopularMovies(), R.string.popular)
        createMovieItemAndMainCard(movieRepository.getNowPlayingMovies(), R.string.recommended)
        createMovieItemAndMainCard(movieRepository.getUpcomingMovies(), R.string.upcoming)
    }

    @SuppressLint("TimberArgCount")
    private fun createMovieItemAndMainCard(
        observable: Observable<ApiResponse>,
        mainCardTitle: Int
    ) {
        disposable = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val listMovieItem = it.results.map {
                    MovieItem(it) { movie ->
                        openMovieDetails(
                            movie
                        )
                    }
                }.toList()

                val listCardContainer = listOf(listMovieItem.let {
                    MainCardContainer(mainCardTitle, it)
                })

                movies_recycler_view.adapter = adapter.apply {
                    addAll(listCardContainer)
                }
            }, { error ->
                Timber.d(getString(mainCardTitle), error.message)
            }
            )
        compositeDisposable.add(disposable)
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
        compositeDisposable.clear()
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
