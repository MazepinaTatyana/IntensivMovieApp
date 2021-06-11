package ru.androidschool.intensiv.extensions

import android.widget.ImageView
import com.squareup.picasso.Picasso
import ru.androidschool.intensiv.BuildConfig

fun ImageView.load(
    basePosterUrl: String = BuildConfig.BASE_POSTER_URL,
    sizePoster: String = BuildConfig.SMALL_POSTER_SIZE,
    url: String
) {
    Picasso.get()
        .load(basePosterUrl + sizePoster + url)
        .into(this)
}
