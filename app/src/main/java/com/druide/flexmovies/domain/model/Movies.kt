package com.druide.flexmovies.domain.model

import android.os.Parcelable
import com.druide.flexmovies.common.Utils.json
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString

@Parcelize
@Serializable
data class Movies(
    @SerialName("page") val page: Int,

    @SerialName("results") val results: List<Results>,

    @SerialName("total_pages") val totalPages: Int,

    @SerialName("total_results") val totalResults: Int

) : Parcelable {
    override fun toString(): String {
        return json.encodeToString(this)
    }
}


@Parcelize
@Serializable
data class Results(
    @SerialName("adult") val adult: Boolean? = null,

    @SerialName("backdrop_path") val backdropPath: String?,

    @SerialName("genre_ids") val genreIds: List<Int>,

    @SerialName("id") val id: Int,

    @SerialName("original_language") val originalLanguage: String,

    @SerialName("original_title") val originalTitle: String? = null,

    @SerialName("overview") val overview: String,

    @SerialName("popularity") val popularity: Double,

    @SerialName("poster_path") val posterPath: String? = null,

    @SerialName("release_date") val releaseDate: String? = null,

    @SerialName("title") val title: String? = null,

    @SerialName("video") val video: Boolean? = null,

    @SerialName("vote_average") val voteAverage: Double,

    @SerialName("vote_count") val voteCount: Int,

    @SerialName("original_name") val originalName: String? = null,

    @SerialName("name") val name: String? = null,

    ) : Parcelable {
    override fun toString(): String {
        return json.encodeToString(this)
    }

}
