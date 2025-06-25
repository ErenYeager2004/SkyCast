package com.example.myapplication.api


import com.google.gson.annotations.SerializedName

data class WeatherModel(
    @SerializedName("current")
    val current: Current?,
    @SerializedName("location")
    val location: Location?
) {
    data class Current(
        @SerializedName("cloud")
        val cloud: String?,
        @SerializedName("condition")
        val condition: Condition?,
        @SerializedName("dewpoint_c")
        val dewpointC: String?,
        @SerializedName("dewpoint_f")
        val dewpointF: String?,
        @SerializedName("feelslike_c")
        val feelslikeC: String?,
        @SerializedName("feelslike_f")
        val feelslikeF: String?,
        @SerializedName("gust_kph")
        val gustKph: String?,
        @SerializedName("gust_mph")
        val gustMph: String?,
        @SerializedName("heatindex_c")
        val heatindexC: String?,
        @SerializedName("heatindex_f")
        val heatindexF: String?,
        @SerializedName("humidity")
        val humidity: String?,
        @SerializedName("is_day")
        val isDay: String?,
        @SerializedName("last_updated")
        val lastUpdated: String?,
        @SerializedName("last_updated_epoch")
        val lastUpdatedEpoch: String?,
        @SerializedName("precip_in")
        val precipIn: String?,
        @SerializedName("precip_mm")
        val precipMm: String?,
        @SerializedName("pressure_in")
        val pressureIn: String?,
        @SerializedName("pressure_mb")
        val pressureMb: String?,
        @SerializedName("temp_c")
        val tempC: String?,
        @SerializedName("temp_f")
        val tempF: String?,
        @SerializedName("uv")
        val uv: String?,
        @SerializedName("vis_km")
        val visKm: String?,
        @SerializedName("vis_miles")
        val visMiles: String?,
        @SerializedName("wind_degree")
        val windDegree: String?,
        @SerializedName("wind_dir")
        val windDir: String?,
        @SerializedName("wind_kph")
        val windKph: String?,
        @SerializedName("wind_mph")
        val windMph: String?,
        @SerializedName("windchill_c")
        val windchillC: String?,
        @SerializedName("windchill_f")
        val windchillF: String?
    ) {
        data class Condition(
            @SerializedName("code")
            val code: String?,
            @SerializedName("icon")
            val icon: String?,
            @SerializedName("text")
            val text: String?
        )
    }

    data class Location(
        @SerializedName("country")
        val country: String?,
        @SerializedName("lat")
        val lat: String?,
        @SerializedName("localtime")
        val localtime: String?,
        @SerializedName("localtime_epoch")
        val localtimeEpoch: String?,
        @SerializedName("lon")
        val lon: String?,
        @SerializedName("name")
        val name: String?,
        @SerializedName("region")
        val region: String?,
        @SerializedName("tz_id")
        val tzId: String?
    )
}
