package ru.androidschool.intensiv.presentation.extension

import android.view.View
import io.reactivex.Single

fun <T> Single<T>.applyVisibilityProgressBar(progressBar: View): Single<T> {
    return this.doOnSubscribe { progressBar.visibility = View.VISIBLE }
        .doFinally { progressBar.visibility = View.INVISIBLE }
}
