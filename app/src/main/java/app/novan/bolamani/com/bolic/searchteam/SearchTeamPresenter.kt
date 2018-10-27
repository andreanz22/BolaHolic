package app.novan.bolamani.com.bolic.searchteam

import android.util.Log
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.api.TheSportDBApi
import app.novan.bolamani.com.bolic.model.TeamResponse
import app.novan.bolamani.com.bolic.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchTeamPresenter(private val view:SearchTeamView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamSearchList(dataQuery:String?){
        view.showLoading()
        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getListTeamByName(dataQuery)),
                        TeamResponse::class.java)
            }
            if(data.await().teams==null){
                view.showNotFound()
            }else{
                view.showDataMatch(data.await().teams)
            }
            view.hideLoading()
        }
    }
}