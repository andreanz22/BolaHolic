package app.novan.bolamani.com.bolic.favorite.match

import app.novan.bolamani.com.bolic.db.FavoriteMatch

interface FavoriteMatchView {

    fun showMessage(message:String)
    fun showData(match:List<FavoriteMatch>)
}