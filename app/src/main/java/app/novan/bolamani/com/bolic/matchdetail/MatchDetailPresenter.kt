package app.novan.bolamani.com.bolic.matchdetail

import android.database.sqlite.SQLiteConstraintException
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.api.TheSportDBApi
import app.novan.bolamani.com.bolic.db.DbHelper
import app.novan.bolamani.com.bolic.db.FavoriteMatch
import app.novan.bolamani.com.bolic.db.database
import app.novan.bolamani.com.bolic.model.DetailEvent
import app.novan.bolamani.com.bolic.model.DetailEventResponse
import app.novan.bolamani.com.bolic.model.Event
import app.novan.bolamani.com.bolic.model.TeamResponse
import app.novan.bolamani.com.bolic.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class MatchDetailPresenter(private val view:MatchDetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val database:DbHelper,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamHomeBadge(idTeam:String?){
        view.showLoading()
        async(context.main){
            val dataTeam =bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailTeam(idTeam)),
                        TeamResponse::class.java)
            }
            view.apply {
                val detail= dataTeam.await().teams[0]
                view.insertImageHome(detail)
                view.hideLoading()
            }
        }
    }

    fun getTeamAwayBadge(idTeam: String?){
        view.showLoading()
        async(context.main){
            val dataTeam =bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailTeam(idTeam)),
                        TeamResponse::class.java)
            }
            view.apply {
                val detail= dataTeam.await().teams[0]
                view.insertImageAway(detail)
                view.hideLoading()
            }
        }
    }

    fun getDetailMatch(idEvent:String?){
        view.showLoading()
        async(context.main){
            val dataTeam =bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getDetailMatch(idEvent)),
                        DetailEventResponse::class.java)
            }
            view.apply {
                val detail= dataTeam.await().events[0]
                view.showMatchDetail(detail)
                view.hideLoading()
            }
        }
    }

    fun favoriteState(matchId:String):Boolean{
        var isFavorite = false
        try {
            database.use {
                val result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                        .whereArgs("(MATCH_ID = {id})",
                                "id" to matchId)
                val favorite = result.parseList(classParser<FavoriteMatch>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        }catch (e:SQLiteConstraintException){
            isFavorite=false
        }
        return isFavorite
    }

    fun addToFavorite(event: DetailEvent){
        try {
            database.use {
                insert(FavoriteMatch.TABLE_FAVORITE_MATCH,
                        FavoriteMatch.MATCH_ID to event.matchId,
                        FavoriteMatch.MATCH_DATE to event.tanggal,
                        FavoriteMatch.MATCH_TIME to event.timeEvent,
                        FavoriteMatch.MATCH_HOME_NAME to event.hometeam,
                        FavoriteMatch.MATCH_HOME_SCORE to event.homescore,
                        FavoriteMatch.MATCH_AWAY_NAME to event.awayteam,
                        FavoriteMatch.MATCH_AWAY_SCORE to event.awayscore,
                        FavoriteMatch.MATCH_ID_HOME_TEAM to event.idteamhome,
                        FavoriteMatch.MATCH_ID_AWAY_TEAM to event.idteamaway)
            }
            view.showMessage("Added To Favorites")
        }
        catch (e:SQLiteConstraintException){
            view.showMessage(e.localizedMessage)
        }
    }

    fun removeFromFavorite(idEvent: String?){
        try {
            database.use {
                delete(FavoriteMatch.TABLE_FAVORITE_MATCH, "(MATCH_ID = {id})",
                        "id" to idEvent!!)
            }
            view.showMessage("Removed From Favorites")
        }catch (e:SQLiteConstraintException){
            view.showMessage(e.localizedMessage)
        }
    }

}