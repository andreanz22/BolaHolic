package app.novan.bolamani.com.bolic.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.favorite.match.FavoriteMatchFragment
import app.novan.bolamani.com.bolic.favorite.teams.FavoriteTeamFragment
import app.novan.bolamani.com.bolic.util.SupportFragmentManager
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.jetbrains.anko.support.v4.toast

class FavoriteFragment:Fragment() {

    companion object {
        fun newInstance():FavoriteFragment= FavoriteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataTab= listOf(
            SupportFragmentManager.Model(getString(R.string.favorite_match),FavoriteMatchFragment.newInstance()),
            SupportFragmentManager.Model(getString(R.string.favorite_team),FavoriteTeamFragment.newInstance())
        )

        val favoriteTabAdapter= SupportFragmentManager(dataTab,childFragmentManager)
        viewPagerMatch.adapter=favoriteTabAdapter

        tab.setupWithViewPager(viewPagerMatch)
    }
}