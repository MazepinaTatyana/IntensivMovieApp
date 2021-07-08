package ru.androidschool.intensiv.ui.movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.details_movie.DetailsMovieRepository
import ru.androidschool.intensiv.data.movies.DBMovieRepository
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.extensions.load
import ru.androidschool.intensiv.model.db_movie_model.FavouriteMoviesEntity
import ru.androidschool.intensiv.model.details_movie_model.DetailsMovieModel
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }
    private lateinit var movieDetailsFragmentBinding: MovieDetailsFragmentBinding
    private val detailsMovieRepository = DetailsMovieRepository
    private lateinit var dbRepository: DBMovieRepository
    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()
    private lateinit var detailsMovie: DetailsMovieModel
    private lateinit var favouriteMovie: FavouriteMoviesEntity

    @SuppressLint("TimberArgCount")
    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsFragmentBinding = MovieDetailsFragmentBinding.bind(view)
        dbRepository = DBMovieRepository(requireContext())
        val navArgs: MovieDetailsFragmentArgs by navArgs()
        val id = navArgs.movieId
        getMovieDatabase(id)

        movieDetailsFragmentBinding.detailsMovieBackIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        disposable = detailsMovieRepository.getDetailsMovieById(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                detailsMovie = it
                checkFavouriteMovie(detailsMovie.id)
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

                val year = getYear(detailsMovie.releaseDate)
                movieDetailsFragmentBinding.detailsMovieYear.text = year.toString()
                movieDetailsFragmentBinding.detailsMovieImg.load(
                    sizePoster = BuildConfig.BIG_POSTER_SIZE,
                    url = detailsMovie.posterPath
                )
            }, { error ->
                Timber.d("error details movie", error.message)
            })

        disposable = detailsMovieRepository.getActorsMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val actors = it.actors.map { actor ->
                    ActorItem(actor)
                }.toList()

                movieDetailsFragmentBinding.detailsMovieRecyclerView.adapter =
                    adapter.apply {
                        addAll(actors)
                    }
            }, { error ->
                Timber.d("error actors", error.message)
            })

    }

    @SuppressLint("TimberArgCount")
    fun getMovieDatabase(movieId: Int) {
        disposable = dbRepository.getFavouriteMovieById(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({favouriteMovie ->
                movieDetailsFragmentBinding.detailsMovieFavoriteIcon.isChecked = favouriteMovie.movie.id == movieId
            },{
                Timber.e("error db", it.message)
            })

        compositeDisposable.add(disposable)
    }

    @SuppressLint("TimberArgCount")
    private fun checkFavouriteMovie(id: Int) {
        favouriteMovie = FavouriteMoviesEntity(id)
        movieDetailsFragmentBinding.detailsMovieFavoriteIcon.setOnCheckedChangeListener { _, isChecked ->
            when(isChecked) {
                true -> {
                    dbRepository.saveFavouriteMovie(favouriteMovie)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            Timber.e("saved movie", "saved movie")
                        },{
                            Timber.e("error db", it.message)
                        })
                }
                false -> {
                    dbRepository.deleteFavouriteMovie(favouriteMovie)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({
                            Timber.e("delete movie", "dalete movie")
                        },{
                            Timber.e("error db", it.message)
                        })
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getYear(date: String): Int {
        val calendar = Calendar.getInstance()
        calendar.time = SimpleDateFormat("yyyy").parse(date)
        val year = calendar.get(Calendar.YEAR)
        return year
    }

    override fun onStop() {
        super.onStop()
        adapter.clear()
        compositeDisposable.clear()
    }
}