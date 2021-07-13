package ru.androidschool.intensiv.presentation.base

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V> {
    var compositeDisposable = CompositeDisposable()

    protected var view: V? = null

    fun attachView(view: V) {
        this.view = view
    }

    fun detachView() {
        this.view = null
    }

    fun clear() {
        compositeDisposable.clear()
    }
}
