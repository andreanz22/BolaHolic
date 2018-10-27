package app.novan.bolamani.com.bolic.favorite.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.db.DbHelper
import app.novan.bolamani.com.bolic.db.FavoriteMatch
import app.novan.bolamani.com.bolic.db.database
import app.novan.bolamani.com.bolic.matchdetail.MatchDetailActivity
import app.novan.bolamani.com.bolic.model.Event
import kotlinx.android.synthetic.main.fragment_favorites_match.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.startActivity

class FavoriteMatchFragment:Fragment(),FavoriteMatchView {

    private val favoriteMatch:MutableList<FavoriteMatch> = mutableListOf()
    private lateinit var presenter: FavoriteMatchPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var tampungView: RecyclerView
    private lateinit var event: Event

    companion object {
        fun newInstance():FavoriteMatchFragment{
            return FavoriteMatchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites_match,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshLayout=swipeRefreshFavorite
        tampungView=matchRecyclerView
        tampungView.layoutManager=LinearLayoutManager(context)
        tampungView.adapter=FavoriteMatchAdapter(context,favoriteMatch){
            val idEvent = it.matchId.toString()
            val idTeamHome = it.idHomeTeam.toString()
            val idTeamAway = it.idAwayTeam.toString()
            startActivity<MatchDetailActivity>("idevent" to idEvent,
                    "id_teamhome" to idTeamHome,
                    "id_teamaway" to idTeamAway)
        }

        presenter= FavoriteMatchPresenter(this,context!!.database)
        presenter.getFavoriteMatchList()

        swipeRefreshLayout.onRefresh {
            presenter.getFavoriteMatchList()
        }

    }

    override fun showData(match: List<FavoriteMatch>) {
        favoriteMatch.clear()
        favoriteMatch.addAll(match)
        tampungView.adapter.notifyDataSetChanged()
        swipeRefreshLayout.isRefreshing=false
    }

    override fun showMessage(message: String) {

        snackbar(swipeRefreshLayout,message)
    }
}