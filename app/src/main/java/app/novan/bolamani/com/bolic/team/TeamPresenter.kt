package app.novan.bolamani.com.bolic.team

import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.api.TheSportDBApi
import app.novan.bolamani.com.bolic.model.TeamResponse
import app.novan.bolamani.com.bolic.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(private val view:TeamView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson,
                    private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getTeamList(idLeague:String?){
        view.showLoading()
        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getListTeam(idLeague)),
                        TeamResponse::class.java)
            }
            view.showDataTeam(data.await().teams)
            view.hideLoading()
        }
    }

}