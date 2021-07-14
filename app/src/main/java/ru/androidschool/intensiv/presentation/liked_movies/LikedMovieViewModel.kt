package ru.androidschool.intensiv.presentation.liked_movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMovies
import ru.androidschool.intensiv.data.repository.db_repository.DbFavouriteMoviesRepository
import ru.androidschool.intensiv.domain.usecase.db_use_case.DbFavouriteMovieUseCase
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers

class LikedMovieViewModel(private val dbFavouriteRepository: DbFavouriteMoviesRepository) : ViewModel() {

    private var dbFavouriteUseCase = DbFavouriteMovieUseCase(dbFavouriteRepository)
    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()

    val moviesList: LiveData<List<FavouriteMovies>>
    get() = list
    private var list = MutableLiveData<List<FavouriteMovies>>()

    val error: LiveData<Throwable>
    get() = throwable
    private var throwable = MutableLiveData<Throwable>()

    init {
        getFavouriteMovies()
    }

    private fun getFavouriteMovies() {
        disposable = dbFavouriteUseCase.getFavouriteMovies()
            .applySchedulers()
            .subscribe({ movies ->
                list.postValue(movies)
            }, {
                throwable.postValue(it)
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
