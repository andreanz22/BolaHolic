package app.novan.bolamani.com.bolic.match.prevmatch

import app.novan.bolamani.com.bolic.model.Event

interface PrevMatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data:List<Event>)
}