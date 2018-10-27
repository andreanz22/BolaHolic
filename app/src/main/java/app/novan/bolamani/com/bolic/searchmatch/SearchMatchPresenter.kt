package app.novan.bolamani.com.bolic.searchmatch

import android.util.Log
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.api.TheSportDBApi
import app.novan.bolamani.com.bolic.model.EventResponse
import app.novan.bolamani.com.bolic.model.EventSearchResponse
import app.novan.bolamani.com.bolic.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchPresenter(private val view:SearchMatchView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getSearchMatch(dataQuery:String?){
        view.showLoading()
        async(context.main){
            val data=bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getListEventByName(dataQuery)),
                        EventSearchResponse::class.java)
            }
            if(data.await().event==null){
                view.showNotFound()
            }else{
                view.showDataMatch(data.await().event)
            }
            view.hideLoading()
        }
    }

}