package ru.androidschool.intensiv.presentation.tvshows

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.tv_shows_fragment.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.data.mappers.Mapper
import ru.androidschool.intensiv.presentation.tv_shows.TVShowsRepository
import ru.androidschool.intensiv.databinding.TvShowsFragmentBinding
import timber.log.Timber
import java.util.concurrent.TimeUnit

class TvShowsFragment : Fragment(R.layout.tv_shows_fragment) {

    private lateinit var tvShowsFragmentBinding: TvShowsFragmentBinding
    private var tvShowsRepository = TVShowsRepository
    private lateinit var disposable: Disposable
    private var compositeDisposable = CompositeDisposable()

    private val adapter by lazy {
        GroupAdapter<GroupieViewHolder>()
    }

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvShowsFragmentBinding = TvShowsFragmentBinding.bind(view)
        disposable = tvShowsRepository.getTVShows()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { progress_tv_shows.visibility = View.VISIBLE }
            .doFinally { progress_tv_shows.visibility = View.INVISIBLE }
            .subscribe({
                val listMov = Mapper().toValueObject(it)
                val list = listMov.map {
                    TvShowItem(
                        it
                    ) { tvShow -> }
                }
                tvShowsFragmentBinding.tvShowRecyclerView.adapter =
                    adapter.apply {
                        addAll(list)
                    }
            }, { error ->
                Timber.d("Error tvShow", error.message)
            })
        compositeDisposable.add(disposable)
    }

    override fun onStop() {
        super.onStop()
        adapter.clear()
        compositeDisposable.clear()
    }
}
