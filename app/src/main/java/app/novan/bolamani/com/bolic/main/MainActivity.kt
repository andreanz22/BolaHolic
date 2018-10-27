package app.novan.bolamani.com.bolic.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.novan.bolamani.com.bolic.R
import kotlinx.android.synthetic.main.activity_main.*
import app.novan.bolamani.com.bolic.R.layout.activity_main
import app.novan.bolamani.com.bolic.R.id.*
import app.novan.bolamani.com.bolic.favorite.FavoriteFragment
import app.novan.bolamani.com.bolic.match.MatchFragment
import app.novan.bolamani.com.bolic.team.TeamFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main)

        navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                matchs -> {
                    loadMatchFragment(savedInstanceState)
                }
                teams -> {
                    loadTeamFragment(savedInstanceState)
                }
                favorites -> {
                    loadFavoriteFragment(savedInstanceState)
                }
            }
            true
        }
        navigation.selectedItemId= matchs
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?){
        if(savedInstanceState==null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentmain,MatchFragment()
                            ,MatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadTeamFragment(savedInstanceState: Bundle?){
        if(savedInstanceState==null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentmain,TeamFragment(),
                            TeamFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavoriteFragment(savedInstanceState: Bundle?){
        if(savedInstanceState==null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragmentmain,FavoriteFragment(),
                            FavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }
}
