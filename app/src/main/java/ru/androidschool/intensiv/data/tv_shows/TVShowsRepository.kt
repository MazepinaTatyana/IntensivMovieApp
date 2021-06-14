package ru.androidschool.intensiv.data.tv_shows

object TVShowsRepository {

    fun getTVShows(): List<TvShow> {

        val tvShowsList = mutableListOf<TvShow>()
        for (x in 0..10) {
            val tvShow = TvShow(
                title = "Game of Thrones $x",
                voteAverage = 10.0 - x
            )
            tvShowsList.add(tvShow)
        }

        return tvShowsList
    }
}
