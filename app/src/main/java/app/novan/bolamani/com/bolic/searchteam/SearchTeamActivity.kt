package app.novan.bolamani.com.bolic.searchteam

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.widget.ProgressBar
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.model.Team
import app.novan.bolamani.com.bolic.searchmatch.SearchMatchPresenter
import app.novan.bolamani.com.bolic.teamdetail.TeamDetailActivity
import app.novan.bolamani.com.bolic.util.gone
import app.novan.bolamani.com.bolic.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.support.v4.startActivity

class SearchTeamActivity:AppCompatActivity(),SearchTeamView,SearchView.OnQueryTextListener {

    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: SearchTeamPresenter
    private lateinit var tampungView: RecyclerView
    private var team:MutableList<Team> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        progressBar= progresBar
        tampungView= searchRecyclerView
        tampungView.layoutManager=LinearLayoutManager(this)
        tampungView.adapter= SearchTeamAdapter(this,team){
            val idTeam = it.idTeam.toString()
            val overview = it.strDescriptionEN.toString()
            startActivity<TeamDetailActivity>("idTeam" to idTeam,
                    "overview" to overview)
        }

        searchView.setOnQueryTextListener(this)

        val request = ApiRepository()
        val gson= Gson()
        presenter=SearchTeamPresenter(this,request,gson)

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showDataMatch(teams: List<Team>) {
        team.clear()
        team.addAll(teams)
        tampungView.adapter.notifyDataSetChanged()
    }

    override fun showNotFound() {
        team.clear()
        searchText.text="No Data Found"
        tampungView.adapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        if (query.isNotEmpty()){
            presenter.getTeamSearchList(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}