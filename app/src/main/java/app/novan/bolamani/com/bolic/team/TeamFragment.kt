package app.novan.bolamani.com.bolic.team

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.R.array.league_id
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.matchdetail.MatchDetailActivity
import app.novan.bolamani.com.bolic.model.Team
import app.novan.bolamani.com.bolic.searchteam.SearchTeamActivity
import app.novan.bolamani.com.bolic.teamdetail.TeamDetailActivity
import app.novan.bolamani.com.bolic.util.gone
import app.novan.bolamani.com.bolic.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import java.util.*

class TeamFragment:Fragment(),TeamView {

    private lateinit var spinner: Spinner
    private lateinit var progressBar: ProgressBar
    private lateinit var tampungView: RecyclerView
    private lateinit var presenter: TeamPresenter
    private var team: MutableList<Team> = mutableListOf()
    private lateinit var idLeague: String

    companion object {
        fun newInstance():TeamFragment= TeamFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_teams,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinner=spinnerleague
        progressBar=progresBar
        tampungView=teamRecyclerView
        tampungView.layoutManager=LinearLayoutManager(context)
        tampungView.adapter=TeamAdapter(context,team){
            val idTeam = it.idTeam.toString()
            val overview = it.strDescriptionEN.toString()
            startActivity<TeamDetailActivity>("idTeam" to idTeam,
                    "overview" to overview)
        }

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        val request = ApiRepository()
        val gson= Gson()
        presenter= TeamPresenter(this,request,gson)

        spinner.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                idLeague=resources.getStringArray(league_id)[position].toString()
                presenter.getTeamList(idLeague)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showDataTeam(data:List<Team>) {
        team.clear()
        team.addAll(data)
        tampungView.adapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.searchMenu->{
                startActivity<SearchTeamActivity>()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}