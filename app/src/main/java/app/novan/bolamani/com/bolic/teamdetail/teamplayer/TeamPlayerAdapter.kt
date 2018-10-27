package app.novan.bolamani.com.bolic.teamdetail.teamplayer

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.novan.bolamani.com.bolic.R.layout.item_player
import app.novan.bolamani.com.bolic.model.Player
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_player.*

class TeamPlayerAdapter (private val context: Context?,
                         private val player:List<Player>,
                         private val listener:(Player)->Unit):RecyclerView.Adapter<TeamPlayerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder =  ViewHolder(LayoutInflater.from(context).inflate(item_player,parent,
                false))
        return holder
    }

    override fun getItemCount(): Int = player.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(player[position],listener)
    }


    class ViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView),
            LayoutContainer{
        fun bindItem(player:Player,listener: (Player) -> Unit){
            Picasso.get().load(player.strCutout).into(playerBadge)
            playerName.text=player.strPlayer
            playerPosition.text=player.strPosition

            containerView.setOnClickListener { listener(player) }
        }
    }
}