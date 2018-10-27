package app.novan.bolamani.com.bolic.searchteam

import app.novan.bolamani.com.bolic.model.Team

interface SearchTeamView {

    fun showLoading()
    fun hideLoading()
    fun showDataMatch(team:List<Team>)
    fun showNotFound()
}