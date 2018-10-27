package app.novan.bolamani.com.bolic.teamdetail.teamoverview

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.novan.bolamani.com.bolic.R
import kotlinx.android.synthetic.main.fragment_team_overview.*

class TeamOverviewFragment: Fragment() {

    private var teamOverview:String? = null

    companion object {
        fun newInstance(teamOverview:String?): TeamOverviewFragment{
            val teamFragment= TeamOverviewFragment()
            val bundle= Bundle()
            bundle.putString("overview",teamOverview)

            teamFragment.arguments=bundle
            return teamFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        teamOverview=arguments?.getString("overview")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_overview,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamOverviews.text=teamOverview

    }
}