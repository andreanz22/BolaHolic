package app.novan.bolamani.com.bolic.model

import com.google.gson.annotations.SerializedName

data class DetailEvent (
    @SerializedName("idEvent") var matchId:String?=null,
    @SerializedName("dateEvent") var tanggal:String?=null,
    @SerializedName("strTime") val timeEvent: String?=null,
    @SerializedName("strHomeTeam")var hometeam:String?=null,
    @SerializedName("strAwayTeam")var awayteam:String?=null,
    @SerializedName("intHomeScore")var homescore:String?=null,
    @SerializedName("intAwayScore")var awayscore:String?=null,
    @SerializedName("idHomeTeam") var idteamhome: String? = null,
    @SerializedName("idAwayTeam") var idteamaway: String? = null,
    @SerializedName("strHomeGoalDetails")var home_goal_detail:String?=null,
    @SerializedName("intHomeShots")var homeshot:String?=null,
    @SerializedName("strHomeLineupGoalkeeper")var home_goal_keeper:String?=null,
    @SerializedName("strHomeLineupDefense")var homedefense:String?=null,
    @SerializedName("strHomeLineupMidfield")var homemidfield:String?=null,
    @SerializedName("strHomeLineupForward")var homeforward:String?=null,
    @SerializedName("strHomeLineupSubstitutes")var homesubstitutes:String?=null,
    @SerializedName("strAwayGoalDetails")var away_goal_detail:String?=null,
    @SerializedName("intAwayShots")var awayshot:String?=null,
    @SerializedName("strAwayLineupGoalkeeper")var away_goal_keeper:String?=null,
    @SerializedName("strAwayLineupDefense")var awaydefense:String?=null,
    @SerializedName("strAwayLineupMidfield") var awaymidfield:String?=null,
    @SerializedName("strAwayLineupForward") var awayforward:String?=null,
    @SerializedName("strAwayLineupSubstitutes") var awaysubstitutes:String?=null
)