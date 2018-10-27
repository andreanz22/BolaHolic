package app.novan.bolamani.com.bolic.matchdetail

import app.novan.bolamani.com.bolic.model.DetailEvent
import app.novan.bolamani.com.bolic.model.Event
import app.novan.bolamani.com.bolic.model.Team

interface MatchDetailView {

    fun showMessage(message:String)
    fun showLoading()
    fun hideLoading()
    fun showMatchDetail(event:DetailEvent)
    fun insertImageHome(team: Team)
    fun insertImageAway(team: Team)
}