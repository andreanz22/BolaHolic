package app.novan.bolamani.com.bolic.team

import app.novan.bolamani.com.bolic.model.Team

interface TeamView {

    fun showLoading()
    fun hideLoading()
    fun showDataTeam(data:List<Team>)
}