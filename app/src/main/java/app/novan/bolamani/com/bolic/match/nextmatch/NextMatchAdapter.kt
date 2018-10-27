package app.novan.bolamani.com.bolic.match.nextmatch

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract
import android.provider.CalendarContract.CONTENT_URI
import android.provider.CalendarContract.EXTRA_EVENT_BEGIN_TIME
import android.provider.CalendarContract.Events.*
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.ScrollingTabContainerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.novan.bolamani.com.bolic.R.layout.item_match_next
import app.novan.bolamani.com.bolic.R.layout.item_match_prev
import app.novan.bolamani.com.bolic.model.Event
import app.novan.bolamani.com.bolic.util.formatDate
import app.novan.bolamani.com.bolic.util.formatTime
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match_next.*
import org.jetbrains.anko.startActivity

class NextMatchAdapter(private val context: Context?,
                       private val event: List<Event>,
                       private val listener:(Event)->Unit):RecyclerView.Adapter<NextMatchAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(context).inflate(item_match_next,parent,
        false))
        return holder
    }


    override fun getItemCount(): Int = event.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(event[position],listener)
//        holder.calender.setOnClickListener(View.OnClickListener {
//            listener(event[position])
//        })
    }


    class ViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView),
    LayoutContainer{
        fun bindItem(item:Event, listener: (Event) -> Unit){
            tanggal.formatDate(item.dateEvent)
            waktu.formatTime(item.timeEvent?.subSequence(0, 5).toString())
            teamhome.text=item.strHomeTeam
            teamaway.text=item.strAwayTeam
            scoreaway.text=item.intAwayScore
            scorehome.text=item.intHomeScore

            containerView.setOnClickListener { listener(item) }
        }
    }

    interface NextMatchListener {
        fun onCalendarClick(view:View,postion:Int)
    }
}