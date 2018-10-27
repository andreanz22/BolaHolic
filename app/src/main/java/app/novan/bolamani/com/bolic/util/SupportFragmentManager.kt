package app.novan.bolamani.com.bolic.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class SupportFragmentManager(var dataTab:List<Model>,fm: FragmentManager):FragmentPagerAdapter(fm){
    override fun getItem(position: Int): android.support.v4.app.Fragment {
        return dataTab[position].fragment
    }

    override fun getCount(): Int {
        return dataTab.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return dataTab[position].tittle
    }

    data class Model(val tittle:String, val fragment: Fragment)
}

