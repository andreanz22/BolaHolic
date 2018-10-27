package app.novan.bolamani.com.bolic.match.nextmatch

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.matchdetail.MatchDetailActivity
import app.novan.bolamani.com.bolic.model.Event
import app.novan.bolamani.com.bolic.util.gone
import app.novan.bolamani.com.bolic.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_matchs_next.*
import org.jetbrains.anko.support.v4.toast

class NextMatchFragment: Fragment(),NextMatchView {

    private lateinit var spinner: Spinner
    private var event: MutableList<Event> = mutableListOf()
    private lateinit var presenter: NextMatchPresenter
    private lateinit var tampungView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var idLeague: String

    companion object {
        fun newInstance(): NextMatchFragment {
            return NextMatchFragment()
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showMatchList(data: List<Event>) {
        event.clear()
        event.addAll(data)
        tampungView.adapter.notifyDataSetChanged()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matchs_next,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = progresBar
        tampungView = matchRecyclerView
        tampungView.layoutManager=LinearLayoutManager(context)
        tampungView.adapter=NextMatchAdapter(context,event){
            val intent= Intent(context, MatchDetailActivity::class.java)
            intent.putExtra("eventParcel",it)
            startActivity(intent)
        }

        val request = ApiRepository()
        val gson= Gson()
        presenter= NextMatchPresenter(this,request,gson)

        spinner = spinnerleague
        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                idLeague = resources.getStringArray(R.array.league_id)[position].toString()
                presenter.getMatchNextList(idLeague)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

}