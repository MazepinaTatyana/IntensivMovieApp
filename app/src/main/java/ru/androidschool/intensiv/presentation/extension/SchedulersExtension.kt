package ru.androidschool.intensiv.extensions

import android.view.View
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.applySchedulers(): Single<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun Completable.applySchedulers(): Completable {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}

fun <T> Single<T>.applyVisibilityProgressBar(progressBar: View): Single<T> {
    return this.doOnSubscribe { progressBar.visibility = View.VISIBLE }
        .doFinally { progressBar.visibility = View.INVISIBLE }
}

fun Completable.applyVisibilityProgressBar(progressBar: View): Completable {
    return this.doOnSubscribe { progressBar.visibility = View.VISIBLE }
        .doFinally { progressBar.visibility = View.INVISIBLE }
}
