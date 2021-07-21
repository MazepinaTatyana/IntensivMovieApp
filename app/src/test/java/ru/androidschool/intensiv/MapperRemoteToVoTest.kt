package ru.androidschool.intensiv

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import ru.androidschool.intensiv.data.dto.movie_dto.MovieDto
import ru.androidschool.intensiv.data.mappers.MapperRemoteToVo
import ru.androidschool.intensiv.data.vo.Movie

class MapperRemoteToVoTest {
    private lateinit var movieMapper: MapperRemoteToVo
    private lateinit var movieDto: MovieDto

    @Before
    @Throws(Exception::class)
    fun setUp() {
        movieMapper = MapperRemoteToVo
        movieDto = MovieDto()
    }

    @Test
    fun checkEqualsDtoAndVo(dto: MovieDto, vo: Movie) {
        MatcherAssert.assertThat(vo.id, CoreMatchers.`is` (dto.id))
        MatcherAssert.assertThat(vo.originalTitle, CoreMatchers.`is` (dto.originalTitle))
        MatcherAssert.assertThat(vo.overview, CoreMatchers.`is` (dto.overview))
        MatcherAssert.assertThat(vo.popularity, CoreMatchers.`is` (dto.popularity))
        MatcherAssert.assertThat(vo.posterPath, CoreMatchers.`is` (dto.posterPath))
        MatcherAssert.assertThat(vo.releaseDate, CoreMatchers.`is` (dto.releaseDate))
        MatcherAssert.assertThat(vo.title, CoreMatchers.`is` (dto.title))
        MatcherAssert.assertThat(vo.voteAverage, CoreMatchers.`is` (dto.voteAverage))
        MatcherAssert.assertThat(vo.name, CoreMatchers.`is` (dto.name))
    }
}