package ru.androidschool.intensiv.presentation.liked_movies

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMovies
import ru.androidschool.intensiv.data.repository.db_repository.DbFavouriteMovieRepository
import ru.androidschool.intensiv.domain.usecase.db_use_case.DbFavouriteMovieUseCase
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers
import timber.log.Timber

class LikedMovieViewModel(private val dbFavouriteRepository: DbFavouriteMovieRepository) {

    private var dbFavouriteUseCase = DbFavouriteMovieUseCase(dbFavouriteRepository)
    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()
    private var moviesList = MutableLiveData<List<FavouriteMovies>>()

    init {
        getFavouriteMovies()
    }

    @SuppressLint("TimberArgCount")
    fun getFavouriteMovies() {
        disposable = dbFavouriteUseCase.getFavouriteMovies()
            .applySchedulers()
            .subscribe({ movies ->
                moviesList.postValue(movies)
            }, {
                Timber.e("error db", it.message)
            })
        compositeDisposable.add(disposable)
    }
}