package com.riyaz.data.remote.model.forecast


enum class WeatherConditions(val code: Int, val description: String) {
    CLEAR_SKY(0, "Clear Sky"),
    MAINLY_CLEAR(1, "Mainly Clear"),
    PARTLY_CLOUDY(2, "Partly Cloudy"),
    OVERCAST(3, "Overcast"),
    FOG(45, "Fog"),
    DEPOSITING_RIME_FOG(48, "Depositing Rime Fog"),
    LIGHT_DRIZZLE(51, "Light Drizzle"),
    MODERATE_DRIZZLE(53, "Moderate Drizzle"),
    DENSE_INTENSITY_DRIZZLE(55, "Dense Intensity Drizzle"),
    LIGHT_FREEZING_DRIZZLE(56, "Light Freezing Drizzle"),
    DENSE_INTENSITY_FREEZING_DRIZZLE(57, "Dense Intensity Freezing Drizzle"),
    SLIGHT_RAIN(61, "Slight Rain"),
    MODERATE_RAIN(63, "Moderate Rain"),
    HEAVY_INTENSITY_RAIN(65, "Heavy Intensity Rain"),
    LIGHT_FREEZING_RAIN(66, "Light Freezing Rain"),
    HEAVY_INTENSITY_FREEZING_RAIN(67, "Heavy Intensity Freezing Rain"),
    SLIGHT_SNOW_FALL(71, "Slight Snow Fall"),
    MODERATE_SNOW_FALL(73, "Moderate Snow Fall"),
    HEAVY_SNOW_FALL(75, "Heavy Snow Fall"),
    SNOW_GRAINS(77, "Snow Grains"),
    SLIGHT_RAIN_SHOWER(80, "Slight Rain Shower"),
    MODERATE_RAIN_SHOWER(81, "Moderate Rain Shower"),
    VIOLENT_RAIN_SHOWER(82, "Violent Rain Shower"),
    SLIGHT_SNOW_SHOWER(85, "Slight Snow Shower"),
    HEAVY_SNOW_SHOWER(86, "Heavy Snow Shower"),
    MODERATE_THUNDERSTORM(95, "Moderate Thunderstorm"),
    SLIGHT_HAILED_THUNDERSTORM(96, "Slight Hailed Thunderstorm"),
    HEAVY_HAILED_THUNDERSTORM(99, "Heavy Hailed Thunderstorm"),
    UNKNOWN(-1, "Unknown");


    companion object {
         internal fun Int.condition(): WeatherConditions {
            return entries.find { it.code == this } ?: UNKNOWN
        }
    }
}
