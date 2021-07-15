package ru.androidschool.intensiv.presentation.liked_movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.koin.core.KoinComponent
import org.koin.core.inject
import ru.androidschool.intensiv.data.db.model_db.entities_db.FavouriteMovies
import ru.androidschool.intensiv.domain.usecase.db_use_case.DbFavouriteMovieUseCase
import ru.androidschool.intensiv.domain.usecase.extensions.applySchedulers
import ru.androidschool.intensiv.presentation.base.Resource

class LikedMovieViewModel : ViewModel(), KoinComponent {

    private val dbFavouriteUseCase: DbFavouriteMovieUseCase by inject()

    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()

    val moviesList: LiveData<Resource<List<FavouriteMovies>>>
    get() = list
    private var list = MutableLiveData<Resource<List<FavouriteMovies>>>()

    val error: LiveData<Resource<Throwable>>
    get() = throwable
    private var throwable = MutableLiveData<Resource<Throwable>>()

    init {
        getFavouriteMovies()
    }

    private fun getFavouriteMovies() {
        disposable = dbFavouriteUseCase.getFavouriteMovies()
            .applySchedulers()
            .subscribe({ movies ->
                list.value = Resource.success(movies)
            }, {
                throwable.value = Resource.error(it.message)
            })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
