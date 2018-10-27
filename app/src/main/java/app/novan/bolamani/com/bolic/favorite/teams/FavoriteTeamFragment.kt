package app.novan.bolamani.com.bolic.favorite.teams

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.db.DbHelper
import app.novan.bolamani.com.bolic.db.FavoriteTeam
import app.novan.bolamani.com.bolic.db.database
import app.novan.bolamani.com.bolic.teamdetail.TeamDetailActivity
import kotlinx.android.synthetic.main.fragment_favorites_team.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class FavoriteTeamFragment:Fragment(),FavoriteTeamView {

    private val favoriteTeam:MutableList<FavoriteTeam> = mutableListOf()
    private lateinit var presenter:FavoriteTeamPresenter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var tampungView:RecyclerView


    companion object {
        fun newInstance():FavoriteTeamFragment{
            return FavoriteTeamFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites_team,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshLayout= swipeRefreshFavorite
        tampungView= teamRecyclerView
        tampungView.layoutManager=LinearLayoutManager(context)
        tampungView.adapter=FavoriteTeamAdapter(context,favoriteTeam){
            val idTeam = it.teamId.toString()
            val overview = it.teamDescription.toString()
            startActivity<TeamDetailActivity>("idTeam" to idTeam,
                    "overview" to overview)
        }

        val database= DbHelper(this.context!!)
        presenter= FavoriteTeamPresenter(this,database)
        presenter.getListFavoriteTeam()
        swipeRefreshLayout.onRefresh {
            presenter.getListFavoriteTeam()
        }
    }

    override fun showMessage(message: String) {
        snackbar(swipeRefreshLayout,message)
    }

    override fun showData(team: List<FavoriteTeam>) {
        favoriteTeam.clear()
        favoriteTeam.addAll(team)
        tampungView.adapter.notifyDataSetChanged()
        swipeRefreshLayout.isRefreshing=false
    }
}