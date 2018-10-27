package app.novan.bolamani.com.bolic.match.nextmatch

import app.novan.bolamani.com.bolic.model.Event

interface NextMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data:List<Event>)
}