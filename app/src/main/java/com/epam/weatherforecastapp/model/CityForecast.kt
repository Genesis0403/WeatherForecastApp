package com.epam.weatherforecastapp.model

import kotlinx.android.parcel.Parcelize

@Parcelize
data class CityForecast(
    val cityName: String,
    val info: String,
    val temperature: Int,
    val units: String,
    val image: String,
    val weather: Weather,
    var isFavorite: Boolean = false,
    override val isHeader: Boolean = false
) : ForecastElement {

    companion object {
        val unit = "\u2103"
        val list = arrayListOf<ForecastElement>(
            CityForecast(
                "Minsk",
                "The best city in Belarus.",
                20,
                unit,
                "https://4esnok.by/wp-content/uploads/2018/05/1ffdf19f8814695789d4b9e5f752d561.jpg",
                Weather.SUNNY
            ),
            CityForecast(
                "Vienna",
                "A city of magnificent palaces of the most ancient Austrian princely dynasties and magical cathedrals",
                10,
                unit,
                "https://tripmydream.cc/travelhub/travel/block_gallery/73/10/gallery_big_7310.jpg",
                Weather.CLOUDY

            ),
            CityForecast(
                "Copenhagen",
                "A cozy and hospitable city with clean air and charming narrow streets in historical areas",
                11,
                unit,
                "https://tripmydream.cc/travelhub/travel/block_gallery/83/96/gallery_big_8396.jpg",
                Weather.COLD
            ),
            CityForecast(
                "Oslo",
                "A city with amazing local architecture, the good nature of the locals, the choc sea food and, of course, the beaty of the northern nature",
                25,
                unit,
                "https://tripmydream.cc/travelhub/travel/block_gallery/24/803/gallery_big_24803.jpg",
                Weather.CLOUDY
            ),
            CityForecast(
                "Stockholm",
                "A city of legendary band ABBA, well-known Karlsson, IKEA and the world-famous car brand Volvo",
                10,
                unit,
                "https://tripmydream.cc/travelhub/travel/block_gallery/15/554/gallery_big_15554.jpg",
                Weather.STORM
            ),
            CityForecast(
                "Helsinki",
                "A city where statues of workers and peasants made in the beast traditions of socialist realism side by side with luxurious mansions of the 18th centruy",
                36,
                unit,
                "https://tripmydream.cc/travelhub/travel/block_gallery/24/784/gallery_big_24784.jpg",
                Weather.SUNNY
            ),
            CityForecast(
                "Reykjavik",
                "A city with small houses of the same type upholstered with multi-colored tin soaked in the magic of the Iceland capital",
                5,
                unit,
                "https://tripmydream.cc/travelhub/travel/block_gallery/82/318/gallery_big_82318.jpg",
                Weather.SUNNY
            )
        )
    }
}