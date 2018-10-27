package app.novan.bolamani.com.bolic.team

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.novan.bolamani.com.bolic.R.layout.item_team_list
import app.novan.bolamani.com.bolic.model.Team
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_team_list.*

class TeamAdapter(private val context: Context?,
                  private val team: List<Team>,
                  private val listener: (Team)->Unit):RecyclerView.Adapter<TeamAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val holder = ViewHolder(LayoutInflater.from(context).inflate(item_team_list,parent,
                false))
        return holder
    }

    override fun getItemCount(): Int= team.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(team[position],listener)
    }


    class ViewHolder(override val containerView: View):RecyclerView.ViewHolder(containerView),
            LayoutContainer{
        fun bindItem(team: Team, listener: (Team) -> Unit){
            Picasso.get().load(team.strTeamBadge).into(teamBadge)
            teamName.text=team.strTeam
            containerView.setOnClickListener { listener(team) }
        }
    }
}