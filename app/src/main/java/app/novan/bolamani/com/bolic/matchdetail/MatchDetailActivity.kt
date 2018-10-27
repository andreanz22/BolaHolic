package app.novan.bolamani.com.bolic.matchdetail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.R.drawable.ic_add_to_favorites
import app.novan.bolamani.com.bolic.R.drawable.ic_added_to_favorites
import app.novan.bolamani.com.bolic.R.id.add_to_favorite
import app.novan.bolamani.com.bolic.R.menu.detail_menu
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.db.DbHelper
import app.novan.bolamani.com.bolic.model.DetailEvent
import app.novan.bolamani.com.bolic.model.Event
import app.novan.bolamani.com.bolic.model.Team
import app.novan.bolamani.com.bolic.util.formatDate
import app.novan.bolamani.com.bolic.util.formatTime
import app.novan.bolamani.com.bolic.util.gone
import app.novan.bolamani.com.bolic.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.toast

class MatchDetailActivity:AppCompatActivity(),MatchDetailView {

    private var event:Event? = null
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var progressBar:ProgressBar
    private lateinit var presenter:MatchDetailPresenter
    private lateinit var match:DetailEvent
    private var menuItem:Menu?=null
    private var isFavorite:Boolean=false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        event= intent?.extras?.getParcelable("eventParcel")
        if(event==null){
            val intent = intent
            val idEvent= intent.getStringExtra("idevent")
            val idTeamHome = intent.getStringExtra("id_teamhome")
            val idTeamAway = intent.getStringExtra("id_teamaway")
            event=Event(idEvent,idTeamHome,idTeamAway)
        }
        //getParcelableExtra("eventParcel")


        swipeRefreshLayout=swipeRefreshDetail
        progressBar=progresBar

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val request= ApiRepository()
        val gson= Gson()
        val database= DbHelper(this)
        presenter= MatchDetailPresenter(this,request,gson,database)
        presenter.getTeamHomeBadge(event?.idHomeTeam)
        presenter.getTeamAwayBadge(event?.idAwayTeam)
        presenter.getDetailMatch(event?.idEvent)
        isFavorite=presenter.favoriteState(event?.idEvent!!)

        swipeRefreshLayout.onRefresh {
            presenter.getTeamHomeBadge(event?.idHomeTeam)
            presenter.getTeamAwayBadge(event?.idAwayTeam)
            presenter.getDetailMatch(event?.idEvent)
            isFavorite=presenter.favoriteState(event?.idEvent!!)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu,menu)
        menuItem=menu
        menuItem?.getItem(0)?.setVisible(false)
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home->{
                finish()
                true
            }
            add_to_favorite->{
                if (isFavorite) presenter.removeFromFavorite(event?.idEvent) else presenter.addToFavorite(match)

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else ->super.onContextItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showMatchDetail(event: DetailEvent) {
        match= DetailEvent(event.matchId,
                event.tanggal,
                event.timeEvent,
                event.hometeam,
                event.awayteam,
                event.homescore,
                event.awayscore,
                event.idteamhome,
                event.idteamaway)
        
        tanggal.formatDate(event.tanggal)
        waktu.formatTime(event.timeEvent)
        
        scorehome.text=event.homescore
        scoreaway.text=event.awayscore

        homeTeam.text=event.hometeam
        awayTeam.text=event.awayteam
        
        homeshot.text=event.homeshot
        awayshot.text=event.awayshot

        homeScorers.text= event.home_goal_detail?.replace(";","\n")
        awayScorers.text=event.away_goal_detail?.replace(";","\n")
        
        homegoal_keeper.text=event.home_goal_keeper?.replace(";","\n")
        awaygoal_keeper.text=event.away_goal_keeper?.replace(";","\n")
        
        homedefender.text=event.homedefense?.replace(";","\n")
        awaydefender.text=event.awaydefense?.replace(";","\n")
        
        homemidfield.text=event.homemidfield?.replace(";","\n")
        awaymidfield.text=event.awaymidfield?.replace(";","\n")
        
        homeforward.text=event.homeforward?.replace(";","\n")
        awayforward.text=event.awayforward?.replace(";","\n")
        
        homesubstitutes.text=event.homesubstitutes?.replace(";","\n")
        awaysubstitutes.text=event.awaysubstitutes?.replace(";","\n")

        swipeRefreshLayout.isRefreshing=false
        menuItem?.getItem(0)?.setVisible(true)
    }

    override fun insertImageHome(team: Team) {
        Picasso.get().load(team.strTeamBadge).into(imageHome)
    }

    override fun insertImageAway(team: Team) {
        Picasso.get().load(team.strTeamBadge).into(imageAway)
    }

    override fun showMessage(message: String) {
        snackbar(swipeRefreshLayout,message).show()
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    private fun setFavorite(){
        if(isFavorite){
            menuItem?.getItem(0)?.icon= ContextCompat.getDrawable(this, ic_added_to_favorites)
        }
        else
            menuItem?.getItem(0)?.icon= ContextCompat.getDrawable(this,ic_add_to_favorites)
    }

}