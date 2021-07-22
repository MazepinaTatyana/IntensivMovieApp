package ru.androidschool.intensiv

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import ru.androidschool.intensiv.data.dto.details_movie.*
import ru.androidschool.intensiv.data.mappers.MapperToDetailsMovie
import ru.androidschool.intensiv.data.mappers.Rating
import ru.androidschool.intensiv.data.vo.DetailsMovie
import kotlin.jvm.Throws

class MapperToDetailsMovieNullTest {
    private lateinit var movieMapper: MapperToDetailsMovie
    private lateinit var detailDto: DetailsMovieDto
    private lateinit var detailVoActual: DetailsMovie
    private lateinit var detailVoExpected: DetailsMovie
    private lateinit var rating: Rating

    @Before
    @Throws(Exception::class)
    fun setUp() {
        movieMapper = MapperToDetailsMovie
        detailDto = DetailsMovieDto()
        rating = Rating
        detailVoExpected = DetailsMovie(
            backdropPath = "",
            id = 0,
            originalLanguage = "",
            originalTitle = "",
            overview = "",
            popularity = 0.0,
            posterPath = "",
            releaseDate = "",
            title = "",
            video = false,
            voteAverage = 0.0,
            voteCount = 0,
            genreDtos = listOf(),
            homepage = "",
            productionCompanyDtos = listOf(),
            productionCountryDtos = listOf(),
            revenue = 0,
            runtime = 0,
            spokenLanguageDtos = listOf(),
            status = "",
            tagline = "",
            calculatedRating = 0.0
        )
    }

    @Test
    fun checkEqualsDtoAndVo() {
        detailVoActual = MapperToDetailsMovie.convertToDetailsMovie(detailDto)
        MatcherAssert.assertThat(detailVoExpected, CoreMatchers.`is`(detailVoActual))
    }

    @Test
    fun checkGetRating() {
        MatcherAssert.assertThat(0.0, CoreMatchers.`is`(rating.calculateRating(detailDto.voteAverage)))
    }
}
