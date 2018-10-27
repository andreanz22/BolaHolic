package app.novan.bolamani.com.bolic.searchmatch

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ProgressBar
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.api.ApiRepository
import app.novan.bolamani.com.bolic.matchdetail.MatchDetailActivity
import app.novan.bolamani.com.bolic.model.Event
import app.novan.bolamani.com.bolic.util.gone
import app.novan.bolamani.com.bolic.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.toast

class SearchMatchAtivity: AppCompatActivity(),SearchMatchView, android.support.v7.widget.SearchView.OnQueryTextListener{

    private lateinit var progressBar: ProgressBar
    private lateinit var presenter: SearchMatchPresenter
    private lateinit var tampungView:RecyclerView
    private var event: MutableList<Event> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        progressBar=progresBar
        tampungView=searchRecyclerView
        tampungView.layoutManager= LinearLayoutManager(this)
        tampungView.adapter= SearchMatchAdapter(this,event){
            val intent= Intent(this, MatchDetailActivity::class.java)
            intent.putExtra("eventParcel",it)
            startActivity(intent)
        }

        searchView.setOnQueryTextListener(this)

        val request = ApiRepository()
        val gson= Gson()
        presenter=SearchMatchPresenter(this,request,gson)

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.gone()
    }

    override fun showNotFound() {
        event.clear()
        searchText.text="No Data Found"
        tampungView.adapter.notifyDataSetChanged()
    }

    override fun showDataMatch(match: List<Event>) {
        event.clear()
        event.addAll(match)
        tampungView.adapter.notifyDataSetChanged()
    }

    override fun onQueryTextSubmit(query: String): Boolean {
        if (query.isNotEmpty()){
            presenter.getSearchMatch(query)
        }
        return true
    }

    override fun onQueryTextChange(newText: String): Boolean {
        return false
    }

}