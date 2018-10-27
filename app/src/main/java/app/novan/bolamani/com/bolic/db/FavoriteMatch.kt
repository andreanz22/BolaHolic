package app.novan.bolamani.com.bolic.db

data class FavoriteMatch (val id:Long?,
                     val matchId:String?,
                     val matchDate:String?,
                     val matchTime:String?,
                     val homeName:String?,
                     val homeScore:String?,
                     val awayName:String?,
                     val awayScore:String?,
                     val idHomeTeam:String?,
                     val idAwayTeam:String?){

    companion object {
        const val TABLE_FAVORITE_MATCH = "TABLE_FAVORITE_MATCH"
        const val ID = "ID_"
        const val MATCH_ID = "MATCH_ID"
        const val MATCH_DATE = "MATCH_TANGGAL"
        const val MATCH_TIME = "MATCH_WAKTU"
        const val MATCH_HOME_NAME = "MATCH_HOME_NAME"
        const val MATCH_HOME_SCORE = "MATCH_HOME_SCORE"
        const val MATCH_AWAY_NAME = "MATCH_AWAY_NAME"
        const val MATCH_AWAY_SCORE = "MATCH_AWAY_SCORE"
        const val MATCH_ID_HOME_TEAM = "MATCH_ID_HOME_TEAM"
        const val MATCH_ID_AWAY_TEAM = "MATCH_ID_AWAY_TEAM"
    }

}