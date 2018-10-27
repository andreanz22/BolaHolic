package app.novan.bolamani.com.bolic.favorite.match

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.novan.bolamani.com.bolic.R.layout.item_match_prev
import app.novan.bolamani.com.bolic.db.FavoriteMatch
import app.novan.bolamani.com.bolic.util.formatDate
import app.novan.bolamani.com.bolic.util.formatTime
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_match_prev.*

class FavoriteMatchAdapter(private val context:Context?,
                           private val favorite:List<FavoriteMatch>,
                           private val listener:(FavoriteMatch)->Unit):RecyclerView.Adapter<FavoriteMatchAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(context).inflate(item_match_prev,parent,
                false))
        return holder
    }

    override fun getItemCount(): Int =favorite.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(favorite[position],listener)
    }

    class ViewHolder(override val containerView: View ):RecyclerView.ViewHolder(containerView),
            LayoutContainer{

        fun bindItem(items:FavoriteMatch,listener: (FavoriteMatch) -> Unit){
            tanggal.formatDate(items.matchDate)
            waktu.formatTime(items.matchTime?.subSequence(0,5).toString())
            teamhome.text=items.homeName
            teamaway.text=items.awayName
            scoreaway.text=items.awayScore
            scorehome.text=items.homeScore

            containerView.setOnClickListener { listener(items) }
        }

    }
}