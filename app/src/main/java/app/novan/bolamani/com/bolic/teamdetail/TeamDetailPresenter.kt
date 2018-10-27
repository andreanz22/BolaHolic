package app.novan.bolamani.com.bolic.teamdetail

import android.database.sqlite.SQLiteConstraintException
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.api.TheSportDBApi
import app.novan.bolamani.com.bolic.db.DbHelper
import app.novan.bolamani.com.bolic.db.FavoriteMatch
import app.novan.bolamani.com.bolic.db.FavoriteTeam
import app.novan.bolamani.com.bolic.model.DetailEvent
import app.novan.bolamani.com.bolic.model.Team
import app.novan.bolamani.com.bolic.model.TeamResponse
import app.novan.bolamani.com.bolic.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class TeamDetailPresenter(private val view:TeamDetailView,
                          private val apiRepository: ApiRepository,
                          private val gson:Gson,
                          private val database: DbHelper,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamHeader(idTeam:String?){
        view.showLoading()
        async(context.main){
            val dataTeam= bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailTeam(idTeam)),
                        TeamResponse::class.java)
            }
            view.apply {
                val detail= dataTeam.await().teams[0]
                view.showTeam(detail)
            }
        }
    }

    fun favoriteState(teamId:String):Boolean{
        var isFavorite = false
        try {
            database.use {
                val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                        .whereArgs("(TEAM_ID = {id})",
                                "id" to teamId)
                val favorite = result.parseList(classParser<FavoriteTeam>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        }catch (e: SQLiteConstraintException){
            isFavorite=false
        }
        return isFavorite
    }

    fun addToFavorite(team: Team){
        try {
            database.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.TEAM_ID to team.idTeam,
                        FavoriteTeam.TEAM_NAME to team.strTeam,
                        FavoriteTeam.TEAM_FORMED_YEAR to team.intFormedYear,
                        FavoriteTeam.TEAM_STADIUM to team.strStadium,
                        FavoriteTeam.TEAM_DESCRIPTION to team.strDescriptionEN,
                        FavoriteTeam.TEAM_BADGE to team.strTeamBadge,
                        FavoriteTeam.TEAM_FANART to team.strTeamFanart)
            }
            view.showMessage("Added To Favorites")
        }
        catch (e:SQLiteConstraintException){
            view.showMessage(e.localizedMessage)
        }
    }

    fun removeFromFavorite(idTeam: String){
        try {
            database.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                        "id" to idTeam)
            }
            view.showMessage("Removed From Favorites")
        }catch (e:SQLiteConstraintException){
            view.showMessage(e.localizedMessage)
        }
    }
}