package app.novan.bolamani.com.bolic.favorite.teams

import android.database.sqlite.SQLiteConstraintException
import app.novan.bolamani.com.bolic.db.DbHelper
import app.novan.bolamani.com.bolic.db.FavoriteMatch
import app.novan.bolamani.com.bolic.db.FavoriteTeam
import app.novan.bolamani.com.bolic.util.CoroutineContextProvider
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteTeamPresenter(private val view:FavoriteTeamView,
                            private val database:DbHelper,
                            private val context: CoroutineContextProvider = CoroutineContextProvider()) {


    fun getListFavoriteTeam(){
        try {
            database.use {
                var result = select(FavoriteTeam.TABLE_FAVORITE_TEAM)
                val favorite = result.parseList(classParser<FavoriteTeam>())
                if(favorite==null){
                    view.showMessage("No Data")
                }else{
                    view.showData(favorite)
                }
            }
        }catch (e: SQLiteConstraintException){
            view.showMessage(e.localizedMessage)
        }
    }
}