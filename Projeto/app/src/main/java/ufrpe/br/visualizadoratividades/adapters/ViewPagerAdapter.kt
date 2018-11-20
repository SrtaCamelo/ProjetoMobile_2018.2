package ufrpe.br.visualizadoratividades.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter



class ViewPagerAdapter(fm : FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragmentList = ArrayList<Fragment>()
    private val fragmentListTitles = ArrayList<String>()

    override fun getItem(p0: Int): Fragment {
        return fragmentList.get(p0)
    }

    override fun getCount(): Int {
        return fragmentListTitles.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentListTitles.get(position)
    }

    fun AddFragment(fragment : Fragment, title : String){
        fragmentList.add(fragment)
        fragmentListTitles.add(title)
    }
}

