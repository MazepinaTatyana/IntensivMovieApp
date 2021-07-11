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
import ru.androidschool.intensiv.BuildConfig
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.db.model_db.entities_db.MovieDb
import ru.androidschool.intensiv.data.repository.db_repository.DBMovieRepository
import ru.androidschool.intensiv.data.repository.remote_repository.ActorsMovieRemoteRepository
import ru.androidschool.intensiv.data.repository.remote_repository.DetailsMovieRemoteRepository
import ru.androidschool.intensiv.data.vo.DetailsMovie
import ru.androidschool.intensiv.data.db.database.MovieDatabase
import ru.androidschool.intensiv.databinding.MovieDetailsFragmentBinding
import ru.androidschool.intensiv.domain.usecase.ActorsMovieRemoteUseCase
import ru.androidschool.intensiv.domain.usecase.DetailsMovieRemoteUseCase
import ru.androidschool.intensiv.presentation.extension.load
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMoviesEntity
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }
    private lateinit var movieDetailsFragmentBinding: MovieDetailsFragmentBinding
    private val detailsMovieRemoteUseCase = DetailsMovieRemoteUseCase(DetailsMovieRemoteRepository())
    private val actorsDetailRemoteUseCase = ActorsMovieRemoteUseCase(ActorsMovieRemoteRepository())
    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()
    private lateinit var dbMovieRepository: DBMovieRepository
    private lateinit var detailsMovie: DetailsMovie
    private lateinit var movieFromDb: MovieDb

    @SuppressLint("TimberArgCount")
    @ExperimentalStdlibApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailsFragmentBinding = MovieDetailsFragmentBinding.bind(view)
        val database = MovieDatabase.getInstance(requireContext())
        dbMovieRepository = DBMovieRepository(database)
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
                    genresName.add(it.name)
                }
                movieDetailsFragmentBinding.detailsMovieGenre.text =
                    genresName.joinToString(", ")

                val productionsCompany = arrayListOf<String>()
                detailsMovie.productionCompanyDtos.forEach {
                    productionsCompany.add(it.name)
                }
                movieDetailsFragmentBinding.detailsMovieStudioName.text =
                    productionsCompany.joinToString(", ")

                movieDetailsFragmentBinding.detailsMovieRating.rating = detailsMovie.rating.toFloat()
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
        disposable = dbMovieRepository.getFavouriteMovieById(movieId)
            .applySchedulers()
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
                    dbMovieRepository.saveFavouriteMovie(favouriteMovie)
                        .applySchedulers()
                        .subscribe({
                            Timber.e("saved movie", "saved movie")
                        }, {
                            Timber.e("error db", it.message)
                        })
                }
                false -> {
                    dbMovieRepository.deleteFavouriteMovie(favouriteMovie)
                        .applySchedulers()
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
