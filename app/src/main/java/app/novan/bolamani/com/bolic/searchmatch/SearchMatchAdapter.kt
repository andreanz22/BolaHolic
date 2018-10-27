package app.novan.bolamani.com.bolic.searchmatch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.novan.bolamani.com.bolic.R.layout.item_match_prev
import app.novan.bolamani.com.bolic.match.nextmatch.NextMatchAdapter
import app.novan.bolamani.com.bolic.model.Event
import app.novan.bolamani.com.bolic.util.formatDate
import app.novan.bolamani.com.bolic.util.formatTime
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match_prev.*

class SearchMatchAdapter(private val context: Context?,
                                 private val event: List<Event>,
                                 private val listener:(Event)->Unit): RecyclerView.Adapter<SearchMatchAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(event[position],listener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(context).inflate(item_match_prev,parent,
                false))
        return holder
    }

    override fun getItemCount(): Int = event.size

    class ViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView),
            LayoutContainer {
        fun bindItem(item:Event, listener: (Event) -> Unit){
            tanggal.formatDate(item.dateEvent)
            waktu.text = item.timeEvent?.subSequence(0, 5)
            teamhome.text=item.strHomeTeam
            teamaway.text=item.strAwayTeam
            scoreaway.text=item.intAwayScore
            scorehome.text=item.intHomeScore
            containerView.setOnClickListener { listener(item) }
        }
    }
}