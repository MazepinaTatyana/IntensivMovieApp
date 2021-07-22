package ru.androidschool.intensiv

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import ru.androidschool.intensiv.data.dto.movie_dto.MovieDto
import ru.androidschool.intensiv.data.dto.movie_dto.MoviesApiResponseDto
import ru.androidschool.intensiv.data.mappers.MapperRemoteToVo
import ru.androidschool.intensiv.data.mappers.Rating
import ru.androidschool.intensiv.data.vo.Movie
import kotlin.jvm.Throws

class MapperRemoteToVoTest {
    private lateinit var movieMapper: MapperRemoteToVo
    private lateinit var moviesApiResponseDto: MoviesApiResponseDto
    private lateinit var movieDto: MovieDto
    private lateinit var movieVoActual: Movie
    private lateinit var movieVoExpected: Movie
    private lateinit var rating: Rating

    @Before
    @Throws(Exception::class)
    fun setUp() {
        movieMapper = MapperRemoteToVo
        rating = Rating
        movieDto = MovieDto(
            adult = false,
            backdropPath = "/w2uGvCpMtvRqZg6waC1hvLyZoJa.jpg",
            genreIds = listOf(10749),
            id = 696374,
            originalLanguage = "en",
            originalTitle = "Gabriel's Inferno",
            overview = "История побега одного человека из собственного ада в попытке получить невозможное — прощение и любовь.",
            popularity = 10.412,
            posterPath = "/oyG9TL7FcRP4EZ9Vid6uKzwdndz.jpg",
            releaseDate = "2020-05-29",
            title = "Инферно Габриэля",
            video = false,
            voteAverage = 8.7,
            voteCount = 1965,
            name = "Gabriel's Inferno"
        )

        movieVoExpected = Movie(
            id = 696374,
            originalTitle = "Gabriel's Inferno",
            overview = "История побега одного человека из собственного ада в попытке получить невозможное — прощение и любовь.",
            popularity = 10.412,
            posterPath = "/oyG9TL7FcRP4EZ9Vid6uKzwdndz.jpg",
            releaseDate = "2020-05-29",
            title = "Инферно Габриэля",
            voteAverage = 8.7,
            name = "Gabriel's Inferno",
            calculatedRating = 4.35
        )

        moviesApiResponseDto = MoviesApiResponseDto(
            results = listOf(movieDto)
        )
    }

    @Test
    fun checkEqualsDtoAndVo() {
        movieVoActual = MapperRemoteToVo.toViewObject(movieDto)
        MatcherAssert.assertThat(movieVoExpected, CoreMatchers.`is`(movieVoActual))
    }

    @Test
    fun checkConvertToListMovie() {
        MapperRemoteToVo.convertToListMovie(moviesApiResponseDto)
        MatcherAssert.assertThat(movieDto, CoreMatchers.`is`(moviesApiResponseDto.results?.get(0)))
    }

    @Test
    fun checkGetRating() {
        MatcherAssert.assertThat(4.35, CoreMatchers.`is`(rating.calculateRating(movieDto.voteAverage)))
    }
}
