package app.novan.bolamani.com.bolic.match.prevmatch

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.ScrollingTabContainerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.model.Event
import app.novan.bolamani.com.bolic.util.formatDate
import app.novan.bolamani.com.bolic.util.formatTime
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match_prev.*
import kotlinx.android.synthetic.main.item_match_prev.view.*

class PrevMatchAdapter(private val context:Context?,
                       private val event:List<Event>,
                       private val listener: (Event)->Unit):RecyclerView.Adapter<PrevMatchAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
            ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_match_prev,parent,
                    false))

    override fun getItemCount(): Int= event.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(event[position],listener)
    }

    class ViewHolder (override val containerView: View):RecyclerView.ViewHolder(containerView),
            LayoutContainer{
        fun bindItem(item:Event,listener: (Event) -> Unit){
            tanggal.formatDate(item.dateEvent)
            waktu.formatTime(item.timeEvent?.subSequence(0, 5).toString())
            teamhome.text=item.strHomeTeam
            teamaway.text=item.strAwayTeam
            scoreaway.text=item.intAwayScore
            scorehome.text=item.intHomeScore
            containerView.setOnClickListener { listener(item) }
        }
    }
}