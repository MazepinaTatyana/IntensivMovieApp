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

class MapperRemoteToVoTestNull {

    private lateinit var movieMapper: MapperRemoteToVo
    private lateinit var movieDto: MovieDto
    private lateinit var movieVoActual: Movie
    private lateinit var movieVoExpected: Movie
    private lateinit var moviesApiResponseDto: MoviesApiResponseDto
    private lateinit var rating: Rating

    @Before
    @Throws(Exception::class)
    fun setUpNull() {
        movieMapper = MapperRemoteToVo
        movieDto = MovieDto()
        rating = Rating
        moviesApiResponseDto = MoviesApiResponseDto(
            results = listOf(movieDto)
        )
        movieVoExpected = Movie(
            id = 0,
            originalTitle = "",
            overview = "",
            popularity = 0.0,
            posterPath = "",
            releaseDate = "",
            title = "",
            voteAverage = 0.0,
            name = "",
            calculatedRating = 0.0
        )
    }

    @Test
    fun checkEqualsDtoAndVoNull() {
        movieVoActual = MapperRemoteToVo.toViewObject(movieDto)
        MatcherAssert.assertThat(movieVoExpected, CoreMatchers.`is`(movieVoActual))
    }

    @Test
    fun checkNullGetRating() {
        MatcherAssert.assertThat(0.0, CoreMatchers.`is`(rating.calculateRating(movieDto.voteAverage)))
    }

    @Test
    fun checkConvertToListMovieNull() {
        MapperRemoteToVo.convertToListMovie(moviesApiResponseDto)
        MatcherAssert.assertThat(movieDto, CoreMatchers.`is`(moviesApiResponseDto.results?.get(0)))
    }
}
