package app.novan.bolamani.com.bolic.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team (
        val id: Int?, //Need this for database autoincrement
        @SerializedName("idTeam") val idTeam: String?,
        @SerializedName("strTeam") val strTeam: String?,
        @SerializedName("intFormedYear") val intFormedYear: String?,
        @SerializedName("strStadium") val strStadium: String?,
        @SerializedName("strDescriptionEN") val strDescriptionEN: String?,
        @SerializedName("strTeamBadge") val strTeamBadge: String?,
        @SerializedName("strTeamFanart4") val strTeamFanart: String?
):Parcelable