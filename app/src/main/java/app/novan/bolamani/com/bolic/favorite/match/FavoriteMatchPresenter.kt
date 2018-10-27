package app.novan.bolamani.com.bolic.favorite.match

import android.database.sqlite.SQLiteConstraintException
import app.novan.bolamani.com.bolic.db.DbHelper
import app.novan.bolamani.com.bolic.db.FavoriteMatch
import app.novan.bolamani.com.bolic.util.CoroutineContextProvider
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select

class FavoriteMatchPresenter(private val view:FavoriteMatchView,
                             private val database: DbHelper,
                             private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getFavoriteMatchList(){
        try {
            database.use {
                var result = select(FavoriteMatch.TABLE_FAVORITE_MATCH)
                val favorite = result.parseList(classParser<FavoriteMatch>())
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