package ru.androidschool.intensiv.domain.usecase.extensions

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.response(callback: CallBack<T>.() -> Unit) {
    val callBack = CallBack<T>()
    callback.invoke(callBack)
    this.enqueue(callBack)
}

class CallBack<T> : Callback<T> {

    var onResponse: ((Response<T>) -> Unit)? = null
    var onFailure: ((t: Throwable?) -> Unit)? = null

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure?.invoke(t)
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        onResponse?.invoke(response)
    }
}
