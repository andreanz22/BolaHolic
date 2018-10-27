package app.novan.bolamani.com.bolic.favorite.teams

import app.novan.bolamani.com.bolic.db.FavoriteTeam

interface FavoriteTeamView {
    fun showMessage(message:String)
    fun showData(team:List<FavoriteTeam>)
}