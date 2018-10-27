package app.novan.bolamani.com.bolic.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        @SerializedName("idEvent") val idEvent: String?=null,
        @SerializedName("idHomeTeam")var idHomeTeam: String? = null,
        @SerializedName("idAwayTeam")var idAwayTeam: String? = null,
        @SerializedName("dateEvent") val dateEvent: String?=null,
        @SerializedName("strTime") val timeEvent: String?=null,
        @SerializedName("strHomeTeam") val strHomeTeam: String?=null,
        @SerializedName("strAwayTeam") val strAwayTeam: String?=null,
        @SerializedName("intHomeScore") val intHomeScore: String?=null,
        @SerializedName("intAwayScore") val intAwayScore: String?=null) : Parcelable