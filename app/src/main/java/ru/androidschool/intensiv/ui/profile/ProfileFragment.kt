package ru.androidschool.intensiv.ui.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.android.synthetic.main.fragment_profile.*
import ru.androidschool.intensiv.R
import ru.androidschool.intensiv.database.MovieDatabase
import ru.androidschool.intensiv.ui.liked_movies.LikedMoviesFragment
import ru.androidschool.intensiv.ui.watchlist.MoviePreviewItem
import timber.log.Timber

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var profileTabLayoutTitles: Array<String>
    private var countLikedMovies = 0

    private var profilePageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            Toast.makeText(
                requireContext(),
                "Selected position: $position",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    @SuppressLint("TimberArgCount")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Picasso.get()
            .load(R.drawable.ic_avatar)
            .transform(CropCircleTransformation())
            .placeholder(R.drawable.ic_avatar)
            .into(avatar)

        profileTabLayoutTitles = resources.getStringArray(R.array.tab_titles)

        val profileAdapter = ProfileAdapter(
            this,
            profileTabLayoutTitles.size
        )
        doppelgangerViewPager.adapter = profileAdapter

        doppelgangerViewPager.registerOnPageChangeCallback(profilePageChangeCallback)

        TabLayoutMediator(tabLayout, doppelgangerViewPager) { tab, position ->
            var title = ""
            when (position) {
                0 -> {
                    MovieDatabase.getInstance(requireContext()).getMovieDao().getMovies()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ movies ->
                            countLikedMovies = movies.size
                            title = String.format(getString(R.string.liked), countLikedMovies)
                            setTableTitle(title, tab)
                        }, {
                            Timber.e("error db", it.message)
                        })
                }

                1 -> {
                    title = profileTabLayoutTitles[position]
                    val parts = profileTabLayoutTitles[position].split(" ")
                    val number = parts[0]
                    setTableTitle(title, tab)
                }
            }
        }.attach()
    }

    private fun setTableTitle(
        title: String,
        tab: TabLayout.Tab
    ) {
        val spannableStringTitle = SpannableString(title)
        spannableStringTitle.setSpan(RelativeSizeSpan(PROPORTION_TABLE_COLUMN), START_TABLE_COLUMN, END_TABLE_COLUMN, FLAG_TABLE_COLUMN)
        tab.text = spannableStringTitle
    }

    companion object {
        private const val PROPORTION_TABLE_COLUMN = 2F
        private const val START_TABLE_COLUMN = 0
        private const val END_TABLE_COLUMN = 2
        private const val FLAG_TABLE_COLUMN = 2
    }
}
