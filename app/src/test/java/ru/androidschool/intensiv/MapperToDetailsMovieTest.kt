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

class MapperToDetailsMovieTest {
    private lateinit var movieMapper: MapperToDetailsMovie
    private lateinit var detailDto: DetailsMovieDto
    private lateinit var detailVoActual: DetailsMovie
    private lateinit var detailVoExpected: DetailsMovie
    private lateinit var rating: Rating

    @Before
    @Throws(Exception::class)
    fun setUp() {
        movieMapper = MapperToDetailsMovie
        rating = Rating
        detailDto = DetailsMovieDto(
            backdropPath = "/uWVkEo9PWHu9algZsiLPi6sRU64.jpg",
            id = 556574,
            originalLanguage = "en",
            originalTitle = "Hamilton",
            overview = "Александр Гамильтон — один из отцов-основателей США, выходец из Британской Вест-Индии. Он стал одним из главных американских государственных деятелей, участвовал в войне за независимость США, помог сформировать правительство и конституцию новой страны, стал первым министром финансов.",
            popularity = 13.545,
            posterPath = "/beuhh55O9DZAUQ0K4FzPTEWkUrk.jpg",
            releaseDate = "2020-07-03",
            title = "Гамильтон",
            video = false,
            voteAverage = 8.4,
            voteCount = 762,
            genres = listOf(
                GenreDto(10402, "музыка"),
                GenreDto(36, "история"),
                GenreDto(18, "драма")
            ),
            homepage = "https://www.disneyplus.com/movies/hamilton/3uPmBHWlO6HJ",
            productionCompany = listOf(
                ProductionCompanyDto(
                    89394,
                    "/m1Hku3PhgdsQiPO8uVe7szyrxAb.png",
                    "RadicalMedia",
                    "US"
                ),
                ProductionCompanyDto(
                    122645,
                    null,
                    "Nevis Productions",
                    ""
                )
            ),
            productionCountry = listOf(
                ProductionCountryDto(
                    "US",
                    "United States of America"
                )
            ),
            revenue = 0,
            runtime = 150,
            spokenLanguage = listOf(
                SpokenLanguageDto(
                    "English",
                    "en",
                    "English"
                )
            ),
            status = "Released",
            tagline = "Американский мюзикл"
        )

        detailVoExpected = DetailsMovie(
            backdropPath = "/uWVkEo9PWHu9algZsiLPi6sRU64.jpg",
            id = 556574,
            originalLanguage = "en",
            originalTitle = "Hamilton",
            overview = "Александр Гамильтон — один из отцов-основателей США, выходец из Британской Вест-Индии. Он стал одним из главных американских государственных деятелей, участвовал в войне за независимость США, помог сформировать правительство и конституцию новой страны, стал первым министром финансов.",
            popularity = 13.545,
            posterPath = "/beuhh55O9DZAUQ0K4FzPTEWkUrk.jpg",
            releaseDate = "2020-07-03",
            title = "Гамильтон",
            video = false,
            voteAverage = 8.4,
            voteCount = 762,
            genreDtos = listOf(
                GenreDto(10402, "музыка"),
                GenreDto(36, "история"),
                GenreDto(18, "драма")
            ),
            homepage = "https://www.disneyplus.com/movies/hamilton/3uPmBHWlO6HJ",
            productionCompanyDtos = listOf(
                ProductionCompanyDto(
                    89394,
                    "/m1Hku3PhgdsQiPO8uVe7szyrxAb.png",
                    "RadicalMedia",
                    "US"
                ),
                ProductionCompanyDto(
                    122645,
                    null,
                    "Nevis Productions",
                    ""
                )
            ),
            productionCountryDtos = listOf(
                ProductionCountryDto(
                    "US",
                    "United States of America"
                )
            ),
            revenue = 0,
            runtime = 150,
            spokenLanguageDtos = listOf(
                SpokenLanguageDto(
                    "English",
                    "en",
                    "English"
                )
            ),
            status = "Released",
            tagline = "Американский мюзикл",
            calculatedRating = 4.2
        )
    }

    @Test
    fun checkEqualsDtoAndVo() {
        detailVoActual = MapperToDetailsMovie.convertToDetailsMovie(detailDto)
        MatcherAssert.assertThat(detailVoExpected, CoreMatchers.`is`(detailVoActual))
    }

    @Test
    fun checkGetRating() {
        MatcherAssert.assertThat(4.2, CoreMatchers.`is`(rating.calculateRating(detailDto.voteAverage)))
    }
}
