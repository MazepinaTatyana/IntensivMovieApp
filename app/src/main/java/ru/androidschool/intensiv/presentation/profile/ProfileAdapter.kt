package ru.androidschool.intensiv.presentation.profile

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.androidschool.intensiv.presentation.watchlist.WatchlistFragment
import ru.androidschool.intensiv.presentation.liked_movies.LikedMoviesFragment

class ProfileAdapter(
    fragment: Fragment,
    private val itemsCount: Int
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return itemsCount
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> LikedMoviesFragment()
            else -> WatchlistFragment()
        }
    }
}
