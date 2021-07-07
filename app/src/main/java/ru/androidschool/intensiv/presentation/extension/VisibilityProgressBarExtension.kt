package ru.androidschool.intensiv.presentation.extension

import android.view.View
import android.widget.ProgressBar
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.feed_fragment.*

fun <T> Single<T>.applyVisibilityProgressBar(progressBar: ProgressBar): Single<T> {
    return this.doOnSubscribe { progressBar.visibility = View.VISIBLE }
        .doFinally { progressBar.visibility = View.INVISIBLE }
}