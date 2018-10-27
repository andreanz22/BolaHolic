package app.novan.bolamani.com.bolic.match.prevmatch

import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.api.TheSportDBApi
import app.novan.bolamani.com.bolic.model.EventResponse
import app.novan.bolamani.com.bolic.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PrevMatchPresenter(private val view: PrevMatchView,
                         private val apiRepository: ApiRepository,
                         private val gson: Gson,
                         private val context:CoroutineContextProvider= CoroutineContextProvider()) {

    fun getPrevMatchList(idLeague:String?){
        view.showLoading()
        async(context.main){
            val data=bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getListLastMatch(idLeague)),
                        EventResponse::class.java)
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }

}