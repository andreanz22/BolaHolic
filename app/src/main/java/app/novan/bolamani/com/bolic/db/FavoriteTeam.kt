package app.novan.bolamani.com.bolic.db

class FavoriteTeam (val id: Long?,
                    val teamId:String?,
                    val teaName:String?,
                    val teamFormedYear:String?,
                    val teamStadium:String?,
                    val teamDescription:String?,
                    val teamBadge:String?,
                    val teamFanart:String?){

    companion object {
        const val TABLE_FAVORITE_TEAM: String = "TABLE_FAVORITE_TEAM"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_NAME: String = "TEAM_NAME"
        const val TEAM_FORMED_YEAR: String = "TEAM_FORMED_YEAR"
        const val TEAM_STADIUM: String = "TEAM_STADIUM"
        const val TEAM_DESCRIPTION: String = "TEAM_DESCRIPTION"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_FANART: String = "TEAM_FANART"
    }
}