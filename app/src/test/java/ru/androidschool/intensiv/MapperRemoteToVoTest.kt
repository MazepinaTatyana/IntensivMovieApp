package ru.androidschool.intensiv

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import ru.androidschool.intensiv.data.dto.movie_dto.MovieDto
import ru.androidschool.intensiv.data.dto.movie_dto.MoviesApiResponseDto
import ru.androidschool.intensiv.data.mappers.MapperRemoteToVo
import ru.androidschool.intensiv.data.mappers.Rating
import ru.androidschool.intensiv.data.vo.Movie

class MapperRemoteToVoTest {
    private lateinit var movieMapper: MapperRemoteToVo
    private lateinit var moviesApiResponseDto: MoviesApiResponseDto
    private lateinit var movieDto: MovieDto
    private lateinit var movieVo: Movie
    private lateinit var rating: Rating

    @Before
    @Throws(Exception::class)
    fun setUp() {
        movieMapper = MapperRemoteToVo
        rating = Rating()
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

        moviesApiResponseDto = MoviesApiResponseDto(
            results = listOf(movieDto)
        )
    }

    @Test
    fun checkEqualsDtoAndVo() {
        movieVo = MapperRemoteToVo.toViewObject(movieDto)
        MatcherAssert.assertThat(movieVo.id, CoreMatchers.`is`(movieDto.id))
        MatcherAssert.assertThat(movieVo.originalTitle, CoreMatchers.`is`(movieDto.originalTitle))
        MatcherAssert.assertThat(movieVo.overview, CoreMatchers.`is`(movieDto.overview))
        MatcherAssert.assertThat(movieVo.popularity, CoreMatchers.`is`(movieDto.popularity))
        MatcherAssert.assertThat(movieVo.posterPath, CoreMatchers.`is`(movieDto.posterPath))
        MatcherAssert.assertThat(movieVo.releaseDate, CoreMatchers.`is`(movieDto.releaseDate))
        MatcherAssert.assertThat(movieVo.title, CoreMatchers.`is`(movieDto.title))
        MatcherAssert.assertThat(movieVo.voteAverage, CoreMatchers.`is`(movieDto.voteAverage))
        MatcherAssert.assertThat(movieVo.name, CoreMatchers.`is`(movieDto.name))
        MatcherAssert.assertThat(movieVo.rating, CoreMatchers.`is`(4.35))
    }

    @Test
    fun checkConvertToListMovie() {
        MapperRemoteToVo.convertToListMovie(moviesApiResponseDto)
        MatcherAssert.assertThat(movieDto, CoreMatchers.`is`(moviesApiResponseDto.results?.get(0)))
    }

    @Test
    fun checkGetRating() {
        MatcherAssert.assertThat(4.35, CoreMatchers.`is`(rating.getRating(movieDto)))
    }

    @After
    fun afterTest() {
        movieDto = MovieDto()
    }
}

class MapperRemoteToVoTestNull {

    private lateinit var movieMapper: MapperRemoteToVo
    private lateinit var movieDto: MovieDto
    private lateinit var movieVo: Movie
    private lateinit var moviesApiResponseDto: MoviesApiResponseDto
    private lateinit var rating: Rating

    @Before
    @Throws(Exception::class)
    fun setUpNull() {
        movieMapper = MapperRemoteToVo
        movieDto = MovieDto()
        rating = Rating()
        moviesApiResponseDto = MoviesApiResponseDto(
            results = listOf(movieDto)
        )
    }

    @Test
    fun checkEqualsDtoAndVoNull() {
        movieVo = MapperRemoteToVo.toViewObject(movieDto)
        MatcherAssert.assertThat(0, CoreMatchers.`is`(movieVo.id))
        MatcherAssert.assertThat("", CoreMatchers.`is`(movieVo.originalTitle))
        MatcherAssert.assertThat("", CoreMatchers.`is`(movieVo.overview))
        MatcherAssert.assertThat(0.0, CoreMatchers.`is`(movieVo.popularity))
        MatcherAssert.assertThat("", CoreMatchers.`is`(movieVo.posterPath))
        MatcherAssert.assertThat("", CoreMatchers.`is`(movieVo.releaseDate))
        MatcherAssert.assertThat("", CoreMatchers.`is`(movieVo.title))
        MatcherAssert.assertThat(0.0, CoreMatchers.`is`(movieVo.voteAverage))
        MatcherAssert.assertThat("", CoreMatchers.`is`(movieVo.name))
        MatcherAssert.assertThat(0.0, CoreMatchers.`is`(movieVo.rating))
    }

    @Test
    fun checkNullGetRating() {
        MatcherAssert.assertThat(0.0, CoreMatchers.`is`(rating.getRating(movieDto)))
    }

    @Test
    fun checkConvertToListMovieNull() {
        MapperRemoteToVo.convertToListMovie(moviesApiResponseDto)
        MatcherAssert.assertThat(movieDto, CoreMatchers.`is`(moviesApiResponseDto.results?.get(0)))
    }

    @After
    fun afterTestNull() {
        movieDto = MovieDto()
    }
}
