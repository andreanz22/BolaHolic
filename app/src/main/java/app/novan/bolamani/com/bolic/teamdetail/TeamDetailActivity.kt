package app.novan.bolamani.com.bolic.teamdetail

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.R.string.team
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.db.DbHelper
import app.novan.bolamani.com.bolic.model.Team
import app.novan.bolamani.com.bolic.teamdetail.teamoverview.TeamOverviewFragment
import app.novan.bolamani.com.bolic.teamdetail.teamplayer.TeamPlayerFragment
import app.novan.bolamani.com.bolic.util.SupportFragmentManager
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_team_detail.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class TeamDetailActivity:AppCompatActivity(),TeamDetailView {

    private lateinit var idTeam:String
    private lateinit var teamOverview:String
    private lateinit var teamDetail:Team
    private lateinit var presenter: TeamDetailPresenter
    private var menuItem: Menu?=null
    private var isFavorite:Boolean=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_detail)

        supportActionBar?.title = ""
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent
        idTeam= intent.getStringExtra("idTeam")
        teamOverview= intent.getStringExtra("overview")

        val request= ApiRepository()
        val gson= Gson()
        val database= DbHelper(this)
        presenter=TeamDetailPresenter(this,request,gson,database)
        presenter.getTeamHeader(idTeam)
        isFavorite=presenter.favoriteState(idTeam)

        val dataTab= listOf(
                SupportFragmentManager.Model(getString(R.string.team_overview), TeamOverviewFragment.newInstance(teamOverview)),
                SupportFragmentManager.Model(getString(R.string.team_player), TeamPlayerFragment.newInstance(idTeam))
        )

        val teamFragmentAdapter= SupportFragmentManager(dataTab,supportFragmentManager)
        viewPageTeam.adapter=teamFragmentAdapter

        tab.setupWithViewPager(viewPageTeam)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu,menu)
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
            R.id.add_to_favorite ->{
                if (isFavorite) presenter.removeFromFavorite(idTeam) else presenter.addToFavorite(teamDetail)

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else ->super.onContextItemSelected(item)
        }
    }

    override fun showMessage(message: String) {
        toast(message)
    }

    override fun showTeam(team: Team) {
        teamDetail= team
        Picasso.get().load(team.strTeamBadge).into(teamBadge)
        teamName.text=team.strTeam
        yearFormed.text=team.intFormedYear
        teamStadium.text=team.strStadium
        menuItem?.getItem(0)?.setVisible(true)
    }

    override fun showLoading() {
        teamName.text="Loading"
    }

    private fun setFavorite(){
        if(isFavorite){
            menuItem?.getItem(0)?.icon= ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        }
        else
            menuItem?.getItem(0)?.icon= ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
    }
}