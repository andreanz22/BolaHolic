package app.novan.bolamani.com.bolic.match

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import app.novan.bolamani.com.bolic.R
import app.novan.bolamani.com.bolic.match.nextmatch.NextMatchFragment
import app.novan.bolamani.com.bolic.match.prevmatch.PrevMatchFragment
import app.novan.bolamani.com.bolic.searchmatch.SearchMatchAtivity
import app.novan.bolamani.com.bolic.util.SupportFragmentManager
import kotlinx.android.synthetic.main.fragment_matchs.*
import org.jetbrains.anko.support.v4.startActivity

class MatchFragment: Fragment() {

    companion object {


        fun newInstance():MatchFragment= MatchFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matchs,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataTab= listOf(
                SupportFragmentManager.Model(getString(R.string.match_prev),PrevMatchFragment.newInstance()),
                SupportFragmentManager.Model(getString(R.string.match_next),NextMatchFragment.newInstance())
        )

        val matchFragmentAdapter= SupportFragmentManager(dataTab,childFragmentManager)
        viewPagerMatch.adapter=matchFragmentAdapter

        tab.setupWithViewPager(viewPagerMatch)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu,menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.searchMenu->{
                startActivity<SearchMatchAtivity>()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }

}