package app.novan.bolamani.com.bolic.teamdetail

import app.novan.bolamani.com.bolic.model.Team

interface TeamDetailView {
    fun showTeam(team: Team)
    fun showMessage(message:String)
    fun showLoading()
}