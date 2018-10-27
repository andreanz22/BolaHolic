package app.novan.bolamani.com.bolic.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DbHelper (context:Context): ManagedSQLiteOpenHelper(context,"FooballFavorites.db") {

    companion object {
        private var instance:DbHelper?=null

        @Synchronized
        fun getInstance(context: Context):DbHelper{
            if(instance==null){
                instance= DbHelper(context.applicationContext)
            }
            return instance as DbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteMatch.TABLE_FAVORITE_MATCH,true,
                FavoriteMatch.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteMatch.MATCH_ID to TEXT + UNIQUE,
                FavoriteMatch.MATCH_DATE to TEXT,
                FavoriteMatch.MATCH_TIME to TEXT,
                FavoriteMatch.MATCH_HOME_NAME to TEXT,
                FavoriteMatch.MATCH_HOME_SCORE to TEXT,
                FavoriteMatch.MATCH_AWAY_NAME to TEXT,
                FavoriteMatch.MATCH_AWAY_SCORE to TEXT,
                FavoriteMatch.MATCH_ID_HOME_TEAM to TEXT,
                FavoriteMatch.MATCH_ID_AWAY_TEAM to TEXT)

        db.createTable(FavoriteTeam.TABLE_FAVORITE_TEAM,true,
                FavoriteTeam.ID to INTEGER+ PRIMARY_KEY+ AUTOINCREMENT,
                FavoriteTeam.TEAM_ID to TEXT,
                FavoriteTeam.TEAM_NAME to TEXT,
                FavoriteTeam.TEAM_FORMED_YEAR to TEXT,
                FavoriteTeam.TEAM_STADIUM to TEXT,
                FavoriteTeam.TEAM_DESCRIPTION to TEXT,
                FavoriteTeam.TEAM_BADGE to TEXT,
                FavoriteTeam.TEAM_FANART to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteTeam.TABLE_FAVORITE_TEAM, true)
        db.dropTable(FavoriteMatch.TABLE_FAVORITE_MATCH, true)
    }
}

val Context.database:DbHelper
    get() = DbHelper.getInstance(applicationContext)