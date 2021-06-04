package ru.androidschool.intensiv.ui.movie_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_with_text.*
import kotlinx.android.synthetic.main.movie_details_fragment.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.ui.feed.FeedFragment

class MovieDetailsFragment : Fragment(R.layout.movie_details_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val detailsTitle = requireArguments().getString(FeedFragment.KEY_TITLE)
        details_movie_title.text = detailsTitle

        Picasso.get()
            .load("https://m.media-amazon.com/images/M/MV5BYTk3MDljOWQtNGI2My00OTEzLTlhYjQtOTQ4ODM2MzUwY2IwXkEyXkFqcGdeQXVyNTIzOTk5ODM@._V1_.jpg")
            .into(details_movie_img)
    }
}
