package app.novan.bolamani.com.bolic.searchmatch

import app.novan.bolamani.com.bolic.model.Event

interface SearchMatchView{
    fun showLoading()
    fun hideLoading()
    fun showDataMatch(match:List<Event>)
    fun showNotFound()
}