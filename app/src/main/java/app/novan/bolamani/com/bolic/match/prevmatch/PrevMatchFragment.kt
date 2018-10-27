package app.novan.bolamani.com.bolic.match.prevmatch

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.R.array.league
import app.novan.bolamani.com.bolic.R.array.league_id
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.matchdetail.MatchDetailActivity
import app.novan.bolamani.com.bolic.model.Event
import app.novan.bolamani.com.bolic.util.gone
import app.novan.bolamani.com.bolic.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_matchs_prev.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class PrevMatchFragment : Fragment(),PrevMatchView {
    private lateinit var spinner: Spinner
    private var event: MutableList<Event> = mutableListOf()
    private lateinit var presenter: PrevMatchPresenter
    private lateinit var tampungView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var idLeague: String

    companion object {
        fun newInstance(): PrevMatchFragment {
            return PrevMatchFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matchs_prev, container, false)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressBar = progresBar
        tampungView = matchPrevRecyclerView
        tampungView.layoutManager=LinearLayoutManager(context)
        tampungView.adapter=PrevMatchAdapter(context,event){
            val intent=Intent(context,MatchDetailActivity::class.java)
            intent.putExtra("eventParcel",it)
            startActivity(intent)
        }


        spinner = spinnerleague
        val spinnerItems = resources.getStringArray(league)
        val spinnerAdapter = ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, spinnerItems)
        spinner.adapter = spinnerAdapter

        val request = ApiRepository()
        val gson= Gson()
        presenter=PrevMatchPresenter(this,request,gson)

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                idLeague = resources.getStringArray(league_id)[position].toString()
                presenter.getPrevMatchList(idLeague)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }
}