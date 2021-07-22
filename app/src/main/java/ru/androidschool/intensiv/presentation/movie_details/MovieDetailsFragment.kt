package ru.androidschool.intensiv.presentation.movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMoviesEntity
import ru.androidschool.intensiv.data.vo.DetailsMovie
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.domain.usecase.db_use_case.DbFavouriteMovieUseCase
import ru.androidschool.intensiv.domain.usecase.remote_use_case.ActorsMovieRemoteUseCase
import ru.androidschool.intensiv.domain.usecase.remote_use_case.DetailsMovieRemoteUseCase
import ru.androidschool.intensiv.presentation.extension.load
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment), KoinComponent {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    private val detailsMovieRemoteUseCase: DetailsMovieRemoteUseCase by inject()
    private val actorsDetailRemoteUseCase: ActorsMovieRemoteUseCase by inject()
    private val dbFavouriteMovieUseCase: DbFavouriteMovieUseCase by inject()

    private lateinit var movieDetailsFragmentBinding: MovieDetailsFragmentBinding
    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()
    private lateinit var detailsMovie: DetailsMovie

    @SuppressLint("TimberArgCount")
    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsFragmentBinding = MovieDetailsFragmentBinding.bind(view)
        val navArgs: MovieDetailsFragmentArgs by navArgs()
        val id = navArgs.movieId
        getMovieDatabase(id)

        movieDetailsFragmentBinding.detailsMovieBackIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        disposable = detailsMovieRemoteUseCase.getDetailsMovieById(id)
            .subscribe({
                detailsMovie = it
                checkFavouriteMovie(id)
                val genresName = arrayListOf<String>()
                detailsMovie.genreDtos?.forEach {
                    it.name?.let { it1 -> genresName.add(it1) }
                }
                movieDetailsFragmentBinding.detailsMovieGenre.text =
                    genresName.joinToString(", ")

                val productionsCompany = arrayListOf<String>()
                detailsMovie.productionCompanyDtos.forEach {
                    it.name?.let { it1 -> productionsCompany.add(it1) }
                }
                movieDetailsFragmentBinding.detailsMovieStudioName.text =
                    productionsCompany.joinToString(", ")

                movieDetailsFragmentBinding.detailsMovieRating.rating = detailsMovie.calculatedRating.toFloat()
                movieDetailsFragmentBinding.detailsMovieTextDescription.text =
                    detailsMovie.overview

                val year = detailsMovie.releaseDate?.let { releaseDate -> getYear(releaseDate) }
                movieDetailsFragmentBinding.detailsMovieYear.text = year.toString()
                movieDetailsFragmentBinding.detailsMovieImg.load(
                    sizePoster = BuildConfig.BIG_POSTER_SIZE,
                    url = detailsMovie.posterPath
                )
            }, { error ->
                Timber.d("error details movie", error.message)
            })

        disposable = actorsDetailRemoteUseCase.getActorsMovie(id)
            .subscribe({
                val actors = it.actors?.map { actor ->
                    ActorItem(actor)
                }?.toList()
                movieDetailsFragmentBinding.detailsMovieRecyclerView.adapter =
                    adapter.apply {
                        actors?.let { addAll(actors) }
                    }
            }, { error ->
                Timber.d("error actors", error.message)
            })
    }

    @SuppressLint("TimberArgCount")
    fun getMovieDatabase(movieId: Int) {
        disposable = dbFavouriteMovieUseCase.getFavouriteMovieById(movieId)
            .subscribe({ favouriteMovie ->
                movieDetailsFragmentBinding.detailsMovieFavoriteIcon.isChecked =
                    favouriteMovie.favouriteMovie.favouriteMovieId == movieId
            }, {
                Timber.e("error db", it.message)
            })

        compositeDisposable.add(disposable)
    }

    @SuppressLint("TimberArgCount")
    private fun checkFavouriteMovie(id: Int) {
        val favouriteMovie = FavouriteMoviesEntity(id)
        movieDetailsFragmentBinding.detailsMovieFavoriteIcon.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    dbFavouriteMovieUseCase.saveFavouriteMovie(favouriteMovie)
                        .subscribe({
                            Timber.e("saved movie", "saved movie")
                        }, {
                            Timber.e("error db", it.message)
                        })
                }
                false -> {
                    dbFavouriteMovieUseCase.deleteFavouriteMovie(favouriteMovie)
                        .subscribe({
                            Timber.e("delete movie", "dalete movie")
                        }, {
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
