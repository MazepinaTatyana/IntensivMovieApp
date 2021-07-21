package ru.androidschool.intensiv

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test
import ru.androidschool.intensiv.data.dto.details_movie.*
import ru.androidschool.intensiv.data.mappers.MapperToDetailsMovie
import ru.androidschool.intensiv.data.vo.DetailsMovie

class MapperToDetailsMovieTest {
    private lateinit var movieMapper: MapperToDetailsMovie
    private lateinit var detailDto: DetailsMovieDto
    private lateinit var detailVo: DetailsMovie

    @Before
    @Throws(Exception::class)
    fun setUp() {
        movieMapper = MapperToDetailsMovie

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
                GenreDto(18, "драма")),
            homepage = "https://www.disneyplus.com/movies/hamilton/3uPmBHWlO6HJ",
            productionCompany = listOf(
                ProductionCompanyDto(
                    89394,
                    "/m1Hku3PhgdsQiPO8uVe7szyrxAb.png",
                    "RadicalMedia",
                    "US"),
                ProductionCompanyDto(
                    122645,
                    null,
                    "Nevis Productions",
                    "")
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
    }

    @Test
    fun checkEqualsDtoAndVo() {
        detailVo = MapperToDetailsMovie.convertToDetailsMovie(detailDto)
        MatcherAssert.assertThat(detailVo.id, CoreMatchers.`is`(detailDto.id))
        MatcherAssert.assertThat(detailVo.backdropPath, CoreMatchers.`is`(detailDto.backdropPath))
        MatcherAssert.assertThat(detailVo.originalLanguage, CoreMatchers.`is`(detailDto.originalLanguage))
        MatcherAssert.assertThat(detailVo.video, CoreMatchers.`is`(detailDto.video))
        MatcherAssert.assertThat(detailVo.voteCount, CoreMatchers.`is`(detailDto.voteCount))
        MatcherAssert.assertThat(detailVo.genreDtos, CoreMatchers.`is`(detailDto.genres))
        MatcherAssert.assertThat(detailVo.homepage, CoreMatchers.`is`(detailDto.homepage))
        MatcherAssert.assertThat(detailVo.productionCompanyDtos, CoreMatchers.`is`(detailDto.productionCompany))
        MatcherAssert.assertThat(detailVo.productionCountryDtos, CoreMatchers.`is`(detailDto.productionCountry))
        MatcherAssert.assertThat(detailVo.revenue, CoreMatchers.`is`(detailDto.revenue))
        MatcherAssert.assertThat(detailVo.runtime, CoreMatchers.`is`(detailDto.runtime))
        MatcherAssert.assertThat(detailVo.spokenLanguageDtos, CoreMatchers.`is`(detailDto.spokenLanguage))
        MatcherAssert.assertThat(detailVo.status, CoreMatchers.`is`(detailDto.status))
        MatcherAssert.assertThat(detailVo.tagline, CoreMatchers.`is`(detailDto.tagline))
        MatcherAssert.assertThat(detailVo.originalTitle, CoreMatchers.`is`(detailDto.originalTitle))
        MatcherAssert.assertThat(detailVo.overview, CoreMatchers.`is`(detailDto.overview))
        MatcherAssert.assertThat(detailVo.popularity, CoreMatchers.`is`(detailDto.popularity))
        MatcherAssert.assertThat(detailVo.posterPath, CoreMatchers.`is`(detailDto.posterPath))
        MatcherAssert.assertThat(detailVo.releaseDate, CoreMatchers.`is`(detailDto.releaseDate))
        MatcherAssert.assertThat(detailVo.title, CoreMatchers.`is`(detailDto.title))
        MatcherAssert.assertThat(detailVo.voteAverage, CoreMatchers.`is`(detailDto.voteAverage))
        MatcherAssert.assertThat(detailVo.rating, CoreMatchers.`is`(4.2))
    }

//    @Test
//    fun checkGetRating() {
//        MatcherAssert.assertThat(4.2, CoreMatchers.`is`(MapperToDetailsMovie.getRating(detailDto)))
//    }

    @After
    fun afterTest() {
        detailDto = DetailsMovieDto()
    }
}

class MapperToDetailsMovieNullTest {
    private lateinit var movieMapper: MapperToDetailsMovie
    private lateinit var detailDto: DetailsMovieDto
    private lateinit var detailVo: DetailsMovie

    @Before
    @Throws(Exception::class)
    fun setUp() {
        movieMapper = MapperToDetailsMovie
        detailDto = DetailsMovieDto()
    }

    @Test
    fun checkEqualsDtoAndVo() {
        detailVo = MapperToDetailsMovie.convertToDetailsMovie(detailDto)
        MatcherAssert.assertThat(0, CoreMatchers.`is`(detailVo.id))
        MatcherAssert.assertThat("", CoreMatchers.`is`(detailVo.backdropPath))
        MatcherAssert.assertThat("", CoreMatchers.`is`(detailVo.originalLanguage))
        MatcherAssert.assertThat(false, CoreMatchers.`is`(detailVo.video))
        MatcherAssert.assertThat(0, CoreMatchers.`is`(detailVo.voteCount))
        MatcherAssert.assertThat(listOf(), CoreMatchers.`is`(detailVo.genreDtos))
        MatcherAssert.assertThat("", CoreMatchers.`is`(detailVo.homepage))
        MatcherAssert.assertThat(listOf(), CoreMatchers.`is`(detailVo.productionCompanyDtos))
        MatcherAssert.assertThat(listOf(), CoreMatchers.`is`(detailVo.productionCountryDtos))
        MatcherAssert.assertThat(0, CoreMatchers.`is`(detailVo.revenue))
        MatcherAssert.assertThat(0, CoreMatchers.`is`(detailVo.runtime))
        MatcherAssert.assertThat(listOf(), CoreMatchers.`is`(detailVo.spokenLanguageDtos))
        MatcherAssert.assertThat("", CoreMatchers.`is`(detailVo.status))
        MatcherAssert.assertThat("", CoreMatchers.`is`(detailVo.tagline))
        MatcherAssert.assertThat("", CoreMatchers.`is`(detailVo.originalTitle))
        MatcherAssert.assertThat("", CoreMatchers.`is`(detailVo.overview))
        MatcherAssert.assertThat(0.0, CoreMatchers.`is`(detailVo.popularity))
        MatcherAssert.assertThat("", CoreMatchers.`is`(detailVo.posterPath))
        MatcherAssert.assertThat("", CoreMatchers.`is`(detailVo.releaseDate))
        MatcherAssert.assertThat("", CoreMatchers.`is`(detailVo.title))
        MatcherAssert.assertThat(0.0, CoreMatchers.`is`(detailVo.voteAverage))
        MatcherAssert.assertThat(0.0, CoreMatchers.`is`(detailVo.rating))
    }
//
//    @Test
//    fun checkGetRating() {
//        MatcherAssert.assertThat(0.0, CoreMatchers.`is`(MapperToDetailsMovie.getRating(detailDto)))
//    }

    @After
    fun afterTest() {
        detailDto = DetailsMovieDto()
    }
}