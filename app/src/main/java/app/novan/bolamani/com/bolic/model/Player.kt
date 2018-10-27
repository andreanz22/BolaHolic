package app.novan.bolamani.com.bolic.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(@SerializedName("idPlayer") val idPlayer: String?,
                  @SerializedName("strPlayer") val strPlayer: String?,
                  @SerializedName("strDescriptionEN") val strDescriptionEN: String?,
                  @SerializedName("strPosition") val strPosition: String?,
                  @SerializedName("strHeight") val strHeight: String?,
                  @SerializedName("strWeight") val strWeight: String?,
                  @SerializedName("strThumb") val strThumb: String?,
                  @SerializedName("strCutout") val strCutout: String?) :Parcelable