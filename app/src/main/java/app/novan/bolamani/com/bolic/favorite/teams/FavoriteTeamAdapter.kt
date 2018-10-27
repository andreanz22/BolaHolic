package app.novan.bolamani.com.bolic.favorite.teams

import android.content.ContentProvider
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.db.FavoriteTeam
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team_list.*

class FavoriteTeamAdapter(private val context: Context?,
                          private val favoriteTeam: List<FavoriteTeam>,
                          private val listemer:(FavoriteTeam)->Unit):RecyclerView.Adapter<FavoriteTeamAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: FavoriteTeamAdapter.ViewHolder, position: Int) {
        holder.bindItem(favoriteTeam[position],listemer)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamAdapter.ViewHolder {
        val holder= ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_team_list
                ,parent,false))
        return holder
    }

    override fun getItemCount(): Int = favoriteTeam.size


    class ViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView),
            LayoutContainer{
        fun bindItem(team: FavoriteTeam,listener: (FavoriteTeam) -> Unit){
            Picasso.get().load(team.teamBadge).into(teamBadge)
            teamName.text=team.teaName
            containerView.setOnClickListener { listener(team) }
        }
    }
}