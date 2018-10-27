package app.novan.bolamani.com.bolic.teamdetail.teamplayer

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.model.Player
import app.novan.bolamani.com.bolic.playerdetail.PlayerDetailActivity
import app.novan.bolamani.com.bolic.util.gone
import app.novan.bolamani.com.bolic.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team_player.*
import org.jetbrains.anko.support.v4.toast

class TeamPlayerFragment:Fragment(),TeamPlayerView {

    private lateinit var progressBars: ProgressBar
    private lateinit var tampungView:RecyclerView
    private lateinit var presenter:TeamPlayerPresenter
    private var player: MutableList<Player> = mutableListOf()
    private var idTeamPlayer:String?=null


    companion object {
        fun newInstance(idTeam:String): TeamPlayerFragment {
            val teamFragment= TeamPlayerFragment()
            val bundle= Bundle()
            bundle.putString("idTeam",idTeam)

            teamFragment.arguments=bundle
            return teamFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        idTeamPlayer=arguments?.getString("idTeam")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_player,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        progressBars=progresBar
        tampungView=playerRecyclerView
        tampungView.layoutManager=LinearLayoutManager(context)
        tampungView.adapter=TeamPlayerAdapter(context,player){
            val intent= Intent(context, PlayerDetailActivity::class.java)
            intent.putExtra("playerDetail",it)
            startActivity(intent)
        }

        val request = ApiRepository()
        val gson= Gson()
        presenter= TeamPlayerPresenter(this,request,gson)
        presenter.getPlayerList(idTeamPlayer)
    }

    override fun showLoading() {
        progressBars.visible()
    }

    override fun hideLoading() {
        progressBars.gone()
    }

    override fun showDataPlayer(players: List<Player>) {
        player.clear()
        player.addAll(players)
        tampungView.adapter.notifyDataSetChanged()
    }
}