package app.novan.bolamani.com.bolic.teamdetail.teamplayer

import app.novan.bolamani.com.bolic.model.Player

interface TeamPlayerView {
    fun showLoading()
    fun hideLoading()
    fun showDataPlayer(player:List<Player>)
}