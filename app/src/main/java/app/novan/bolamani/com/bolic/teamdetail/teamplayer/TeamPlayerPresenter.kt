package app.novan.bolamani.com.bolic.teamdetail.teamplayer

import android.util.Log
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.api.TheSportDBApi
import app.novan.bolamani.com.bolic.model.PlayerResponse
import app.novan.bolamani.com.bolic.util.CoroutineContextProvider
import com.google.gson.Gson
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPlayerPresenter(private val view:TeamPlayerView,
                          private val apiRepository: ApiRepository,
                          private val gson: Gson,
                          private val context: CoroutineContextProvider = CoroutineContextProvider()) {

    fun getPlayerList(idTeam:String?){
        view.showLoading()
        async(context.main){
            val dataPlayer = bg{
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getListPlayer(idTeam)),
                        PlayerResponse::class.java)
            }
            Log.e("esek",dataPlayer.await().player.toString())
            view.showDataPlayer(dataPlayer.await().player)
            view.hideLoading()
        }
    }
}