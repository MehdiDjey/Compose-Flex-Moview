package com.druide.flexmovies.domain.model

import android.os.Parcelable
import com.druide.flexmovies.common.Utils.json
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
@Parcelize
data class Credit(

    @SerialName("id") var id: Int? = null,

    @SerialName("cast") var cast: List<Cast> = listOf()

) : Parcelable {
    override fun toString(): String {
        return Json.encodeToString(this)
    }

}

@Serializable
@Parcelize
data class Cast(
    @SerialName("adult") var adult: Boolean? = null,

    @SerialName("gender") var gender: Int? = null,

    @SerialName("id") var id: Int? = null,

    @SerialName("known_for_department") var knownForDepartment: String? = null,

    @SerialName("name") var name: String? = null,

    @SerialName("original_name") var originalName: String? = null,

    @SerialName("popularity") var popularity: Double? = null,

    @SerialName("profile_path") var profilePath: String? = null,

    @SerialName("cast_id") var castId: Int? = null,

    @SerialName("character") var character: String? = null,

    @SerialName("credit_id") var creditId: String? = null,

    @SerialName("order") var order: Int? = null

) : Parcelable {

    override fun toString(): String {
        return json.encodeToString(this)
    }
}
